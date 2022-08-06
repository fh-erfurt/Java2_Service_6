package com.appointmentsService.sessionControls;

import com.appointmentsService.model.Appointment;
import com.appointmentsService.model.Course;
import com.appointmentsService.model.Faculty;
import com.appointmentsService.model.Location;
import com.appointmentsService.model.Person;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class sessionParentClass {
    protected static SessionFactory sessionFactory;
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

                configuration.addAnnotatedClass(Appointment.class);
                configuration.addAnnotatedClass(Course.class);
                configuration.addAnnotatedClass(Faculty.class);
                configuration.addAnnotatedClass(Location.class);
                configuration.addAnnotatedClass(Person.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
