package lms.api;

import lms.dto.request.CourseReq;
import lms.dto.request.CourseReqWithComp;
import lms.dto.response.CourseRes;
import lms.dto.response.CourseResWithAll;
import lms.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/course/api")
@RequiredArgsConstructor
public class CourseApi {
    private final CourseService courseService;

    //CRUD
    @Secured("ADMIN")

    @PostMapping("/save")
    public CourseRes save(@RequestBody CourseReq courseReq) {
        return courseService.save(courseReq);
    }
    @Secured("ADMIN")

    @GetMapping("/getAll")
    public List<CourseResWithAll> getAll() {
        List<CourseResWithAll> l = courseService.getAllCoursies();
        System.out.println(l.size());
        return l;
    }
    @Secured("ADMIN")

    @GetMapping("/findById/{id}")
    public CourseResWithAll getCompanyById(@PathVariable Long id) {
        return courseService.findById(id);
    }

    @Secured("ADMIN")

    @PutMapping("/update/{id}")
    public String update(@RequestBody CourseReq courseReq, @PathVariable Long id) {
        return courseService.update(courseReq, id);
    }
    @Secured("ADMIN")

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        return courseService.remove(id);
    }

    @Secured("ADMIN")

    //UNIQ
    @PostMapping("/save2/{compId}")
    public CourseRes save2(@PathVariable Long compId,@RequestBody CourseReqWithComp courseReq) {
        return courseService.save2(courseReq,compId);
    }
    @Secured("ADMIN")

    @GetMapping("/sortByDate")
    public List<CourseResWithAll> sortCourse(@RequestParam String key){
        return courseService.sortCourseByDate(key);
    }
}
