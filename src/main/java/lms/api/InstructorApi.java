package lms.api;
import lms.dto.request.InstructorReq;

import lms.dto.response.InstructorForGet;
import lms.dto.response.InstructorResWithAll;
import lms.service.InstructorService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inst/api")
@RequiredArgsConstructor
public class InstructorApi {
    private final InstructorService instructorService;
    //CRUD
    @Secured("ADMIN")
    @PostMapping("/save")
    public InstructorResWithAll save(@RequestBody InstructorReq instructorReq) {
        return instructorService.save(instructorReq);
    }
    @Secured("ADMIN")
    @GetMapping("/getAll")
    public List<InstructorResWithAll> getAll() {
        List<InstructorResWithAll> l = instructorService.getAllGroups();
        System.out.println(l.size());
        return l;
    }
    @Secured("ADMIN")
    @GetMapping("/findById/{id}")
    public InstructorResWithAll getCompanyById(@PathVariable Long id) {
        return instructorService.findById(id);
    }
    @Secured("ADMIN")
    @PutMapping("/update/{id}")
    public String update(@RequestBody InstructorReq instructorReq, @PathVariable Long id) {
        return instructorService.update(instructorReq, id);
    }
    @Secured("ADMIN")
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        return instructorService.remove(id);
    }
    //UNIQ
    @Secured("ADMIN")

    @PutMapping("/assignToComp/{instId}/{compId}")
    public String assignToComp(@PathVariable Long instId,@PathVariable Long compId){
        return instructorService.assignToComp(instId,compId);
    }
    @Secured("ADMIN")

    @GetMapping("/getStudentsCount/{instId}")
    public Long getStudCount(@PathVariable Long instId){
        return instructorService.getStudCount(instId);
    }
    @Secured("ADMIN")

    @GetMapping("/getInstInfo/{instId}")
    public InstructorForGet getInst(@PathVariable Long instId){
        return instructorService.getInst(instId);
    }
    @Secured("ADMIN")
    @PutMapping("/assignToCourse/{instId}/{courseId}")
    public String assignToCourse (@PathVariable Long instId,@PathVariable Long courseId){
        return instructorService.assignToCourse(instId,courseId);
    }
}
