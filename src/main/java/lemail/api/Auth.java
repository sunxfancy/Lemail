package lemail.api;

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

    public String login() {
        Session s = DBSession.getSession();
        User u = (User) DBSession.find_first(User.class,
                Restrictions.eq("username", username));
        if (u == null) {
            return Action.error("找不到用户");
        }
        s.close();
        if (u.check_passwd(password)) {
            return Action.text("true");
        }
        return Action.error("密码错误");
    }

    public static String logout() {
        return null;
    }

    public static String signin() {
        return null;
    }

}
