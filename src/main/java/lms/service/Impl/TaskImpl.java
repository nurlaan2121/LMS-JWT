package lms.service.Impl;

import lms.dto.request.TaskReq;
import lms.dto.response.TaskResWithAll;
import lms.entities.Lesson;
import lms.entities.Task;
import lms.exceptions.NotFound;
import lms.repository.LessonRepo;
import lms.repository.TaskRepo;
import lms.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor

public class TaskImpl implements TaskService {
    private final TaskRepo taskRepo;
    private final LessonRepo lessonRepo;

    @Override
    public TaskResWithAll save(TaskReq taskReq) {
        taskRepo.save(new Task(taskReq.getName(), taskReq.getText(), taskReq.getDeadline()));
        return taskReq.build();
    }

    @Override
    public List<TaskResWithAll> getAllTasks() {
        return taskRepo.myGetAll();
    }

    @Override
    public TaskResWithAll findById(Long id) {
        TaskResWithAll taskResWithAll = taskRepo.myFindId(id);
        if (taskResWithAll == null) {
            throw new NotFound(id);
        }
        return taskResWithAll;
    }

    @Override
    @Transactional
    public String update(TaskReq taskReq, Long id) {
        Task task = taskRepo.findById(id).orElseThrow(() -> new NotFound(id));
        task.setName(taskReq.getName());
        task.setText(taskReq.getText());
        task.setDeadline(taskReq.getDeadline());
        return "Success";
    }

    @Override
    @Transactional
    public String remove(Long id) {
        Task task = taskRepo.findById(id).orElseThrow(() -> new NotFound(id));
        Lesson lesson = lessonRepo.findByIdTaskId(id);
        lesson.getTasks().remove(task);
        taskRepo.delete(task);
        return "Success";
    }

    @Override
    @Transactional
    public TaskResWithAll save2(TaskReq taskReq, Long lessonId) {
        Lesson lesson = lessonRepo.findById(lessonId).orElseThrow(() -> new NotFound(lessonId));
        Task task = new Task(taskReq.getName(), taskReq.getText(), taskReq.getDeadline());
        task.setLesson(lesson);
        taskRepo.save(task);
        lesson.getTasks().add(task);
        TaskResWithAll build = taskReq.build();
        build.setLessonId(lessonId);
        return build;
    }
}
