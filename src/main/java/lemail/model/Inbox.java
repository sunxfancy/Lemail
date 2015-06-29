package lemail.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 收件箱
 * Created by sxf on 15-6-28.
 */
@Entity
@Table(name = "inbox")
public class Inbox implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String subject;
    @Column(columnDefinition = "LONGTEXT")
    private String content;
    private Date date;
    @Column(columnDefinition = "LONGTEXT")
    private String attachment;
    private int state;
    private String from;
    @Column(columnDefinition = "TINYINT(1)")
    private boolean review;
    private String tag;
    private int belong_user_id;

    public Inbox(String subject, String content, Date date, String from) {
        this.subject = subject;
        this.content = content;
        this.date = date;
        this.state = 0;
        this.from = from;
    }

    public Inbox() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public boolean isReview() {
        return review;
    }

    public void setReview(boolean review) {
        this.review = review;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getBelongUserId() {
        return belong_user_id;
    }

    public void setBelongUserId(int belongUserId) {
        this.belong_user_id = belongUserId;
    }
}
