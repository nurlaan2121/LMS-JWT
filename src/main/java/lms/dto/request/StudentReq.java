package lms.dto.request;

import lms.dto.response.StudentResWithAll;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentReq {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private int studyFormat;
    private String password;

    public StudentReq(String firstName, String lastName, String phoneNumber, String email, int studyFormat) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.studyFormat = studyFormat;
    }


    public StudentResWithAll build() {
        return new StudentResWithAll(this.firstName, this.lastName, this.phoneNumber, this.email, this.studyFormat == 0 ? "OnLine" : "Offline");
    }
}
