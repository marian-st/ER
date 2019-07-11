package State;
import State.Entities.Patient;
import State.Entities.Role;
import State.Entities.User;

import java.io.Serializable;
import java.util.*;

public class State implements Serializable {
    private User userCheck;
    private User user;
    private List<Patient> patients = new ArrayList<>();

    // Must be the initial state
    public State() {

        this.user = new User();
        this.userCheck = new User("eme", "password", Role.HEAD_PHYSICIAN, true);
        this.patients.add(new Patient("Carlo", "Combi", "CMBCBMWHATEVER291Z", "Verona",
                new GregorianCalendar(1965, Calendar.APRIL, 11).getTime()));
        this.patients.add(new Patient("Barbara", "Oliboni", "BRBLBIDONTKNOW329I", "Verona",
                new GregorianCalendar(1973, Calendar.OCTOBER, 8).getTime()));
    }

    /*
    ** To keep track of the initial state
    */
    public State initial() {
        return new State();
    }

    public String toString() {
        return String.format("{user = %s, patients = %s}", this.user, this.patients);
    }

    /*
    *****************************************************
    *               Getters and setters                 *
    *****************************************************
    */

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

    public List<Patient> getPatients() {
        return this.patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

}
