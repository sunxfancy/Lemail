package lemail.api;

import lemail.model.Inbox;
import lemail.model.User;
import lemail.utils.Action;
import lemail.utils.DBSession;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * 处理者接口
 * Created by sxf on 15-7-3.
 */
public class Handler {

    /**
     * 获取用户的全部消息
     */
    public String getMessage() {

        return null;
    }

    /**
     * 获取用户的全部邮件
     */
    public String getAll() {
        try {
            User u = checkUser();
            List<Inbox> list = DBSession.find_list(
                Inbox.class,
                Order.desc("date"),
                Restrictions.eq("belong_user_id", u.getId()));
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            for (Inbox in : list) {
                sb.append(in.toJson());
                sb.append(',');
            }
            if (sb.length() > 1)
                 sb.setCharAt(sb.length()-1, ']');
            else sb.append(']');
            Action.echojson(0, "success", sb.toString());
            return null;
        } catch (HandlerException e) {
            e.printStackTrace();
            return Action.error(e.id, e.getMessage());
        }
    }

    /**
     * 获取用户
     */
    private User getUser() {
        return (User)Action.getSession("uid");
    }

    /**
     * 获取用户并检查
     * @throws HandlerException 报告用户未登录或无权限错误
     */
    private User checkUser() throws HandlerException {
        User u = getUser();
        if (u == null) throw new HandlerException(401, "用户未登录");
        if (!u.checkRole("H")) throw new HandlerException(500, "用户缺少处理者权限");
        return u;
    }

    private class HandlerException extends Exception{
        int id;

        public HandlerException(int id, String message) {
            super(message);
            this.id = id;
        }
    }
}
