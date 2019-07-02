package State;

import java.util.UUID;

public interface Command {
    String name();
    UUID issuer();
}

