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
    JPanel pHead,pBody,pFoot,containPanel;
    JLabel lTitle;
    JTable table;

    DefaultTableModel defaultTableModel;

    public Manager(Vector data, Vector index){
        Font titleFont = new Font("苹方",Font.BOLD,24);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();//获取当前屏幕长宽工具
        //窗口长宽设定
        int SwingX = screenSize.width/3;
        int SwingY = screenSize.height/3;
        frame = new JFrame("办公设备管理系统");
        containPanel = new JPanel();
        pHead = new JPanel();
        pBody = new JPanel();
        pFoot = new JPanel();
        lTitle = new JLabel("Neko的办公设备管理系统");

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
        containPanel.setLayout(new GridLayout(3,1));
        containPanel.add(pHead);
        containPanel.add(pBody);
        containPanel.add(pFoot);
        frame.add(containPanel);
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

        Vector row = new Vector();
        row.addElement("1");
        row.addElement("neko");
        Vector vector = new Vector();
        vector.addElement(row);
        Vector index = new Vector();
        index.addElement("id");
        index.addElement("name");
        Manager manager = new Manager(new UserService().getUserData(),index);
    }
}
