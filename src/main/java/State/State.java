package State;
import java.io.Serializable;

public class State implements Serializable {
    public int numberOfLikes;
    public String name;

    public State(int nl, String name) {
        this.numberOfLikes = nl;
        this.name = name;
    }
    public State() {
        this.name = "Edo";
        this.numberOfLikes = 0;
    }

    /**
     * @todo
     */
    public State initial() {
        this.numberOfLikes = 0; this.name = "Edo"; return this;
    }
    public String toString() {
        return this.name + " " + this.numberOfLikes;
    }
}
