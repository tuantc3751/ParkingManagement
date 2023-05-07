/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.parking.view;


import com.mycompany.parking.entity.ParkingLot;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SpringLayout;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Ad
 */
public class ParkingLotJPanel extends javax.swing.JPanel {
    private String [] columnNames = new String [] {
            "ID bãi xe", "Tên", "Địa chỉ", "Sức chứa", "Đã chứa"};
    /**
     * Creates new form ParkingLotJPanel
     */
    public ParkingLotJPanel() {
        initComponents();
        solve();
    }
    
    private void solve(){
        SpringLayout layout = new SpringLayout();
        // disable Edit and Delete buttons
        editParkingLotBtn.setEnabled(false);
        deleteParkingLotBtn.setEnabled(false);
        // enable Add button
        addParkingLotBtn.setEnabled(true);
    }
    
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
    
    /**
     * hiển thị list parkingLot vào bảng parkingLotTable
     * 
     * @param list
     */
    public void showListParkingLots(List<ParkingLot> list) {
        int size = list.size();
        // với bảng parkingLotTable có 5 cột, 
        // khởi tạo mảng 2 chiều parkingLots, trong đó:
        // số hàng: là kích thước của list parkingLot 
        // số cột: là 5
        Object [][] parkingLots = new Object[size][7];
        for (int i = 0; i < size; i++) {
            parkingLots[i][0] = list.get(i).getParkingLotId();
            parkingLots[i][1] = list.get(i).getName();
            parkingLots[i][2] = list.get(i).getAddress();
            parkingLots[i][3] = list.get(i).getCapacity();
            parkingLots[i][4] = list.get(i).getCurrentOccupancy();
        }
        parkingLotTable.setModel(new DefaultTableModel(parkingLots,columnNames));
        TableColumnModel columnModel = parkingLotTable.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(60); 
        columnModel.getColumn(0).setMaxWidth(60);
        columnModel.getColumn(1).setPreferredWidth(100);
        columnModel.getColumn(2).setPreferredWidth(100);
        columnModel.getColumn(3).setPreferredWidth(100);
        columnModel.getColumn(4).setPreferredWidth(100);
    }
    
    /**
     * điền thông tin của hàng được chọn từ bảng parkingLot 
     * vào các trường tương ứng của parkingLot.
     */
    public void fillParkingLotFromSelectedRow()  {
        // lấy chỉ số của hàng được chọn 
        int row = parkingLotTable.getSelectedRow();
        if (row >= 0) {
            parkingLotIDField.setText(parkingLotTable.getModel().getValueAt(row, 0).toString());
            nameField.setText(parkingLotTable.getModel().getValueAt(row, 1).toString());
            addressField.setText(parkingLotTable.getModel().getValueAt(row, 2).toString());
            capacityField.setText(parkingLotTable.getModel().getValueAt(row, 3).toString());
            currentOccupancyField.setText(parkingLotTable.getModel().getValueAt(row, 4).toString());
            // enable Edit and Delete buttons
            editParkingLotBtn.setEnabled(true);
            deleteParkingLotBtn.setEnabled(true);
            // disable Add button
            addParkingLotBtn.setEnabled(false);
        }
    }

    /**
     * xóa thông tin parkingLot
     */
    public void clearParkingLotInfo() {
        parkingLotIDField.setText("");
        nameField.setText("");
        addressField.setText("");
        capacityField.setText("");
        currentOccupancyField.setText("");
        // disable Edit and Delete buttons
        editParkingLotBtn.setEnabled(false);
        deleteParkingLotBtn.setEnabled(false);
        // enable Add button
        addParkingLotBtn.setEnabled(true);
    }
    
    /**
     * hiện thị thông tin parkingLot
     * 
     * @param parkingLot
     */
    public void showParkingLot(ParkingLot parkingLot) {
        parkingLotIDField.setText("" + parkingLot.getParkingLotId());
        nameField.setText("" + parkingLot.getName());
        addressField.setText("" + parkingLot.getAddress());
        capacityField.setText("" + parkingLot.getCapacity());
        currentOccupancyField.setText("" + parkingLot.getCurrentOccupancy());
        // enable Edit and Delete buttons
        editParkingLotBtn.setEnabled(true);
        deleteParkingLotBtn.setEnabled(true);
        // disable Add button
        addParkingLotBtn.setEnabled(true);
    }
    
    /**
     * lấy thông tin parkingLot
     * 
     * @return
     */
    public ParkingLot getParkingLotInfo() {
        // validate parkingLot
        if (!validateName() ||  !validateAddress() || !validateCapacity()) {
            return null;
        }
        try {
            ParkingLot parkingLot = new ParkingLot();
            if (parkingLotIDField.getText() != null && !"".equals(parkingLotIDField.getText())) {
                parkingLot.setParkingLotId(Integer.parseInt(parkingLotIDField.getText()));
            }
            parkingLot.setName(nameField.getText().trim());
            parkingLot.setAddress(addressField.getText().trim());
            parkingLot.setCapacity(Integer.parseInt(capacityField.getText()));
            parkingLot.setCurrentOccupancy(Integer.parseInt(currentOccupancyField.getText()));
            return parkingLot;
        } catch (Exception e) {
            showMessage(e.getMessage());
        }
        return null;
    }
    
    private boolean validateName() {
        String name = nameField.getText();
        if (name == null || "".equals(name.trim())) {
            nameField.requestFocus();
            showMessage("Name không được trống.");
            return false;
        }
        return true;
    }
    
    private boolean validateAddress() {
        String address = addressField.getText();
        if (address == null || "".equals(address.trim())) {
            addressField.requestFocus();
            showMessage("Address không được trống.");
            return false;
        }
        return true;
    }
    
    private boolean validateCapacity() {
        try {
            int capacity = Integer.parseInt(capacityField.getText().trim());
            if (capacity < 0) {
                capacityField.requestFocus();
                showMessage("Sức chứa không hợp lệ, sức chứa phải lớn hơn 0.");
                return false;
            }
        } catch (Exception e) {
            capacityField.requestFocus();
            showMessage("Sức chứa không hợp lệ!");
            return false;
        }
        return true;
    }

    
    public String getTypeSearch(){
        return searchComboBox.getSelectedItem().toString();
    }
    public String getFieldSearch(){
        return searchField.getText().trim();
    }
    public void actionPerformed(ActionEvent e) {
    }
    
    public void valueChanged(ListSelectionEvent e) {
    }
    
    public void addAddParkingLotListener(ActionListener listener) {
        addParkingLotBtn.addActionListener(listener);
    }
    
    public void addEdiParkingLotListener(ActionListener listener) {
        editParkingLotBtn.addActionListener(listener);
    }
    
    public void addDeleteParkingLotListener(ActionListener listener) {
        deleteParkingLotBtn.addActionListener(listener);
    }
    
    public void addClearListener(ActionListener listener) {
        clearBtn.addActionListener(listener);
    }
    public void addSortParkingLotNameListener(ActionListener listener) {
        sortParkingLotNameBtn.addActionListener(listener);
    }
    
    public void addSortParkingLotCapacityListener(ActionListener listener) {
        sortParkingLotCapacityBtn.addActionListener(listener);
    }
    
    public void addSortParkingLotCurrentOccupancyListener(ActionListener listener) {
        sortParkingLotCurrentOccupancyBtn.addActionListener(listener);
    }
    
    public void addSearchListener(ActionListener listener) {
        searchButton.addActionListener(listener);
    }
//    
//    public void addSortParkingLotGPAListener(ActionListener listener) {
//        sortParkingLotGPABtn.addActionListener(listener);
//    }
//    
//    public void addSortParkingLotNameListener(ActionListener listener) {
//        sortParkingLotNameBtn.addActionListener(listener);
//    }
    
    public void addListParkingLotSelectionListener(ListSelectionListener listener) {
        parkingLotTable.getSelectionModel().addListSelectionListener(listener);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        parkingLotIDLabel = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        addressLabel = new javax.swing.JLabel();
        capacityLabel = new javax.swing.JLabel();
        currentOccupancyLabel = new javax.swing.JLabel();
        parkingLotIDField = new javax.swing.JTextField();
        nameField = new javax.swing.JTextField();
        addressField = new javax.swing.JTextField();
        capacityField = new javax.swing.JTextField();
        currentOccupancyField = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        addParkingLotBtn = new javax.swing.JButton();
        editParkingLotBtn = new javax.swing.JButton();
        deleteParkingLotBtn = new javax.swing.JButton();
        clearBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        parkingLotTable = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        sortParkingLotNameBtn = new javax.swing.JButton();
        sortParkingLotCapacityBtn = new javax.swing.JButton();
        sortParkingLotCurrentOccupancyBtn = new javax.swing.JButton();
        searchButton = new javax.swing.JButton();
        searchComboBox = new javax.swing.JComboBox<>();
        searchField = new javax.swing.JTextField();

        jPanel1.setBackground(new java.awt.Color(255, 255, 204));

        jPanel2.setBackground(new java.awt.Color(255, 204, 204));

        parkingLotIDLabel.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        parkingLotIDLabel.setText("ID Bãi xe");

        nameLabel.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        nameLabel.setText("Tên");

        addressLabel.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        addressLabel.setText("Địa chỉ");

        capacityLabel.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        capacityLabel.setText("Sức chứa");

        currentOccupancyLabel.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        currentOccupancyLabel.setText("Đã chứa");

        parkingLotIDField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                parkingLotIDFieldActionPerformed(evt);
            }
        });
        parkingLotIDField.setEnabled(false);

        currentOccupancyField.setEnabled(false);
        currentOccupancyField.setText("0");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(parkingLotIDLabel)
                        .addGap(28, 28, 28)
                        .addComponent(parkingLotIDField, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nameLabel)
                            .addComponent(addressLabel)
                            .addComponent(currentOccupancyLabel)
                            .addComponent(capacityLabel))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(addressField, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(currentOccupancyField, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(capacityField, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(parkingLotIDLabel)
                    .addComponent(parkingLotIDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameLabel))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addressField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addressLabel))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(capacityLabel)
                    .addComponent(capacityField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(currentOccupancyLabel)
                    .addComponent(currentOccupancyField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(204, 255, 204));

        addParkingLotBtn.setText("Thêm");

        editParkingLotBtn.setText("Sửa");

        deleteParkingLotBtn.setText("Xóa");

        clearBtn.setText("Làm mới");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(deleteParkingLotBtn)
                    .addComponent(addParkingLotBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(clearBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(editParkingLotBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addParkingLotBtn)
                    .addComponent(editParkingLotBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteParkingLotBtn)
                    .addComponent(clearBtn))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        parkingLotTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID bãi xe", "Tên", "Địa chỉ", "Sức chứa", "Đã chứa"
            }
        ));
        parkingLotTable.getTableHeader().setFont(new Font("Segoe UI",Font.BOLD,12));
        parkingLotTable.getTableHeader().setOpaque(false);
        parkingLotTable.getTableHeader().setBackground(new Color(32,136,203));
        parkingLotTable.getTableHeader().setForeground(new Color(255,255,255));
        parkingLotTable.getRowHeight(25);
        jScrollPane1.setViewportView(parkingLotTable);

        jPanel4.setBackground(new java.awt.Color(204, 204, 255));

        sortParkingLotNameBtn.setText("Sắp xếp theo tên");

        sortParkingLotCapacityBtn.setText("Sắp xếp theo sức chứa");

        sortParkingLotCurrentOccupancyBtn.setText("Sắp xếp theo đã chứa");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(sortParkingLotNameBtn)
                .addGap(18, 18, 18)
                .addComponent(sortParkingLotCapacityBtn)
                .addGap(18, 18, 18)
                .addComponent(sortParkingLotCurrentOccupancyBtn)
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sortParkingLotCapacityBtn)
                    .addComponent(sortParkingLotCurrentOccupancyBtn)
                    .addComponent(sortParkingLotNameBtn))
                .addGap(56, 56, 56))
        );

        searchButton.setText("Tìm kiếm ");

        searchComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        searchComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tên", "Địa chỉ"}));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 529, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(273, 273, 273)
                .addComponent(searchComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchField)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(searchButton)
                .addGap(103, 103, 103))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(searchComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(searchButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void parkingLotIDFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_parkingLotIDFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_parkingLotIDFieldActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addParkingLotBtn;
    private javax.swing.JTextField addressField;
    private javax.swing.JLabel addressLabel;
    private javax.swing.JTextField capacityField;
    private javax.swing.JLabel capacityLabel;
    private javax.swing.JButton clearBtn;
    private javax.swing.JTextField currentOccupancyField;
    private javax.swing.JLabel currentOccupancyLabel;
    private javax.swing.JButton deleteParkingLotBtn;
    private javax.swing.JButton editParkingLotBtn;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nameField;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField parkingLotIDField;
    private javax.swing.JLabel parkingLotIDLabel;
    private javax.swing.JTable parkingLotTable;
    private javax.swing.JButton searchButton;
    private javax.swing.JComboBox<String> searchComboBox;
    private javax.swing.JTextField searchField;
    private javax.swing.JButton sortParkingLotCapacityBtn;
    private javax.swing.JButton sortParkingLotCurrentOccupancyBtn;
    private javax.swing.JButton sortParkingLotNameBtn;
    // End of variables declaration//GEN-END:variables
}
