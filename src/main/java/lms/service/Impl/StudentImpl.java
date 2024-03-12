package lms.service.Impl;

import lms.dto.request.SignIn;
import lms.dto.request.StudentReq;
import lms.dto.response.LessonResWithAll;
import lms.dto.response.SimpleRes;
import lms.dto.response.StudentResWithAll;
import lms.entities.Group;
import lms.entities.Role;
import lms.entities.Student;
import lms.exceptions.NotFound;
import lms.repository.GroupRepo;
import lms.repository.InstructorRepo;
import lms.repository.StudentRepo;
import lms.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.AttributedString;
import java.util.List;

@Service
@RequiredArgsConstructor

public class StudentImpl implements StudentService {
    private final StudentRepo studentRepo;
    private final GroupRepo groupRepo;
    private final InstructorRepo instructorRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public StudentResWithAll save(StudentReq studentReq) {
        Student student = new Student(studentReq.getFirstName(), studentReq.getLastName(), studentReq.getPhoneNumber(), studentReq.getEmail());
        student.setStudyFormat(studentReq.getStudyFormat() == 0);
        studentRepo.save(student);
        return studentReq.build();
    }

    @Override
    public List<StudentResWithAll> getAllLessons() {
        return studentRepo.myGetAll();
    }

    @Override
    public StudentResWithAll findById(Long id) {
        StudentResWithAll studentResWithAll = studentRepo.myFindById(id);
        if (studentResWithAll == null) {
            throw new NotFound(id);
        }
        return studentResWithAll;
    }

    @Override
    @Transactional
    public String update(StudentReq studentReq, Long id) {
        Student student = studentRepo.findById(id).orElseThrow(() -> new NotFound(id));
        student.setFirstName(studentReq.getFirstName());
        student.setLastName(studentReq.getLastName());
        student.setEmail(studentReq.getEmail());
        student.setPhoneNumber(studentReq.getPhoneNumber());
        student.setStudyFormat(studentReq.getStudyFormat() == 0);
        return "Success";
    }

    @Override
    public String remove(Long id) {
        Student student = studentRepo.findById(id).orElseThrow(() -> new NotFound(id));
        studentRepo.delete(student);
        return "Success";
    }

    @Override
    @Transactional
    public String save2(Long groupId, StudentReq studentReq) {
        Group group = groupRepo.findById(groupId).orElseThrow(() -> new NotFound(groupId));
        Student student = new Student(studentReq.getFirstName(), studentReq.getLastName(), studentReq.getPhoneNumber(), studentReq.getEmail());
        student.setStudyFormat(studentReq.getStudyFormat() == 0);
        student.setGroup(group);
        studentRepo.save(student);
        group.getStudents().add(student);
        return "Success";
    }

    @Override
    @Transactional
    public String block(Long studId) {
        Student student = studentRepo.findById(studId).orElseThrow(() -> new NotFound(studId));
        if (student.isPayment()) {
            student.setPayment(false);
            return "Success";
        } else {
            student.setPayment(true);
            return "Success";
        }

    }

    @Override
    public SimpleRes singUp(StudentReq studentReq) {
        Boolean bb = studentRepo.existsByEmail(studentReq.getEmail());
        if (bb) {
           return new SimpleRes(HttpStatus.ALREADY_REPORTED,"This email already exists : " + studentReq.getEmail());
        }
        if(instructorRepo.existsByEmail(studentReq.getEmail())) return new SimpleRes(HttpStatus.ALREADY_REPORTED,"This email already exists: " + studentReq.getEmail());
        Student student = new Student(studentReq.getFirstName(), studentReq.getLastName(), studentReq.getPhoneNumber(), studentReq.getEmail(), passwordEncoder.encode(studentReq.getPassword()));
        student.setStudyFormat(studentReq.getStudyFormat() == 0);
        student.setRole(Role.CLIENT);
        studentRepo.save(student);
        return new SimpleRes(HttpStatus.OK, "Success sing up");
    }

    @Override
    public Student signIn(SignIn signIn) {
        Student student = studentRepo.findByEmail(signIn.getEmail()).orElse(null);
        if (student!=null) if (passwordEncoder.matches(signIn.getPassword(), student.getPassword())) return student;
        return null;
    }
}
