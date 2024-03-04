package lms.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "companies")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@SequenceGenerator(name = "id_gen",allocationSize = 1)
public class Company extends BaseModel{
    private String name;
    private String country;
    private String address;
    private String phoneNumber;
    @OneToMany(mappedBy = "company",cascade = {CascadeType.REMOVE})
    private List<Course> courses = new ArrayList<>();
    @ManyToMany(mappedBy = "companies")
    private List<Instructor> instructors = new ArrayList<>();

    public Company(String name, String country, String address, String phoneNumber) {
        this.name = name;
        this.country = country;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
}
