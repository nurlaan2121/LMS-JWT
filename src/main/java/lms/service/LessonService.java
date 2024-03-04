package lms.service;

import lms.dto.request.LessonReq;
import lms.dto.response.LessonResWithAll;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LessonService {
    LessonResWithAll save(LessonReq lessonReq);
    List<LessonResWithAll> getAllLessons();

    LessonResWithAll findById(Long id);

    String update(LessonReq lessonReq, Long id);

    String remove(Long id);

    LessonResWithAll save2(LessonReq lessonReq, Long courseId);

}
