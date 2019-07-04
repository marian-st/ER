package Main;

import Component.Clicker;
import Component.Viewer;
import State.State;
import State.MyString;

import State.CaptorString;
import State.Store;
import State.Captor;
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
        Store store = new Store<State, MyString>(new State(), reducer);
        subscription = store.getStateStream();

        Disposable dis = subscription.subscribe(s -> {
             System.out.println( s.numberOfLikes);
             c++;
        });
        Viewer viewer = new Viewer(store);
        Clicker clicker = new Clicker(store);

        /*while(true) {
            store.update(new MyString("INC", id));
            try {
                Thread.sleep(1000);
            } catch (Exception e) {}
            if (c%4==0) {
                store.update(new MyString("DEC", id));
                c--;
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {}
            }
        }*/

    }

}
