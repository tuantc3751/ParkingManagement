/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parking.dao;

import com.mycompany.parking.entity.ParkingLot;
import com.mycompany.parking.entity.ParkingLotXML;
import com.mycompany.parking.entity.Statistic;
import com.mycompany.parking.entity.StatisticXML;
import com.mycompany.parking.utils.FileUtils;

/**
 *
 * @author Ad
 */
public class StatisticDao {
    private static final String STATISTIC_FILE_NAME = "statistic.xml";
    private Statistic statistic;
    public StatisticDao() {
        this.statistic= readStatistic();
    }
    
    public Statistic readStatistic() {
        Statistic statistic = new Statistic();
        StatisticXML statisticXML = (StatisticXML) FileUtils.readXMLFile(STATISTIC_FILE_NAME, StatisticXML.class);
        if (statisticXML != null) {
            statistic = statisticXML.getStatistic();
        }
        return statistic;
    }
    
    public void writeStatistic(Statistic statistic) {
        StatisticXML statisticXML = new StatisticXML();
        statisticXML.setStatistic(statistic);
        FileUtils.writeXMLtoFile(STATISTIC_FILE_NAME, statisticXML);
    }

    public Statistic getStatistic() {
        return statistic;
    }

    public void setStatistic(Statistic statistic) {
        this.statistic = statistic;
    }
}
