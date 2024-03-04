package lms.api;
import lms.dto.request.InstructorReq;

import lms.dto.response.InstructorForGet;
import lms.dto.response.InstructorResWithAll;
import lms.service.InstructorService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inst/api")
@RequiredArgsConstructor
public class InstructorApi {
    private final InstructorService instructorService;
    //CRUD
    @PostMapping("/save")
    public InstructorResWithAll save(@RequestBody InstructorReq instructorReq) {
        return instructorService.save(instructorReq);
    }

    @GetMapping("/getAll")
    public List<InstructorResWithAll> getAll() {
        List<InstructorResWithAll> l = instructorService.getAllGroups();
        System.out.println(l.size());
        return l;
    }

    @GetMapping("/findById/{id}")
    public InstructorResWithAll getCompanyById(@PathVariable Long id) {
        return instructorService.findById(id);
    }


    @PutMapping("/update/{id}")
    public String update(@RequestBody InstructorReq instructorReq, @PathVariable Long id) {
        return instructorService.update(instructorReq, id);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        return instructorService.remove(id);
    }
    //UNIQ
    @PutMapping("/assignToComp/{instId}/{compId}")
    public String assignToComp(@PathVariable Long instId,@PathVariable Long compId){
        return instructorService.assignToComp(instId,compId);
    }
    @GetMapping("/getStudentsCount/{instId}")
    public Long getStudCount(@PathVariable Long instId){
        return instructorService.getStudCount(instId);
    }
    @GetMapping("/getInstInfo/{instId}")
    public InstructorForGet getInst(@PathVariable Long instId){
        return instructorService.getInst(instId);
    }
    @PutMapping("/assignToCourse/{instId}/{courseId}")
    public String assignToCourse (@PathVariable Long instId,@PathVariable Long courseId){
        return instructorService.assignToCourse(instId,courseId);
    }
}
