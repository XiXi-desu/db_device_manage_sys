package com.xixi.ddms.service;

import com.xixi.ddms.dao.DeviceDao;
import com.xixi.ddms.dao.UserDao;
import com.xixi.ddms.modal.DeviceBean;
import com.xixi.ddms.modal.UserBean;

import java.util.ArrayList;
import java.util.Vector;

/**
 * @author: create by zhengyuxi
 * @description: com.xixi.ddms.service
 * @date:2022/4/29
 */
public class UserService {
    public Vector getUserData(){
        UserDao userDao = new UserDao();
        ArrayList<UserBean> users = userDao.getUserInfo();
        Vector userVector = new Vector<>();
        for (int i = 0 ; i < users.size() ; i++){
            UserBean user = users.get(i);
            Vector row = new Vector();
            row.addElement(user.getUId());
            row.addElement(user.getUName());
            userVector.addElement(row);
        }
        return userVector;
    }
}
