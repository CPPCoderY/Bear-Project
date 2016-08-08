package ru.bearteam.bear.network;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Networker {
    private Socket server;
    private DataInputStream in;
    private DataOutputStream out;

    public Networker(String host, int port, String name) {
        try {
            this.server = new Socket(host, port);
            this.setOut(server.getOutputStream());
            this.setIn(server.getInputStream());
        } catch (UnknownHostException e) {
            System.out.println("Can not connect to host, aborting!!!");
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message, String adress) {
        try {
            //1 is code of simple string message
            getOut().write(1);
            getOut().write(message.getBytes().length);
            getOut().write(message.getBytes());
            getOut().write(adress.getBytes().length);
            getOut().write(adress.getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void login(String name) {
        try {
            getOut().write(name.length());
            getOut().write(name.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public DataOutputStream getOut() {
        return out;
    }

    public void setOut(OutputStream out) {
        this.out = new DataOutputStream(out);
    }

    public DataInputStream getIn() {
        return in;
    }

    public void setIn(InputStream in) {
        this.in = new DataInputStream(in);
    }

    public String readString() {
        int length = readInt();
        byte[] data = new byte[length];
        try {
            getIn().read(data, 0, length);
            return new String(data);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int readInt() {
        try {
            int value = getIn().readInt();
            return value;
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }
}


