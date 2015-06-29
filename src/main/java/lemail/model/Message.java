package lemail.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 消息类
 * Created by sxf on 15-6-28.
 */
@Entity
@Table(name = "`message`")
public class Message implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`")
    private Integer id;
    @Column(name = "`from`")
    private Integer from;
    @Column(name = "`to`")
    private Integer to;
    @Column(name = "`date`")
    private Date date;
    @Column(name = "`content`", columnDefinition = "LONGTEXT")
    private String content;


}
