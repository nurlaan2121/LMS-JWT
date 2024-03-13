package lms.repository;

import lms.dto.response.LessonResWithAll;
import lms.entities.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface LessonRepo extends JpaRepository<Lesson,Long> {
    @Query("select new lms.dto.response.LessonResWithAll(l.LessonName,l.course.id,count (distinct t)) from Lesson l left join Task t on l.id = t.lesson.id group by l.LessonName,l.course.id ")
    List<LessonResWithAll> myGetAll();

    @Query("select new lms.dto.response.LessonResWithAll(l.LessonName,l.course.id,count (distinct t)) from Lesson l left join Task t on l.id = t.lesson.id where l.id =:id group by l.LessonName,l.course.id")
    LessonResWithAll myFindById(Long id);
    @Query("select l from Lesson  l join Task t on l.id = t.lesson.id where t.id = :id")
    Lesson findByIdTaskId(Long id);
}
