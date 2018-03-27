package entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table (name = "customers" )
public class Customers {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column
    private int id_customer;

    @Column (name = "CustomerName")
    private String customerName;

    @Column (name = "StateOrPrivate")
    private byte stateOrPrivate;

    @ManyToMany
    @JoinTable (name = "customers_projects",
                joinColumns = @JoinColumn (name = "id_customer"),
                inverseJoinColumns = @JoinColumn(name = "id_project"))
    private Set<Projects> projects;

    public int getId_customer() {
        return id_customer;
    }

    public void setId_customer(int id_customer) {
        this.id_customer = id_customer;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public byte getStateOrPrivate() {
        return stateOrPrivate;
    }

    public void setStateOrPrivate(byte stateOrPrivate) {
        this.stateOrPrivate = stateOrPrivate;
    }

    public Set<Projects> getProjects() {
        return projects;
    }

    public void setProjects(Set<Projects> projects) {
        this.projects = projects;
    }
}
