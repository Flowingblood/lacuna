package cx.corp.lacuna;

import com.sun.jna.FunctionMapper;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;
import cx.corp.lacuna.core.MemoryReader;
import cx.corp.lacuna.core.domain.NativeProcess;
import cx.corp.lacuna.core.serialization.Boolean8Serializer;
import cx.corp.lacuna.core.serialization.Int32Serializer;
import cx.corp.lacuna.core.serialization.TypeSerializer;
import cx.corp.lacuna.core.serialization.TypeSerializers;
import cx.corp.lacuna.core.serialization.TypeSerializersImpl;
import cx.corp.lacuna.core.NativeProcessCollector;
import cx.corp.lacuna.core.NativeProcessEnumerator;
import cx.corp.lacuna.core.PidEnumerator;
import cx.corp.lacuna.core.linux.FileMemoryProvider;
import cx.corp.lacuna.core.linux.LinuxConstants;
import cx.corp.lacuna.core.linux.LinuxMemoryReader;
import cx.corp.lacuna.core.linux.LinuxNativeProcessEnumerator;
import cx.corp.lacuna.core.windows.ProcessDescriptionGetter;
import cx.corp.lacuna.core.windows.ProcessOpener;
import cx.corp.lacuna.core.windows.ProcessOwnerGetter;
import cx.corp.lacuna.core.windows.WindowsNativeProcessCollector;
import cx.corp.lacuna.core.windows.winapi.WinApiProcessDescriptionGetter;
import cx.corp.lacuna.core.windows.winapi.WinApiProcessOpener;
import cx.corp.lacuna.core.windows.winapi.WinApiProcessOwnerGetter;
import cx.corp.lacuna.core.windows.WindowsMemoryReader;
import cx.corp.lacuna.core.windows.WindowsNativeProcessEnumerator;
import cx.corp.lacuna.core.windows.winapi.WinApiPidEnumerator;
import cx.corp.lacuna.core.windows.winapi.Advapi32;
import cx.corp.lacuna.core.windows.winapi.CamelToPascalCaseFunctionMapper;
import cx.corp.lacuna.core.windows.winapi.Kernel32;
import cx.corp.lacuna.core.windows.winapi.Psapi;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    private static NativeProcessEnumerator processEnumerator;
    private static MemoryReader memoryReader;
    private static TypeSerializers typeSerializers;

    public static void main(String[] args) throws IOException {
        setupPlatformSpecificStuff();
        setupSerializers();

        List<NativeProcess> processes = processEnumerator.getProcesses();
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
            System.out.println("Process " + targetPid +  " not found!");
            return;
        }

        System.out.print("Enter target offset: 0x");
        int offset = scanner.nextInt(16);
        System.out.print("How many bytes to read? 0x");
        int count = scanner.nextInt(16);

        byte[] bytes = memoryReader.read(
                proc.get(),
                offset,
                count);

        for (int i = 0; i < bytes.length; i++) {
            if (i != 0 && i % 16 == 0) {
                System.out.println();
            }
            System.out.printf("%02X ", bytes[i]);
        }
        System.out.println();

        if (bytes.length >= 0x10) {
            TypeSerializer<Integer> intSerializer = typeSerializers.find(Integer.class);
            byte[] firstInt = Arrays.copyOfRange(bytes, 0, 4);
            byte[] secondInt = Arrays.copyOfRange(bytes, 4, 8);
            System.out.println("firstInt = " + intSerializer.deserialize(firstInt));
            System.out.println("secondInt = " + intSerializer.deserialize(secondInt));


            TypeSerializer<Boolean> boolSerializer = typeSerializers.find(Boolean.class);
            byte[] firstBool = Arrays.copyOfRange(bytes, 8, 9);
            byte[] secondBool = Arrays.copyOfRange(bytes, 9, 10);

            System.out.println("firstBool = " + boolSerializer.deserialize(firstBool));
            System.out.println("secondBool = " + boolSerializer.deserialize(secondBool));
        }
    }

    private static void setupSerializers() {
        typeSerializers = new TypeSerializersImpl();
        typeSerializers.register(Boolean.class, new Boolean8Serializer());
        typeSerializers.register(Integer.class, new Int32Serializer());
    }

    private static void setupPlatformSpecificStuff() {
        if (Platform.isWindows()) {
            setupForWindows();
        } else {
            setupForLinux();
        }
    }

    private static void setupForWindows() {
        Map<String, Object> options = new HashMap<>();
        // Use a mapper so that we can use Java-style function names in the interfaces
        FunctionMapper nameMapper = new CamelToPascalCaseFunctionMapper();
        options.put(Library.OPTION_FUNCTION_MAPPER, nameMapper);

        Kernel32 kernel = Native.loadLibrary("Kernel32", Kernel32.class, options);
        Psapi psapi = Native.loadLibrary("Psapi", Psapi.class, options);
        Advapi32 advapi = Native.loadLibrary("Advapi32", Advapi32.class, options);

        PidEnumerator enumerator = new WinApiPidEnumerator(psapi);
        ProcessOpener procOpener = new WinApiProcessOpener(kernel);
        ProcessOwnerGetter ownerGetter = new WinApiProcessOwnerGetter(advapi);
        ProcessDescriptionGetter descriptionGetter = new WinApiProcessDescriptionGetter(kernel);
        NativeProcessCollector collector =
            new WindowsNativeProcessCollector(procOpener, ownerGetter, descriptionGetter);
        processEnumerator = new WindowsNativeProcessEnumerator(enumerator, collector);
        memoryReader = new WindowsMemoryReader(procOpener, kernel);
    }

    private static void setupForLinux() {
        Path procRoot = LinuxConstants.DEFAULT_PROC_ROOT;
        processEnumerator = new LinuxNativeProcessEnumerator(procRoot);
        FileMemoryProvider memProvider = new FileMemoryProvider(Paths.get("/proc"), "mem");
        memoryReader = new LinuxMemoryReader(memProvider);
    }
}
