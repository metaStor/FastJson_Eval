package main.java.Bean;

/**
 * Created by metaStor on 2020/7/11.
 */
//定义用户类
public class User {
    private Long id;
    private String name;

    //增加get/set方法
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
}
