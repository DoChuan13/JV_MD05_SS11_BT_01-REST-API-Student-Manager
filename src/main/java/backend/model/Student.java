package backend.model;

import javax.persistence.*;
import java.lang.reflect.Field;
import java.util.Date;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer age;
    private Boolean sex;
    private Date birthDay;

    public Student() {
    }

    public Student(Long id, String name, Integer age, Boolean sex, Date birthDay) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.birthDay = birthDay;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", birthDay=" + birthDay +
                '}';
    }

    public boolean checkAllNull() {
        for (Field f : getClass().getDeclaredFields()) {
            try {
                if (f.get(this) != null)
                    return false;
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return true;
    }
}
