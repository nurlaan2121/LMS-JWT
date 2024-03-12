package lms.service.Impl;

import lms.dto.request.LessonReq;
import lms.dto.response.LessonResWithAll;
import lms.entities.Course;
import lms.entities.Lesson;
import lms.exceptions.NotFound;
import lms.repository.CourseRepo;
import lms.repository.LessonRepo;
import lms.repository.StudentRepo;
import lms.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonImpl implements LessonService {
    private final LessonRepo lessonRepo;
    private final CourseRepo courseRepo;

    @Override
    public LessonResWithAll save(LessonReq lessonReq) {
        lessonRepo.save(new Lesson(lessonReq.getLessonName()));
        return lessonReq.build();
    }

    @Override
    public List<LessonResWithAll> getAllLessons() {
        return lessonRepo.myGetAll();
    }

    @Override
    public LessonResWithAll findById(Long id) {
        LessonResWithAll lessonResWithAll = lessonRepo.myFindById(id);
        if (lessonResWithAll != null) {
            return lessonResWithAll;
        }
        throw new NotFound(id);
    }

    @Override
    @Transactional
    public String update(LessonReq lessonReq, Long id) {
        Lesson lesson = lessonRepo.findById(id).orElseThrow(() -> new NotFound(id));
        lesson.setLessonName(lessonReq.getLessonName());
        return "Success";
    }

    @Override
    public String remove(Long id) {
        Lesson lesson = lessonRepo.findById(id).orElseThrow(() -> new NotFound(id));
        lessonRepo.delete(lesson);
        return "Success";
    }

    @Override
    @Transactional
    public LessonResWithAll save2(LessonReq lessonReq, Long courseId) {
        Course course = courseRepo.findById(courseId).orElseThrow(() -> new NotFound(courseId));
        Lesson lesson = new Lesson(lessonReq.getLessonName());
        lesson.setCourse(course);
        lessonRepo.save(lesson);
        course.getLessons().add(lesson);
        LessonResWithAll build = lessonReq.build();
        build.setCourseId(courseId);
        return build;
    }
}
