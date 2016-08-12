package ru.bearteam.bear.serializableObjects;

import java.io.Serializable;

public class LoginRequest implements Serializable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
