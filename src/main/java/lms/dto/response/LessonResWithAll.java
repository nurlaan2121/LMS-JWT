package lms.dto.response;

import lombok.Data;

@Data
public class LessonResWithAll {
    private String LessonName;
    private Long courseId;
    private Long countTasks;

    public LessonResWithAll(String lessonName) {
        LessonName = lessonName;
    }

    public LessonResWithAll(String lessonName, Long courseId, Long countTasks) {
        LessonName = lessonName;
        this.courseId = courseId;
        this.countTasks = countTasks;
    }
}
