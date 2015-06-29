package lemail.model;


import javax.persistence.*;
import java.io.Serializable;

/**
 * 部门，仅有一个名字
 * Created by sxf on 15-6-28.
 */

@Entity
@Table(name = "department")
public class Department implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    public String name;

    public Department(String name) {
        this.name = name;
    }

    public Department() {
    }
}
