package xyz.erupt.eql.data;

import java.util.Date;

public class Master {

    private Integer age;

    private String name;

    private Date date;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Master(Integer age, String name, Date date) {
        this.age = age;
        this.name = name;
    }

}
