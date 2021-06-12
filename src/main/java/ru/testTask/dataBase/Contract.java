package ru.testTask.dataBase;

import javax.persistence.*;

/**
 * класс описывающий сущность "Contract"-договор
 * contract2 - это назваеие таблицы
 */
@Entity
@Table(name = "contract2")
public class Contract {
    /**
     * переменная ключ в таблице contract2
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "number_contract")
    private String numberContract;

    @Column(name = "date_contract")
    private String dateContract;

    @Column(name = "date_change")
    private String dateChange;

    public Contract() {
    }

    public Contract(String numberContract, String dateContract) {
        this.numberContract = numberContract;
        this.dateContract = dateContract;
    }

    public Contract(String numberContract, String dateContract, String dateChange) {
        this.numberContract = numberContract;
        this.dateContract = dateContract;
        this.dateChange = dateChange;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumberContract() {
        return numberContract;
    }

    public void setNumberContract(String numberContract) {
        this.numberContract = numberContract;
    }

    public String getDateContract() {
        return dateContract;
    }

    public void setDateContract(String dateContract) {
        this.dateContract = dateContract;
    }

    public String getDateChange() {
        return dateChange;
    }

    public void setDateChange(String dateChange) {
        this.dateChange = dateChange;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "id=" + id +
                ", numberContract='" + numberContract + '\'' +
                ", dateContract='" + dateContract + '\'' +
                ", dateChange='" + dateChange + '\'' +
                '}';
    }
}
