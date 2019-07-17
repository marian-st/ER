package Main;



import State.State;

import State.StringCommand;

import State.ReducerString;

import State.Store;

import State.MiddlewareString;

import State.Reducer;



import java.util.UUID;



public class Main {

    private final UUID id;

    private int c = 0;

    public static void main(String[] args) {

        new Main();

    }



    Main() {

        this.id = UUID.randomUUID();



        Reducer<StringCommand> reducer = new ReducerString();

        /* command             |       associated function                    | state change enum  */



        Store store = new Store<StringCommand>(new State(), reducer, new MiddlewareString());



        /*

        Subject<StateEvent> subscription = store.getEventStream();



        while(true) {

            store.update(new StringCommand("INC", id));

            c++;

            try {

                Thread.sleep(1000);

            } catch (Exception e) {}

            if (c%5==0) {

                store.update(new StringCommand("DEC", id));

                c=0;

                try {

                    Thread.sleep(1000);

                } catch (Exception e) {}

            }

        }



    }*/



    }

}