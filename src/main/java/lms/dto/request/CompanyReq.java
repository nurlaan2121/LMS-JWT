package lms.dto.request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyReq {
    private String name;
    private String country;
    private String address;
    private String phoneNumber;
}
