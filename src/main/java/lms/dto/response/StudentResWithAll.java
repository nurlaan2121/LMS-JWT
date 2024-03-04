package lms.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentResWithAll {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String studyFormat;
    private Long groupId;
    private String payment;

    public StudentResWithAll(String firstName, String lastName, String phoneNumber, String email, Long groupId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.groupId = groupId;
    }

    public StudentResWithAll(String firstName, String lastName, String phoneNumber, String email, String studyFormat) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.studyFormat = studyFormat;
    }

    public StudentResWithAll(String firstName, String lastName, String phoneNumber, String email, boolean studyFormat, Long groupId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.studyFormat = studyFormat ? "Online" : "Offline";
        this.groupId = groupId;
    }

    public StudentResWithAll(String firstName, String lastName, String phoneNumber, String email, boolean studyFormat, boolean payment, Long groupId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.studyFormat = studyFormat ? "Online" : "Offline";
        this.payment = payment ? "BLOCK" : "UNBLOCK";
        this.groupId = groupId;
    }
}
