package State.Entities;

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
        List<Person> persons = session.createQuery("FROM Person ").list();
        @SuppressWarnings("unchecked")
        List<Role> users = session.createQuery("FROM Role ").list();
        persons.forEach((x) -> System.out.printf("- %s%n", x));
        users.forEach((x) -> System.out.printf("- %s%n", x));
        session.close();
    }

    private static void persist(SessionFactory sessionFactory) {
        Person p1 = new Person("Test", 33, "Via Marco Polo 22");
        Role u1 = new Role("Edo", "aaa");
        Role u2 = new Role("Elia", "aaa");
        Role u3 = new Role("Marian", "bbb");

        System.out.println("-- persisting users --");


        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(p1);
        session.save(u1);
        session.save(u2);
        session.save(u3);
        session.getTransaction().commit();

    }
}