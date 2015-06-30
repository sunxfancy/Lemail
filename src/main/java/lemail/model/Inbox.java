package lemail.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 收件箱
 * Created by sxf on 15-6-28.
 */
@Entity
@Table(name = "`inbox`")
public class Inbox implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`")
    private Integer id;
    @Column(name = "`subject`")
    private String subject;
    @Column(name = "`content`", columnDefinition = "LONGTEXT")
    private String content;
    @Column(name = "`date`")
    private Date date;
    @Column(name = "`attachment`", columnDefinition = "LONGTEXT")
    private String attachment;
    @Column(name = "`state`")
    private Integer state;
    @Column(name = "`from`")
    private String from;
    @Column(name = "`review`", columnDefinition = "TINYINT(1)")
    private Boolean review;
    @Column(name = "`tag`")
    private String tag;
    @Column(name = "`belong_user_id`")
    private Integer belong_user_id;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "`user_inbox`",
            joinColumns = {@JoinColumn(name = "`inbox_id`")},
            inverseJoinColumns = {@JoinColumn(name = "`user_id`")})
    private Set<User> readers = new LinkedHashSet<User>();

    public Inbox(String subject, String content, Date date, String from) {
        this.subject = subject;
        this.content = content;
        this.date = date;
        this.state = 0;
        this.from = from;
    }

    public Inbox() {

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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public Boolean isReview() {
        return review;
    }

    public void setReview(Boolean review) {
        this.review = review;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getBelongUserId() {
        return belong_user_id;
    }

    public void setBelongUserId(Integer belongUserId) {
        this.belong_user_id = belongUserId;
    }

    public Set<User> getReaders() {
        return readers;
    }
}
