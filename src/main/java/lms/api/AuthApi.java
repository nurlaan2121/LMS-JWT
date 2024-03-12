package lms.api;
import lms.dto.request.InstructorReq;
import lms.dto.request.SignIn;
import lms.dto.request.StudentReq;
import lms.dto.response.SimpleRes;
import lms.entities.Instructor;
import lms.entities.Student;
import lms.service.InstructorService;
import lms.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/api")
@RequiredArgsConstructor
public class AuthApi {
    private final StudentService studentService;
    private final InstructorService instructorService;

    @PostMapping("/signUpStud")
    public SimpleRes signUpForStud(@RequestBody StudentReq studentReq) {
        return studentService.singUp(studentReq);
    }

    @PostMapping("/signUpInst")
    public SimpleRes singUpForInstructor(@RequestBody InstructorReq instructorReq) {
        return instructorService.signUp(instructorReq);
    }

    @GetMapping("/signIn")
    public SimpleRes signInForStud(@RequestBody SignIn signIn) {
        Student student = studentService.signIn(signIn);
        if (student == null) {
            Instructor instructor = instructorService.signIn(signIn);
            if (instructor == null) return new SimpleRes(HttpStatus.NOT_FOUND, "This info invalid");
            return new SimpleRes(HttpStatus.OK, "Success signIn");
        }
        return new SimpleRes(HttpStatus.OK, "Success signIn");
    }
}
