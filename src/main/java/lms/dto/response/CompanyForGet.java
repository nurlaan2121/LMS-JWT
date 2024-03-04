package lms.dto.response;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class CompanyForGet {
        private String name;
        private String country;
        private String address;
        private String phoneNumber;
        private Long studentsCount;
        private List<String> coursesName = new ArrayList<>();
        private List<String> groupsName = new ArrayList<>();
        private List<String> instructorsName = new ArrayList<>();

}
