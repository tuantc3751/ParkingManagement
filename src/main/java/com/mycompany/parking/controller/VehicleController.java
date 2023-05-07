/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parking.controller;


import com.mycompany.parking.dao.ParkingLotDao;
import com.mycompany.parking.dao.StatisticDao;
import com.mycompany.parking.dao.VehicleDao;
import com.mycompany.parking.entity.ParkingLot;
import com.mycompany.parking.entity.Statistic;
import com.mycompany.parking.entity.Vehicle;
import com.mycompany.parking.view.VehicleJPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Ad
 */
public class VehicleController{
    private VehicleDao vehicleDao;
    private VehicleJPanel vehicleView;
    private ParkingLotDao parkingLotDao;
    private StatisticDao statisticDao;
    
    public VehicleController(VehicleJPanel view){
        this.vehicleView = view;
        vehicleDao = new VehicleDao();
        vehicleView.showListVehicles(vehicleDao.getListVehicles());
        
        vehicleView.addAddVehicleListener(new AddVehicleListener());
        vehicleView.addEdiVehicleListener(new EditVehicleListener());
        vehicleView.addDeleteVehicleListener(new DeleteVehicleListener());
        vehicleView.addClearListener(new ClearVehicleListener());
        vehicleView.addSortVehicleParkingLotListener(new SortVehicleParkingLotListener());
        vehicleView.addSortVehicleTypeListener(new SortVehicleTypeListener());
        vehicleView.addSortVehicleEnterTimeListener(new SortVehicleEnterTimeListener());
        vehicleView.addPaymentListener(new PaymentVehicleListener());
        vehicleView.addSearchListener(new SearchVehicleListener());
        vehicleView.addListVehicleSelectionListener(new ListVehicleSelectionListener());
    }

    public void showVehicleView() {
        List<Vehicle> vehicleList = vehicleDao.getListVehicles();
        vehicleView.setVisible(true);
        vehicleView.showListVehicles(vehicleList);
    }

    /**
     * Lớp AddVehicleListener 
     * chứa cài đặt cho sự kiện click button "Add"
     */
    class AddVehicleListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Vehicle vehicle = vehicleView.getVehicleInfo();
            if (vehicle != null) {
                if(vehicleDao.isFull(vehicle)==true){
                    vehicleView.showMessage("Bãi xe đã hết chỗ trống. Vui lòng chọn bãi xe khác!");
                    return;
                }
                vehicleDao.add(vehicle);
                vehicleView.showVehicle(vehicle);
                vehicleView.showListVehicles(vehicleDao.getListVehicles());
                vehicleView.showMessage("Thêm thành công!");
                
                // Tăng phần đã chữa của bãi đỗ xe thêm 1
                parkingLotDao=new ParkingLotDao();
                List<ParkingLot> listParkingLot=parkingLotDao.getListParkingLots();
                for(int i=0;i<listParkingLot.size();i++){
                    int temp=listParkingLot.get(i).getCurrentOccupancy();
                    if(listParkingLot.get(i).getName().equals(vehicle.getParkingLot())) listParkingLot.get(i).setCurrentOccupancy(temp+1);
                }
                parkingLotDao.writeListParkingLots(listParkingLot);
            }
        }
    }

    /**
     * Lớp EditVehicleListener 
     * chứa cài đặt cho sự kiện click button "Edit"
     */
    class EditVehicleListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Vehicle vehicle = vehicleView.getVehicleInfo();
            if (vehicle != null) {
                vehicleDao.edit(vehicle);
                vehicleView.showVehicle(vehicle);
                vehicleView.showListVehicles(vehicleDao.getListVehicles());
                vehicleView.showMessage("Cập nhật thành công!");
            }
        }
    }
//
    /**
     * Lớp DeleteVehicleListener 
     * chứa cài đặt cho sự kiện click button "Delete"
     */
    class DeleteVehicleListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Vehicle vehicle = vehicleView.getVehicleInfo();
            if (vehicle != null) {
                vehicleDao.delete(vehicle);
                vehicleView.clearVehicleInfo();
                vehicleView.showListVehicles(vehicleDao.getListVehicles());
                vehicleView.showMessage("Xóa thành công!");
                
                //Giảm phần đã chữa của bãi đỗ xe đi 1
                parkingLotDao=new ParkingLotDao();
                List<ParkingLot> listParkingLot=parkingLotDao.getListParkingLots();
                for(int i=0;i<listParkingLot.size();i++){
                    int temp=listParkingLot.get(i).getCurrentOccupancy();
                    if(listParkingLot.get(i).getName().equals(vehicle.getParkingLot())) listParkingLot.get(i).setCurrentOccupancy(temp-1);
                }
                parkingLotDao.writeListParkingLots(listParkingLot);
            }
        }
    }

    /**
     * Lớp ClearVehicleListener 
     * chứa cài đặt cho sự kiện click button "Clear"
     */
    class ClearVehicleListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            vehicleView.clearVehicleInfo();
        }
    }
    
    /**
     * Lớp SortVehicleParkingLotListener 
     * chứa cài đặt cho sự kiện click button "Sort By ParkingLot"
     */
    class SortVehicleParkingLotListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            vehicleDao.sortVehicleByParkingLot();
            vehicleView.showListVehicles(vehicleDao.getListVehicles());
        }
    }
    
    /**
     * Lớp SortVehicleTypeListener 
     * chứa cài đặt cho sự kiện click button "Sort By Type"
     */
    class SortVehicleTypeListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            vehicleDao.sortVehicleByType();
            vehicleView.showListVehicles(vehicleDao.getListVehicles());
        }
    }
    
    /**
     * Lớp SortVehicleEnterTimeListener 
     * chứa cài đặt cho sự kiện click button "Sort By EnterTime"
     */
    class SortVehicleEnterTimeListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            vehicleDao.sortVehicleByEnterTime();
            vehicleView.showListVehicles(vehicleDao.getListVehicles());
        }
    }
    
    /**
     * Lớp Listener 
     * chứa cài đặt cho sự kiện click button "Sort By EnterTime"
     */
    class PaymentVehicleListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Vehicle vehicle = vehicleView.getVehicleInfo();
            double totalPrice=0;
            //DecimalFormat df = new DecimalFormat("#.###");
            Locale locale = new Locale("vi", "VN");
            NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
            if(vehicle!=null){
                Date enterTime=vehicle.getEnterTime();
                Date exitTime=new Date();
                double pricePerHour = 1000;
                long diffInMillies = exitTime.getTime() - enterTime.getTime(); // tính khoảng thời gian giữa hai thời điểm
                long diffInMinutes = diffInMillies / (60 * 1000); // tính số phút
                totalPrice = diffInMinutes * pricePerHour / 60.0; // tính tổng chi phí (giá tiền trên mỗi phút là 100 đồng/1 giờ)
            }
            vehicleView.showMessage("Phí gửi xe của bạn là: " + currencyFormatter.format(totalPrice));
            
            //Tăng tiền thu được
            statisticDao=new StatisticDao();
            Statistic statistic = statisticDao.getStatistic();
            statistic.setPaymentVehicle(statistic.getPaymentVehicle()+totalPrice);
            if(vehicle.getType().equals("Xe máy")){
                statistic.setPaymentMotorbike(statistic.getPaymentMotorbike()+totalPrice);
            }else{
                statistic.setPaymentCar(statistic.getPaymentCar()+totalPrice);
            }
            statisticDao.setStatistic(statistic);
            statisticDao.writeStatistic(statistic);
            //Xóa vehicle sau khi thanh toán
            if (vehicle != null) {
                vehicleDao.delete(vehicle);
                vehicleView.clearVehicleInfo();
                vehicleView.showListVehicles(vehicleDao.getListVehicles());
                
                //Giảm phần đã chữa của bãi đỗ xe đi 1
                parkingLotDao=new ParkingLotDao();
                List<ParkingLot> listParkingLot=parkingLotDao.getListParkingLots();
                for(int i=0;i<listParkingLot.size();i++){
                    int temp=listParkingLot.get(i).getCurrentOccupancy();
                    if(listParkingLot.get(i).getName().equals(vehicle.getParkingLot())) listParkingLot.get(i).setCurrentOccupancy(temp-1);
                }
                parkingLotDao.writeListParkingLots(listParkingLot);
            }
        }
    }
    
    /**
     * Lớp SortVehicleEnterTimeListener 
     * chứa cài đặt cho sự kiện click button "Sort By EnterTime"
     */
    class SearchVehicleListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String type=vehicleView.getTypeSearch(), search = vehicleView.getFieldSearch();
            List<Vehicle> listVehicle=vehicleDao.getListVehicles();
            List<Vehicle> listSearchVehicle= new ArrayList<Vehicle>();
            if(type.equals("Bãi xe")){
                for(int i=0;i<listVehicle.size();i++){
                    if(listVehicle.get(i).getParkingLot().equals(search)) listSearchVehicle.add(listVehicle.get(i));
                }
            }else if(type.equals("Loại xe")){
                for(int i=0;i<listVehicle.size();i++){
                    if(listVehicle.get(i).getType().equals(search)) listSearchVehicle.add(listVehicle.get(i));
                }
            }else{
                for(int i=0;i<listVehicle.size();i++){
                    if(listVehicle.get(i).getBrand().equals(search)) listSearchVehicle.add(listVehicle.get(i));
                }
            }
            vehicleView.showListVehicles(listSearchVehicle);
        }
    }
  
    /**
     * Lớp ListVehicleSelectionListener 
     * chứa cài đặt cho sự kiện chọn vehicle trong bảng vehicle
     */
    class ListVehicleSelectionListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            vehicleView.fillVehicleFromSelectedRow();
        }
    }
}
