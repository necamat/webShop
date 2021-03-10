package com.mycompany.assigmentweb.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty()
    @Column(name = "USER_NAME", unique = true, nullable = false)
    private String userName;

    @NotEmpty()
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @NotEmpty()
    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @NotEmpty(message = "Last name can not be blank.")
    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Email()
    @NotEmpty()
    @Column(name = "EMAIL", nullable = false)
    private String email;

    
    @NotEmpty()
    @Column(name = "STATE", nullable = false)
    private String state = State.ACTIVE.getState();

    @NotEmpty()
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USER_USER_PROFILE",
               joinColumns = {@JoinColumn(name = "USER_ID")},
               inverseJoinColumns = {@JoinColumn(name = "USER_PROFILE_ID")})
    private Set<UserProfile> userProfiles = new HashSet<UserProfile>();
    
    @OneToMany(mappedBy = "user")
    private List<OrderPr> orderPrs;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Set<UserProfile> getUserProfiles() {
        return userProfiles;
    }

    public void setUserProfiles(Set<UserProfile> userProfiles) {
        this.userProfiles = userProfiles;
    }

    public List<OrderPr> getOrderPrs() {
        return orderPrs;
    }

    public void setOrderPrs(List<OrderPr> orderPrs) {
        this.orderPrs = orderPrs;
    }
    

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((userName == null) ? 0 : userName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof User)) {
            return false;
        }
        User other = (User) obj;
        if (id != other.id) {
            return false;
        }
        if (userName == null) {
            if (other.userName != null) {
                return false;
            }
        } else if (!userName.equals(other.userName)) {
            return false;
        }
        return true;
    }

    //the password is not displayed in toString for security reasons
    @Override
    public String toString() {
        return "User [id=" + id + ", userName=" + userName
                + ", firstName=" + firstName + ", lastName=" + lastName
                + ", email=" + email + ", state=" + state
                + ", userProfiles=" + userProfiles + "]";
    }

}
