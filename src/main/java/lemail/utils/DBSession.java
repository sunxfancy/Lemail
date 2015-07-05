package lemail.utils;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;

import java.lang.reflect.Array;
import java.util.Collection;
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

    public static List find_list(Class T, Order order, Criterion... res) {
        DetachedCriteria dc = DetachedCriteria.forClass(T);
        if (order != null)
            dc.addOrder(order);
        for (Criterion r : res) {
            dc.add(r);
        }
        return list(dc);
    }

    public static List find_list(Class T, Criterion... res) {
        return find_list(T, null, res);
    }

    public static Object find_first(Class T, Criterion... res) {
        DetachedCriteria dc = DetachedCriteria.forClass(T);
        for (Criterion r : res) {
            dc.add(r);
        }
        return first(dc);
    }

    public static List executeSql(String sql, int offset, int max, String order, Condition... conditions) {
        Session s = getSession();
        StringBuilder sb = new StringBuilder(sql + " ");
        if (conditions.length > 0) {
            sb.append(" where ");
            for (int i = 0; i < conditions.length; i++) {
                sb.append(conditions[i].getCondition());
                sb.append(" ");
                if (i != conditions.length - 1) {
                    sb.append("and ");
                }
            }
        }
        sb.append(order);
        try {
            s.beginTransaction();
            Query q = s.createQuery(sb.toString()).setFirstResult(offset).setMaxResults(max);
            if (conditions.length > 0) {
                for (int i = 0; i < conditions.length; i++) {
                    if (conditions[i].getValue() instanceof Collection) {
                        q.setParameterList(conditions[i].getName(), (Collection) (conditions[i].getValue()));
                    } else
                        q.setParameter(conditions[i].getName(), conditions[i].getValue());
                }
            }
            List l = q.list();
            s.getTransaction().commit();
            return l;
        } finally {
            s.close();
        }
    }

    public static int count(String name, Condition... conditions) {
        Session s = getSession();
        int result;
        StringBuilder sql = new StringBuilder(String.format("select count(*) from %s", name));
        if (conditions.length > 0) {
            sql.append(" where ");
            for (int i = 0; i < conditions.length; i++) {
                sql.append(conditions[i].getCondition());
                sql.append(" ");
                if (i != conditions.length - 1) {
                    sql.append("and ");
                }
            }
        }
        try {
            s.beginTransaction();
            Query q = s.createQuery(sql.toString());
            if (conditions.length > 0) {
                for (int i = 0; i < conditions.length; i++) {
                    if (conditions[i].getValue() instanceof Collection) {
                        q.setParameterList(conditions[i].getName(), (Collection) (conditions[i].getValue()));
                    } else
                        q.setParameter(conditions[i].getName(), conditions[i].getValue());
                }
            }
            result = ((Number) q.uniqueResult()).intValue();
            s.getTransaction().commit();
            return result;
        } finally {
            s.close();
        }
    }

    private static void init() {
        Configuration config = new Configuration().configure();
        StandardServiceRegistryBuilder standardBuilder = new StandardServiceRegistryBuilder()
                .applySettings(config.getProperties());
        StandardServiceRegistry standardServiceRegistry = standardBuilder.build();
        sf = config.buildSessionFactory(standardServiceRegistry);
    }
}
