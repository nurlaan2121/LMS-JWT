package lms.api;

import lms.dto.request.TaskReq;
import lms.dto.response.TaskResWithAll;
import lms.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/task/api")
@RequiredArgsConstructor
public class TaskApi {
    private final TaskService taskService;
    //CRUD
    @PostMapping("/save")
    public TaskResWithAll save(@RequestBody TaskReq taskReq) {
        return taskService.save(taskReq);
    }

    @GetMapping("/getAll")
    public List<TaskResWithAll> getAll() {
        List<TaskResWithAll> l = taskService.getAllTasks();
        System.out.println(l.size());
        return l;
    }

    @GetMapping("/findById/{id}")
    public TaskResWithAll getTaskById(@PathVariable Long id) {
        return taskService.findById(id);
    }


    @PutMapping("/update/{id}")
    public String update(@RequestBody TaskReq taskReq, @PathVariable Long id) {
        return taskService.update(taskReq, id);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        return taskService.remove(id);
    }
    //UNIQ
    @PostMapping("/save2/{lessonId}")
    public TaskResWithAll save2(@PathVariable Long lessonId,@RequestBody TaskReq taskReq) {
        return taskService.save2(taskReq,lessonId);
    }

}
