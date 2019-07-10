package State;

import java.util.UUID;

public class StringCommand implements Command {
    private final String s;
    private final UUID u;
    private final Object obj;
    public StringCommand(String s, UUID u, Object obj) {
        this.s = s;
        this.u = u;
        this.obj = obj;
    }

    public StringCommand(String s, UUID u) {
        this(s,u, null);
    }
    @Override
    public String name() {
        return s;
    }

    @Override
    public UUID issuer() {
            return u;
        }

    @Override
    public Object getArg() {
        return obj;
    }

    public String toString() {
        return this.name();
    }
}