package lms.api;

import lms.dto.request.GroupReq;
import lms.dto.request.GroupReqWithCourse;
import lms.dto.response.GroupRes;
import lms.dto.response.GroupResWithAll;
import lms.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/group/api")
@RequiredArgsConstructor
public class GroupApi {
    private final GroupService groupService;

    //CRUD
    @PostMapping("/save")
    public GroupRes save(@RequestBody GroupReq groupReq) {
        return groupService.save(groupReq);
    }

    @GetMapping("/getAll")
    public List<GroupResWithAll> getAll() {
        List<GroupResWithAll> l = groupService.getAllGroups();
        System.out.println(l.size());
        return l;
    }

    @GetMapping("/findById/{id}")
    public GroupResWithAll getCompanyById(@PathVariable Long id) {
        return groupService.findById(id);
    }


    @PutMapping("/update/{id}")
    public String update(@RequestBody GroupReq groupReq, @PathVariable Long id) {
        return groupService.update(groupReq, id);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        return groupService.remove(id);
    }

    @PostMapping("/save2")
    public GroupRes save2(@RequestBody GroupReqWithCourse groupReqWithCourse) {
        return groupService.save2(groupReqWithCourse);
    }
    @GetMapping("/getStudentsCount/{groupId}")
    public Long getStudCunt(@PathVariable Long groupId){
        return groupService.getStudCunt(groupId);
    }

}
