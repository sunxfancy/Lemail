package lemail.utils;

import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.search.SearchTerm;
import java.util.Properties;

/**
 * 邮件的基础操作类
 * Created by sxf on 15-6-29.
 */
public class Mail {

    private String username;
    private String password;
    private String hostname;
    private String emailprovider = "imap";
    private Authenticator auth;

    public Mail(String _username, String _password, String _hostname) {
        this.username = _username;
        this.password = _password;
        this.hostname = _hostname;

        auth = new MyAuthenticator(username,password);
    }

    public void GetMail() {

    }

    private class MyAuthenticator extends Authenticator {
        String userName = null;
        String password = null;

        public MyAuthenticator(String username, String password) {
            this.userName = username;
            this.password = password;
        }

        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(userName, password);
        }
    }

    /**
     * 发送一封邮件
     * @param subject 主题
     * @param content 内容
     * @param to      收件人列表
     * @throws MessagingException 异常情况
     */
    public void PostMail(String subject, String content, String... to)
            throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.host", hostname);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.user", username);
        props.put("mail.smtp.password", password);

        Session session = Session.getDefaultInstance(props, auth);
        session.setDebug(true);
        // create a message
        Message msg = new MimeMessage(session);

        // set the from and to address
        InternetAddress addressFrom = new InternetAddress(username);
        msg.setFrom(addressFrom);

        InternetAddress[] addressTo = new InternetAddress[to.length];
        for (int i = 0; i < to.length; i++) {
            addressTo[i] = new InternetAddress(to[i]);
        }
        msg.setRecipients(Message.RecipientType.TO, addressTo);

        // Setting the Subject and Content Type
        msg.setSubject(subject);
        msg.setContent(content, "text/plain;charset=UTF-8");
        Transport.send(msg);
    }



    // 得到所有的邮件
    public Message[] getBox(String boxname) throws Exception {
        Properties props=System.getProperties();
        props.setProperty("mail.pop3s.rsetbeforequit","true");
        props.setProperty("mail.pop3.rsetbeforequit","true");
        Session session=Session.getInstance(props,null);
        // 打印出错误信息
        session.setDebug(true);

        Store store = session.getStore(emailprovider);
        store.connect(hostname, username, password);
        Folder folder = store.getFolder(boxname);
        if( folder==null )
            throw new Exception("No default folder");
        folder.open(Folder.READ_WRITE);
        return folder.search(new SearchTerm() {
            @Override
            public boolean match(Message message) {
                try {
                    return !(message.getFlags().contains(Flags.Flag.SEEN));
                } catch (MessagingException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        });
    }



}