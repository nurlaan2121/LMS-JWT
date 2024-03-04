package lms.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "id_gen",allocationSize = 1)
public class Task extends BaseModel{
    private String name;
    private String text;
    private LocalDate deadline;
    @ManyToOne
    private Lesson lesson;

    public Task(String name, String text, LocalDate deadline) {
        this.name = name;
        this.text = text;
        this.deadline = deadline;
    }
}
