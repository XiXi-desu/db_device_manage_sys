package com.xixi.ddms.dao;


import com.xixi.ddms.modal.DeviceBean;
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

public class DeviceDao {
    private static final String QUERY_SQL ="select device_id,fixing,scrapped,used,uuid,dt_name from device inner join device_type on device.dt_id=device_type.dt_id";
    public ArrayList<DeviceBean> getInstanceInfo(){
        ArrayList<DeviceBean> deviceBeans = null;
        Connection connection = ManageDruidConn.getConn();
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
            throw new RuntimeException(e);
        }
        finally {
            ManageDruidConn.closeConn(rs,null,preparedStatement,null,connection);
        }
        return deviceBeans;
    }

    public static void main(String[] args) {
        System.out.println(new DeviceDao().getInstanceInfo());
    }
}
