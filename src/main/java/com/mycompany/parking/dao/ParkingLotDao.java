/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parking.dao;

import com.mycompany.parking.entity.ParkingLot;
import com.mycompany.parking.entity.ParkingLotXML;
import com.mycompany.parking.utils.FileUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Ad
 */
public class ParkingLotDao {
    private static final String PARKINGLOT_FILE_NAME = "parkingLot.xml";
    private List<ParkingLot> listParkingLots;

    public ParkingLotDao() {
        this.listParkingLots = readListParkingLots();
        if (listParkingLots == null) {
            listParkingLots = new ArrayList<ParkingLot>();
        }
    }

    /**
     * Lưu các đối tượng parkingLot vào file parkingLot.xml
     * 
     * @param parkingLots
     */
    public void writeListParkingLots(List<ParkingLot> parkingLots) {
        ParkingLotXML parkingLotXML = new ParkingLotXML();
        parkingLotXML.setParkingLot(parkingLots);
        FileUtils.writeXMLtoFile(PARKINGLOT_FILE_NAME, parkingLotXML);
    }

    /**
     * Đọc các đối tượng parkingLot từ file parkingLot.xml
     * 
     * @return list parkingLot
     */
    public List<ParkingLot> readListParkingLots() {
        List<ParkingLot> list = new ArrayList<ParkingLot>();
        ParkingLotXML parkingLotXML = (ParkingLotXML) FileUtils.readXMLFile(
                PARKINGLOT_FILE_NAME, ParkingLotXML.class);
        if (parkingLotXML != null) {
            list = parkingLotXML.getParkingLot();
        }
        return list;
    }
    

    /**
     * thêm parkingLot vào listParkingLots và lưu listParkingLots vào file
     * 
     * @param parkingLot
     */
    public void add(ParkingLot parkingLot){
        int id = 1;
        boolean[] used= new boolean[100005];
        for(int i=0;i<listParkingLots.size();i++){
            used[listParkingLots.get(i).getParkingLotId()]=true;
        }
        for(int i=1;i<=100000;i++){
            if(used[i]==false){
                id=i;
                break;
            }
        }

        parkingLot.setParkingLotId(id);
        listParkingLots.add(parkingLot);
        writeListParkingLots(listParkingLots);
    }

    /**
     * cập nhật parkingLot vào listParkingLots và lưu listParkingLots vào file
     * 
     * @param parkingLot
     */
    public void edit(ParkingLot parkingLot) {
        int size = listParkingLots.size();
        for (int i = 0; i < size; i++){
            if (listParkingLots.get(i).getParkingLotId() == parkingLot.getParkingLotId()) {
                listParkingLots.get(i).setName(parkingLot.getName());
                listParkingLots.get(i).setAddress(parkingLot.getAddress());
                listParkingLots.get(i).setCapacity(parkingLot.getCapacity());
                listParkingLots.get(i).setCurrentOccupancy(parkingLot.getCurrentOccupancy());
                writeListParkingLots(listParkingLots);
                break;
            }
        }
    }

    /**
     * xóa parkingLot từ listParkingLots và lưu listParkingLots vào file
     * 
     * @param parkingLot
     */
    
    public boolean isHaveVehicle(ParkingLot parkingLot){
        if(parkingLot.getCurrentOccupancy()>=1) return true;
        return false;
    }
    
    public boolean delete(ParkingLot parkingLot) {
        boolean isFound = false;
        int size = listParkingLots.size();
        for (int i = 0; i < size; i++) {
            if (listParkingLots.get(i).getParkingLotId() == parkingLot.getParkingLotId()) {
                parkingLot = listParkingLots.get(i);
                isFound = true;
                break;
            }
        }
        if (isFound) {
            listParkingLots.remove(parkingLot);
            writeListParkingLots(listParkingLots);
            return true;
        }
        return false;
    }

    /**
     * sắp xếp danh sách parkingLot theo name theo tứ tự tăng dần
     */
    public void sortParkingLotByName() {
        Collections.sort(listParkingLots, new Comparator<ParkingLot>() {
            @Override
            public int compare(ParkingLot parkingLot1, ParkingLot parkingLot2) {
                return parkingLot1.getName().compareTo(parkingLot2.getName());
            }
        });
    }

    /**
     * sắp xếp danh sách parkingLot theo sức chứa theo tứ tự tăng dần
     */
    public void sortParkingLotByCapacity() {
        Collections.sort(listParkingLots, new Comparator<ParkingLot>() {
            public int compare(ParkingLot parkingLot1, ParkingLot parkingLot2) {
                if (parkingLot1.getCapacity() > parkingLot2.getCapacity()) {
                    return 1;
                }
                return -1;
            }
        });
    }
    
    /**
     * sắp xếp danh sách parkingLot theo số vị trí có xe đỗ theo tứ tự tăng dần
     */
    public void sortParkingLotByCurrentOccupancy() {
        Collections.sort(listParkingLots, new Comparator<ParkingLot>() {
            public int compare(ParkingLot parkingLot1, ParkingLot parkingLot2) {
                if (parkingLot1.getCurrentOccupancy() > parkingLot2.getCurrentOccupancy()) {
                    return 1;
                }
                return -1;
            }
        });
    }

    public List<ParkingLot> getListParkingLots() {
        return listParkingLots;
    }

    public void setListParkingLots(List<ParkingLot> listParkingLots) {
        this.listParkingLots = listParkingLots;
    }
}
