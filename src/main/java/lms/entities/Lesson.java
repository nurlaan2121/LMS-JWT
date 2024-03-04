package lms.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lessons")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "id_gen",allocationSize = 1)
public class Lesson extends BaseModel {
    private String LessonName;
    @ManyToOne
    private Course course;
    @OneToMany(cascade = {CascadeType.REMOVE})
    private List<Task> tasks = new ArrayList<>();

    public Lesson(String lessonName) {
        LessonName = lessonName;
    }
}
