package Main;

import State.State;
import State.Captor;
import State.Command;
import State.CaptorString;
import State.Store;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.Subject;

import java.util.UUID;

public class Main {
    private final UUID id;
    private int c = 0;
    public static void main(String[] args) {
        new Main();
    }

    Main() {
        this.id = UUID.randomUUID();

        Captor<State, MyString> reducer = new CaptorString()
                .with(new MyString("INC", id), s -> new State(s.numberOfLikes+1, s.name))
                .with(new MyString("DEC", id), s -> new State(s.numberOfLikes-3, s.name));
        Subject<State> subscription;
        Store sm = new Store<State, MyString>(new State(), reducer);
        subscription = sm.getStateStream();

        Disposable dis = subscription.subscribe(s -> {
             System.out.println( s.numberOfLikes);
             c++;
        });

        while(true) {
            sm.update(new MyString("INC", id));
            try {
                Thread.sleep(1000);
            } catch (Exception e) {}
            if (c%4==0) {
                sm.update(new MyString("DEC", id));
                c--;
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {}
            }
        }

    }

    public class MyString implements Command {
        private final String s;
        private final UUID u;
        MyString(String s, UUID u) {
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

}
