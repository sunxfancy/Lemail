package lemail.utils;

import junit.framework.TestCase;

public class AutoMailTest extends TestCase {

    public void testSetProp() throws Exception {
        AutoMail mail = AutoMail.getInstance();
        mail.setProp("lemailtest@sina.com", "1234qwer", "smtp.sina.com", "imap.sina.com");
    }

    public void testUpdata() throws Exception {
        AutoMail.getInstance().Update();
    }

    public void testGetInstance() throws Exception {
        System.out.println(AutoMail.getInstance().getHostname());
        System.out.println(AutoMail.getInstance().getUsername());
        System.out.println(AutoMail.getInstance().getPassword());
    }
}