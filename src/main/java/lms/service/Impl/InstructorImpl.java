package lms.service.Impl;

import lms.dto.request.InstructorReq;
import lms.dto.response.GroupResWithAll;
import lms.dto.response.InstructorForGet;
import lms.dto.response.InstructorResWithAll;
import lms.entities.Company;
import lms.entities.Course;
import lms.entities.Instructor;
import lms.exceptions.NotFound;
import lms.repository.CompanyRepo;
import lms.repository.CourseRepo;
import lms.repository.InstructorRepo;
import lms.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor

public class InstructorImpl implements InstructorService {
    private final InstructorRepo instructorRepo;
    private final CompanyRepo companyRepo;
    private final CourseRepo courseRepo;

    @Override
    public InstructorResWithAll save(InstructorReq instructorReq) {
        instructorRepo.save(instructorReq.build());
        return instructorReq.convert();
    }

    @Override
    public List<InstructorResWithAll> getAllGroups() {
        List<InstructorResWithAll> withAllList = new ArrayList<>();
        List<Instructor> all = instructorRepo.findAll();
        for (int i = 0; i < all.size(); i++) {
            InstructorResWithAll instructorResWithAll = new InstructorResWithAll(all.get(i).getFirstName(), all.get(i).getLastName(), all.get(i).getPhoneNumber(), all.get(i).getSpecialization());
            Long countCompanies = instructorRepo.getCountCompany(all.get(i).getId());
            instructorResWithAll.setCountCompanies(countCompanies != null ? countCompanies : 0L);
            Long countCourse = instructorRepo.getCountCourses(all.get(i).getId());
            instructorResWithAll.setCountCourses(countCourse != null ? countCourse : 0L);
            withAllList.add(instructorResWithAll);
        }
        return withAllList;
    }

    @Override
    public InstructorResWithAll findById(Long id) {
        Instructor instructor = instructorRepo.findById(id).orElseThrow(() -> new NotFound(id));
        InstructorResWithAll instructorResWithAll = new InstructorResWithAll(instructor.getFirstName(), instructor.getLastName(), instructor.getPhoneNumber(), instructor.getSpecialization());
        Long countCompanies = instructorRepo.getCountCompany(instructor.getId());
        instructorResWithAll.setCountCompanies(countCompanies != null ? countCompanies : 0L);
        Long countCourse = instructorRepo.getCountCourses(instructor.getId());
        instructorResWithAll.setCountCourses(countCourse != null ? countCourse : 0L);
        return instructorResWithAll;
    }

    @Override
    @Transactional
    public String update(InstructorReq instructorReq, Long id) {
        Instructor instructor = instructorRepo.findById(id).orElseThrow(() -> new NotFound(id));
        instructor.setFirstName(instructorReq.getFirstName());
        instructor.setLastName(instructorReq.getLastName());
        instructor.setPhoneNumber(instructorReq.getPhoneNumber());
        instructor.setSpecialization(instructorReq.getSpecialization());
        return "Success";

    }

    @Override
    public String remove(Long id) {
        Instructor instructor = instructorRepo.findById(id).orElseThrow(() -> new NotFound(id));
        instructorRepo.delete(instructor);
        return "Success";
    }

    @Override
    @Transactional
    public String assignToComp(Long instId, Long compId) {
        Company company = companyRepo.findById(compId).orElseThrow(() -> new NotFound(compId));
        Instructor instructor = instructorRepo.findById(instId).orElseThrow(() -> new NotFound(instId));
        company.getInstructors().add(instructor);
        instructor.getCompanies().add(company);
        return "Success";
    }

    @Override
    public Long getStudCount(Long instId) {
        return instructorRepo.getStudCount(instId);
    }

    @Override
    public InstructorForGet getInst(Long instId) {
        Instructor instructor = instructorRepo.findById(instId).orElseThrow(() -> new NotFound(instId));
        return new InstructorForGet(instructor.getFirstName(), instructor.getLastName(), instructor.getPhoneNumber(), instructor.getSpecialization(), instructorRepo.getStudCount(instId), instructorRepo.getGroupName(instId));
    }

    @Override @Transactional
    public String assignToCourse(Long instId, Long courseId) {
        Course course = courseRepo.findById(courseId).orElseThrow(() -> new NotFound(courseId));
        Instructor instructor = instructorRepo.findById(instId).orElseThrow(() -> new NotFound(instId));
        course.setInstructor(instructor);
        instructor.getCourses().add(course);
        return "Success";
    }
}
