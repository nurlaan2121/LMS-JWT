package lms.dto.request;

import lms.dto.response.TaskResWithAll;
import lombok.Data;

import java.time.LocalDate;
@Data
public class TaskReq {
    private String name;
    private String text;
    private LocalDate deadline;

    public TaskResWithAll build() {
       return new TaskResWithAll(this.getName(),this.getText(),this.getDeadline(),null);
    }
}
