package lms.dto.request;

import lms.dto.response.LessonResWithAll;
import lombok.Data;

@Data
public class LessonReq {
    private String LessonName;

    public LessonResWithAll build() {
        return new LessonResWithAll(this.LessonName);
    }
}
