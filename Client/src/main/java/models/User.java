package models;

public class User {

    private String username;
    private String password;
    private String area;

    public User() {
        this.username = null;
        this.password = null;
        this.area = null;
    }

    public User(String username, String password, String area) {
        this.username = username;
        this.password = password;
        this.area = area;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getArea() {
        return area;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setArea(String area) {
        this.area = area;
    }


}
