package System.LoginDemo;

public class User {
    private String userID;
    private double ID;
    private Role role;

    public User(String username, String password) {
        this.userID = username;
        this.ID = Math.random(); //substitute with db entry key
        this.role = Sistema.checkUser(new Tuple<>(username, password));
    }

    public String toString() {
        return "I'm " + this.userID + "\nID: " + this.ID + "\nRole: " + this.role;
    }
}
