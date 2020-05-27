package entity;
 
import java.util.HashSet;
import java.util.Set;
 
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
 
@Entity
@Table(name="employee")
public class Employee {
 
    @Id
    @Column(name="employee_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long employeeId;
 
    @Column(name="firstname")
    private String firstname;
 
    @Column(name="lastname")
    private String lastname;
 
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name="employee_meeting", joinColumns= {@JoinColumn(name="employee_id")},  inverseJoinColumns= {@JoinColumn(name="meeting_id")})
    private Set<Meeting> meetings = new HashSet<Meeting>();
 
    public Employee() { }
 
    public Employee(String first_name, String last_name) {
        this.firstname = first_name;
        this.lastname = last_name;
    }
 
    public Long getEmployeeId() {
        return employeeId;
    }
 
    public void setEmployeeId(Long employee_id) {
        this.employeeId = employee_id;
    }
 
    public String getFirstname() {
        return firstname;
    }
 
    public void setFirstname(String first_name) {
        this.firstname = first_name;
    }
 
    public String getLastname() {
        return lastname;
    }
 
    public void setLastname(String last_name) {
        this.lastname = last_name;
    }
 
    public Set<Meeting> getMeetings() {
        return meetings;
    }
 
    public void setMeetings(Set<Meeting> meetings) {
        this.meetings = meetings;
    }
}