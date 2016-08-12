package ru.bearteam.bear.daemons;

import ru.bearteam.bear.network.Networker;
import ru.bearteam.bear.serializableObjects.Message;

public class MessageDaemon extends Thread {
    private Networker net;

    public MessageDaemon(Networker net) {
        this.net = net;
    }

    @Override
    public void run() {
        while (true) {
            Object o = net.readObject();
            if (o instanceof Message) {
                System.out.println("New message from " + ((Message) o).getAddress() + ((Message) o).getMessage());
            }
        }
    }
}
