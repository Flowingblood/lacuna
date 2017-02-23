package cx.corp.lacuna.core.windows;

import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.ptr.IntByReference;
import cx.corp.lacuna.core.MemoryAccessException;
import cx.corp.lacuna.core.RawMemoryReader;
import cx.corp.lacuna.core.MemoryReaderImpl;
import cx.corp.lacuna.core.domain.NativeProcess;
import cx.corp.lacuna.core.windows.winapi.ProcessAccessFlags;
import cx.corp.lacuna.core.windows.winapi.ReadProcessMemory;
import cx.corp.lacuna.core.windows.winapi.SystemErrorCode;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class WindowsRawMemoryReader implements RawMemoryReader {

    private static final int FLAGS_READMEMORY =
        ProcessAccessFlags.QUERY_INFORMATION | ProcessAccessFlags.VIRTUAL_MEMORY_READ;

    private final ProcessOpener processOpener;
    private final ReadProcessMemory memoryReader;

    /**
     * Instantiates a new {@link WindowsRawMemoryReader} instance using the specified
     * process opener and Kernel32 WindowsAPI proxy.
     *
     * @param processOpener The process opener used to open a handle to the target process.
     * @param memoryReader The WindowsAPI proxy used for process memory reading.
     * @cx.useswinapi
     */
    public WindowsRawMemoryReader(ProcessOpener processOpener, ReadProcessMemory memoryReader) {
        if (processOpener == null || memoryReader == null) {
            throw new IllegalArgumentException("Parameters cannot be null!");
        }
        this.processOpener = processOpener;
        this.memoryReader = memoryReader;
    }

    /**
     * {@inheritDoc}
     *
     * <p>This implementation is largely implemented on top of the Windows
     * API. First, a handle is opened to the target process, then the
     * memory is read with the Kernel32 {@code ReadProcessMemory} function.
     * @param process The native process whose memory to read.
     * @param offset The memory address offset to read from. This value is
     *               interpreted as an unsigned value, meaning that negative
     *               values are allowed.
     * @param bytesToRead The amount of bytes to read.
     * @return
     */
    @Override
    public ByteBuffer read(NativeProcess process, int offset, int bytesToRead) {
        //throws ProcessOpenException, MemoryAccessException {
        if (process == null) {
            throw new NullPointerException("Process cannot be null!");
        }
        if (bytesToRead < 1) {
            throw new IllegalArgumentException("Cannot read fewer than 1 byte!");
        }

        // open might throw ProcessOpenException
        try (ProcessHandle handle = processOpener.open(process.getPid(), FLAGS_READMEMORY)) {
            Memory buffer = new Memory(bytesToRead);
            IntByReference bytesRead = new IntByReference(0);
            boolean success = memoryReader.readProcessMemory(
                handle.getNativeHandle(),
                offset,
                buffer,
                bytesToRead,
                bytesRead);

            if (!success) {
                int errorCode = Native.getLastError();
                throw createReadExceptionFromErrorCode(errorCode);
            }

            ByteBuffer buf;
            try {
                buf = buffer.getByteBuffer(0, bytesToRead);
            } catch (Error err) {
                // e.g. "Invalid memory access" if reading outside memory bounds.
                // the Memory class does bounds checking but you can never be sure since
                // it ultimately calls the natively implemented Native.read method.
                // This is a bit of a doomsday defensive programming scenario so not sure
                // how to unit test this one.
                throw new MemoryAccessException(
                    "An error occurred while reading memory. Use getCause() to get the cause.",
                    err);
            }

            buf.order(ByteOrder.LITTLE_ENDIAN);
            return buf;
        }
    }

    private MemoryAccessException createReadExceptionFromErrorCode(int nativeError) {
        SystemErrorCode error = SystemErrorCode.fromId(nativeError);
        String message;
        if (error == null) {
            message = "System error " + Native.getLastError() + " occurred.";
        } else {
            message = error.toString();
        }
        return new MemoryAccessException(message);
    }
}
