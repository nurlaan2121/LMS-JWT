package lms.repository;

import lms.dto.response.CourseRes;
import lms.dto.response.CourseResWithAll;
import lms.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface CourseRepo extends JpaRepository<Course, Long> {

    @Query("select co.id from Course c left join Company co on c.company.id = co.id where c.id = :id")
    Long getCompanyId(Long id);

    @Query("select i.id from Course c left join Instructor i on c.instructor.id = i.id where c.id = :id")
    Long getInstructorId(Long id);
    @Query("SELECT COUNT(DISTINCT g) FROM Course c JOIN c.groups g WHERE c.id = :id")
    Long getCountGroup(Long id);
    @Query("select count (distinct l) from Course c join Lesson l on c.id = l.course.id where c.id = :id")
    Long getCountLesson(Long id);
}
