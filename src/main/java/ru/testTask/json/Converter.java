package ru.testTask.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.testTask.dataBase.Contract;

/**
 * класс реализующий парсинг обьекта Contract
 * в JSON и обратно в обьект
 */
public class Converter {
    ObjectMapper mapper = new ObjectMapper();

    private Contract contract;

    /**
     * метод парсит в JSON
     *
     * @param contract
     * @return
     */
    public String toJSON(Contract contract) {
        String jsonString = null;
        try {
            jsonString = mapper.writeValueAsString(contract);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

    /**
     * метод создает обьект из JSON
     *
     * @param str
     * @return
     * @throws JsonProcessingException
     */
    public Contract toJavaObject(String str) throws JsonProcessingException {
        contract = mapper.readValue(str, Contract.class);
        return contract;
    }
}
