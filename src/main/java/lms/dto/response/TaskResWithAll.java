package lms.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data

public class TaskResWithAll {
    private String name;
    private String text;
    private LocalDate deadline;
    private Long lessonId;

    public TaskResWithAll(String name, String text, LocalDate deadline, Long lessonId) {
        this.name = name;
        this.text = text;
        this.deadline = deadline;
        this.lessonId = lessonId;
    }
}
