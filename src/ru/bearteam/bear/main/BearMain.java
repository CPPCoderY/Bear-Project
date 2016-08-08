package ru.bearteam.bear.main;

import ru.bearteam.bear.daemons.MessageDaemon;
import ru.bearteam.bear.network.Networker;

import java.util.Scanner;

public class BearMain {
    public static Networker networker;
    public static String name;

    public static void main(String[] args) {
        name = args[0];
        System.out.println("1");
        networker = new Networker("alex2772.ru", 5123, args[0]);
        System.out.println("2");
        MessageDaemon messageDaemon = new MessageDaemon(networker);
        messageDaemon.setDaemon(true);
        messageDaemon.start();
        System.out.println("3");
        Scanner console = new Scanner(System.in);
        while (true) {
            String message = console.nextLine();
            System.out.println("Кому вы хотите отправить сообщение: " + message + " ?");
            String addr = console.nextLine();
            networker.sendMessage(message, addr);

        }
    }
}
