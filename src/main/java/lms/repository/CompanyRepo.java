package lms.repository;

import lms.dto.response.CompanyRes;
import lms.dto.response.StudentResWithAll;
import lms.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepo extends JpaRepository<Company, Long> {
    @Query("SELECT NEW lms.dto.response.CompanyRes(" +
            "c.name, c.country, c.address, c.phoneNumber,COUNT(DISTINCT cr), COUNT(DISTINCT i)) " +
            "FROM Company c " +
            "LEFT JOIN c.courses cr " +
            "LEFT JOIN c.instructors i " +
            "WHERE c.id = :companyId " +
            "GROUP BY c.name, c.country, c.address, c.phoneNumber")
    CompanyRes myFindById(Long companyId);

    @Query("SELECT NEW lms.dto.response.CompanyRes(" +
            "c.name, c.country, c.address, c.phoneNumber,COUNT(DISTINCT cr), COUNT(DISTINCT i)) " +
            "FROM Company c " +
            "LEFT JOIN c.courses cr " +
            "LEFT JOIN c.instructors i " +
            "GROUP BY c.name, c.country, c.address, c.phoneNumber")
    List<CompanyRes> myFindAll();

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Company c WHERE c.name = :name")
    boolean checkUniqName(String name);

    @Query("select count (*) from Company cmp join Course crs on cmp.id  = crs.company.id join crs.groups crsgr join Student s on crsgr.id = s.group.id where cmp.id = :compId")
    Long getCountStudents(Long compId);

    @Query("select crs.courseName from Company cmp join Course crs on  cmp.id = crs.company.id where cmp.id = :id")
    List<String> getAllCoursesName(Long id);

    @Query("select crsgr.groupName from Company cmp join Course crs on cmp.id = crs.company.id join crs.groups crsgr where cmp.id =:id")
    List<String> getAllGroupsName(Long id);

    @Query("select concat(cmpin.firstName,' ',cmpin.lastName) from Company cpm join cpm.instructors cmpin where cpm.id = :id")
    List<String> getAllInstructorsName(Long id);

    @Query("select new lms.dto.response.StudentResWithAll(s.firstName,s.lastName,s.email,s.phoneNumber,s.studyFormat,gr.id) from Company cpm join Course crs on cpm.id = crs.company.id left join crs.groups gr join Student s on s.group.id = gr.id where cpm.id = :compId group by s.studyFormat,s.firstName,s.lastName,s.email,s.phoneNumber,gr.id")
    List<StudentResWithAll> groupBy(Long compId);
}
