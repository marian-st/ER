package State.Entities;


import System.LoginDemo.Role;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class RoleEntry {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "userid")
    private String name;
    private String password;
    private Role role;

    public RoleEntry() {
    }
    public RoleEntry(String name, String password, Role role) {
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return this.name + ": " + this.password + " (" + this.role +")";
    }
}
