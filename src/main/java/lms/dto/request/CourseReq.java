package lms.dto.request;
import lms.dto.response.CourseRes;
import lombok.*;

import java.time.LocalDate;
@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseReq {
    private String courseName;
    private LocalDate dateOfStart;
    private String description;

    public CourseRes build(){
        CourseRes courseRes = new CourseRes();
        courseRes.setCourseName(this.getCourseName());
        courseRes.setDescription(this.getDescription());
        courseRes.setDateOfStart(this.getDateOfStart());
        return courseRes;
    }
}
