package ru.testTask.client;

import ru.testTask.dataBase.Contract;
import ru.testTask.json.Converter;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * класс создающий клиента
 */

public class ClientLoader {
    private Socket socket;

    /**
     * метод создающий соединение с сервером
     */
    public void connect() {
        try {
            socket = new Socket("localhost", 8888);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * метод отправляющий запрос к серверу
     * строкой JOIN
     * применяется OutputStreamWriter
     *
     * @param contract
     */
    public void handle(Contract contract) {
        Converter converter = new Converter();
        String jsonString = converter.toJSON(contract);
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                socket.getOutputStream(),
                StandardCharsets.UTF_8
        ))) {
            bw.write(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * метод закрывает соединение
     */
    public void end() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
