package lemail.utils;


import lemail.model.Message;
import org.hibernate.criterion.Restrictions;

import java.util.Date;
import java.util.List;

/**
 * 基础的全局消息发送处理类
 * Created by sxf on 15-7-4.
 */
public class MessageSender {

    /**
     * 发送消息
     * @param from_id 当前登录的用户id
     * @param to_id   发送目标的id
     * @param content 消息的内容
     */
    public void SendMessage(Integer from_id, Integer to_id, String content) {
        Message m = new Message(from_id, to_id, new Date(), content);
        DBSession.getSession().save(m);
    }

    /**
     * 读取一条站内消息，如果是接收方查阅，则会将消息的状态转换为已读
     * @param id 当前登录状态的用户的id
     * @param message_id 要查看的消息的id
     * @return 返回消息对象，若无阅读权限，则返回null
     */
    public Message ReadMessage(Integer id, Integer message_id) {
        Message m = (Message) DBSession.find_first(
                Message.class, Restrictions.eq("id", message_id));
        if (m.getFrom().equals(id)) return m;
        if (m.getTo().equals(id)) {
            m.setState(1);
            return m;
        }
        return null;
    }

    /**
     * 获取当前用户的全部未读消息
     * @param id 用户id
     * @return 未读消息列表
     */
    public List<Message> getUnReadMessage(Integer id) {
        return DBSession.find_list(
                Message.class, Restrictions.eq("to", id), Restrictions.eq("state", 0));
    }

    private MessageSender() {
    }

    private static MessageSender instance = null;

    public static MessageSender getInstance() {
        if (instance == null) instance = new MessageSender();
        return instance;
    }
}
