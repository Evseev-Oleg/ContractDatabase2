package ru.testTask.sample;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import ru.testTask.client.ClientLoader;
import ru.testTask.dataBase.Contract;

public class Controller {
    ClientLoader clientLoader;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addButton;

    @FXML
    private TextField number_contract;

    @FXML
    private TextField date_contract;

    /**
     * метод определящий введенное значение
     * и проверяющий на пустое значение
     */
    @FXML
    void initialize() {
        addButton.setOnAction(event -> {
            String newNumberContract = number_contract.getText().trim();
            String newDataContract = date_contract.getText().trim();

            if (!newNumberContract.equals("") && !newDataContract.equals("")) {
                makeClient(newNumberContract, newDataContract);
            }

        });
    }

    /**
     * метод создающий, запускающий, закрыващий клиента
     * чистит окна после ввода данных
     *
     * @param newNumberContract
     * @param newDataContract
     */
    private void makeClient(String newNumberContract, String newDataContract) {
        Contract contract = new Contract(newNumberContract, newDataContract);
        clientLoader = new ClientLoader();
        clientLoader.connect();
        clientLoader.handle(contract);
        clientLoader.end();
        number_contract.clear();
        date_contract.clear();
    }
}

