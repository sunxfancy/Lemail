package lemail.utils;

import lemail.model.Inbox;

import javax.mail.*;
import javax.mail.internet.MimeUtility;
import java.io.*;
import java.util.UUID;

/**
 * 这是一个自动拉取邮件，并自动存储配置的类
 * Created by sxf on 15-7-3.
 */
public class AutoMail {

    private Mail mail = null;
    private static AutoMail autoMail = new AutoMail();

    // 需要保障这三个字段中都不存在回车
    private String username;
    private String password;
    private String hostname;

    // 路径配置项
    private final String path="save.prop";
    private final String attachment_path = "./attachment/";

    public AutoMail() {
        if (!load()) return;
        mail = new Mail(username, password, hostname);
    }

    public void setProp(String username, String password, String hostname, String frequency) {
        this.username = username;
        this.password = password;
        this.hostname = hostname;
        mail = new Mail(username, password, hostname);
        save();
    }

    /**
     * 核心更新方法，每次调用，都会向邮件客户端拉取邮件
     */
    public void Updata() {
        try {
            Message[] msgs = mail.getBox("INBOX");
            for (Message msg : msgs) {
                msg.getFlags().add(Flags.Flag.SEEN);
                String content = null;
                StringBuilder sb = null;
                if (msg.getContentType().contains("multipart")) {
                    sb = new StringBuilder();
                    content = multipart(msg, sb);
                } else {
                    content = msg.getContent().toString();
                }
                Inbox in_msg = new Inbox(
                    msg.getSubject(),
                    content,
                    msg.getSentDate(),
                    msg.getFrom()[0].toString()
                );
                if (sb != null)
                    in_msg.setAttachment(sb.toString());

            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private String multipart(Message msg, StringBuilder sb) throws IOException, MessagingException {
        Multipart multipart = (Multipart) msg.getContent();
        String content = null;
        int count = multipart.getCount();    // 部件个数
        for (int i = 0; i < count; i++) {
            BodyPart part = multipart.getBodyPart(i);
            // 单个部件类型
            String type = part.getContentType().split(";")[0];
            if (type.equals("multipart/alternative")
                    || type.contains("text")
                    || type.equals("multipart/related")) {
                content = part.getContent().toString();
            } else {
                // 文件名解码
                sb.append(MimeUtility.decodeText(part.getFileName())).append('|');
                UUID uuid = UUID.randomUUID(); // 创建uuid
                File f;
                while ((f = new File(attachment_path+uuid)).exists()) {
                    uuid = UUID.randomUUID();
                }
                sb.append(uuid).append('|');
                DataInputStream in = new DataInputStream(part.getInputStream());
                FileOutputStream out = new FileOutputStream(f);
                byte[] buffer = new byte[2048];
                int k;
                while ((k = in.read(buffer)) > 0) {
                    out.write(buffer, 0, k);
                }
            }
        }
        return content;
    }

    private void save() {
        File file = new File(path);
        try {
            if(!file.exists())
                file.createNewFile();
            FileOutputStream out = new FileOutputStream(file,false);
            OutputStreamWriter writer =new OutputStreamWriter(out);
            writer.write(username);
            writer.write("\n");
            writer.write(password);
            writer.write("\n");
            writer.write(hostname);
            writer.write("\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean load() {
        File file = new File(path);
        if (!file.exists()) return false;
        try {
            FileInputStream in = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            username = br.readLine();
            password = br.readLine();
            hostname = br.readLine();
            br.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static AutoMail getInstance() {
        return autoMail;
    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getHostname() {
        return hostname;
    }

}
