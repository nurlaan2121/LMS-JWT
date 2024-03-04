package lms.service;

import lms.dto.request.TaskReq;
import lms.dto.response.TaskResWithAll;

import java.util.List;

public interface TaskService {
    TaskResWithAll save(TaskReq taskReq);

    List<TaskResWithAll> getAllTasks();

    TaskResWithAll findById(Long id);

    String update(TaskReq taskReq, Long id);

    String remove(Long id);

    TaskResWithAll save2(TaskReq taskReq, Long lessonId);
}
