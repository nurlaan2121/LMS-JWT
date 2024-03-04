package lms.api;

import lms.dto.request.LessonReq;
import lms.dto.request.StudentReq;
import lms.dto.response.LessonResWithAll;
import lms.dto.response.StudentResWithAll;
import lms.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student/api")
@RequiredArgsConstructor
public class StudentApi {
    private final StudentService studentService;

    //CRUD
    @PostMapping("/save")
    public StudentResWithAll save(@RequestBody StudentReq studentReq) {
        return studentService.save(studentReq);
    }

    @GetMapping("/getAll")
    public List<StudentResWithAll> getAll() {
        List<StudentResWithAll> l = studentService.getAllLessons();
        System.out.println(l.size());
        return l;
    }

    @GetMapping("/findById/{id}")
    public StudentResWithAll getCompanyById(@PathVariable Long id) {
        return studentService.findById(id);
    }


    @PutMapping("/update/{id}")
    public String update(@RequestBody StudentReq studentReq, @PathVariable Long id) {
        return studentService.update(studentReq, id);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        return studentService.remove(id);
    }

    @PostMapping("/save2/{groupId}")
    public String save2(@PathVariable Long groupId, @RequestBody StudentReq studentReq) {
        return studentService.save2(groupId, studentReq);
    }
    //UNIQ
    @PutMapping("/block/{studId}")
    public String block(@PathVariable Long studId){
        return studentService.block(studId);
    }
}
