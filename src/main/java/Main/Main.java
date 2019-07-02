package Main;

import State.State;
import State.Runner;
import State.Command;
import State.ReducerString;
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

        Runner<State, MyString> reducer = new ReducerString().with(new MyString("DEC", id), s -> new State(s.numberOfLikes+1, s.name));
        Subject<State> subscription;
        Store sm = new Store<State, MyString>(new State(), reducer);
        subscription = sm.getState();

        Disposable dis = subscription.subscribe(s -> {
             System.out.println( s.numberOfLikes);
             c++;
        });

        while(true) {
            sm.update(new MyString("DEC", id));
            try {
                Thread.sleep(1000);
            } catch (Exception e) {}
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
