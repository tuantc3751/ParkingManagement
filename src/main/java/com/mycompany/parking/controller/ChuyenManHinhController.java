/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parking.controller;

import com.mycompany.parking.bean.DanhMucBean;
import com.mycompany.parking.view.ParkingLotJPanel;
import com.mycompany.parking.view.StaticJPanel;
import com.mycompany.parking.view.VehicleJPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Ad
 */
public class ChuyenManHinhController {
    private JPanel root;
    private String kindSelected="";
    
    private List<DanhMucBean> listItem=null;

    public ChuyenManHinhController(JPanel root) {
        this.root = root;   
    }
    
    public void setView(JPanel jpnItem,JLabel jlbItem){
        kindSelected="Vehicle";
        jpnItem.setBackground(new Color(96,100,191));
        jlbItem.setBackground(new Color(96,100,191));
    
        root.removeAll();
        root.setLayout(new BorderLayout());
        VehicleJPanel node = new VehicleJPanel();
        root.add(node);
        VehicleController controller = new VehicleController(node);
        root.validate();
        root.repaint();
    }
    
    public void setEvent(List<DanhMucBean> listItem){
        this.listItem=listItem;
        for(DanhMucBean item :listItem){
            item.getJlb().addMouseListener(new LabelEvent(item.getKind(),item.getJpn(),item.getJlb()));
        }
    }
    
    class LabelEvent implements MouseListener{
        private JPanel node;
        
        private String kind;
        private JPanel jpnItem;
        private JLabel jlbItem;

        public LabelEvent(String kind, JPanel jpnItem, JLabel jlbItem) {
            this.kind = kind;
            this.jpnItem = jpnItem;
            this.jlbItem = jlbItem;
        }
        
        @Override
        public void mouseClicked(java.awt.event.MouseEvent e) {
            switch (kind) {
                case "Vehicle":
                    node=new VehicleJPanel();
                    VehicleController controller = new VehicleController((VehicleJPanel) node);
                    break;
                case "ParkingLot":
                    node=new ParkingLotJPanel();
                    ParkingLotController controller1 = new ParkingLotController((ParkingLotJPanel) node);
                    break;
                case "Static":
                    node= new StaticJPanel();
                default:
                    break;
            }
            root.removeAll();
            root.setLayout(new BorderLayout());
            root.add(node);
            
            root.validate();
            root.repaint();
            setChangeBackground(kind);
            
        }

        @Override
        public void mousePressed(java.awt.event.MouseEvent e) {
            kindSelected=kind;
            jpnItem.setBackground(new Color(96,100,191));
            jlbItem.setBackground(new Color(96,100,191));
        }

        @Override
        public void mouseReleased(java.awt.event.MouseEvent e) {
        }

        @Override
        public void mouseEntered(java.awt.event.MouseEvent e) {
            jpnItem.setBackground(new Color(96,100,191));
            jlbItem.setBackground(new Color(96,100,191));
        }

        @Override
        public void mouseExited(java.awt.event.MouseEvent e) {
            if(!kindSelected.equalsIgnoreCase(kind)){
                jpnItem.setBackground(new Color(51,255,0));
                jlbItem.setBackground(new Color(51,255,0));
            }
        }
    }
    
    private void setChangeBackground(String kind){
        for(DanhMucBean item: listItem){
            if(item.getKind().equalsIgnoreCase(kind)){
                item.getJpn().setBackground(new Color(96,100,191));
                item.getJlb().setBackground(new Color(96,100,191));
            }else{
                item.getJpn().setBackground(new Color(51,255,0));
                item.getJlb().setBackground(new Color(51,255,0));
            }
        }
    }
        
}
