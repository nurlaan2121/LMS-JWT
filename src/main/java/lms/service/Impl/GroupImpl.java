package lms.service.Impl;

import lms.dto.request.GroupReq;
import lms.dto.request.GroupReqWithCourse;
import lms.dto.response.GroupRes;
import lms.dto.response.GroupResWithAll;
import lms.entities.Course;
import lms.entities.Group;
import lms.exceptions.NotFound;
import lms.repository.CourseRepo;
import lms.repository.GroupRepo;
import lms.repository.StudentRepo;
import lms.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor

public class GroupImpl implements GroupService {
    private final GroupRepo groupRepo;
    private final CourseRepo courseRepo;
    private final StudentRepo studentRepo;

    @Override
    public GroupRes save(GroupReq groupReq) {
        groupRepo.save(groupReq.build());
        return groupReq.convert();
    }

    @Override
    public List<GroupResWithAll> getAllGroups() {
        List<Group> all = groupRepo.findAll();
        List<GroupResWithAll> resWithAllList = new ArrayList<>();
        for (int i = 0; i < all.size(); i++) {
            GroupResWithAll group = new GroupResWithAll(all.get(i).getGroupName(), all.get(i).getImageLink(), all.get(i).getDescription());
            Long studentsCount = groupRepo.studentsCount(all.get(i).getId());
            group.setStudentsCount(studentsCount != null ? studentsCount : 0L);
            Long coursesCount = groupRepo.coursesCount(all.get(i).getId());
            group.setCoursesCount(coursesCount != null ? coursesCount : 0L);
            resWithAllList.add(group);
        }
        return resWithAllList;
    }

    @Override
    public GroupResWithAll findById(Long id) {
        Group group1 = groupRepo.findById(id).orElseThrow(() -> new NotFound(id));
        GroupResWithAll group = new GroupResWithAll(group1.getGroupName(), group1.getImageLink(), group1.getDescription());
        Long studentsCount = groupRepo.studentsCount(group1.getId());
        group.setStudentsCount(studentsCount != null ? studentsCount : 0L);
        Long coursesCount = groupRepo.coursesCount(group1.getId());
        group.setCoursesCount(coursesCount != null ? coursesCount : 0L);
        return group;
    }

    @Override
    @Transactional
    public String update(GroupReq groupReq, Long id) {
        Group group = groupRepo.findById(id).orElseThrow(() -> new NotFound(id));
        group.setGroupName(groupReq.getGroupName());
        group.setDescription(groupReq.getDescription());
        group.setImageLink(groupReq.getImageLink());
        return "Success";
    }

    @Override @Transactional
    public String remove(Long id) {
        Group group = groupRepo.findById(id).orElseThrow(() -> new NotFound(id));
        group.getCourses().clear();
        for (int i = 0; i < group.getStudents().size(); i++) {
            studentRepo.deleteById(group.getStudents().get(i).getId());
        }
        groupRepo.delete(group);
        return "Success";
    }

    @Override
    @Transactional
    public GroupRes save2(GroupReqWithCourse groupReqWithCourse) {
        GroupReq groupReq = new GroupReq(groupReqWithCourse.getGroupName(), groupReqWithCourse.getDescription(), groupReqWithCourse.getImageLink());
        String string = groupReqWithCourse.getString();
        String[] split = string.trim().split(",");
        Group group = groupReq.build();
        groupRepo.save(group);
        for (int i = 0; i < split.length; i++) {
            String trim = split[i].trim();
            Course course = courseRepo.findById(Long.valueOf(trim)).orElseThrow(() -> new NotFound(Long.valueOf(trim)));
            group.getCourses().add(course);
            course.getGroups().add(group);
        }
        return groupReq.convert();
    }

    @Override
    public Long getStudCunt(Long groupId) {
        return groupRepo.getStudCunt(groupId);
    }
}
