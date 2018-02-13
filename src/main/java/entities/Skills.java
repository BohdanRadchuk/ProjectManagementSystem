package entities;

import entities.skillEnums.Branch;
import entities.skillEnums.SkillLevel;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table (name = "skills")
public class Skills{

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column
    private int id_skill;

    @Column
    private Branch branch;

    @Column
    private SkillLevel skill_level;

    @ManyToMany
    @JoinTable(name = "developer_skill",
            joinColumns =  @JoinColumn (name = "id_skill"),
            inverseJoinColumns = @JoinColumn (name = "id_dev"))
    private Set<Developer> developers;

    public int getId_skill() {
        return id_skill;
    }

    public void setId_skill(int id_skill) {
        this.id_skill = id_skill;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public SkillLevel getSkill_level() {
        return skill_level;
    }

    public void setSkill_level(SkillLevel skill_level) {
        this.skill_level = skill_level;
    }

    public Set<Developer> getDevelopers() {
        return developers;
    }

    public void setDevelopers(Set<Developer> developers) {
        this.developers = developers;
    }

}
