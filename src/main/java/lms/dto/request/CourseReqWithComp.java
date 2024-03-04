package lms.dto.request;

import lms.dto.response.CourseRes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CourseReqWithComp {
    private String courseName;
    private LocalDate dateOfStart;
    private String description;
    private Long companyId;

    public CourseRes build(){
        CourseRes courseRes = new CourseRes();
        courseRes.setCourseName(this.getCourseName());
        courseRes.setDescription(this.getDescription());
        courseRes.setDateOfStart(this.getDateOfStart());
        courseRes.setCompanyId(this.companyId);
        return courseRes;
    }
}
