package main.java.Filter;

import com.alibaba.fastjson.JSON;
import main.java.Bean.User;

import javax.servlet.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Created by metaStor on 2021/6/18.
 * 解析请求中的json对象中的name和id
 */
public class UserFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("FIlter初始化中");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            User guestUser = new User();
            guestUser.setId(1L);
            guestUser.setName("admin");
            //将java对象序列化为json对象
            String jsonString1 = JSON.toJSONString(guestUser);
            System.out.println("Json Class: " + jsonString1);
            //将json反序列化为java对象
//        String jsonString2 = "{\"@type\":\"main.java.Bean.User\",\"id\":2,\"name\":\"root\"}";
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(servletRequest.getInputStream(), "UTF-8"));
            StringBuilder responseStrBuilder = new StringBuilder();
            String inputStr;
            while ((inputStr = streamReader.readLine()) != null)
                responseStrBuilder.append(inputStr);
            User user = JSON.parseObject(responseStrBuilder.toString(), User.class);
            //打印变量类型
            PrintWriter writer = servletResponse.getWriter();
//            System.out.println(user.getId());
//            System.out.println(user.getName());
            writer.write("ID: " + user.getId() + "\n");
            writer.write("User: " + user.getName() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
