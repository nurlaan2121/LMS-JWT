package lms.dto.response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseResWithAll {
    private String courseName;
    private String description;
    private LocalDate dateOfStart;
    private long companyId;
    private long instructorId;
    private long countGroups;
    private long countLessons;

    public CourseResWithAll(String courseName, String description, LocalDate dateOfStart, Long companyId, Long instructorId, Long groupCount, Long lessonCount) {
        this.courseName = courseName;
        this.description = description;
        this.dateOfStart = dateOfStart;
        this.companyId = companyId;
        this.instructorId = instructorId;
        this.countGroups = groupCount;
        this.countLessons = lessonCount;
    }
}
