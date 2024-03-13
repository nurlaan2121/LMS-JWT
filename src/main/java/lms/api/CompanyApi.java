package lms.api;
import lms.dto.request.CompanyReq;
import lms.dto.response.CompanyForGet;
import lms.dto.response.CompanyRes;
import lms.dto.response.StudentResWithAll;
import lms.entities.Company;
import lms.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/company/api")
@RequiredArgsConstructor
public class CompanyApi {
    private final CompanyService companyService;
    private static final Logger log = LoggerFactory.getLogger(CompanyApi.class);


    //CRUD
    @Secured("ADMIN")
    @PostMapping("/save")
    public CompanyRes save(@RequestBody CompanyReq company) {
        if (!companyService.checkUniqName(company.getName())) {
            Company save = companyService.save(company);
            return new CompanyRes(save.getName(), save.getCountry(), save.getAddress(), save.getPhoneNumber(), save.getCourses().size(), save.getInstructors().size());
        }else {
            return new CompanyRes("This name already exists:  " +  company.getName());
        }
    }
    @Secured({"ADMIN","INSTRUCTOR"})
    @GetMapping("/getAll")
    public List<CompanyRes> getAll() {
        return companyService.getAllCompanies();
    }
    @Secured("ADMIN")
    @GetMapping("/findById/{id}")
    public CompanyRes getCompanyById(@PathVariable Long id) {
        return companyService.findById(id);
    }

    @Secured("ADMIN")
    @PutMapping("/update/{id}")
    public String update(@RequestBody CompanyReq company, @PathVariable Long id) {
        return companyService.update(company, id);
    }
    @Secured("ADMIN")
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        return companyService.remove(id);
    }
    //UNIQ
    @Secured("ADMIN")
    @GetMapping("/getInfo/{compId}")
    public CompanyForGet getInfo(@PathVariable Long compId){
       return companyService.getInfo(compId);
    }
    @Secured("ADMIN")
    @GetMapping("/groupByFormat/{compId}")
    public List<StudentResWithAll> groupBy(@PathVariable Long compId){
        return companyService.groupByFormat(compId);
    }
}
