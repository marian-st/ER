package State.Entities;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ExampleDatabase {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure()
                .buildSessionFactory();
        try {
            persist(sessionFactory);
            load(sessionFactory);
        } finally {
            sessionFactory.close();
        }
    }

    private static void load(SessionFactory sessionFactory) {
        System.out.println("-- loading persons --");
        Session session = sessionFactory.openSession();
        @SuppressWarnings("unchecked")
        List<User> users = session.createQuery("FROM User").list();
        users.forEach((x) -> System.out.printf("- %s%n", x));
        session.close();
    }

    private static void persist(SessionFactory sessionFactory) {
        User u1 = new User("Edo", "aaa", Role.DOCTOR, true);
        User u2 = new User("Elia", "aaa", Role.DOCTOR, true);
        User u3 = new User("Marian", "bbb", Role.HEAD_PHYSICIAN, true);

        System.out.println("-- persisting users --");


        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(u1);
        session.save(u2);
        session.save(u3);
        session.getTransaction().commit();

    }
}