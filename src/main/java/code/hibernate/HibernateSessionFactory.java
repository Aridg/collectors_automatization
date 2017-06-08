package code.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateSessionFactory {
    private static SessionFactory ourSessionFactory;

    public static Session getSession() throws HibernateException {
        while (ourSessionFactory == null)
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        return ourSessionFactory.openSession();
    }

    public synchronized static void init(String dbPath){
        try {
            ourSessionFactory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .setProperty("hibernate.connection.url", String.format("jdbc:sqlite:%s",dbPath))
                    .buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    };
    public static void shutdown(){
        ourSessionFactory.close();
    }
}
