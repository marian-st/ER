package Main;

import Component.Clicker;
import Component.Viewer;
import State.State;
import State.StateChange;
import State.MyString;

import State.CaptorString;
import State.Store;
import State.Captor;
import Stats.Statistics;
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

        Captor<MyString> reducer = new CaptorString()
                      /* command               |     associated function                   */
                .with(new MyString("INC", id), s -> new State(s.getCounter()+1, s.getName()), StateChange.COUNTER)
                .with(new MyString("DEC", id), s -> new State(s.getCounter()-3, s.getName()), StateChange.COUNTER);
        Subject<State> subscription;
        Store store = new Store<MyString>(new State(), reducer);
        subscription = store.getStateStream();


        Statistics.generate_values(30,49,50);
        Viewer viewer = new Viewer(store);
        Clicker clicker = new Clicker(store);

        /*
        Disposable dis = subscription.subscribe(s -> {
             System.out.println( s.getCounter());
             c++;
        });

        while(true) {
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
