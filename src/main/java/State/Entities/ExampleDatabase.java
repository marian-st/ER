package State.Entities;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.time.Month;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
        Session session = sessionFactory.openSession();

        @SuppressWarnings("unchecked")
        List<Administration> administrations = session.createQuery("FROM Administration ").list();
        for (Administration ad : administrations) {
            System.out.println(ad.getPatient());
        }
        session.close();
    }

    private static void persist(SessionFactory sessionFactory) {
        System.out.println("-------- Saving Entries --------");
        User u1 = new User("Edo", "aaa", Role.DOCTOR, true);
        User u2 = new User("Elia", "aaa", Role.DOCTOR, true);
        User u3 = new User("Marian", "bbb", Role.HEAD_PHYSICIAN, true);

        Patient patient1 = new Patient("Carlo", "Combi", "CMBCBMWHATEVER291Z", "Verona",
                new GregorianCalendar(1965, Calendar.APRIL, 11).getTime());
        Patient patient2 = new Patient("Barbara", "Oliboni", "BRBLBIDONTKNOW329I", "Verona",
                new GregorianCalendar(1973, Calendar.OCTOBER, 8).getTime());
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(u1);
        session.save(u2);
        session.save(u3);
        session.save(patient1);
        session.save(patient2);
        try {
            Administration adm1 = new Administration(new GregorianCalendar(2019, Calendar.JULY, 11).getTime(),
                    14, 75.76,"All good", patient1);
            session.save(adm1);
        } catch (Exception e) {System.out.println(e);}
        session.getTransaction().commit();

    }
}