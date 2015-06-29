package lemail.utils;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by sxf on 15-6-29.
 */
public class Mail {

    private String username;
    private String password;
    private String hostname;

    public Mail(String username, String password, String hostname) {
        this.username = username;
        this.password = password;
        this.hostname = hostname;
    }

    private List<String> mail_list = new LinkedList<String>();

    public void GetMail() {

    }

    public void PostMail(String subject, String content, String from, String... to) {

    }

}
