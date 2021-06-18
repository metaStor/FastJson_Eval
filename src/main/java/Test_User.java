package main.java;


import com.alibaba.fastjson.*;
import main.java.Bean.User;


/**
 * Created by metaStor on 2020/7/11.
 */
public class Test_User {

    public static void main(String[] args) {
        User guestUser = new User();
        guestUser.setId(1L);
        guestUser.setName("guest");
        //将java对象序列化为json对象
        String jsonString1 = JSON.toJSONString(guestUser);
        System.out.println("Json对象: " + jsonString1);
        //将json反序列化为java对象
//        String jsonString2 = "{\"id\":2,\"name\":\"root\"}";
        String jsonString2 = "{\"@type\":\"main.java.Bean.User\",\"id\":2,\"name\":\"root\"}";
        User user = JSON.parseObject(jsonString2, User.class);
        System.out.println("Java对象: " + user);
        System.out.println("User对象: " + user.getId());
        //打印变量类型
        System.out.println(user.getClass().getTypeParameters());
    }
}
