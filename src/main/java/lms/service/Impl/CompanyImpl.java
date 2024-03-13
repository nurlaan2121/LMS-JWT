package lms.service.Impl;

import jakarta.transaction.Transactional;
import lms.dto.request.CompanyReq;
import lms.dto.response.CompanyForGet;
import lms.dto.response.CompanyRes;
import lms.dto.response.StudentResWithAll;
import lms.entities.Company;
import lms.entities.Course;
import lms.entities.Group;
import lms.entities.Instructor;
import lms.exceptions.NotFound;
import lms.repository.*;
import lms.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.beans.Transient;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyImpl implements CompanyService {
    private final CompanyRepo companyRepo;
    private final InstructorRepo instructorRepo;
    private final StudentRepo studentRepo;
    private final GroupRepo groupRepo;
    private final LessonRepo lessonRepo;
    private final CourseRepo courseRepo;

    @Override
    public Company save(CompanyReq company) {
        Company companyOrg = new Company(company.getName(), company.getCountry(), company.getAddress(), company.getPhoneNumber());
        return companyRepo.save(companyOrg);
    }

    @Override
    public List<CompanyRes> getAllCompanies() {
        return companyRepo.myFindAll();
    }

    @Override
    public CompanyRes findById(Long id) {
        return companyRepo.myFindById(id);
    }

    @Override
    @Transactional
    public String update(CompanyReq company, Long id) {
        Company company1 = companyRepo.findById(id).orElseThrow(() -> new NotFound(id));
        company1.setPhoneNumber(company.getPhoneNumber());
        company1.setName(company.getName());
        company1.setCountry(company.getCountry());
        company1.setAddress(company.getAddress());
        return "Success";
    }

    //    @Override
//    @Transactional
//    public String remove(Long id) {
//        Company company = companyRepo.findById(id).orElseThrow(() -> new NotFound(id));
//        for (Course course : company.getCourses()) {
//            course.getGroups().clear(); // Clear groups to avoid orphan removal issues
//            courseRepo.delete(course);
//        }
//        for (Instructor instructor : company.getInstructors()) {
//            instructor.getCompanies().remove(company);
//        }
//        companyRepo.delete(company);
//        return "Success";
//    }
    @Override
    @Transactional
    public String remove(Long id) {

        Company company = companyRepo.findById(id).orElseThrow(() -> new NotFound(id));
        try {
            for (Instructor instructor : company.getInstructors()) {
                instructor.getCompanies().remove(company);
            }
            for (int e = 0; e < company.getCourses().size(); e++) {
                Course course = courseRepo.findById(company.getCourses().get(e).getId()).orElseThrow(() -> new NotFound(id));
                for (int w = 0; w < course.getGroups().size(); w++) {
                    Group group = groupRepo.findById(course.getGroups().get(w).getId()).get();
                    group.getCourses().remove(course);
                }
                for (int i = 0; i < course.getGroups().size(); i++) {
                    Group group = groupRepo.findById(course.getGroups().get(i).getId()).get();
                    for (int t = 0; t < group.getStudents().size(); t++) {
                        studentRepo.deleteById(group.getStudents().get(t).getId());
                    }
                }
                course.getLessons().clear();
                courseRepo.deleteById(course.getId());
            }
            companyRepo.delete(company);

            return "Success";
        } catch (Exception e) {
            // Rollback in case of any exception
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return "Error occurred during removal: " + e.getMessage();
        }

    }


    @Override
    public boolean checkUniqName(String name) {
        return companyRepo.checkUniqName(name);
    }

    @Override
    public CompanyForGet getInfo(Long compId) {
        Company company = companyRepo.findById(compId).orElseThrow(() -> new NotFound(compId));
        return new CompanyForGet(company.getName(), company.getCountry(), company.getAddress(), company.getPhoneNumber(), companyRepo.getCountStudents(company.getId()), companyRepo.getAllCoursesName(company.getId()), companyRepo.getAllGroupsName(company.getId()), companyRepo.getAllInstructorsName(company.getId()));
    }

    @Override
    public List<StudentResWithAll> groupByFormat(Long compId) {
        return companyRepo.groupBy(compId);
    }
}
