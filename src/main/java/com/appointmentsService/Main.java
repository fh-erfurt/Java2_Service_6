package com.appointmentsService;

import com.appointmentsService.model.Appointment;
import com.appointmentsService.model.Person;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.math.BigInteger;
import java.sql.Array;
import java.sql.Timestamp;
import java.util.ArrayList;
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

    /** Search for a person by name
     * @param searchTerm the search term
     * @return list of type <Person> containing matching persons
     */
    public List<Person> searchPerson(String searchTerm) {
        // nach Person suchen
        getSessionFactory();

        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Person p WHERE p.name LIKE :term", Person.class).setParameter("term", "%"+ searchTerm +"%").list();
        }

    }
    /** Search for a public appointments by certain criteria
     * @param searchTerm search term included in the title of the appointment
     * @param fromDate define the start of time interval, can only be used in conjunction with "toDate" (see below)
     * @param toDate define the end of time interval, can only be used in conjunction with "fromDate"
     * @param courseID show appointments from one course
     * @param facultyID show appointments from one faculty
     * @param locationID show appointments from one location
     * @param organizerID show appointments from one organizer
     * @return list of type <Appointment> containing matching appointments
     */
    public List<Appointment> searchAppointment(String searchTerm, Timestamp fromDate, Timestamp toDate, BigInteger courseID,
                                               BigInteger facultyID, BigInteger locationID, BigInteger organizerID) {
        getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Appointment> cr = criteriaBuilder.createQuery(Appointment.class);
            Root<Appointment> root = cr.from(Appointment.class);
            ArrayList<Predicate> predicates = new ArrayList<Predicate>();

            predicates.add(criteriaBuilder.isTrue(root.get("ISPUBLIC")));
            if (searchTerm != null) {
                predicates.add(criteriaBuilder.like(root.get("TITLE"), "%" + searchTerm + "%"));
            }
            if (fromDate != null  && toDate != null) {
                predicates.add(criteriaBuilder.or(criteriaBuilder.lessThan(root.get("STARTDATETIME"), toDate), criteriaBuilder.greaterThan(root.get("ENDDATETIME"), fromDate )));
            }
            if (courseID != null) {
                predicates.add(criteriaBuilder.equal(root.get("COURSE_ID"), courseID));
            }
            if (facultyID != null) {
                predicates.add(criteriaBuilder.equal(root.get("FACULTY_ID"), facultyID));
            }
            if (locationID != null) {
                predicates.add(criteriaBuilder.equal(root.get("LOCATION_ID"), locationID));
            }
            if (organizerID != null) {
                predicates.add(criteriaBuilder.equal(root.get("ORGANIZER_ID"), organizerID));
            }
            Predicate[] array = predicates.toArray(new Predicate[0]);
            cr.select(root).where(array);
            return session.createQuery(cr).list();

        }

    }
}
