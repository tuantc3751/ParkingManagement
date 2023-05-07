/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parking.controller;

import com.mycompany.parking.dao.ParkingLotDao;
import com.mycompany.parking.entity.ParkingLot;
import com.mycompany.parking.view.ParkingLotJPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Ad
 */
public class ParkingLotController {
    private ParkingLotDao parkingLotDao;
    private ParkingLotJPanel parkingLotView;

    public ParkingLotController(ParkingLotJPanel view) {
        this.parkingLotView = view;
        parkingLotDao = new ParkingLotDao();
        parkingLotView.showListParkingLots(parkingLotDao.getListParkingLots());

        parkingLotView.addAddParkingLotListener(new AddParkingLotListener());
        parkingLotView.addEdiParkingLotListener(new EditParkingLotListener());
        parkingLotView.addDeleteParkingLotListener(new DeleteParkingLotListener());
        parkingLotView.addClearListener(new ClearParkingLotListener());
        parkingLotView.addSortParkingLotNameListener(new SortParkingLotNameListener());
        parkingLotView.addSortParkingLotCapacityListener(new SortParkingLotCapacityListener());
        parkingLotView.addSortParkingLotCurrentOccupancyListener(new SortParkingLotCurrentOccupancyListener());
        parkingLotView.addSearchListener(new SearchParkingLotListener());
        parkingLotView.addListParkingLotSelectionListener(new ListParkingLotSelectionListener());
    }

    public void showParkingLotView() {
        List<ParkingLot> parkingLotList = parkingLotDao.getListParkingLots();
        parkingLotView.setVisible(true);
        parkingLotView.showListParkingLots(parkingLotList);
    }

    /**
     * Lớp AddParkingLotListener 
     * chứa cài đặt cho sự kiện click button "Add"
     */
    class AddParkingLotListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ParkingLot parkingLot = parkingLotView.getParkingLotInfo();
            if (parkingLot != null) {
                parkingLotDao.add(parkingLot);
                parkingLotView.showParkingLot(parkingLot);
                parkingLotView.showListParkingLots(parkingLotDao.getListParkingLots());
                parkingLotView.showMessage("Thêm thành công!");
            }
        }
    }

    /**
     * Lớp EditParkingLotListener 
     * chứa cài đặt cho sự kiện click button "Edit"
     */
    class EditParkingLotListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ParkingLot parkingLot = parkingLotView.getParkingLotInfo();
            if (parkingLot != null) {
                parkingLotDao.edit(parkingLot);
                parkingLotView.showParkingLot(parkingLot);
                parkingLotView.showListParkingLots(parkingLotDao.getListParkingLots());
                parkingLotView.showMessage("Cập nhật thành công!");
            }
        }
    }

    /**
     * Lớp DeleteParkingLotListener 
     * chứa cài đặt cho sự kiện click button "Delete"
     */
    class DeleteParkingLotListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ParkingLot parkingLot = parkingLotView.getParkingLotInfo();
            if (parkingLot != null) {
                if(parkingLotDao.isHaveVehicle(parkingLot)==true){
                    parkingLotView.showMessage("Không thể xóa vì còn xe trong bãi!");
                    return;
                }
                parkingLotDao.delete(parkingLot);
                parkingLotView.clearParkingLotInfo();
                parkingLotView.showListParkingLots(parkingLotDao.getListParkingLots());
                parkingLotView.showMessage("Xóa thành công!");
            }
        }
    }

    /**
     * Lớp ClearParkingLotListener 
     * chứa cài đặt cho sự kiện click button "Clear"
     */
    class ClearParkingLotListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            parkingLotView.clearParkingLotInfo();
        }
    }

    /**
     * Lớp SortParkingLotNameListener 
     * chứa cài đặt cho sự kiện click button "Sort By Name"
     */
    class SortParkingLotNameListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            parkingLotDao.sortParkingLotByName();
            parkingLotView.showListParkingLots(parkingLotDao.getListParkingLots());
        }
    }
    
     /**
     * Lớp SortParkingLotCapacityListener 
     * chứa cài đặt cho sự kiện click button "Sort By Capacity"
     */
    class SortParkingLotCapacityListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            parkingLotDao.sortParkingLotByCapacity();
            parkingLotView.showListParkingLots(parkingLotDao.getListParkingLots());
        }
    }
    
     /**
     * Lớp SortParkingLotCurrentOccupancyListener 
     * chứa cài đặt cho sự kiện click button "Sort By current Occupancy"
     */
    class SortParkingLotCurrentOccupancyListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            parkingLotDao.sortParkingLotByCurrentOccupancy();
            parkingLotView.showListParkingLots(parkingLotDao.getListParkingLots());
        }
    }
    
    // Chứa sự kiện khi nhấn nút tìm kiếm
    class SearchParkingLotListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String type=parkingLotView.getTypeSearch(), search = parkingLotView.getFieldSearch();
            List<ParkingLot> listParkingLot=parkingLotDao.getListParkingLots();
            List<ParkingLot> listSearchParkingLot= new ArrayList<ParkingLot>();
            if(type.equals("Tên")){
                for(int i=0;i<listParkingLot.size();i++){
                    if(listParkingLot.get(i).getName().equals(search)) listSearchParkingLot.add(listParkingLot.get(i));
                }
            }else{
                for(int i=0;i<listParkingLot.size();i++){
                    if(listParkingLot.get(i).getAddress().equals(search)) listSearchParkingLot.add(listParkingLot.get(i));
                }
            }
            parkingLotView.showListParkingLots(listSearchParkingLot);
        }
    }
    /**
     * Lớp ListParkingLotSelectionListener 
     * chứa cài đặt cho sự kiện chọn parkingLot trong bảng parkingLot
     */
    class ListParkingLotSelectionListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            parkingLotView.fillParkingLotFromSelectedRow();
        }
    }
}
