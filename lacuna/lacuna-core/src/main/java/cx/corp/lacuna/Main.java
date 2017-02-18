package cx.corp.lacuna;

import com.sun.jna.FunctionMapper;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;
import cx.corp.lacuna.core.LacunaBootstrap;
import cx.corp.lacuna.core.MemoryReader;
import cx.corp.lacuna.core.MemoryReaderImpl;
import cx.corp.lacuna.core.MemoryWriter;
import cx.corp.lacuna.core.MemoryWriterImpl;
import cx.corp.lacuna.core.NativeProcessCollector;
import cx.corp.lacuna.core.NativeProcessEnumerator;
import cx.corp.lacuna.core.NativeProcessEnumeratorImpl;
import cx.corp.lacuna.core.PidEnumerator;
import cx.corp.lacuna.core.RawMemoryReader;
import cx.corp.lacuna.core.RawMemoryWriter;
import cx.corp.lacuna.core.domain.NativeProcess;
import cx.corp.lacuna.core.linux.FileMemoryProvider;
import cx.corp.lacuna.core.linux.LinuxConstants;
import cx.corp.lacuna.core.linux.LinuxNativeProcessCollector;
import cx.corp.lacuna.core.linux.LinuxPidEnumerator;
import cx.corp.lacuna.core.linux.LinuxRawMemoryReader;
import cx.corp.lacuna.core.linux.LinuxRawMemoryWriter;
import cx.corp.lacuna.core.windows.ProcessDescriptionGetter;
import cx.corp.lacuna.core.windows.ProcessOpener;
import cx.corp.lacuna.core.windows.ProcessOwnerGetter;
import cx.corp.lacuna.core.windows.ProcessTokenOpener;
import cx.corp.lacuna.core.windows.TokenOwnerNameFinder;
import cx.corp.lacuna.core.windows.TokenUserFinder;
import cx.corp.lacuna.core.windows.WindowsNativeProcessCollector;
import cx.corp.lacuna.core.windows.WindowsPidEnumerator;
import cx.corp.lacuna.core.windows.WindowsProcessDescriptionGetter;
import cx.corp.lacuna.core.windows.WindowsProcessOpener;
import cx.corp.lacuna.core.windows.WindowsProcessOwnerGetter;
import cx.corp.lacuna.core.windows.WindowsRawMemoryReader;
import cx.corp.lacuna.core.windows.WindowsRawMemoryWriter;
import cx.corp.lacuna.core.windows.winapi.Advapi32;
import cx.corp.lacuna.core.windows.winapi.CamelToPascalCaseFunctionMapper;
import cx.corp.lacuna.core.windows.winapi.Kernel32;
import cx.corp.lacuna.core.windows.winapi.Psapi;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    private static LacunaBootstrap lacuna;

    public static void main(String[] args) throws IOException {
        setupPlatformSpecificStuff();

        List<NativeProcess> processes = lacuna.getNativeProcessEnumerator().getProcesses();
        for (NativeProcess proc : processes) {
            System.out.printf(
                "%-5d    %-8s    %s%n",
                proc.getPid(),
                proc.getOwner(),
                proc.getDescription());
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter target PID: ");
        final int targetPid = scanner.nextInt();

        Optional<NativeProcess> proc = processes
            .stream()
            .filter(p -> p.getPid() == targetPid)
            .findFirst();

        if (!proc.isPresent()) {
            System.out.println("Process " + targetPid + " not found!");
            return;
        }

        System.out.print("Enter target data structure offset: 0x");
        int offset = scanner.nextInt(16);

        NativeProcess targetProcess = proc.get();
        readAndDumpTestTargetData(targetProcess, offset);

        System.out.print("New value of int1: ");
        int int1 = scanner.nextInt();
        System.out.print("New value of int2: ");
        int int2 = scanner.nextInt();
        System.out.print("New value of bool3: ");
        boolean bool3 = scanner.nextBoolean();
        System.out.print("New value of bool4: ");
        boolean bool4 = scanner.nextBoolean();
        System.out.print("New value of short5: ");
        short short5 = scanner.nextShort();
        System.out.print("New value of int6: ");
        int int6 = scanner.nextInt();

        writeTestTargetData(targetProcess, offset, int1, int2, bool3, bool4, short5, int6);

        readAndDumpTestTargetData(targetProcess, offset);
    }

    private static void writeTestTargetData(NativeProcess process,
                                            int offset,
                                            int int1,
                                            int int2,
                                            boolean bool3,
                                            boolean bool4,
                                            short short5,
                                            int int6) {
        MemoryWriter mw = lacuna.getMemoryWriter();
        mw.writeInt(process, offset + 0, int1);
        mw.writeInt(process, offset + 4, int2);
        mw.writeBoolean(process, offset + 8, bool3);
        mw.writeBoolean(process, offset + 9, bool4);
        mw.writeShort(process, offset + 10, short5);
        mw.writeInt(process, offset + 12, int6);
    }

    private static void readAndDumpTestTargetData(NativeProcess process, int offset) {
        MemoryReader mr = lacuna.getMemoryReader();
        byte[] bytes = mr.read(process, offset, 0x10);
        hexDump(bytes);

        System.out.print(mr.readInt(process, offset + 0) + " ");
        System.out.print(mr.readInt(process, offset + 4) + " ");
        System.out.print(mr.readBoolean(process, offset + 8) + " ");
        System.out.print(mr.readBoolean(process, offset + 9) + " ");
        System.out.print(mr.readShort(process, offset + 10) + " ");
        int str5Ptr = mr.readInt(process, offset + 12);
        System.out.print(str5Ptr);
        System.out.print(" (" + mr.readStringUTF8(process, str5Ptr, 0x100) + ")");
        System.out.println();
    }

    private static void hexDump(byte[] bytes) {
        for (int i = 0; i < bytes.length; i++) {
            if (i != 0 && i % 16 == 0) {
                System.out.println();
            }
            System.out.printf("%02X ", bytes[i]);
        }
        System.out.println();
    }

    private static byte[] listToBytes(List<Integer> payload) {
        byte[] data = new byte[payload.size()];
        for (int i = 0; i < payload.size(); i++) {
            data[i] = payload.get(i).byteValue();
        }
        return data;
    }

    private static void setupPlatformSpecificStuff() {
        if (Platform.isWindows()) {
            lacuna = LacunaBootstrap.forWindows();
        } else {
            lacuna = LacunaBootstrap.forLinux();
        }
    }
}