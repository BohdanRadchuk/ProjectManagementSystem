package entities;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "developers")
public class Developer  {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id_dev")
    private int id;

    @Column (name = "firstName")
    private String firstName;

    @Column (name = "secondaryName")
    private String secondaryName;

    @Column (name = "age")
    private int age;

    @Column (name = "gender")
    private String gender;

    @Column (name = "salary")
    private BigDecimal salary;

    @ManyToMany
    @JoinTable(name = "developer_projects",
                joinColumns =  @JoinColumn (name = "id_dev"),
                inverseJoinColumns = @JoinColumn (name = "id_project"))
    private Set<Projects> projects;

    @ManyToMany
    @JoinTable(name = "developer_projects",
            joinColumns =  @JoinColumn (name = "id_dev"),
            inverseJoinColumns = @JoinColumn (name = "id_skill"))
    private Set<Skills> skills;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondaryName() {
        return secondaryName;
    }

    public void setSecondaryName(String secondaryName) {
        this.secondaryName = secondaryName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Set<Projects> getProjects() {
        return projects;
    }

    public void setProjects(Set<Projects> projects) {
        this.projects = projects;
    }

    public Set<Skills> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skills> skills) {
        this.skills = skills;
    }

    @Override
    public String toString() {
        return "Developer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", secondaryName='" + secondaryName + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", salary=" + salary +
                /*", projects=" + projects +
                ", skills=" + skills +*/
                '}';
    }
}
