package lms.dto.response;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class InstructorForGet {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String specialization;
    Long studentsCount;
    List<String> groupName = new ArrayList<>();

    public InstructorForGet(String firstName, String lastName, String phoneNumber, String specialization) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.specialization = specialization;
    }
}
