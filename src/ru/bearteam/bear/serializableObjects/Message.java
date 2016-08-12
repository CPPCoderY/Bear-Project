package ru.bearteam.bear.serializableObjects;

import java.io.Serializable;

public class Message implements Serializable {
    private String address;
    private String message;

    public Message(String mes, String address) {
        setAddress(address);
        setMessage(mes);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
