package lms.dto.request;

import lms.dto.response.InstructorResWithAll;
import lms.entities.Instructor;
import lombok.Data;


@Data

public class InstructorReq {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String specialization;
    public Instructor build() {
       return new Instructor(this.firstName,this.lastName,this.phoneNumber,this.specialization);
    }

    public InstructorResWithAll convert() {
        return new InstructorResWithAll(this.firstName,this.lastName,this.phoneNumber,this.specialization);
    }
}
