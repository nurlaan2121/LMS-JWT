package lms.service;

import lms.dto.request.CompanyReq;
import lms.dto.response.CompanyForGet;
import lms.dto.response.CompanyRes;
import lms.dto.response.StudentResWithAll;
import lms.entities.Company;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CompanyService {

    Company save(CompanyReq company);

    List<CompanyRes> getAllCompanies();

    CompanyRes findById(Long id);

    String update(CompanyReq company, Long id);

    String remove(Long id);

    boolean checkUniqName(String name);

    CompanyForGet getInfo(Long compId);

    List<StudentResWithAll> groupByFormat(Long compId);
}
