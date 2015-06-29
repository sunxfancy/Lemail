package lemail.utils;

import junit.framework.TestCase;

public class MailTest extends TestCase {

    public void testPostMail() throws Exception {
        Mail mail = new Mail("lemailtest@sina.com", "1234qwer", "smtp.sina.com");
        mail.PostMail("测试一下", "测试邮件啊。。。。", "lemailtest@sina.com");
    }

    public void testRetrieveAllMailMessage() throws Exception {
        Mail mail = new Mail("lemailtest@sina.com", "1234qwer", "imap.sina.com");
        mail.retrieveAllMailMessage();
    }
}