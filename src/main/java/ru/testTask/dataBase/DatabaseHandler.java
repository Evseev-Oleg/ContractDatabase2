package ru.testTask.dataBase;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

/**
 * класс реализующий работу с БД
 * технологией Hibernate
 */
public class DatabaseHandler {
    Session session;
    SessionFactory factory;

    /**
     * метод создает соединение с БД
     *
     * @return
     */
    private Session getDbConnection() {
        factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Contract.class)
                .buildSessionFactory();
        session = factory.getCurrentSession();
        return session;
    }

    /**
     * метод проверяет существует ли уже в БД такой номер договора
     *
     * @param contract
     */
    public void chekContract(Contract contract) {
        try {
            getDbConnection().beginTransaction();
            List<Contract> cont = session.createQuery("from Contract " + "where numberContract = "
                    + contract.getNumberContract()).getResultList();
            if (cont.size() == 0) {
                addContract(contract);
            } else {
                changeContract(cont, contract);
            }
            session.getTransaction().commit();
        } finally {
            factory.close();
        }
    }

    /**
     * метод изменяет столбец date_change(последнее изменение)
     * в таблице, если договор существует
     *
     * @param con
     * @param contract
     */
    private void changeContract(List<Contract> con, Contract contract) {
        System.out.println(con.get(0));
        System.out.println(contract);
        session.createQuery("update Contract set dateChange = '" + contract.getDateContract() +
                "' where numberContract = " + contract.getNumberContract()).executeUpdate();
    }

    /**
     * метод добавляет новый договор со всеми полями,
     * если такого договора не существует
     *
     * @param contract
     */
    private void addContract(Contract contract) {
        Contract newContract = new Contract(contract.getNumberContract()
                , contract.getDateContract(), contract.getDateContract());
        session.save(newContract);
    }
}
