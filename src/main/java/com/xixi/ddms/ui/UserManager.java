package com.xixi.ddms.ui;

import com.xixi.ddms.service.UserService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;

/**
 * &#064;author:  create by zhengyuxi
 * &#064;description:  com.xixi.ddms.utils
 * &#064;date:2022/4/29
 */
public class UserManager {
    JFrame frame;
    JPanel pHead,pBody1,pBody2,pFoot,contentPane;
    JLabel lTitle;
    JTable table;
    JButton jbAdd, jbRemove, jbEdit, jbSearch, jbReset;
    JTextField TFSearch;

    DefaultTableModel defaultTableModel;

    public UserManager(){
        Vector<String> index = new Vector<>();
        index.addElement("id");
        index.addElement("name");
        Vector<Vector<String>> data = new UserService().getUserData();
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
        };
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
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        jbSearch.addActionListener(e -> {
            defaultTableModel.setDataVector(new UserService().searchUserData(TFSearch.getText()),index);
        });
        jbReset.addActionListener(e -> {
            defaultTableModel.setDataVector(new UserService().getUserData(),index);
            TFSearch.setText("");
        });
    }

    public void setTable(Vector<Vector<String>> data, Vector<String> index)
    {
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        defaultTableModel.setDataVector(data, index);
        this.table.setModel(defaultTableModel);
    }

    public static void main(String[] args) {
        UserManager userManager = new UserManager();
    }
}
