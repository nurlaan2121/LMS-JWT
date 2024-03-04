package lms.service;

import lms.dto.request.StudentReq;
import lms.dto.response.LessonResWithAll;
import lms.dto.response.StudentResWithAll;

import java.util.List;

public interface StudentService{
    StudentResWithAll save(StudentReq studentReq);

    List<StudentResWithAll> getAllLessons();

    StudentResWithAll findById(Long id);

    String update(StudentReq studentReq, Long id);

    String remove(Long id);

    String save2(Long groupId, StudentReq studentReq);

    String block(Long studId);
}
