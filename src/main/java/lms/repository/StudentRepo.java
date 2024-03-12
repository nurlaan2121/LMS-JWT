package lms.repository;

import lms.dto.response.StudentResWithAll;
import lms.entities.BaseModel;
import lms.entities.Instructor;
import lms.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {
    @Query("select new lms.dto.response.StudentResWithAll(s.firstName,s.lastName,s.email,s.phoneNumber,s.studyFormat,gr.id) from Student s left join Group gr on s.group.id = gr.id")
    List<StudentResWithAll> myGetAll();

    @Query("select new lms.dto.response.StudentResWithAll(s.firstName,s.lastName,s.email,s.phoneNumber,s.studyFormat,s.payment,gr.id) from Student s left join Group gr on s.group.id = gr.id where s.id = :id")
    StudentResWithAll myFindById(Long id);

    @Query("SELECT s FROM Student s WHERE s.email = :email")
    Optional<Student> findStudentByEmail(String email);

    @Query("SELECT i FROM Instructor i WHERE i.email = :email")
    Optional<Instructor> findInstructorByEmail(String email);

    default Optional<? extends BaseModel> findByEmailForSec(String email) {
        Optional<Student> student = findStudentByEmail(email);
        if (student.isPresent()) {
            return student;
        }
        return findInstructorByEmail(email);
    }


    @Query("select s from Student s where  s.email = :email")
    Optional<Student> findByEmail(String email);

    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM Student s WHERE s.email = :email")
    Boolean existsByEmail(String email);

}
