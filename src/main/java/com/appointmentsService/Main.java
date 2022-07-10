package com.appointmentsService;

import com.appointmentsService.model.Person;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class Main {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                // Hibernate settings equivalent to hibernate.cfg.xml's properties
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "org.h2.Driver");
                settings.put(Environment.URL, "jdbc:h2:~/IdeaProjects\\Java_2_Service_6_Appointments\\src\\main\\resources\\DataBase");
                settings.put(Environment.USER, "sa");
                settings.put(Environment.PASS, "");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.H2Dialect");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, "create-drop"); // Kill me when u'r done
                configuration.setProperties(settings);

                // TODO Hier alle Entities registrieren
                configuration.addAnnotatedClass(Person.class);
                // configuration.addAnnotatedClass(Appointment.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

    public static void main(String[] args) {
        getSessionFactory();

        try (Session session = sessionFactory.openSession()) {

            // Person anlegen
            Transaction tx = null;
            try {
                tx = session.beginTransaction();

                Person person = new Person();
                person.setName("Georg");
                session.persist(person);

                tx.commit();
            } catch (Exception e) {
                if (tx != null) tx.rollback();
                throw e;
            }

            // Person verändern
            try {
                tx = session.beginTransaction();

                String hql = "FROM Person p WHERE p.name = :name";
                Query query = session.createQuery(hql, Person.class);
                query.setParameter("name", "Georg");
                Optional<Person> first = query.getResultList().stream().findFirst();
                Person person = first.get();
                person.setName("Klaus");
                session.persist(person);

                tx.commit();
            } catch (Exception e) {
                if (tx != null) tx.rollback();
                throw e;
            }

            // Alle Peronen ausgeben
            List<Person> persons = session.createQuery("from Person", Person.class).list();
            for (Person p : persons) {
                System.out.println(p.toString());
            }
        }
    }
}
