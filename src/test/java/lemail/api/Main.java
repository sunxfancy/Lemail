package lemail.api;

import lemail.model.Department;
import lemail.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * 测试数据库连接用
 * Created by sxf on 15-6-28.
 */
public class Main {
    private static final SessionFactory sf;

    static {
        try {
            Configuration config = new Configuration();
            config.configure();
            StandardServiceRegistryBuilder standardBuilder
                    = new StandardServiceRegistryBuilder()
                    .applySettings(config.getProperties());
            StandardServiceRegistry sr = standardBuilder.build();
            sf = config.buildSessionFactory(sr);
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return sf.openSession();
    }

    public static void main(final String[] args) throws Exception {
        final Session session = getSession();
        try {
            Department d = new Department("教务处");

            User s = new User("sxf", "fajoewijfaoiwe", "孙笑凡", "hello", d);
            session.beginTransaction();
            session.save(d);
            session.save(s);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }
}
