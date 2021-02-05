package uz.tdpu.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private Integer age;

    @Column(name = "full_name")
    private String fullName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", insertable = false, updatable = false)
    @JsonIgnore
    private Category parent;

    @Column(name = "parent_id", columnDefinition = "integer default NULL")
    private Long parentId;

    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private Date updatedAt;

    public Category(String name, Integer age, String fullName, Long parentId) {
        this.name = name;
        this.age = age;
        this.fullName = fullName;
        this.parentId = parentId;
    }

    public Category(String name, Long parentId) {
        this.name = name;
        this.parentId = parentId;
    }
}
