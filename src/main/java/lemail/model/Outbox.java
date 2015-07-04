package lemail.model;

import javax.persistence.*;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;


/**
 * 发件箱的模型类
 * Created by sxf on 15-6-28.
 */

@Entity
@Table(name = "`outbox`")
public class Outbox implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`")
    private Integer id;
    @Column(name = "`subject`")
    private String subject;
    @Column(name = "`content`", columnDefinition = "MEDIUMTEXT")
    private String content;
    @Column(name = "`attachment`", columnDefinition = "TEXT")
    private String attachment;
    @Column(name = "`date`")
    private Date date;
    @Column(name = "`state`")
    private Integer state;
    @Column(name = "`to`")
    private String to;
    @Column(name = "`tag`")
    private String tag;
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "checker")
    private User checker;

    public Outbox(String subject, String content, Date date, String to) {
        this.subject = subject;
        this.content = content;
        this.date = date;
        this.state = 0;
        this.to = to;
    }

    public Outbox() {

    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public User getChecker() {
        return checker;
    }

    public void setChecker(User checker) {
        this.checker = checker;
    }
}
