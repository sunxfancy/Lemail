package lemail.api;

import lemail.model.Inbox;
import lemail.utils.Action;
import lemail.utils.DBSession;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.exception.ConstraintViolationException;

import java.util.List;

/**
 * Created by lilelr on 2015/7/3.
 */
public class Dispatcher {
    public String getAll() {
        Integer uid = (Integer) Action.getSession("uid");
        if (uid != null) {
            try {
                List<Inbox> inboxMails = (List<Inbox>) DBSession.find_list(Inbox.class, Order.desc("date"));
                StringBuilder sb = new StringBuilder();
                sb.append("[");
                System.out.write(inboxMails.size());
                for (Inbox itememail : inboxMails) {
                    sb.append(itememail.toJson());
                    sb.append(',');
                }
                if (sb.length() > 1) {
                    sb.setCharAt(sb.length() - 1, ']');
                } else {
                    sb.append("]");
                }
                Action.echojson(0, "success", sb.toString());
                System.out.println("gg");
            } catch (Exception ex) {
                ex.printStackTrace();
                Action.error(402, "数据库异常");
            }
        } else {
            Action.error(401, "分发人员未登录");
        }

        return null;
    }
}
