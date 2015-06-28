package lemail.utils;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

/**
 * 连接数据库的工具类
 * Created by sxf on 15-6-28.
 */
public class DBSession {

    public static SessionFactory sf = null;

    public static Session getSession() {
        if (sf == null) init();
        return sf.openSession();
    }

    public static List list(DetachedCriteria dc) {
        Session s = getSession();
        Criteria c = dc.getExecutableCriteria(s);
        List rs = c.list();
        s.close();
        return rs;
    }
    public static Object first(DetachedCriteria dc) {
        Session s = getSession();
        Criteria c = dc.getExecutableCriteria(s);
        Object d = c.uniqueResult();
        s.close();
        return d;
    }

    public static List find_list(Class T, Criterion... res) {
        DetachedCriteria dc = DetachedCriteria.forClass(T);
        for (Criterion r : res) {
            dc.add(r);
        }
        return list(dc);
    }
    public static Object find_first(Class T, Criterion... res) {
        DetachedCriteria dc = DetachedCriteria.forClass(T);
        for (Criterion r : res) {
            dc.add(r);
        }
        return first(dc);
    }


    private static void init() {
        Configuration config = new Configuration().configure();
        StandardServiceRegistryBuilder standardBuilder = new StandardServiceRegistryBuilder()
                .applySettings(config.getProperties());
        StandardServiceRegistry standardServiceRegistry = standardBuilder.build();
        sf = config.buildSessionFactory(standardServiceRegistry);
    }
}
