/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parking.dao;


import com.mycompany.parking.entity.ParkingLot;
import com.mycompany.parking.entity.Statistic;
import com.mycompany.parking.entity.Vehicle;
import com.mycompany.parking.entity.VehicleXML;
import com.mycompany.parking.utils.FileUtils;
import com.mycompany.parking.view.StaticJPanel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Ad
 */
public class VehicleDao {
    private static final String VEHICLE_FILE_NAME = "vehicle.xml";
    private List<Vehicle> listVehicles;
    private ParkingLotDao parkingLotDao;
    private StatisticDao statisticDao;

    public VehicleDao() {
        statisticDao = new StatisticDao();
        this.listVehicles = readListVehicles();
        if (listVehicles == null) {
            listVehicles = new ArrayList<Vehicle>();
        }
    }

    /**
     * Lưu các đối tượng vehicle vào file vehicle.xml
     * 
     * @param vehicles
     */
    public void writeListVehicles(List<Vehicle> vehicles) {
        VehicleXML vehicleXML = new VehicleXML();
        vehicleXML.setVehicle(vehicles);
        FileUtils.writeXMLtoFile(VEHICLE_FILE_NAME, vehicleXML);
    }

    /**
     * Đọc các đối tượng vehicle từ file vehicle.xml
     * 
     * @return list vehicle
     */
    public List<Vehicle> readListVehicles() {
        List<Vehicle> list = new ArrayList<Vehicle>();
        VehicleXML vehicleXML = (VehicleXML) FileUtils.readXMLFile(
                VEHICLE_FILE_NAME, VehicleXML.class);
        if (vehicleXML != null) {
            list = vehicleXML.getVehicle();
        }
        return list;
    }
    
    public boolean isFull(Vehicle vehicle){
        parkingLotDao= new ParkingLotDao();
        List<ParkingLot> listParkingLot=parkingLotDao.getListParkingLots();
        for(int i=0;i<listParkingLot.size();i++){
            if(listParkingLot.get(i).getName().equals(vehicle.getParkingLot())){
                int currentOccupancy=listParkingLot.get(i).getCurrentOccupancy();
                int capacity=listParkingLot.get(i).getCapacity();
                if(currentOccupancy>=capacity){
                    return true;
                }else{
                    return false;
                }
            }
        }
        return false;
    }

    /**
     * thêm vehicle vào listVehicles và lưu listVehicles vào file
     * 
     * @param vehicle
     */
    public void add(Vehicle vehicle) {
        int id = 1;
        boolean[] used= new boolean[100005];
        for(int i=0;i<listVehicles.size();i++){
            used[listVehicles.get(i).getTicketId()]=true;
        }
        for(int i=1;i<=100000;i++){
            if(used[i]==false){
                id=i;
                break;
            }
        }
        vehicle.setTicketId(id);
        listVehicles.add(vehicle);
        writeListVehicles(listVehicles);
        
        //Tăng thống kê lên 1
        Statistic statistic = statisticDao.getStatistic();
        statistic.setSumVehicle(statistic.getSumVehicle()+1);
        if(vehicle.getType().equals("Xe máy")){
            statistic.setSumMotorbike(statistic.getSumMotorbike()+1);
        }else{
            statistic.setSumCar(statistic.getSumCar()+1);
        }
        statisticDao.setStatistic(statistic);
        statisticDao.writeStatistic(statistic);
    }

    /**
     * cập nhật vehicle vào listVehicles và lưu listVehicles vào file
     * 
     * @param vehicle
     */
    public void edit(Vehicle vehicle) {
        int size = listVehicles.size();
        for (int i = 0; i < size; i++) {
            if (listVehicles.get(i).getTicketId() == vehicle.getTicketId() && listVehicles.get(i).getParkingLot().equals(vehicle.getParkingLot())) {
                listVehicles.get(i).setLicensePlate(vehicle.getLicensePlate());
                listVehicles.get(i).setType(vehicle.getType());
                listVehicles.get(i).setBrand(vehicle.getBrand());
                listVehicles.get(i).setModel(vehicle.getModel());
                listVehicles.get(i).setEnterTime(vehicle.getEnterTime());
                writeListVehicles(listVehicles);
                break;
            }
        }
    }

    /**
     * xóa vehicle từ listVehicles và lưu listVehicles vào file
     * 
     * @param vehicle
     */
    public boolean delete(Vehicle vehicle) {
        boolean isFound = false;
        int size = listVehicles.size();
        for (int i = 0; i < size; i++) {
            if (listVehicles.get(i).getTicketId() == vehicle.getTicketId() && listVehicles.get(i).getParkingLot().equals(vehicle.getParkingLot())) {
                vehicle = listVehicles.get(i);
                isFound = true;
                break;
            }
        }
        if (isFound) {
            listVehicles.remove(vehicle);
            writeListVehicles(listVehicles);
            return true;
        }
        return false;
    }

    /**
     * sắp xếp danh sách vehicle theo bãi đỗ xe theo tứ tự tăng dần
     */
    public void sortVehicleByParkingLot() {
        Collections.sort(listVehicles, new Comparator<Vehicle>() {
            @Override
            public int compare(Vehicle vehicle1, Vehicle vehicle2) {
                return vehicle1.getParkingLot().compareTo(vehicle2.getParkingLot());
            }
        });
    }
    
    /**
     * sắp xếp danh sách vehicle theo loại xe
     */
    public void sortVehicleByType() {
        Collections.sort(listVehicles, new Comparator<Vehicle>() {
            @Override
            public int compare(Vehicle vehicle1, Vehicle vehicle2) {
                return vehicle1.getType().compareTo(vehicle2.getType());
            }
        });
    }
    
    /**
     * sắp xếp danh sách vehicle theo hãng xe theo tứ tự tăng dần
     */
    public void sortVehicleByBrand() {
        Collections.sort(listVehicles, new Comparator<Vehicle>() {
            @Override
            public int compare(Vehicle vehicle1, Vehicle vehicle2) {
                return vehicle1.getBrand().compareTo(vehicle2.getBrand());
            }
        });
    }
    
    /**
     * sắp xếp danh sách vehicle theo thời gian vào bãi theo tứ tự tăng dần
     */
    public void sortVehicleByEnterTime() {
        Collections.sort(listVehicles, new Comparator<Vehicle>() {
            @Override
            public int compare(Vehicle vehicle1, Vehicle vehicle2) {
                return vehicle1.getEnterTime().compareTo(vehicle2.getEnterTime());
            }
        });
    }
    
    public List<Vehicle> getListVehicles() {
        return listVehicles;
    }

    public void setListVehicles(List<Vehicle> listVehicles) {
        this.listVehicles = listVehicles;
    }
}
