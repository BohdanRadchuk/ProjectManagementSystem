package entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "companies")
public class Companies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id_company;

    @Column(name = "CompanyName")
    private String companyName;

    @Column(name = "CompanyCreationYear")
    private short companyCreationYear;

    @ManyToMany
    @JoinTable(name = "companies_projects",
            joinColumns = @JoinColumn(name = "id_company"),
            inverseJoinColumns = @JoinColumn(name = "id_project"))
    private Set<Projects> projects;

    public int getId_company() {
        return id_company;
    }

    public void setId_company(int id_company) {
        this.id_company = id_company;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public short getCompanyCreationYear() {
        return companyCreationYear;
    }

    public void setCompanyCreationYear(short companyCreationYear) {
        this.companyCreationYear = companyCreationYear;
    }

    public Set<Projects> getProjects() {
        return projects;
    }

    public void setProjects(Set<Projects> projects) {
        this.projects = projects;
    }
}
