package lemail.model;


import javax.persistence.*;
import java.io.Serializable;

/**
 * 部门，仅有一个名字
 * Created by sxf on 15-6-28.
 */

@Entity
@Table(name = "`department`")
public class Department implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`")
    private int id;
    @Column(name = "`name`")
    private String name;

    public Department(String name) {
        this.name = name;
    }

    public Department() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toJson() {
        return String.format("{\"id\":%d, \"name\":\"%s\"}", id, name);
    }
}
