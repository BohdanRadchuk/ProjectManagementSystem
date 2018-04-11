package hibernateFunctionality;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateFactory {
    private static final SessionFactory SESSION_FACTORY = buildSessionFactory();

    //creating session factory
    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration().configure().buildSessionFactory();
        } catch (Throwable e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }

    //closing session factory, runs at the end of program using
    public static void shutdown() {
        getSessionFactory().close();
    }

}

