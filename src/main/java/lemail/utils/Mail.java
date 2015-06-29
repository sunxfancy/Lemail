package lemail.utils;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
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

    private List<String> mail_list = new LinkedList<String>();

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
    public Message[] retrieveAllMailMessage() throws Exception {
        Session session;
        Store store;
        Folder folder;
        Folder inbox_folder;

        Properties props=System.getProperties();
        props.setProperty("mail.pop3s.rsetbeforequit","true");
        props.setProperty("mail.pop3.rsetbeforequit","true");
        session=Session.getInstance(props,null);
        // 打印出错误信息
        session.setDebug(true);

        store=session.getStore(emailprovider);
        store.connect(hostname, username, password);
        folder=store.getFolder("INBOX");
        if( folder==null )
            throw new Exception("No default folder");
        inbox_folder = folder;
        inbox_folder.open(Folder.READ_WRITE);

        Message[] msgs = inbox_folder.getMessages();

        for (Message msg : msgs) {
            System.out.println("################################");
            if (msg != null) {
                System.out.println("邮件标题:" + msg.getSubject());
                System.out.println("邮件发送者：" + Arrays.toString(msg.getFrom()));
                System.out.println("邮件发送时间：" + msg.getSentDate());
                System.out.println("邮件正文:"
                        + ((msg.getContent() == null) ? "没有正文"
                        : msg.getContent()));
            }
        }

        return msgs;
    }



}