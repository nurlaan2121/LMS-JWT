package lms.api;

import lms.dto.request.InstructorReq;
import lms.dto.request.LessonReq;
import lms.dto.response.InstructorResWithAll;
import lms.dto.response.LessonResWithAll;
import lms.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lesson/api")
@RequiredArgsConstructor
public class LessonApi {
    private final LessonService lessonService;
    //CRUD
    @PostMapping("/save")
    public LessonResWithAll save(@RequestBody LessonReq lessonReq) {
        return lessonService.save(lessonReq);
    }

    @GetMapping("/getAll")
    public List<LessonResWithAll> getAll() {
        List<LessonResWithAll> l = lessonService.getAllLessons();
        System.out.println(l.size());
        return l;
    }

    @GetMapping("/findById/{id}")
    public LessonResWithAll getCompanyById(@PathVariable Long id) {
        return lessonService.findById(id);
    }


    @PutMapping("/update/{id}")
    public String update(@RequestBody LessonReq lessonReq, @PathVariable Long id) {
        return lessonService.update(lessonReq, id);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        return lessonService.remove(id);
    }
    //UNIQ
    @PostMapping("/save2/{courseId}")
    public LessonResWithAll save(@PathVariable Long courseId,@RequestBody LessonReq lessonReq) {
        return lessonService.save2(lessonReq,courseId);
    }


}
