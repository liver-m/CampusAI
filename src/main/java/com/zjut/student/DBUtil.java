package com.zjut.student;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DBUtil {

    private static String url;
    private static String user;
    private static String password;
    //1.读取properties文件 2.写入到Properties变量 3.连接数据库
    static{
        System.out.println("正在读取配置文件...");
        Properties props = new Properties();
        String configFile = "src/main/resources/db.properties";

        try(FileInputStream fis = new FileInputStream(configFile)){
            props.load(fis);

            url = props.getProperty("jdbc.url");
            user = props.getProperty("jdbc.user");
            password = props.getProperty("jdbc.password");

        }catch (IOException e){
            throw new RuntimeException("无法读取数据库配置文件：" + configFile,e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,user,password);
    }
}