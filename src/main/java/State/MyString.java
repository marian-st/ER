package State;

import State.Command;

import java.util.UUID;

public class MyString implements Command {
        private final String s;
        private final UUID u;
        private final Object obj;
        public MyString(String s, UUID u, Object obj) {
            this.s = s;
            this.u = u;
            this.obj = obj;
        }

        public MyString(String s, UUID u) {
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
        return null;
    }
}