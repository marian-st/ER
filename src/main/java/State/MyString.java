package State;

import State.Command;

import java.util.UUID;

public class MyString implements Command {
        private final String s;
        private final UUID u;
        public MyString(String s, UUID u) {
            this.s = s;
            this.u = u;
        }

        @Override
        public String name() {
            return s;
        }

        @Override
        public UUID issuer() {
            return u;
        }
    }