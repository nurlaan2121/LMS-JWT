package lms.repository;
import lms.dto.response.TaskResWithAll;
import lms.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepo extends JpaRepository<Task,Long> {
    @Query("select new lms.dto.response.TaskResWithAll(t.name,t.text,t.deadline,l.id) from Task t left join Lesson l on t.lesson.id = l.id")
    List<TaskResWithAll> myGetAll();
    @Query("select new lms.dto.response.TaskResWithAll(t.name,t.text,t.deadline,l.id) from Task t left join Lesson l on t.lesson.id = l.id  where t.id = :id")
    TaskResWithAll myFindId(Long id);
}
