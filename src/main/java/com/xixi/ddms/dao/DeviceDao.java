package com.xixi.ddms.dao;


import com.xixi.ddms.modal.DeviceBean;
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

public class DeviceDao {
    private static final String QUERY_SQL ="select device_id,fixing,scrapped,used,uuid,dt_name from device inner join device_type on device.dt_id=device_type.dt_id";
    public ArrayList<DeviceBean> getDeviceInfo(){
        ArrayList<DeviceBean> deviceBeans = null;
        Connection connection = DruidConn.getConn();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            preparedStatement = connection.prepareStatement(QUERY_SQL);
            rs = preparedStatement.executeQuery();
            deviceBeans = new ArrayList<>();
            while (rs.next()){
                DeviceBean deviceBean = new DeviceBean(rs.getInt("device_id"),
                        rs.getString("dt_name"),
                        rs.getBoolean("fixing"),
                        rs.getBoolean("scrapped"),
                        rs.getBoolean("used"),
                        rs.getInt("uuid"));
                deviceBeans.add(deviceBean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DruidConn.closeConn(rs,null,preparedStatement,null,connection);
        }
        return deviceBeans;
    }

    public static void main(String[] args) {
        System.out.println(new DeviceDao().getDeviceInfo());
    }
}
