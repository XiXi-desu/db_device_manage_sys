package com.xixi.ddms.ui;

import com.xixi.ddms.dao.UserDao;
import com.xixi.ddms.service.UserService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

/**
 * &#064;author:  create by zhengyuxi
 * &#064;description:  com.xixi.ddms.utils
 * &#064;date:2022/4/29
 */
public class UserManager {
    JFrame frame;//主界面
    JPanel pHead,pBody1,pBody2,pFoot,contentPane;//程序分块
    JLabel lTitle;//标题
    JTable table;//数据表格
    JButton jbAdd, jbRemove, jbEdit, jbSearch, jbReset;//各个按钮
    JTextField TFSearch;//查找框

    Vector<String> index = new Vector<>();

    Vector<Vector<String>> data;

    DefaultTableModel defaultTableModel;


    public UserManager(){
        index.addElement("id");
        index.addElement("name");
        data = new UserService().getUserData();
        Font titleFont = new Font("苹方",Font.BOLD,24);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();//获取当前屏幕长宽工具
        //窗口长宽设定
        int SwingX = (int)(screenSize.width*0.5);
        int SwingY = (int)(screenSize.height*0.5);
        frame = new JFrame("办公设备管理系统");
        contentPane = new JPanel();
        pHead = new JPanel();
        pBody1 = new JPanel();
        pBody2 = new JPanel();
        pFoot = new JPanel();
        lTitle = new JLabel("Neko的用户管理系统");
        jbAdd = new JButton("ADD");
        jbRemove = new JButton("REMOVE");
        jbEdit = new JButton("EDIT");
        jbSearch = new JButton("Search");
        jbReset = new JButton("Reset");
        TFSearch = new JTextField(20);
        DefaultTableModel defaultTableModel = new DefaultTableModel(){
            public boolean isCellEditable(int rowIndex, int ColIndex) {
                return false;
            }
        };//表格不可修改
        defaultTableModel.setDataVector(data, index);
        table = new JTable(defaultTableModel);
        JScrollPane s = new JScrollPane(table);
        lTitle.setFont(titleFont);
        pHead.add(lTitle);
        pBody1.add(TFSearch);
        pBody1.add(jbSearch);
        pBody1.add(jbReset);
        pBody2.add(s);
        pFoot.add(jbAdd);
        pFoot.add(jbRemove);
        pFoot.add(jbEdit);
        pHead.setBounds(0,0,SwingX,(int)(0.05*SwingY));
        pBody1.setBounds(0,(int)(0.05*SwingY),SwingX,(int)(0.05*SwingY));
        pBody2.setBounds(0,(int)(0.1*SwingY),SwingX,(int)(0.8*SwingY));
        pFoot.setBounds(0,(int)(0.9*SwingY),SwingX,(int)(0.1*SwingY));
        contentPane.setLayout(null);
        contentPane.add(pHead);
        contentPane.add(pBody1);
        contentPane.add(pBody2);
        contentPane.add(pFoot);
        frame.setContentPane(contentPane);
        frame.setBounds(screenSize.width/2-SwingX/2,screenSize.height/2-SwingY/2,SwingX,SwingY);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//点击关闭按钮既关闭程序
        frame.setVisible(true);

        jbSearch.addActionListener(e -> {
            this.data = new UserService().searchUserData(TFSearch.getText());
            defaultTableModel.setDataVector(data,index);
            table.setModel(defaultTableModel);
        });
        jbReset.addActionListener(e -> {
            updateTable();
            TFSearch.setText("");
        });
        jbAdd.addActionListener(e -> {
            JDialog addDialog = new AddDialog(frame, this);
            addDialog.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
            addDialog.setSize(SwingX/3,SwingY/4);
            addDialog.setLocation(screenSize.width/2-SwingX/6,screenSize.height/2-SwingY/8);
            addDialog.setVisible(true);
        });
        jbRemove.addActionListener(e -> {
            int[] list = Collections.singletonList(table.getSelectedRows()).get(0);
            for (int i = 0; i < list.length; i++) {
                Integer key = new BigInteger(data.get(table.getSelectedRows()[i]).get(0)).intValue();
                new UserDao().removeUser(key);
            }
//            Integer key = new BigInteger(data.get(table.getSelectedRow()).get(0)).intValue();
//            new UserDao().removeUser(key);
            updateTable();
        });
    }

    class AddDialog extends JDialog{//添加時的彈窗設計
//        AddDialog(JFrame frame){
//            super(frame);
//        }
//        AddDialog(JFrame frame,String title){
//            super(frame,title);
//        }
        AddDialog(JFrame frame, UserManager userManager){
            super(frame,"添加用戶",true);
            contentPane = new JPanel(new GridLayout(2,1));
            tfName = new JTextField(20);
            addButton = new JButton("添加");
            cancelButton = new JButton("取消");
            jpBody = new JPanel();
            jpFoot = new JPanel();
            jpBody.add(tfName);
            jpFoot.add(addButton);
            jpFoot.add(cancelButton);
            contentPane.add(jpBody);
            contentPane.add(jpFoot);
            this.add(contentPane);
            AddDialog that = this;
            this.setDefaultCloseOperation(HIDE_ON_CLOSE);
            addButton.addActionListener(e -> {
                UserDao userDao = new UserDao();
                userDao.addUser(tfName.getText());
                that.setVisible(false);
                System.out.println(userManager);
                userManager.updateTable();
            });
            cancelButton.addActionListener(e -> {
                that.setVisible(false);
            });
        }
        JPanel contentPane;
        JTextField tfName;
        JButton addButton, cancelButton;
        JPanel jpBody,jpFoot;



    }
    public void updateTable()
    {
        this.data = new UserService().getUserData();
        this.table.setModel(new DefaultTableModel(this.data,this.index));
        this.defaultTableModel = new DefaultTableModel(this.data,this.index){
            public boolean isCellEditable(int rowIndex, int ColIndex) {
                return false;
            }
        };//表格不可修改
    }
    public static void main(String[] args) {
        UserManager userManager = new UserManager();
    }
}
