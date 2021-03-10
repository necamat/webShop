package com.mycompany.assigmentweb.model;

import com.mycompany.assigmentweb.generator.StringPrefixedSequenceIdGenerator;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.validator.constraints.NotEmpty;



@Entity
@Table(name = "PRODUCT")
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_num")
    @GenericGenerator(
        name = "product_num", 
        strategy = "com.mycompany.assigmentweb.generator.StringPrefixedSequenceIdGenerator", 
        parameters = {
            @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "2"),
            @Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "PR"),
            @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
    @Column(name = "PRODUCT_NUM_ID", nullable = false, unique = true)
    private String id;
    
    @NotEmpty()
    @Column(name = "NAME", nullable = false, length = 150)
    private String name;
    
    
    
    @Column(name = "QUANTITY", nullable = false)
    private int quantity;
    
    
    @Column(name = "PRICE", nullable = false)
    private double  price;
    
    @Size(max = 255) 
    @Column(name = "DESCRIPTION")
    private String description;
    
    
    @Column(name = "PHOTO_NAME", length = 145)
    private String photoName;
    
    
    @OneToMany
    @JoinColumn(name = "PRODUCT_NUM_ID")
    private List<OrderDetails> detailses;

    public Product() {
    }

    public Product( String name, int quantity, double price, String description, String photo) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.description = description;
       
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

   

    public List<OrderDetails> getDetailses() {
        return detailses;
    }

    public void setDetailses(List<OrderDetails> detailses) {
        this.detailses = detailses;
    }
    
    
    
    

    @Override
    public int hashCode() {
        return 14;
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
        final Product other = (Product) obj;
        if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
            return false;
        }
        return true;
    }

    

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name=" + name + ", quantity=" + quantity + ", price=" + price + ", description=" + description + ", photo=" + photoName + '}';
    }
    

    
    
    
    
}
