package lms.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "id_gen",allocationSize = 1)
public class Student extends BaseModel{
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private boolean studyFormat;
    private boolean payment = true;
    @ManyToOne
    private Group group;


    public Student(String firstName, String lastName, String phoneNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Student(String firstName, String lastName, String phoneNumber, String email, boolean studyFormat, Group group) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.studyFormat = studyFormat;
        this.group = group;
    }
}
