package models;


public class User {
    private int id;
    private String username;
    private String fullName;
    private String password;
    private UserRole userRole;

    public User() {
    }

    public User(String username, String fullName, String password, UserRole userRole) {
        this.username = username;
        this.fullName = fullName;
        this.password = password;
        this.userRole = userRole;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public User setUserRole(UserRole userRole) {
        this.userRole = userRole;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public User setFullName(String fullName) {
        this.fullName = fullName;
        return this;

    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }
}
