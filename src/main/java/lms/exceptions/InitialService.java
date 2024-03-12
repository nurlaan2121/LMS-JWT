package lms.exceptions;

import jakarta.annotation.PostConstruct;
import lms.entities.Instructor;
import lms.entities.Role;
import lms.entities.Student;
import lms.repository.InstructorRepo;
import lms.repository.StudentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InitialService {
    private final StudentRepo studentRepo;
    private final InstructorRepo instructorRepo;
    private final PasswordEncoder passwordEncoder;
    @PostConstruct
    private void saveData(){
        Student student = new Student();
        student.setEmail("admin@gmail.com");
        student.setRole(Role.ADMIN);
        student.setPassword(passwordEncoder.encode("java12"));
        Student cli = new Student();
        cli.setEmail("user@gmail.com");
        cli.setRole(Role.CLIENT);
        cli.setPassword(passwordEncoder.encode("java12"));
        Instructor instructor = new Instructor();
        instructor.setEmail("inst@gmail.com");
        instructor.setPassword(passwordEncoder.encode("java12"));
        instructor.setRole(Role.INSTRUCTOR);
        instructorRepo.save(instructor);
        studentRepo.save(student);
        studentRepo.save(cli);
    }
}
