package cx.corp.lacuna.core.windows.winapi;

import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;

/**
 * Contains constant values specific to the Windows API.
 */
public final class WinApiConstants {

    /**
     * The maximum amount of processes to support.
     * The Windows API makes no guarantees about this, but faux-empirical testing shows that
     * if you have 65536 processes running simultaneously, you've never even heard of Lacuna.
     */
    public static final int MAX_PROCESSES_SUPPORTED = 65536;

    /**
     * The maximum length of a Windows file path.
     */
    public static final int MAX_FILENAME_LENGTH = 260;

    public static final int MAX_DOMAIN_NAME_LENGTH = 64;

    /**
     * Size of an int on the supported platform.
     */
    public static final int SIZEOF_INT = 4;

    /**
     * Value of the NULL macro as provided by the Windows.h header.
     */
    public static final int NULL = 0;

    /**
     * When used as the {@code dwFlags} parameter of
     * {@link Kernel32#queryFullProcessImageNameW},
     * depicts that the output name should use the Win32 path format instead of the
     * native system path format.
     */
    public static final int QUERYFULLPROCESSIMAGENAME_PATHFORMAT_WIN32 = 0;

    /**
     * When used as the {@code desiredAccess} parameter of
     * {@link Advapi32#openProcessToken}, depicts that
     * the callee is requesting rights to query an access token.
     */
    public static final int OPENPROCESSTOKEN_TOKEN_QUERY = 0x0008;

    /**
     * When used as the {@code TokenInformationClass} parameter of
     * {@link Advapi32#getTokenInformation},
     * depicts that the callee is requesting a TokenOwner struct.
     */
    public static final int GETTOKENINFORMATION_TOKENUSER = 1;
}
