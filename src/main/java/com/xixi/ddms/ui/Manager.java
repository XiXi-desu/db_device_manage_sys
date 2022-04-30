package com.xixi.ddms.ui;

import com.xixi.ddms.service.UserService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSetMetaData;
import java.util.Vector;

/**
 * @author: create by zhengyuxi
 * @description: com.xixi.ddms.utils
 * @date:2022/4/29
 */
public class Manager {
    JFrame frame;
    JPanel pHead,pBody,pFoot,contentPane;
    JLabel lTitle;
    JTable table;
    JButton jbAdd,jbRemove,jbEdit,jbSearch;
    JTextArea TASearch;

    DefaultTableModel defaultTableModel;

    public Manager(Vector data, Vector index){
        Font titleFont = new Font("苹方",Font.BOLD,24);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();//获取当前屏幕长宽工具
        //窗口长宽设定
        int SwingX = (int)(screenSize.width*0.6);
        int SwingY = (int)(screenSize.height*0.6);
        frame = new JFrame("办公设备管理系统");
        contentPane = new JPanel();
        pHead = new JPanel();
        pBody = new JPanel();
        pFoot = new JPanel();
        lTitle = new JLabel("Neko的办公设备管理系统");
        jbAdd = new JButton("ADD");
        jbRemove = new JButton("REMOVE");
        jbEdit = new JButton("EDIT");
        jbSearch = new JButton("Search");
        TASearch = new JTextArea();
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
        pBody.add(s);
        pFoot.add(jbAdd);
        pFoot.add(jbRemove);
        pFoot.add(jbEdit);
        pHead.setBounds(0,0,SwingX,(int)(0.05*SwingY));
        pBody.setBounds(0,(int)(0.1*SwingY),SwingX,(int)(0.8*SwingY));
        pFoot.setBounds(0,(int)(0.9*SwingY),SwingX,(int)(0.1*SwingY));
        contentPane.setLayout(null);
        contentPane.add(pHead);
        contentPane.add(pBody);
        contentPane.add(pFoot);
        frame.setContentPane(contentPane);
        frame.setBounds(screenSize.width/2-SwingX/2,screenSize.height/2-SwingY/2,SwingX,SwingY);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void setTable(Vector data, Vector index)
    {
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        defaultTableModel.setDataVector(data, index);
        this.table.setModel(defaultTableModel);
    }

    public static void main(String[] args) {
        Vector index = new Vector();
        index.addElement("id");
        index.addElement("name");
        Manager manager = new Manager(new UserService().getUserData(),index);
    }
}
