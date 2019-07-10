package State.Entities;

import System.LoginDemo.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ExampleMain {
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
        List<RoleEntry> users = session.createQuery("FROM RoleEntry").list();
        users.forEach((x) -> System.out.printf("- %s%n", x));
        session.close();
    }

    private static void persist(SessionFactory sessionFactory) {
        RoleEntry u1 = new RoleEntry("Edo", "aaa", Role.DOCTOR);
        RoleEntry u2 = new RoleEntry("Elia", "aaa", Role.DOCTOR);
        RoleEntry u3 = new RoleEntry("Marian", "bbb", Role.HEAD_PHYSICIAN);

        System.out.println("-- persisting users --");


        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(u1);
        session.save(u2);
        session.save(u3);
        session.getTransaction().commit();

    }
}