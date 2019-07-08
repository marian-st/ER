package Main;

import State.State;
import State.StateChange;
import State.MyString;
import State.CaptorString;
import State.Store;
import State.Captor;
import Stats.Statistics;

import java.util.UUID;

public class Main {
    private final UUID id;
    private int c = 0;
    public static void main(String[] args) {
        new Main();
    }

    Main() {
        this.id = UUID.randomUUID();

        Captor<MyString> captor = new CaptorString()
                      /* command             |       associated function                    | state change enum  */
                .with(new MyString("INC", id), (c, s) -> new State(s.getCounter()+1, s.getName()), StateChange.COUNTER)
                .with(new MyString("DEC", id), (c, s) -> new State(s.getCounter()-10, s.getName()), StateChange.COUNTER);

        Store store = new Store<MyString>(new State(), captor);

        Statistics.generate_values(30,4,15);

        /*
        Subject<StateEvent> subscription = store.getEventStream();

        while(true) {
            store.update(new MyString("INC", id));
            c++;
            try {
                Thread.sleep(1000);
            } catch (Exception e) {}
            if (c%5==0) {
                store.update(new MyString("DEC", id));
                c=0;
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {}
            }
        }

    }*/

    }
}
