package com.xixi.ddms.pool;

import com.alibaba.druid.pool.DruidDataSource;

import java.sql.*;

/**
 * @author: create by zhengyuxi
 * @description: com.xixi.ddms.pool
 * @date:2022/4/28
 */
public class ManageDruidConn {
    // 定义MySQL数据库连接信息
    private final static String driverName = "com.mysql.cj.jdbc.Driver";
    //测试数据库
    private final static String dbURL = "jdbc:mysql://sh-cynosdbmysql-grp-4uexg4s0.sql.tencentcdb.com:25633/device_manage_db?useUnicode=true&characterEncoding=utf-8";
    private final static String userName = "dmuser";
    private final static String userPwd = "jy3:de3LJAPMtHG";

    //定义
    private static DruidDataSource ds;

    // 加载数据库驱动，初始化Druid连接池
    static {
        try {
            ds = new DruidDataSource();
            ds.setDriverClassName(driverName);
            ds.setUrl(dbURL);
            ds.setUsername(userName);
            ds.setPassword(userPwd);
            ds.setInitialSize(5);
            ds.setMaxActive(10);
            ds.setMinIdle(3);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Druid数据源属性不匹配！");
        }
    }
    public static Connection getConn() {
        // 从连接池返回一个连接
        Connection conn = null;
        try {
            conn = ds.getConnection();
            System.out.println("Druid数据库连接池初始化成功！");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Druid数据库连接池初始化失败！");
        }
        return conn;
    }
    // 释放资源，关闭数据库连接
    public static void closeConn(ResultSet rs, Statement stmt, PreparedStatement prestmt, CallableStatement cstmt,
                                 Connection conn) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (prestmt != null) {
                prestmt.close();
            }
            if (cstmt != null) {
                cstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
            System.out.println("数据库连接释放成功！");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("数据库连接释放失败！");
        }
    }

    public static void main(String[] args) {
        try {
            Connection conn = getConn();
            if (conn != null) {
                closeConn(null, null, null, null, conn);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
