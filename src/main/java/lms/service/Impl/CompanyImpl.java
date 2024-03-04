package lms.service.Impl;

import jakarta.transaction.Transactional;
import lms.dto.request.CompanyReq;
import lms.dto.response.CompanyForGet;
import lms.dto.response.CompanyRes;
import lms.dto.response.StudentResWithAll;
import lms.entities.Company;
import lms.exceptions.NotFound;
import lms.repository.CompanyRepo;
import lms.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyImpl implements CompanyService {
    private final CompanyRepo companyRepo;

    @Override
    public Company save(CompanyReq company) {
        Company companyOrg = new Company(company.getName(), company.getCountry(), company.getAddress(), company.getPhoneNumber());
        return companyRepo.save(companyOrg);
    }

    @Override
    public List<CompanyRes> getAllCompanies() {
        return companyRepo.myFindAll();
    }

    @Override
    public CompanyRes findById(Long id) {
        return companyRepo.myFindById(id);
    }

    @Override
    @Transactional
    public String update(CompanyReq company, Long id) {
        Company company1 = companyRepo.findById(id).orElseThrow(() -> new NotFound(id));
        company1.setPhoneNumber(company.getPhoneNumber());
        company1.setName(company.getName());
        company1.setCountry(company.getCountry());
        company1.setAddress(company.getAddress());
        return "Success";
    }

    @Override
    public String remove(Long id) {
        Company company = companyRepo.findById(id).orElseThrow(() -> new NotFound(id));
        companyRepo.delete(company);
        return "Success";
    }

    @Override
    public boolean checkUniqName(String name) {
        return companyRepo.checkUniqName(name);
    }

    @Override
    public CompanyForGet getInfo(Long compId) {
        Company company = companyRepo.findById(compId).orElseThrow(() -> new NotFound(compId));
        return new CompanyForGet(company.getName(), company.getCountry(), company.getAddress(), company.getPhoneNumber(), companyRepo.getCountStudents(company.getId()), companyRepo.getAllCoursesName(company.getId()), companyRepo.getAllGroupsName(company.getId()), companyRepo.getAllInstructorsName(company.getId()));
    }

    @Override
    public List<StudentResWithAll> groupByFormat(Long compId) {
        return companyRepo.groupBy(compId);
    }
}
