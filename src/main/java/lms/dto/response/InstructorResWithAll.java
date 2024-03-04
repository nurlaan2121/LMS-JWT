package lms.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class InstructorResWithAll {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String specialization;
    private Long countCourses;
    private Long countCompanies;

    public InstructorResWithAll(String firstName, String lastName, String phoneNumber, String specialization) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.specialization = specialization;
    }
}
