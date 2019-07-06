package System.LoginDemo;

import java.util.ArrayList;

public class Sistema {
    private static Sistema s = new Sistema();
    private static ArrayList<Triple<String, String, Role>> users;

    public static Sistema getInstance() {
        return s;
    }

    public static void systemSetUp() {
        System.out.println("System Setup initialized");
        //other setup stuff
    }

    //will have all the information into the state and iterate over them
    //NB. This is just demo environment
    private Sistema() {
        users = new ArrayList<>();
        Triple<String, String, Role> t = new Triple<>("med", "pass", Role.DOCTOR);
        users.add(t);
    }

    //it will check on the information loaded in state
    public static Role checkUser(Tuple<String, String> t) {
        for(Triple<String, String, Role> x : users)
            if(x.fst().equals(t.fst()) && x.snd().equals(t.snd()))
                return x.trd();
        throw new IllegalArgumentException();
    }
}
