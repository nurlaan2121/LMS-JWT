package lms.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "groups")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "id_gen", allocationSize = 1)
public class Group extends BaseModel {
    private String groupName;
    @Column(length = 2000)
    private String imageLink;
    private String description;
    @ManyToMany @JoinTable(
            name = "groups_courses",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Course> courses = new ArrayList<>();
    @OneToMany(mappedBy = "group",cascade = {CascadeType.ALL})
    private List<Student> students = new ArrayList<>();

    public Group(String groupName, String imageLink, String description) {
        this.groupName = groupName;
        this.imageLink = imageLink;
        this.description = description;
    }
}
