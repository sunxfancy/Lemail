package lemail.utils;

import junit.framework.TestCase;

import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Message;
import javax.mail.Multipart;

public class MailTest extends TestCase {

    public void testPostMail() throws Exception {
        Mail mail = new Mail("lemailtest@sina.com", "1234qwer", "smtp.sina.com");
        mail.PostMail("测试一下", "测试邮件啊。。。。", "lemailtest@sina.com");
    }

    public void testGetBox() throws Exception {
        Mail mail = new Mail("lemailtest@sina.com", "1234qwer", "imap.sina.com");
        Message[] msgs = mail.getBox("INBOX");
        for (Message msg : msgs) {
            System.out.println("Subject:" + msg.getSubject());
            System.out.println("Content:" + msg.getContent().toString());
            boolean b = msg.getFlags().contains(Flags.Flag.SEEN);
            if (!b) {
                System.out.println("这封信你还没读呢");
                msg.setFlag(Flags.Flag.SEEN, true);
            }
        }
        mail.getBox("SENT");
        mail.getBox("DRAFTS");// 草稿
        mail.getBox("TRASH"); // 已删除
        mail.getBox("JUNK");  // 垃圾邮件
    }
}