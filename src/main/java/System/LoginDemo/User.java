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
        return "{Username: " + this.name + ", password: " + this.password + ", role: " + this.role+"}";
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

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
