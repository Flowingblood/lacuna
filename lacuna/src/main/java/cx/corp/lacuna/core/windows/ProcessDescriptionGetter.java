package cx.corp.lacuna.core.windows;

import java.util.Optional;

@FunctionalInterface
public interface ProcessDescriptionGetter {
    Optional<String> get(ProcessHandle processHandle);
}
