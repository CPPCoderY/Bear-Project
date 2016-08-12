package ru.bearteam.bear.serializableObjects;

import java.io.Serializable;

public class LoginStatus implements Serializable {
    private boolean loginStatus;

    public LoginStatus(boolean b) {
        setLoginStatus(b);
    }

    public boolean loginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(boolean loginStatus) {
        this.loginStatus = loginStatus;
    }
}
