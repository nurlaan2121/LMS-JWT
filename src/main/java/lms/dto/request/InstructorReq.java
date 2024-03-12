package lms.dto.request;

import lms.dto.response.InstructorResWithAll;
import lms.entities.Instructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class InstructorReq {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String specialization;

    public InstructorReq(String firstName, String lastName, String phoneNumber, String specialization) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.specialization = specialization;
    }

    private String email;
    private String password;
    public Instructor build() {
       return new Instructor(this.firstName,this.lastName,this.phoneNumber,this.specialization);
    }

    public InstructorResWithAll convert() {
        return new InstructorResWithAll(this.firstName,this.lastName,this.phoneNumber,this.specialization);
    }
}
