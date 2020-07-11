package main.java;

import com.alibaba.fastjson.serializer.SerializerFeature;
import main.java.Bean.Store;
import main.java.Bean.Apple;

import com.alibaba.fastjson.*;

/**
 * Created by metaStor on 2020/7/11.
 */
public class Test_Store {

    public static void main(String[] args) {

        Store store = new Store();
        Apple apple = new Apple();

        apple.setPrice(250.15f);
        store.setName("Pineapple");
        store.setFruit(apple);

        // Serializer
        String jsonString = JSON.toJSONString(store);
        System.out.println("toJSONString:\t" + jsonString);

        // DeSerializer
        Store newStore = JSON.parseObject(jsonString, Store.class);
        System.out.println("parseObject:\t" + newStore);
        System.out.println(newStore.getName());

//        Apple newApple = (Apple) newStore.getFruit(); // Fruit类Cast到Apple类
//        System.out.println(newApple.getPrice()); // 抛出异常
//        System.out.println(newStore.getFruit()); // 原因是反序列化之后的Fruit类是空的，即Apple没有成功反序列化
        /* 解释：
         * 当一个类中包含了一个接口（或抽象类）的时候，
         * 在使用fastjson进行序列化的时候，会将子类型抹去，
         * 只保留接口（抽象类）的类型，使得反序列化时无法拿到原始类型。
         */
        // 正确做法：采用WriteClassName进行标记，即采用了@type（黑白对抗开始了！）
        String jsonString2 = JSON.toJSONString(store, SerializerFeature.WriteClassName);
        System.out.println("jsonString2:\t" + jsonString2);
        // DeSerializer
        Store newStore2 = JSON.parseObject(jsonString2, Store.class);
        Apple newApple2 = (Apple) newStore2.getFruit(); // Fruit类Cast到Apple类
        System.out.println("parseObject:\t" + newApple2 + "\nprice:\t" + newApple2.getPrice()); // 成功
    }
}
