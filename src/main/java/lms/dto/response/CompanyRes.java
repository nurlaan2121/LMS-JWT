package lms.dto.response;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("company")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CompanyRes {
    private String name;
    private String country;
    private String address;
    private String phoneNumber;
    private long countCourses;
    private long countInstructors;

    public CompanyRes(String name) {
        this.name = name;
    }
}
