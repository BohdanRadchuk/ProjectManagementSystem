package entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "projects")
public class Projects {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column
    private int id_project;

    @Column
    private String ProjectName;

    @Column
    private String description;

    @Column
    private int cost;

    @ManyToMany
    @JoinTable(name = "developer_projects",
            joinColumns =  @JoinColumn (name = "id_project"),
            inverseJoinColumns = @JoinColumn (name = "id_dev"))
    private Set<Developer> developers;

    @ManyToMany
    @JoinTable(name = "customers_projects",
            joinColumns =  @JoinColumn (name = "id_project"),
            inverseJoinColumns = @JoinColumn (name = "id_customer"))
    private Set<Customers> customers;

    @ManyToMany
    @JoinTable(name = "companies_projects",
            joinColumns = @JoinColumn(name = "id_project"),
            inverseJoinColumns = @JoinColumn(name = "id_company"))
    private Set<Companies> companies;

    public int getId_project() {
        return id_project;
    }

    public void setId_project(int id_project) {
        this.id_project = id_project;
    }

    public String getProjectName() {
        return ProjectName;
    }

    public void setProjectName(String projectName) {
        ProjectName = projectName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Set<Developer> getDevelopers() {
        return developers;
    }

    public void setDevelopers(Set<Developer> developers) {
        this.developers = developers;
    }

    public Set<Customers> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<Customers> customers) {
        this.customers = customers;
    }

    public Set<Companies> getCompanies() {
        return companies;
    }

    public void setCompanies(Set<Companies> companies) {
        this.companies = companies;
    }
}
