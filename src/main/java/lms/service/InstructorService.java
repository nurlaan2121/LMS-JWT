package lms.service;

import lms.dto.request.InstructorReq;
import lms.dto.request.SignIn;
import lms.dto.response.GroupResWithAll;
import lms.dto.response.InstructorForGet;
import lms.dto.response.InstructorResWithAll;
import lms.dto.response.SimpleRes;
import lms.entities.Instructor;

import java.util.List;

public interface InstructorService {
    InstructorResWithAll save(InstructorReq instructorReq);

    List<InstructorResWithAll> getAllGroups();

    InstructorResWithAll findById(Long id);

    String update(InstructorReq instructorReq, Long id);

    String remove(Long id);

    String assignToComp(Long instId, Long compId);

    Long getStudCount(Long instId);

    InstructorForGet getInst(Long instId);

    String assignToCourse(Long instId, Long courseId);

    SimpleRes signUp(InstructorReq instructorReq);

    Instructor signIn(SignIn signIn);
}
