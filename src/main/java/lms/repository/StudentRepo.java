package lms.repository;

import lms.dto.response.LessonResWithAll;
import lms.dto.response.StudentResWithAll;
import lms.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface StudentRepo extends JpaRepository<Student, Long> {
    @Query("select new lms.dto.response.StudentResWithAll(s.firstName,s.lastName,s.email,s.phoneNumber,s.studyFormat,gr.id) from Student s left join Group gr on s.group.id = gr.id")
    List<StudentResWithAll> myGetAll();

    @Query("select new lms.dto.response.StudentResWithAll(s.firstName,s.lastName,s.email,s.phoneNumber,s.studyFormat,s.payment,gr.id) from Student s left join Group gr on s.group.id = gr.id where s.id = :id")
    StudentResWithAll myFindById(Long id);
}
