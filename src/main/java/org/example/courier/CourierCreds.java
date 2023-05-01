package org.example.courier;

public class CourierCreds {

    private String login;
    private String password;

    public CourierCreds(Courier courier) {
        this.login = courier.getLogin();
        this.password = courier.getPassword();
    }

    public CourierCreds() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
