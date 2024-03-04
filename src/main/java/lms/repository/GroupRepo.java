package lms.repository;

import lms.entities.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository

public interface GroupRepo extends JpaRepository<Group, Long> {
    @Query("select count (distinct s) from Group g left join Student s on  g.id = s.group.id where g.id = :id")
    Long studentsCount(Long id);

    //    @Query("select count (*) from Group g left join Course"
    @Query("SELECT COUNT(DISTINCT c) FROM Group g JOIN g.courses c WHERE g.id = :id")
    Long coursesCount(Long id);
    @Query("select count (*) from Group g join Student s on g.id = s.group.id where g.id = :groupId")
    Long getStudCunt(Long groupId);
}
