package com.xixi.ddms.dao;


import com.xixi.ddms.modal.UserBean;
import com.xixi.ddms.pool.ManageDruidConn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author: create by zhengyuxi
 * @description: com.xixi.ddms.dao
 * @date:2022/4/28
 */

public class UserDao {

    public ArrayList<UserBean> getUserInfo(){
        String QUERY_SQL ="select uuid,uname from user where deleted=0 order by created_at ";
        ArrayList<UserBean> userBeans = null;
        Connection connection = ManageDruidConn.getConn();
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
            ManageDruidConn.closeConn(rs,null,preparedStatement,null,connection);
        }
        return userBeans;
    }
    public ArrayList<UserBean> getUserInfo(String key){
        String QUERY_SQL ="select uuid,uname from user where deleted=0 and (uuid LIKE ? or uname LIKE ?) order by created_at ";
        ArrayList<UserBean> userBeans = null;
        Connection connection = ManageDruidConn.getConn();
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
            ManageDruidConn.closeConn(rs,null,preparedStatement,null,connection);
        }
        return userBeans;
    }

    public static void main(String[] args) {
        System.out.println(new UserDao().getUserInfo("xixi"));
    }
}
