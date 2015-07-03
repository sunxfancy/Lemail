package lemail.api;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import lemail.model.User;
import lemail.utils.Action;
import lemail.utils.DBSession;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;


/**
 * 登录管理
 * Created by sxf on 15-6-28.
 */
public class Auth {

    public String username;
    public String password;
    public String name;
    public Integer department_id;
    public String role;
    public Integer default_checker;

    public String login() {
        User u = (User) DBSession.find_first(User.class,
                Restrictions.eq("username", username));
        if (u == null) {
            return Action.error(1000, "找不到用户");
        }
        if (u.check_passwd(password)) {
            return Action.text("true");
        }
        return Action.error(1001, "密码错误");
    }

    public String logout() {
        return null;
    }

    public String signup() {
        User u = new User(username, password, name, role, department_id);
        if (default_checker != null) {
            u.setDefaultChecker(default_checker);
        }

        Session s = DBSession.getSession();
        try {
            s.beginTransaction();
            s.save(u);
            s.getTransaction().commit();
        } catch (Exception ex) {
            s.getTransaction().rollback();
            if (ex instanceof MySQLIntegrityConstraintViolationException) {
                Action.error(1002, "用户已存在");
            } else {
                Action.error(-1, "未知错误");
            }
        } finally {
            s.close();
        }
        return null;
    }

}
