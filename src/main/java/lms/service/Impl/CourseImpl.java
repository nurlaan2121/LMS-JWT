package lms.service.Impl;

import jakarta.transaction.Transactional;
import lms.dto.request.CourseReq;
import lms.dto.request.CourseReqWithComp;
import lms.dto.response.CompanyRes;
import lms.dto.response.CourseRes;
import lms.dto.response.CourseResWithAll;
import lms.entities.Company;
import lms.entities.Course;
import lms.entities.Group;
import lms.exceptions.NotFound;
import lms.repository.CompanyRepo;
import lms.repository.CourseRepo;
import lms.repository.GroupRepo;
import lms.repository.StudentRepo;
import lms.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor

public class CourseImpl implements CourseService {
    private final CourseRepo courseRepo;
    private final CompanyRepo companyRepo;
    private final GroupRepo groupRepo;
    private final StudentRepo studentRepo;

    @Override
    public CourseRes save(CourseReq courseReq) {
        Course course = new Course(courseReq.getCourseName(), courseReq.getDateOfStart(), courseReq.getDescription());
        courseRepo.save(course);
        return courseReq.build();
    }

    @Override
    @Transactional
    public List<CourseResWithAll> getAllCoursies() {
        List<CourseResWithAll> courseRes = new ArrayList<>();
        List<Course> all = courseRepo.findAll();
        for (int i = 0; i < all.size(); i++) {
            CourseResWithAll courseResWithAll = new CourseResWithAll();
            courseResWithAll.setCourseName(all.get(i).getCourseName());
            courseResWithAll.setDescription(all.get(i).getDescription());
            courseResWithAll.setDateOfStart(all.get(i).getDateOfStart());

            Long companyId = courseRepo.getCompanyId(all.get(i).getId());
            courseResWithAll.setCompanyId(companyId != null ? companyId : 0L);
            Long instructorId = courseRepo.getInstructorId(all.get(i).getId());
            courseResWithAll.setInstructorId(instructorId != null ? instructorId : 0L);
            Long countGroup = courseRepo.getCountGroup(all.get(i).getId());
            courseResWithAll.setCountGroups(countGroup != null ? countGroup : 0L);
            Long countLesson = courseRepo.getCountLesson(all.get(i).getId());
            courseResWithAll.setCountLessons(countLesson != null ? countLesson : 0L);
            courseRes.add(courseResWithAll);
        }
        return courseRes;
    }

    @Override
    public CourseResWithAll findById(Long id) {
        Course course = courseRepo.findById(id).orElseThrow(() -> new NotFound(id));

        CourseResWithAll courseResWithAll = new CourseResWithAll();
        courseResWithAll.setCourseName(course.getCourseName());
        courseResWithAll.setDescription(course.getDescription());
        courseResWithAll.setDateOfStart(course.getDateOfStart());

        Long companyId = courseRepo.getCompanyId(course.getId());
        courseResWithAll.setCompanyId(companyId != null ? companyId : 0L);
        Long instructorId = courseRepo.getInstructorId(course.getId());
        courseResWithAll.setInstructorId(instructorId != null ? instructorId : 0L);
        Long countGroup = courseRepo.getCountGroup(course.getId());
        courseResWithAll.setCountGroups(countGroup != null ? countGroup : 0L);
        Long countLesson = courseRepo.getCountLesson(course.getId());
        courseResWithAll.setCountLessons(countLesson != null ? countLesson : 0L);
        return courseResWithAll;

    }

    @Override
    @Transactional
    public String update(CourseReq courseReq, Long id) {
        Course course = courseRepo.findById(id).orElseThrow(() -> new NotFound(id));
        course.setCourseName(courseReq.getCourseName());
        course.setDescription(courseReq.getDescription());
        course.setDateOfStart(courseReq.getDateOfStart());
        return "Success";
    }

    @Override
    @Transactional
    public String remove(Long id) {
        Course course = courseRepo.findById(id).orElseThrow(() -> new NotFound(id));
        for (int i = 0; i < course.getGroups().size(); i++) {
            Group group = groupRepo.findById(course.getGroups().get(i).getId()).get();
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
        return "Success";
    }

    @Override
    @org.springframework.transaction.annotation.Transactional
    public CourseRes save2(CourseReqWithComp courseReq, Long compId) {
        Company company = companyRepo.findById(compId).orElseThrow(() -> new NotFound(compId));
        Course course = new Course(courseReq.getCourseName(), courseReq.getDateOfStart(), courseReq.getDescription());
        course.setCompany(company);
        courseRepo.save(course);
        company.getCourses().add(course);
        return courseReq.build();
    }

    @Override
    public List<CourseResWithAll> sortCourseByDate(String key) {
        if (key.equalsIgnoreCase("asc")) {
            List<CourseResWithAll> courseRes = new ArrayList<>();
            List<Course> all = courseRepo.findAll();
            for (int i = 0; i < all.size(); i++) {
                CourseResWithAll courseResWithAll = new CourseResWithAll();
                courseResWithAll.setCourseName(all.get(i).getCourseName());
                courseResWithAll.setDescription(all.get(i).getDescription());
                courseResWithAll.setDateOfStart(all.get(i).getDateOfStart());

                Long companyId = courseRepo.getCompanyId(all.get(i).getId());
                courseResWithAll.setCompanyId(companyId != null ? companyId : 0L);
                Long instructorId = courseRepo.getInstructorId(all.get(i).getId());
                courseResWithAll.setInstructorId(instructorId != null ? instructorId : 0L);
                Long countGroup = courseRepo.getCountGroup(all.get(i).getId());
                courseResWithAll.setCountGroups(countGroup != null ? countGroup : 0L);
                Long countLesson = courseRepo.getCountLesson(all.get(i).getId());
                courseResWithAll.setCountLessons(countLesson != null ? countLesson : 0L);
                courseRes.add(courseResWithAll);
            }
            courseRes.sort(courseResWithAllComparator);
            return courseRes;
        } else if (key.equalsIgnoreCase("desc")) {
            List<CourseResWithAll> courseRes = new ArrayList<>();
            List<Course> all = courseRepo.findAll();
            for (int i = 0; i < all.size(); i++) {
                CourseResWithAll courseResWithAll = new CourseResWithAll();
                courseResWithAll.setCourseName(all.get(i).getCourseName());
                courseResWithAll.setDescription(all.get(i).getDescription());
                courseResWithAll.setDateOfStart(all.get(i).getDateOfStart());

                Long companyId = courseRepo.getCompanyId(all.get(i).getId());
                courseResWithAll.setCompanyId(companyId != null ? companyId : 0L);
                Long instructorId = courseRepo.getInstructorId(all.get(i).getId());
                courseResWithAll.setInstructorId(instructorId != null ? instructorId : 0L);
                Long countGroup = courseRepo.getCountGroup(all.get(i).getId());
                courseResWithAll.setCountGroups(countGroup != null ? countGroup : 0L);
                Long countLesson = courseRepo.getCountLesson(all.get(i).getId());
                courseResWithAll.setCountLessons(countLesson != null ? countLesson : 0L);
                courseRes.add(courseResWithAll);
            }
            courseRes.sort(courseResWithAllComparator.reversed());
            return courseRes;
        }
        return Collections.emptyList();
    }

    Comparator<CourseResWithAll> courseResWithAllComparator = new Comparator<CourseResWithAll>() {
        @Override
        public int compare(CourseResWithAll o1, CourseResWithAll o2) {
            return o1.getDateOfStart().compareTo(o2.getDateOfStart());
        }
    };
}
