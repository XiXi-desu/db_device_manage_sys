package com.xixi.ddms.service;

import com.xixi.ddms.dao.UserDao;
import com.xixi.ddms.modal.UserBean;

import java.util.ArrayList;
import java.util.Vector;

/**
 * &#064;author:  create by zhengyuxi
 * &#064;description:  com.xixi.ddms.service
 * &#064;date:2022/4/29
 */
public class UserService {
    public Vector<Vector<String>> getUserData(){
        UserDao userDao = new UserDao();
        ArrayList<UserBean> users = userDao.getUserInfo();
        Vector<Vector<String>> userVector = new Vector<>();
        for (UserBean user : users) {
            Vector<String> row = new Vector<>();
            row.addElement(user.getUId().toString());
            row.addElement(user.getUName());
            userVector.addElement(row);
        }
        return userVector;
    }
    public Vector<Vector<String>> searchUserData(String key){
        UserDao userDao = new UserDao();
        ArrayList<UserBean> users = userDao.getUserInfo(key);
        Vector<Vector<String>> userVector = new Vector<>();
        for (UserBean user : users) {
            Vector<String> row = new Vector<>();
            row.addElement(user.getUId().toString());
            row.addElement(user.getUName());
            userVector.addElement(row);
        }
        return userVector;
    }

}
