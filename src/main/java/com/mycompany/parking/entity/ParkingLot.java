/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parking.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ad
 */
@XmlRootElement(name = "parkingLot")
@XmlAccessorType(XmlAccessType.FIELD)
public class ParkingLot {
    private int parkingLotId;
    private String name;
    private String address;
    private int capacity;
    private int currentOccupancy;

    public ParkingLot() {
    }

    public ParkingLot(int parkingLotId, String name, String address, int capacity, int currentOccupancy) {
        this.parkingLotId = parkingLotId;
        this.name = name;
        this.address = address;
        this.capacity = capacity;
        this.currentOccupancy = currentOccupancy;
    }

    public int getParkingLotId() {
        return parkingLotId;
    }

    public void setParkingLotId(int parkingLotId) {
        this.parkingLotId = parkingLotId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCurrentOccupancy() {
        return currentOccupancy;
    }

    // phương thức tính toán sức chứa còn trống của bãi đỗ xe
    public void setCurrentOccupancy(int currentOccupancy) {
        this.currentOccupancy = currentOccupancy;
    }
    
    public int getRemainingCapacity() {
        return capacity - currentOccupancy;
    }
}
