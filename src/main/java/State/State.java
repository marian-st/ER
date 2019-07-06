package State;
import java.io.Serializable;

public class State implements Serializable {
    private int counter;
    private String name;

    public State(int nl, String name) {
        this.counter = nl;
        this.name = name;
    }
    // Must be the initial state
    public State() {
        this.name = "Edo";
        this.counter = 0;
    }

    /*
    ** To keep track of the initial state
    */
    public State initial() {
        return new State();
    }

    public String toString() {
        return this.name + " " + this.counter;
    }

    /*
    *****************************************************
    *               Getters and setters                 *
    *****************************************************
    */

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
