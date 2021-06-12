package ru.testTask.server;

import ru.testTask.dataBase.Contract;
import ru.testTask.dataBase.DatabaseHandler;
import ru.testTask.json.Converter;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * класс создающий отдельный поток по обращению
 * клиента к серверу
 */
public class ClientHandler extends Thread {
    private final Socket client;

    public ClientHandler(Socket client) {
        this.client = client;
        start();
    }

    /**
     * переопределенный метод run()
     * метод принимает JSON, преобразует его в обьект
     * и передает его в метод работающий с БД
     */
    @Override
    public void run() {
        while (true) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream(),
                        StandardCharsets.UTF_8));
                String text = reader.readLine();
                StringBuilder stringBuilder = new StringBuilder(text);

                while ((text = reader.readLine()) != null) {
                    stringBuilder.append(text);
                }
                DatabaseHandler databaseHandler = new DatabaseHandler();
                String var = stringBuilder.toString();
                Contract contract;
                Converter converter = new Converter();
                contract = converter.toJavaObject(var);
                databaseHandler.chekContract(contract);
                reader.close();
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }


            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
