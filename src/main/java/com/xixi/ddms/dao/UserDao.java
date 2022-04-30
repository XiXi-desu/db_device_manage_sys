package com.xixi.ddms.dao;


import com.xixi.ddms.modal.UserBean;
import com.xixi.ddms.pool.DruidConn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * &#064;author:  create by zhengyuxi
 * &#064;description:  com.xixi.ddms.dao
 * &#064;date:2022/4/28
 */

public class UserDao {

    public ArrayList<UserBean> getUserInfo(){
        String QUERY_SQL ="select uuid,uname from user where deleted=0 order by created_at ";
        ArrayList<UserBean> userBeans;
        Connection connection = DruidConn.getConn();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            preparedStatement = connection.prepareStatement(QUERY_SQL);
            rs = preparedStatement.executeQuery();
            userBeans = new ArrayList<>();
            while (rs.next()){
                UserBean userBean = new UserBean(rs.getInt("uuid"),rs.getString("uname"));
                userBeans.add(userBean);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            DruidConn.closeConn(rs,null,preparedStatement,null,connection);
        }
        return userBeans;
    }
    public ArrayList<UserBean> getUserInfo(String key){
        String QUERY_SQL ="select uuid,uname from user where deleted=0 and (uuid LIKE ? or uname LIKE ?) order by created_at ";
        ArrayList<UserBean> userBeans;
        Connection connection = DruidConn.getConn();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            preparedStatement = connection.prepareStatement(QUERY_SQL);
            preparedStatement.setString(1,'%'+key+'%');
            preparedStatement.setString(2,'%'+key+'%');
            rs = preparedStatement.executeQuery();
            userBeans = new ArrayList<>();
            while (rs.next()){
                UserBean userBean = new UserBean(rs.getInt("uuid"),rs.getString("uname"));
                userBeans.add(userBean);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            DruidConn.closeConn(rs,null,preparedStatement,null,connection);
        }
        return userBeans;
    }
    public void addUser(String name){
        Connection connection = DruidConn.getConn();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement =  connection.prepareStatement("insert into device_manage_db.user(uname) values (?)");
            preparedStatement.setString(1, name);
            preparedStatement.execute();

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DruidConn.closeConn(null,null,preparedStatement,null,connection);
        }
    }
    public void removeUser(Integer key){
        Connection connection = DruidConn.getConn();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("delete from device_manage_db.user where uuid=?");
            preparedStatement.setInt(1, key);
            preparedStatement.execute();

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DruidConn.closeConn(null,null,preparedStatement,null,connection);
        }
    }

    public static void main(String[] args) {
        System.out.println(new UserDao().getUserInfo("xixi"));
    }
}
