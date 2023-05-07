/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.parking.view;

import com.mycompany.parking.dao.StatisticDao;
import com.mycompany.parking.entity.Statistic;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.JLabel;

/**
 *
 * @author Ad
 */
public class StaticJPanel extends javax.swing.JPanel {
    private StatisticDao statisticDao;
    /**
     * Creates new form StaticJPanel
     */
    public StaticJPanel() {
        initComponents();
        Locale locale = new Locale("vi", "VN");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        statisticDao =new StatisticDao();
        Statistic statistic=statisticDao.getStatistic();
        jlbSumVehicle.setText(String.valueOf(statistic.getSumVehicle()));
        jlbSumMotorbike.setText(String.valueOf(statistic.getSumMotorbike()));
        jlbSumCar.setText(String.valueOf(statistic.getSumCar()));
        jlbSumPayment.setText(currencyFormatter.format(statistic.getPaymentVehicle()));
        jlbSumMotorbikePayment.setText(currencyFormatter.format(statistic.getPaymentMotorbike()));
        jlbSumCarPayment.setText(currencyFormatter.format(statistic.getPaymentCar()));
    }

    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jlbSumVehicle = new javax.swing.JLabel();
        jlbSumPayment = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jlbSumCar = new javax.swing.JLabel();
        jlbSumCarPayment = new javax.swing.JLabel();
        jlbSumMotorbike = new javax.swing.JLabel();
        jlbSumMotorbikePayment = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(204, 255, 204));

        jLabel1.setBackground(new java.awt.Color(255, 51, 0));
        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 51));
        jLabel1.setText("Tổng số lượt xe đã gửi:");

        jLabel2.setBackground(new java.awt.Color(255, 51, 0));
        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 51));
        jLabel2.setText("Tổng tiền đã thu được:");

        jlbSumVehicle.setBackground(new java.awt.Color(255, 51, 0));
        jlbSumVehicle.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jlbSumVehicle.setForeground(new java.awt.Color(255, 0, 51));
        jlbSumVehicle.setText("0");

        jlbSumPayment.setBackground(new java.awt.Color(255, 51, 0));
        jlbSumPayment.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jlbSumPayment.setForeground(new java.awt.Color(255, 0, 51));
        jlbSumPayment.setText("0");

        jLabel5.setBackground(new java.awt.Color(255, 51, 0));
        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 0, 51));
        jLabel5.setText("Ô tô: ");

        jLabel6.setBackground(new java.awt.Color(255, 51, 0));
        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 0, 51));
        jLabel6.setText("Ô tô:");

        jLabel7.setBackground(new java.awt.Color(255, 51, 0));
        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 0, 51));
        jLabel7.setText("Xe máy: ");

        jLabel8.setBackground(new java.awt.Color(255, 51, 0));
        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 0, 51));
        jLabel8.setText("Xe máy:");

        jlbSumCar.setBackground(new java.awt.Color(255, 51, 0));
        jlbSumCar.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jlbSumCar.setForeground(new java.awt.Color(255, 0, 51));
        jlbSumCar.setText("0");

        jlbSumCarPayment.setBackground(new java.awt.Color(255, 51, 0));
        jlbSumCarPayment.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jlbSumCarPayment.setForeground(new java.awt.Color(255, 0, 51));
        jlbSumCarPayment.setText("0");

        jlbSumMotorbike.setBackground(new java.awt.Color(255, 51, 0));
        jlbSumMotorbike.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jlbSumMotorbike.setForeground(new java.awt.Color(255, 0, 51));
        jlbSumMotorbike.setText("0");

        jlbSumMotorbikePayment.setBackground(new java.awt.Color(255, 51, 0));
        jlbSumMotorbikePayment.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jlbSumMotorbikePayment.setForeground(new java.awt.Color(255, 0, 51));
        jlbSumMotorbikePayment.setText("0");

        jLabel3.setBackground(new java.awt.Color(255, 0, 255));
        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("THỐNG KÊ ĐẾN THỜI ĐIỂM HIỆN TẠI");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(20, 20, 20))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlbSumVehicle)
                    .addComponent(jlbSumPayment))
                .addGap(83, 83, 83)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlbSumCar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 4, Short.MAX_VALUE))
                    .addComponent(jlbSumCarPayment, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jlbSumMotorbikePayment, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlbSumMotorbike))
                .addGap(119, 119, 119))
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jlbSumVehicle)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7)
                    .addComponent(jlbSumCar)
                    .addComponent(jlbSumMotorbike))
                .addGap(74, 74, 74)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jlbSumPayment)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8)
                    .addComponent(jlbSumCarPayment)
                    .addComponent(jlbSumMotorbikePayment))
                .addContainerGap(105, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jlbSumCar;
    private javax.swing.JLabel jlbSumCarPayment;
    private javax.swing.JLabel jlbSumMotorbike;
    private javax.swing.JLabel jlbSumMotorbikePayment;
    private javax.swing.JLabel jlbSumPayment;
    private javax.swing.JLabel jlbSumVehicle;
    // End of variables declaration//GEN-END:variables
}
