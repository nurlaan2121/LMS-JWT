package lms.repository;

import lms.entities.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface InstructorRepo extends JpaRepository<Instructor, Long> {
    @Query("SELECT COUNT(DISTINCT c) FROM Instructor i JOIN i.companies c WHERE i.id = :id")
    Long getCountCompany(Long id);

    @Query("SELECT COUNT(DISTINCT c) FROM Instructor i JOIN i.courses c WHERE i.id = :id")
    Long getCountCourses(Long id);

    @Query("select count(*) from Instructor i join Course crs on crs.instructor.id = i.id join crs.groups crsgr join Student s on crsgr.id = s.group.id where i.id = :instId")
    Long getStudCount(Long instId);
//    @Query("select crsgr.groupName from Instructor i join Course crs on crs.instructor.id = i.id join crs.groups crsgr where i.id  = :instId")
//    List<String> getGroupName(Long instId);
    @Query("SELECT crsgr.groupName FROM Instructor i JOIN Course crs ON crs.instructor.id = i.id JOIN crs.groups crsgr WHERE i.id = :instId")
    List<String> getGroupName(Long instId);

}
