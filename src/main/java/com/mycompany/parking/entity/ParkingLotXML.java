/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parking.entity;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ad
 */
@XmlRootElement(name = "parkingLots")
@XmlAccessorType(XmlAccessType.FIELD)
public class ParkingLotXML {
    private List<ParkingLot> parkingLots;

    public List<ParkingLot> getParkingLot() {
        return parkingLots;
    }

    public void setParkingLot(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    
}
