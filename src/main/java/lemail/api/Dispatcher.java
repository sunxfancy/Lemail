package lemail.api;

import jdk.internal.org.objectweb.asm.tree.IntInsnNode;
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
    public Integer page;

    public String getAll() {
        Integer uid = (Integer) Action.getSession("uid");
        String role = (String) Action.getSession("role");
        if (uid != null) {
            if (role.contains("D")) {
                try {
                    if (page == null)
                        page = 0;
                    List<Inbox> inboxMails = DBSession.executeSql("from Inbox order by date desc", 10 * page, 10);
                    int count = DBSession.count("Inbox");
                    StringBuilder sb = new StringBuilder();
                    sb.append("{\"list\":[");
                    for (Inbox itememail : inboxMails) {
                        sb.append(itememail.toJsonNoData());
                        sb.append(',');
                    }
                    if (sb.length() > 9) {
                        sb.setCharAt(sb.length() - 1, ']');
                        sb.append(",");
                    } else {
                        sb.append("],");
                    }
                    sb.append("\"page\":");
                    sb.append(page + 1);
                    sb.append(String.format(",\"sum\":%d", count % 10 == 0 ? count % 10 + 1 : count % 10));
                    sb.append("}");
                    Action.echojson(0, "success", sb.toString());
                } catch (Exception ex) {
                    ex.printStackTrace();
                    Action.error(-1, "未知异常");
                }
            } else {
                Action.error(403, "权限不足");
            }
        } else {
            Action.error(401, "分发人员未登录");
        }
        return null;
    }

    public String dispatch() {
        return null;
    }
}
