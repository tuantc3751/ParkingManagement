/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parking.entity;

/**
 *
 * @author Ad
 */
public class Statistic {
    int sumVehicle,sumMotorbike,sumCar;
    double paymentVehicle,paymentMotorbike,paymentCar;
    
    public Statistic() {
    }

    public Statistic(int sumVehicle, int sumMotorbike, int sumCar, double paymentVehicle, double paymentMotorbike, double paymentCar) {
        this.sumVehicle = sumVehicle;
        this.sumMotorbike = sumMotorbike;
        this.sumCar = sumCar;
        this.paymentVehicle = paymentVehicle;
        this.paymentMotorbike = paymentMotorbike;
        this.paymentCar = paymentCar;
    }

    public int getSumVehicle() {
        return sumVehicle;
    }

    public void setSumVehicle(int sumVehicle) {
        this.sumVehicle = sumVehicle;
    }

    public int getSumMotorbike() {
        return sumMotorbike;
    }

    public void setSumMotorbike(int sumMotorbike) {
        this.sumMotorbike = sumMotorbike;
    }

    public int getSumCar() {
        return sumCar;
    }

    public void setSumCar(int sumCar) {
        this.sumCar = sumCar;
    }

    public double getPaymentVehicle() {
        return paymentVehicle;
    }

    public void setPaymentVehicle(double paymentVehicle) {
        this.paymentVehicle = paymentVehicle;
    }

    public double getPaymentMotorbike() {
        return paymentMotorbike;
    }

    public void setPaymentMotorbike(double paymentMotorbike) {
        this.paymentMotorbike = paymentMotorbike;
    }

    public double getPaymentCar() {
        return paymentCar;
    }

    public void setPaymentCar(double paymentCar) {
        this.paymentCar = paymentCar;
    }
}
