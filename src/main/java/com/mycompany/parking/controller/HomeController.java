/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parking.controller;

import com.mycompany.parking.bean.DanhMucBean;
import com.mycompany.parking.view.MainJFrame;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ad
 */
public class HomeController {
    private MainJFrame mainJFrame;
    
    public HomeController(MainJFrame mainJFrame)
    {
        this.mainJFrame = mainJFrame;
        ChuyenManHinhController controller=new ChuyenManHinhController(mainJFrame.getJpnView());
        controller.setView(mainJFrame.getJpnVehicle(), mainJFrame.getJlbVehicle());
        
        List<DanhMucBean> listItem= new ArrayList<>();
        listItem.add(new DanhMucBean("Vehicle",mainJFrame.getJpnVehicle(),mainJFrame.getJlbVehicle()));
        listItem.add(new DanhMucBean("ParkingLot",mainJFrame.getJpnParkingLot(),mainJFrame.getJlbParkingLot()));
        listItem.add(new DanhMucBean("Static",mainJFrame.getJpnStatic(),mainJFrame.getJlbStatic()));
        
        controller.setEvent(listItem);
        
        mainJFrame.setVisible(true);
    }        
    
           
}
