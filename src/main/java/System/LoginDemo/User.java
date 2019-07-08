package System.LoginDemo;

public class User {
    private int userID;
    private String name;
    private String password;
    private Role role;
    private boolean valid;

    public User() {

    }

    public User(int id, String username, String password, Role role, boolean valid) {
        this.userID = id;
        this.name = username;
        this.password = password;
        this.role = role;
        this.valid = valid;
    }

    public User(String username, String password) {
        this(1, username, password, null, false);
    }

    public String toString() {
        return "Username: " + this.name + "\nPassword: " + this.password + "\nRole: " + this.role;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof User))
            return false;
        return this.name.equals(((User) obj).name) && this.password.equals(((User) obj).password);
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

}
