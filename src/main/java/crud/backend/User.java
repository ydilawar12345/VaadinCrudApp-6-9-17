package crud.backend;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type",discriminatorType=DiscriminatorType.STRING)
@DiscriminatorValue(value="user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column()
    private long id;

    @Column()
    private Date birthDay;

    @NotNull(message = "Name is required")
    @Size(min = 1, max = 50, message = "name must be longer than 3 and less than 40 characters")

    @Column()
    private String name;

    @Column()
    private String phoneNumber;

    @Column()
    private String eyeColor;

    @Column()
    private String height;

    @Column()
    private Integer score = 0;

    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeight(){
        return height;
    }

    public void setHeight(String height){
        this.height = height;
    }

    public String getEyeColor()
    {
       return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
          this.eyeColor = eyeColor;}

    public String getPhoneNumber() {

        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;}

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}

