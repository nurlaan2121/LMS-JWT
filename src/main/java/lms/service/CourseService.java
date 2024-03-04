package lms.service;

import lms.dto.request.CourseReq;
import lms.dto.request.CourseReqWithComp;
import lms.dto.response.CompanyRes;
import lms.dto.response.CourseRes;
import lms.dto.response.CourseResWithAll;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CourseService {
    CourseRes save(CourseReq courseReq);

    List<CourseResWithAll> getAllCoursies();

    CourseResWithAll findById(Long id);

    String update(CourseReq courseReq, Long id);

    String remove(Long id);
    CourseRes save2(CourseReqWithComp courseReq, Long compId);

    List<CourseResWithAll> sortCourseByDate(String key);
}
