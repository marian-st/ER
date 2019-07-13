package State;

import State.Entities.Patient;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class DatabaseService {
    private static DatabaseService instance;
    private static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public static DatabaseService getInstance() {
        if (instance == null) {
            instance = new DatabaseService();
        }
        return instance;
    }

    DatabaseService() {

    }

    static List<Patient> getPatients(){
        Session session = sessionFactory.openSession();

        List<Patient> patients = session.createQuery("FROM Patient ").list();
        session.close();
        return patients;
    }


}
