package State;
import System.LoginDemo.Role;
import System.LoginDemo.User;

import java.io.Serializable;

public class State implements Serializable {
    private int counter;
    private String name;
    private User userCheck;
    private User user;

    public State(int nl, String name) {
        this.counter = nl;
        this.name = name;
    }
    // Must be the initial state
    public State() {
        this.name = "Edo";
        this.counter = 0;
        this.user = new User();
        this.userCheck = new User(1, "eme", "password", Role.HEAD_PHYSICIAN, true);
    }

    /*
    ** To keep track of the initial state
    */
    public State initial() {
        return new State();
    }

    public String toString() {
        return String.format("{ name = %s, counter = %d , user = %s}", this.name, this.counter, user);
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

    public User getUserCheck() {
        return userCheck;
    }

    public void setUserCheck(User userCheck) {
        this.userCheck = userCheck;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
