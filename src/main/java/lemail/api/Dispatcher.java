package lemail.api;

import lemail.model.Inbox;
import lemail.utils.Action;
import lemail.utils.DBSession;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

import java.util.List;

/**
 * Created by lilelr on 2015/7/3.
 */
public class Dispatcher {
    public String getAll() {
        Integer uid = (Integer)Action.getSession("uid");
        if (uid!=null){
            try {
                List<Inbox> inboxMails = (List<Inbox>)DBSession.find_list(Inbox.class);
                for (Inbox itememail:inboxMails){
                    Action.echojson(0,"success",itememail.toJson());
                }
                System.out.println("gg");
            } catch (Exception ex) {
                Action.error(402, "数据库异常");
            }
        }else {
            Action.error(401, "分发人员未登录");
        }

      return null;
    }
}
