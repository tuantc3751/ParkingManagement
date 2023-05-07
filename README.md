
* Clone project

```bash
  git clone https://github.com/tuantc3751/ParkingManagement
```

# Ứng dụng quản lý bãi gửi xe

Là ứng dụng sử dụng để thêm, sửa, xóa thông tin của các bãi xe, xe vào bãi, thống kê số lượt gửi và tiền thu được, thanh toán khi xe ra khỏi bãi.


## 🧐 Tính năng
- Tra cứu và tìm kiếm bãi xe
- Tra cứu và tìm kiếm xe trong bãi gửi xe
- Thêm xe vào các bãi gửi xe
- Sửa thông tin xe
- Xóa xe khỏi bãi nếu nhập sai thông tin
- Thống kê số lượt gửi xe và tiền thu được từ trước đến hiện tại
- Tính phí gửi xe khi xe ra khỏi bãi

## 🛠️ Cài đặt

#### Cài đặt môi trường

* Cài đặt [JDK 19](https://download.oracle.com/java/19/archive/jdk-19.0.2_windows-x64_bin.exe (sha256))

* Cài đặt [Apache Netbeans 17](https://netbeans.apache.org/download/nb17/)

#### Cài đặt thư viện

* Cài đặt [JCalender](https://www.toedter.com/download/jcalendar-1.4.zip)

    1. Chạy Netbeans
    2. Chọn Tool -> Palette -> Swing/AWT Components
    ![swing](https://i0.wp.com/kienthuclaptrinh.vn/wp-content/uploads/2012/10/jcalendar1.png?w=454&ssl=1)
    3. Chọn danh mục để bổ sung tệp jar
    ![swing](https://i0.wp.com/kienthuclaptrinh.vn/wp-content/uploads/2012/10/jcalendar4.png?w=461&ssl=1)
    4. Bấm “Add from JAR…” để bổ sung tệp jcalendar-1.4.jar (bạn vừa tải được ở trên) cho danh mục này
    ![swing](https://i0.wp.com/kienthuclaptrinh.vn/wp-content/uploads/2012/10/jcalendar5.png?ssl=1)
    5. Chọn các Component do jCalendar cung cấp để thêm vào thanh công cụ
    ![swing](https://i0.wp.com/kienthuclaptrinh.vn/wp-content/uploads/2012/10/jcalendar6.png?w=620&ssl=1)
    6. Hoàn tất việc thêm jCalendar vào thanh công cụ dành cho thiết kế form
    ![swing](https://i0.wp.com/kienthuclaptrinh.vn/wp-content/uploads/2012/10/jcalendar7.png?w=667&ssl=1)
    7. Kiểm tra sự hiện diện của các component jCalendar trên thành công cụ Palette
    ![swing](https://i0.wp.com/kienthuclaptrinh.vn/wp-content/uploads/2012/10/jcalendar8.png?w=278&ssl=1)
 #### Tải mã nguồn project



#### Cài đặt project
* Mở project trong Apache Netbeans 17

* Chạy ứng dụng bằng cách chạy file App.java
## 💻 Demo

#### Màn hình đăng nhập

![Login](https://github.com/tuantc3751/ParkingManagement/blob/main/readmeImage/LoginFrame.JPG?raw=true)

- Tài khoản đăng nhập mặc định cho ứng dụng là admin và mật khẩu là admin
- Nếu nhập sai tài khoản mật khẩu, ứng dụng sẽ đưa ra thông báo tài khoản mật khẩu bị sai

![LoginFalse](https://github.com/tuantc3751/ParkingManagement/blob/main/readmeImage/LoginFalse.JPG?raw=true)

- Sau khi đăng nhập thành công, ứng dụng sẽ chuyển sang màn hình quản lý phương tiện
- Màn hình quản lý phương tiện gồm chức năng thêm, sửa , xóa, làm mới, tìm kiếm, sắp xếp và hiển thị các xe đang gửi trong các bãi và thanh toán khi xe ra khỏi bãi

#### Màn hình quản lý phương tiện

![Vechile](https://github.com/tuantc3751/ParkingManagement/blob/main/readmeImage/VehicleFrame.JPG?raw=true)

- Ví dụ khi thêm một xe vào bãi

![Add](https://github.com/tuantc3751/ParkingManagement/blob/main/readmeImage/AddVehicle.JPG?raw=true)

- Ví dụ khi thay đổi xe trong bãi

![Edit](https://github.com/tuantc3751/ParkingManagement/blob/main/readmeImage/EditVehicle.JPG?raw=true)

- Ví dụ khi xóa một xe trong bãi

![Delete](https://github.com/tuantc3751/ParkingManagement/blob/main/readmeImage/DeleteVehicle.JPG?raw=true)

- Khi tìm kiếm, ở đây có thể tìm kiếm theo các tiêu chí khác nhau như Loại xe, Bãi xe, Hãng xe. Ví dụ khi tìm kiếm loại xe là "Xe máy":

![SearchHome](https://github.com/tuantc3751/ParkingManagement/blob/main/readmeImage/SearchVehicle.JPG?raw=true)

- Ví dụ khi sắp xếp các xe theo thời gian vào bãi, ở đây có thể sắp xếp theo các tiêu chí khác nhau như bãi xe, loại xe, thời gian vào bãi:

![SortHome](https://github.com/tuantc3751/ParkingManagement/blob/main/readmeImage/SortVehicle.JPG?raw=true)

- Ví dụ về chức năng thanh toán khi ra khỏi bãi. Người dùng cần nhấn vào 1 xe trong bảng sau đó nhấn vào nút thanh toán. Sau đó ứng dụng sẽ hiện lên 1 thông báo về phí gửi xe.

![Payment](https://github.com/tuantc3751/ParkingManagement/blob/main/readmeImage/Payment.JPG?raw=true)

#### Màn hình quản lý bãi xe
- Để chuyển sang màn hình quản lý bãi xe, người dùng click đúp vào mục quản lý bãi xe. Màn hình này có các chức năng thêm, sửa, xóa, tìm kiểm, làm mới, sắp xếp, tìm kiếm tương tự như quản lý các phương tiện

![ParkingLot](https://github.com/tuantc3751/ParkingManagement/blob/main/readmeImage/ParkingLot.JPG?raw=true)

#### Màn hình thống kê
- Để chuyển sang màn hình thống kê, người dùng click đúp vào mục thống kê. Màn hình này sẽ hiển thị tổng số xe đã gửi và tổng số tiền thu được từ trước đến nay

![Stat](https://github.com/tuantc3751/ParkingManagement/blob/main/readmeImage/Statistic.JPG?raw=true)


## Các công nghệ được sử dụng
- jaxb-api
- jaxb-impl
- jcalender
## 🚀 Tác giả

- Github: [Nguyễn Mạnh Tuấn](https://github.com/tuantc3751)
- Facebook: [Nguyễn Mạnh Tuấn](https://www.facebook.com/tuan.toytoy.3)

