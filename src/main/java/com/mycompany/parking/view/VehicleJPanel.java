/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.parking.view;

import com.mycompany.parking.dao.ParkingLotDao;
import com.mycompany.parking.entity.ParkingLot;
import com.mycompany.parking.entity.Vehicle;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SpringLayout;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Ad
 */
public class VehicleJPanel extends javax.swing.JPanel {
    private ParkingLotDao parkingLotDao;
    private String [] columnNames = new String [] {
            "Mã số vé", "Bãi xe", "Biển số", "Loại xe", "Hãng xe","Kiểu xe","Thời gian vào bãi"};
    /**
     * Creates new form VehicleJPanel
     */
    public VehicleJPanel() {
        parkingLotDao=new ParkingLotDao();
        initComponents();
        
        solve();
    }
    
    private void solve(){
        SpringLayout layout = new SpringLayout();
        // disable Edit and Delete buttons
        editVehicleBtn.setEnabled(false);
        deleteVehicleBtn.setEnabled(false);
        // enable Add button
        addVehicleBtn.setEnabled(true);
    }
    
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
    
    /**
     * hiển thị list vehicle vào bảng vehicleTable
     * 
     * @param list
     */
    public void showListVehicles(List<Vehicle> list) {
        int size = list.size();
        // với bảng vehicleTable có 7 cột, 
        // khởi tạo mảng 2 chiều vehicles, trong đó:
        // số hàng: là kích thước của list vehicle 
        // số cột: là 7
        Object [][] vehicles = new Object[size][7];
        for (int i = 0; i < size; i++) {
            vehicles[i][0] = list.get(i).getTicketId();
            vehicles[i][1] = list.get(i).getParkingLot();
            vehicles[i][2] = list.get(i).getLicensePlate();
            vehicles[i][3] = list.get(i).getType();
            vehicles[i][4] = list.get(i).getBrand();
            vehicles[i][5] = list.get(i).getModel();
            //Chuyển từ Date sang String
            Date date=list.get(i).getEnterTime();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String dateString = dateFormat.format(date);
            vehicles[i][6] = dateString;
        }
        vehicleTable.setModel(new DefaultTableModel(vehicles,columnNames));
        TableColumnModel columnModel = vehicleTable.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(60); 
        columnModel.getColumn(0).setMaxWidth(60);
        columnModel.getColumn(1).setPreferredWidth(50);
        columnModel.getColumn(2).setPreferredWidth(50);
        columnModel.getColumn(3).setPreferredWidth(50);
        columnModel.getColumn(4).setPreferredWidth(50);
        columnModel.getColumn(5).setPreferredWidth(50);
        columnModel.getColumn(6).setPreferredWidth(90);
    }
    
    /**
     * điền thông tin của hàng được chọn từ bảng vehicle 
     * vào các trường tương ứng của vehicle.
     */
    public void fillVehicleFromSelectedRow()  {
        // lấy chỉ số của hàng được chọn 
        int row = vehicleTable.getSelectedRow();
        if (row >= 0) {
            try {
                ticketIdField.setText(vehicleTable.getModel().getValueAt(row, 0).toString());
                parkingLotComboBox.setSelectedItem(vehicleTable.getModel().getValueAt(row, 1));
                licensePlateField.setText(vehicleTable.getModel().getValueAt(row, 2).toString());
                typeComboBox.setSelectedItem(vehicleTable.getModel().getValueAt(row, 3));
                brandField.setText(vehicleTable.getModel().getValueAt(row, 4).toString());
                modelField.setText(vehicleTable.getModel().getValueAt(row, 5).toString());
                //Chuyển từ String sang Date
                String dateString=vehicleTable.getModel().getValueAt(row, 6).toString();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date date = dateFormat.parse(dateString);
                //Chuyển từ Date sang 1 ô kiểu Jspinner
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                int second = calendar.get(Calendar.SECOND);
                
                SpinnerDateModel model = new SpinnerDateModel();
                calendar.set(0, 0, 0, hour, minute, second);
                model.setValue(calendar.getTime());
                
                JSpinner.DateEditor editor = new JSpinner.DateEditor(timeSpinner, "HH:mm:ss");
                timeSpinner.setEditor(editor);
                timeSpinner.setModel(model);
                //Chuyển từ Date 1 ô kiểu JDateChooser
                dateChooser.setDate(date);
                dateChooser.setDateFormatString("dd/MM/yyyy");
                // enable Edit and Delete buttons
                editVehicleBtn.setEnabled(true);
                deleteVehicleBtn.setEnabled(true);
                // disable Add button
                addVehicleBtn.setEnabled(true);
                paymentBtn.setEnabled(true);
            } catch (ParseException ex) {
                Logger.getLogger(VehicleJPanel.class.getName()).log(Level.SEVERE, null, ex);
            }  
        }
        
    }

    /**
     * xóa thông tin vehicle
     */
    public void clearVehicleInfo() {
        ticketIdField.setText("");
        parkingLotComboBox.setSelectedIndex(0);
        licensePlateField.setText("");
        typeComboBox.setSelectedIndex(0);
        brandField.setText("");
        modelField.setText("");
        // Tạo đối tượng Date từ dữ liệu đầu vào
        Date date = new Date(); 
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // Thiết lập giá trị cho ô nhập thời gian (JSpinner)
        timeSpinner.setEditor(new JSpinner.DateEditor(timeSpinner, "HH:mm:ss"));
        timeSpinner.setValue(calendar.getTime());
        // Thiết lập giá trị cho ô nhập ngày (JDateChooser)
        dateChooser.setDate(calendar.getTime());
        // disable Edit and Delete buttons
        editVehicleBtn.setEnabled(false);
        deleteVehicleBtn.setEnabled(false);
        // enable Add button
        addVehicleBtn.setEnabled(true);
    }
    
    /**
     * hiện thị thông tin vehicle
     * 
     * @param vehicle
     */
    public void showVehicle(Vehicle vehicle) {
        ticketIdField.setText("" + vehicle.getTicketId());
        parkingLotComboBox.setSelectedItem(vehicle.getParkingLot());
        licensePlateField.setText("" + vehicle.getLicensePlate());
        typeComboBox.setSelectedItem(vehicle.getType());
        brandField.setText(""+vehicle.getBrand());
        modelField.setText(""+vehicle.getModel());
        // Tạo đối tượng Date từ dữ liệu đầu vào
        Date inputDate = vehicle.getEnterTime(); // Thay thế bằng đối tượng Date của bạn
        // Tạo đối tượng Calendar và thiết lập giá trị ngày và giờ
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(inputDate);

        // Thiết lập giá trị cho ô nhập thời gian (JSpinner)
        timeSpinner.setEditor(new JSpinner.DateEditor(timeSpinner, "HH:mm:ss"));
        timeSpinner.setValue(calendar.getTime());

        // Thiết lập giá trị cho ô nhập ngày (JDateChooser)
        dateChooser.setDate(calendar.getTime());
        // enable Edit and Delete buttons
        editVehicleBtn.setEnabled(true);
        deleteVehicleBtn.setEnabled(true);
        // disable Add button
        addVehicleBtn.setEnabled(true);
    }
    
    /**
     * lấy thông tin vehicle
     * 
     * @return
     */
    public Vehicle getVehicleInfo() {
        // validate vehicle
        if (!validateLicensePlate()||!validateBrand()||!validateEnterTime()||!validateDate()) {
            return null;
        }
        try {
            Vehicle vehicle = new Vehicle();
            if (ticketIdField.getText() != null && !"".equals(ticketIdField.getText())) {
                vehicle.setTicketId(Integer.parseInt(ticketIdField.getText()));
            }
            vehicle.setParkingLot(parkingLotComboBox.getSelectedItem().toString());
            vehicle.setLicensePlate(licensePlateField.getText().trim());
            vehicle.setType(typeComboBox.getSelectedItem().toString());
            vehicle.setBrand(brandField.getText().trim());
            vehicle.setModel(modelField.getText().trim());
            // Lấy giá trị từ ô nhập thời gian (JSpinner)
            Date timeValue = (Date) timeSpinner.getValue();
            // Lấy giá trị từ ô nhập ngày (JDateChooser)
            Date dateValue = dateChooser.getDate();
            // Tạo một đối tượng Calendar và thiết lập giá trị ngày và giờ
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateValue);
            calendar.set(Calendar.HOUR_OF_DAY, timeValue.getHours());
            calendar.set(Calendar.MINUTE, timeValue.getMinutes());
            calendar.set(Calendar.SECOND, timeValue.getSeconds());
            // Lưu giá trị vào biến kiểu Date
            Date combinedValue = calendar.getTime();
            vehicle.setEnterTime(combinedValue);
            return vehicle;
        } catch (Exception e) {
            showMessage(e.getMessage());
        }
        return null;
    }
    
    private boolean validateLicensePlate() {
        String licensePlate = licensePlateField.getText();
        if (licensePlate == null || "".equals(licensePlate.trim())) {
            licensePlateField.requestFocus();
            showMessage("Biển số xe không được trống.");
            return false;
        }
        return true;
    }
    
    private boolean validateBrand() {
        String brand = brandField.getText();
        if (brand == null || "".equals(brand.trim())) {
            brandField.requestFocus();
            showMessage("Hãng xe không được trống.");
            return false;
        }
        return true;
    }
    
    private boolean validateEnterTime() {
        // Lấy giá trị từ ô nhập thời gian (JSpinner)
        Date timeValue = (Date) timeSpinner.getValue();
        // Lấy giá trị từ ô nhập ngày (JDateChooser)
        if(validateDate()==false){
            return false;
        }
        Date dateValue = dateChooser.getDate();
        // Tạo một đối tượng Calendar và thiết lập giá trị ngày và giờ
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateValue);
        calendar.set(Calendar.HOUR_OF_DAY, timeValue.getHours());
        calendar.set(Calendar.MINUTE, timeValue.getMinutes());
        calendar.set(Calendar.SECOND, timeValue.getSeconds());
        // Lưu giá trị vào biến kiểu Date
        Date enterTime = calendar.getTime();
        Date currentTime = new Date();
        int compare = enterTime.compareTo(currentTime);
        if(compare > 0) {
            showMessage("Thời gian vào bãi không được sau thời gian hiện tại");
            return false;
        } 
        return true;
    }
    
    private boolean validateDate() {
        if (dateChooser.getDate() == null) {
            showMessage("Ngày vào bãi không được trống.");
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
    
    public void addAddVehicleListener(ActionListener listener) {
        addVehicleBtn.addActionListener(listener);
    }
    
    public void addEdiVehicleListener(ActionListener listener) {
        editVehicleBtn.addActionListener(listener);
    }
    
    public void addDeleteVehicleListener(ActionListener listener) {
        deleteVehicleBtn.addActionListener(listener);
    }
    
    public void addClearListener(ActionListener listener) {
        clearBtn.addActionListener(listener);
    }
    public void addSortVehicleParkingLotListener(ActionListener listener) {
        sortVehicleParkingLotBtn.addActionListener(listener);
    }
    
    public void addSortVehicleTypeListener(ActionListener listener) {
        sortVehicleTypeBtn.addActionListener(listener);
    }
    
    public void addSortVehicleEnterTimeListener(ActionListener listener) {
        sortVehicleEnterTimeBtn.addActionListener(listener);
    }
    
    public void addPaymentListener(ActionListener listener) {
        paymentBtn.addActionListener(listener);
    }
    
    public void addSearchListener(ActionListener listener) {
        searchButton.addActionListener(listener);
    }
//    
//    public void addSortVehicleGPAListener(ActionListener listener) {
//        sortVehicleGPABtn.addActionListener(listener);
//    }
//    
//    public void addSortVehicleNameListener(ActionListener listener) {
//        sortVehicleNameBtn.addActionListener(listener);
//    }
    
    public void addListVehicleSelectionListener(ListSelectionListener listener) {
        vehicleTable.getSelectionModel().addListSelectionListener(listener);
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
        ticketIdLabel = new javax.swing.JLabel();
        ticketIdField = new javax.swing.JTextField();
        parkingLotLabel = new javax.swing.JLabel();
        parkingLotComboBox = new javax.swing.JComboBox<>();
        licensePlateLabel = new javax.swing.JLabel();
        licensePlateField = new javax.swing.JTextField();
        typeLabel = new javax.swing.JLabel();
        typeComboBox = new javax.swing.JComboBox<>();
        brandLabel = new javax.swing.JLabel();
        brandField = new javax.swing.JTextField();
        modelLabel = new javax.swing.JLabel();
        modelField = new javax.swing.JTextField();
        enterTimeLabel = new javax.swing.JLabel();
        Date date= new Date();
        SpinnerDateModel sm = new SpinnerDateModel(date,null,null,Calendar.HOUR_OF_DAY);
        timeSpinner = new javax.swing.JSpinner(sm);
        enterDateLabel = new javax.swing.JLabel();
        dateChooser = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        vehicleTable = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        addVehicleBtn = new javax.swing.JButton();
        editVehicleBtn = new javax.swing.JButton();
        deleteVehicleBtn = new javax.swing.JButton();
        clearBtn = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        sortVehicleParkingLotBtn = new javax.swing.JButton();
        sortVehicleTypeBtn = new javax.swing.JButton();
        sortVehicleEnterTimeBtn = new javax.swing.JButton();
        searchButton = new javax.swing.JButton();
        searchComboBox = new javax.swing.JComboBox<>();
        searchField = new javax.swing.JTextField();
        paymentBtn = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 204));

        ticketIdLabel.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        ticketIdLabel.setText("Mã số vé");

        ticketIdField.setEnabled(false);

        parkingLotLabel.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        parkingLotLabel.setText("Bãi xe");

        parkingLotComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        List<ParkingLot> listParkingLots= parkingLotDao.getListParkingLots();
        String[] comboBoxName=new String[listParkingLots.size()];
        for(int i=0;i<listParkingLots.size();i++){
            comboBoxName[i]=listParkingLots.get(i).getName();
        }
        parkingLotComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(comboBoxName));

        licensePlateLabel.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        licensePlateLabel.setText("Biển số");

        typeLabel.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        typeLabel.setText("Loại xe");

        typeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Xe máy", "Ô tô" }));

        brandLabel.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        brandLabel.setText("Hãng xe");

        modelLabel.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        modelLabel.setText("Kiểu xe");

        enterTimeLabel.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        enterTimeLabel.setText("Giờ vào bãi");

        JSpinner.DateEditor de=new JSpinner.DateEditor(timeSpinner, "HH:mm:ss");
        timeSpinner.setEditor(de);

        enterDateLabel.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        enterDateLabel.setText("Ngày vào bãi");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ticketIdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(parkingLotLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(licensePlateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(typeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(modelLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(brandLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(34, 34, 34))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(enterTimeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(26, 26, 26)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(typeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(ticketIdField, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(parkingLotComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(modelField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                                .addComponent(licensePlateField, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(brandField, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(timeSpinner)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(enterDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(9, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ticketIdField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ticketIdLabel))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(parkingLotComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(parkingLotLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(licensePlateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(licensePlateLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(typeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(typeLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(brandField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(brandLabel))
                .addGap(9, 9, 9)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(modelField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modelLabel))
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(timeSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(enterTimeLabel))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(enterDateLabel)
                    .addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        vehicleTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã số vé", "Bãi xe", "Biển số ", "Loại xe", "Hãng xe", "Kiểu xe", "Thời gian vào bãi"
            }
        ));
        vehicleTable.getTableHeader().setFont(new Font("Segoe UI",Font.BOLD,12));
        vehicleTable.getTableHeader().setOpaque(false);
        vehicleTable.getTableHeader().setBackground(new Color(32,136,203));
        vehicleTable.getTableHeader().setForeground(new Color(255,255,255));
        vehicleTable.getRowHeight(25);
        vehicleTable.setFocusable(false);
        vehicleTable.setSelectionBackground(new java.awt.Color(232, 57, 95));
        vehicleTable.setShowVerticalLines(true);
        vehicleTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(vehicleTable);

        jPanel3.setBackground(new java.awt.Color(204, 255, 204));

        addVehicleBtn.setText("Thêm");
        addVehicleBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addVehicleBtnActionPerformed(evt);
            }
        });

        editVehicleBtn.setText("Sửa");
        editVehicleBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editVehicleBtnActionPerformed(evt);
            }
        });

        deleteVehicleBtn.setText("Xóa");
        deleteVehicleBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteVehicleBtnActionPerformed(evt);
            }
        });

        clearBtn.setText("Clear");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(clearBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(deleteVehicleBtn))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(addVehicleBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(editVehicleBtn)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addVehicleBtn)
                    .addComponent(editVehicleBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteVehicleBtn)
                    .addComponent(clearBtn))
                .addGap(25, 25, 25))
        );

        jPanel4.setBackground(new java.awt.Color(204, 204, 255));

        sortVehicleParkingLotBtn.setText("Sắp xếp theo bãi xe");

        sortVehicleTypeBtn.setText("Sắp xếp theo loại xe");

        sortVehicleEnterTimeBtn.setText("Sắp xếp theo thời gian vào bãi");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sortVehicleParkingLotBtn)
                .addGap(18, 18, 18)
                .addComponent(sortVehicleTypeBtn)
                .addGap(18, 18, 18)
                .addComponent(sortVehicleEnterTimeBtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sortVehicleParkingLotBtn)
                    .addComponent(sortVehicleTypeBtn)
                    .addComponent(sortVehicleEnterTimeBtn))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        searchButton.setText("Tìm kiếm ");

        searchComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        searchComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Bãi xe", "Loại xe","Hãng xe"}));

        paymentBtn.setText("Thanh toán");
        paymentBtn.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(337, 337, 337)
                        .addComponent(searchComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(searchButton)
                        .addGap(90, 90, 90)
                        .addComponent(paymentBtn))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25)))))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchButton)
                    .addComponent(paymentBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addVehicleBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addVehicleBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addVehicleBtnActionPerformed

    private void editVehicleBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editVehicleBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_editVehicleBtnActionPerformed

    private void deleteVehicleBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteVehicleBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_deleteVehicleBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addVehicleBtn;
    private javax.swing.JTextField brandField;
    private javax.swing.JLabel brandLabel;
    private javax.swing.JButton clearBtn;
    private com.toedter.calendar.JDateChooser dateChooser;
    private javax.swing.JButton deleteVehicleBtn;
    private javax.swing.JButton editVehicleBtn;
    private javax.swing.JLabel enterDateLabel;
    private javax.swing.JLabel enterTimeLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField licensePlateField;
    private javax.swing.JLabel licensePlateLabel;
    private javax.swing.JTextField modelField;
    private javax.swing.JLabel modelLabel;
    private javax.swing.JComboBox<String> parkingLotComboBox;
    private javax.swing.JLabel parkingLotLabel;
    private javax.swing.JButton paymentBtn;
    private javax.swing.JButton searchButton;
    private javax.swing.JComboBox<String> searchComboBox;
    private javax.swing.JTextField searchField;
    private javax.swing.JButton sortVehicleEnterTimeBtn;
    private javax.swing.JButton sortVehicleParkingLotBtn;
    private javax.swing.JButton sortVehicleTypeBtn;
    private javax.swing.JTextField ticketIdField;
    private javax.swing.JLabel ticketIdLabel;
    private javax.swing.JSpinner timeSpinner;
    private javax.swing.JComboBox<String> typeComboBox;
    private javax.swing.JLabel typeLabel;
    private javax.swing.JTable vehicleTable;
    // End of variables declaration//GEN-END:variables
}
