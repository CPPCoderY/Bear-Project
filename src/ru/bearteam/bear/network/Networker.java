package ru.bearteam.bear.network;

import ru.bearteam.bear.serializableObjects.LoginRequest;
import ru.bearteam.bear.serializableObjects.LoginStatus;
import ru.bearteam.bear.serializableObjects.Message;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Logger;

public class Networker {
    private Socket server;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private Logger log;
    private String nick;

    public Networker(String host, int port, String name) {
        try {
            log = Logger.getLogger("ru.bearteam.bear.network.Networker");
            nick = name;
            this.server = new Socket(host, port);
            this.setOut(server.getOutputStream());
            this.setIn(server.getInputStream());
            log.info("Connection successfully!");
            login(nick);
        } catch (UnknownHostException e) {
            System.exit(1);
            log.warning("Can not connect to remote server... Exit");
        } catch (IOException e) {
            e.printStackTrace();
            log.severe("Unknown IO error");
        }
    }

    public void sendMessage(String message, String adress) {
        writeObject(new Message(message, adress));
    }

    public void login(String name) {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setName(name);
        writeObject(loginRequest);
        log.info("Trying to login");
        Object o = this.readObject();
        if (o instanceof LoginStatus) {
            System.out.println(((LoginStatus) o).loginStatus());
        }
    }

    public Object readObject() {
        try {
            return getIn().readObject();
        } catch (IOException e) {
            e.printStackTrace();
            log.severe("Unknown error while reading object from remote server");
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            log.severe("Can not type object from server");
            return null;
        }
    }

    public void writeObject(Object o) {
        try {
            getOut().writeObject(o);
            getOut().flush();
        } catch (IOException e) {
            e.printStackTrace();
            log.severe("Unknown error while writing object to remote server...");
        }
    }

    public ObjectOutputStream getOut() {
        return out;
    }

    public void setOut(OutputStream out) throws IOException {
        this.out = new ObjectOutputStream(out);
    }

    public ObjectInputStream getIn() {
        return in;
    }

    public void setIn(InputStream in) throws IOException {
        this.in = new ObjectInputStream(in);
    }
}


