package ru.bearteam.bear.daemons;

import ru.bearteam.bear.main.BearMain;
import ru.bearteam.bear.network.Networker;

public class MessageDaemon extends Thread {
    private Networker net;

    public MessageDaemon(Networker net) {
        System.out.println("Creating Daemon 1");
        this.net = net;
        System.out.println("Creating Daemon 2");
        System.out.println("Creating daemon complete");
    }

    @Override
    public void run() {
        System.out.println("Thread started");
        while (true) {
            int type = net.readInt();
            System.out.println(type);
            switch (type) {
                //On get simple text message
                case 1:
                    String sender = net.readString();
                    String message = net.readString();
                    System.out.println(message + "by " + sender);
                    break;
                //On got login request
                case 2:
                    net.login(BearMain.name);
                default:
                    System.out.println("IO error, unknown decoder");
                    System.exit(1);
            }
        }
    }
}
