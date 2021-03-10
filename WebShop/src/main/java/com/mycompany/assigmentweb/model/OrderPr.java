package com.mycompany.assigmentweb.model;

import com.mycompany.assigmentweb.generator.StringPrefixedSequenceIdGenerator;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "ORDER_PR")
public class OrderPr {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_num")
    @GenericGenerator(
            name = "order_num",
            strategy = "com.mycompany.assigmentweb.generator.StringPrefixedSequenceIdGenerator",
            parameters = {
                @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1")
                ,
            @Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "ORD")
                ,
            @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d")})
    @Column(name = "ORDER_NUM_ID", nullable = false, unique = true)
    private String id;

    @Column(name = "DATETIME_CREATE", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTimeCreate;

    @Column(name = "DATETIME_UPDATE", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTimeUpdate;

    @Column(name = "STATE", nullable = false)
    private String state = StateOrder.PROCESS.getState();
    
    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToOne(optional = false)
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToMany
    @JoinColumn(name = "ORDER_NUM_ID")
    private List<OrderDetails> detailses;

    public OrderPr() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDateTimeCreate() {
        return dateTimeCreate;
    }

    public void setDateTimeCreate(Date dateTimeCreate) {
        this.dateTimeCreate = dateTimeCreate;
    }

    public Date getDateTimeUpdate() {
        return dateTimeUpdate;
    }

    public void setDateTimeUpdate(Date dateTimeUpdate) {
        this.dateTimeUpdate = dateTimeUpdate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    

    public List<OrderDetails> getDetailses() {
        return detailses;
    }

    public void setDetailses(List<OrderDetails> detailses) {
        this.detailses = detailses;
    }

    @Override
    public int hashCode() {
        return 11;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OrderPr other = (OrderPr) obj;
        if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "OrderPr{" + "id=" + id + ", dateTimeCreate=" + dateTimeCreate + ", dateTimeUpdate=" + dateTimeUpdate + ", state=" + state + ", description=" + description + ", user=" + user + ", detailses=" + detailses + '}';
    }

    

}
