package QuanLyNhaThuoc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class DanhSachHoaDon {
    private List<HoaDon> danhSachHoaDon;
    private DanhSachSanPham danhSachSanPham;

    public DanhSachHoaDon() {
        this.danhSachHoaDon = new ArrayList<>();
        this.danhSachSanPham = new DanhSachSanPham();
    }

    public List<HoaDon> getDanhSachHoaDon() {
        return danhSachHoaDon;
    }

    public void setDanhSachHoaDon(List<HoaDon> danhSachHoaDon) {
        this.danhSachHoaDon = danhSachHoaDon;
    }

    public DanhSachSanPham getDanhSachSanPham() {
        return danhSachSanPham;
    }
    
    public void setDanhSachSanPham(DanhSachSanPham danhSachSanPham) {
        this.danhSachSanPham = danhSachSanPham;
    }

    public void them() {
        Scanner scanner = new Scanner(System.in);
        int soLuongHoaDon;

        do {
            System.out.print("Nhập số lượng hóa đơn: ");
            soLuongHoaDon = scanner.nextInt();
            scanner.nextLine(); 

            if (soLuongHoaDon <= 0) {
                System.out.println("Số lượng hóa đơn phải là số nguyên dương. Vui lòng nhập lại.");
            }
        } while (soLuongHoaDon <= 0);

        for (int i = 0; i < soLuongHoaDon; i++) {
            HoaDon hoaDon = new HoaDon();
            System.out.println("Nhập thông tin cho hóa đơn thứ " + (i + 1) + ":");
            hoaDon.nhapThongTin(danhSachSanPham, danhSachHoaDon);
            danhSachHoaDon.add(hoaDon);
        }
    }

 // Hiển thị danh sách hóa đơn
    public void hienThiDanhSach() {
        if (danhSachHoaDon.isEmpty()) {
            System.out.println("Danh sách hóa đơn trống.");
        } else {
             for (HoaDon hoaDon : danhSachHoaDon) {
                System.out.println(hoaDon.toString());
                System.out.println();
            }
        }
    }
    public boolean kiemTraMaHoaDonTonTaiTrongTep(String maHoaDon, String tenTep) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(tenTep));
            for (String line : lines) {
                if (line.equals("Mã hóa đơn: " + maHoaDon)) {
                    System.out.println("Hóa đơn có mã " + maHoaDon + " đã tồn tại trong tệp.");
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void taiDanhSachTuFileText() {
        String tenTep ="C:\\Users\\Admin\\Desktop\\DOAN NHATHUOC\\Input\\hoadon.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(tenTep))) {
            String line;
            HoaDon hoaDon = null;
            ChiTietHoaDon chiTietHoaDon = null;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Mã hóa đơn:")) {
                    if (hoaDon != null) {
                        danhSachHoaDon.add(hoaDon);
                    }
                    hoaDon = new HoaDon();
                    chiTietHoaDon = new ChiTietHoaDon(danhSachSanPham);
                    hoaDon.setMaHoaDon(line.substring("Mã hóa đơn: ".length()).trim());
                } else if (line.startsWith("Ngày tạo hóa đơn:")) {
                    hoaDon.setNgayTaoHoaDon(line.substring("Ngày tạo hóa đơn: ".length()).trim());
                } else if (line.startsWith("Khách hàng:")) {
                    KhachHang khachHang = new KhachHang();
                    khachHang.setTenKhachHang(line.substring("Khách hàng: ".length()).trim());
                    hoaDon.setKhachHang(khachHang);
                } else if (line.startsWith("Nhân viên tạo hóa đơn:")) {
                    NhanVien nhanVien = new NhanVien();
                    nhanVien.setTen(line.substring("Nhân viên tạo hóa đơn: ".length()).trim());
                    hoaDon.setNhanVienTaoHoaDon(nhanVien);
                } else if (line.startsWith("[Mã sản phẩm:") || line.startsWith("Mã sản phẩm:")) {
                    String maSanPham = line.substring("Mã sản phẩm: ".length()).trim();
                    line = reader.readLine(); // Tên sản phẩm
                    String tenSanPham = line.substring("Tên sản phẩm: ".length()).trim();
                    line = reader.readLine(); // Số lượng
                    int soLuong = Integer.parseInt(line.substring("Số lượng: ".length()).trim());
                    line = reader.readLine(); // Thành tiền
                    double thanhTien = Double.parseDouble(line.substring("Thành tiền: ".length()).trim().replace("₫", "").replace(".", ""));
                    chiTietHoaDon.themSanPhamVaoHoaDon(maSanPham, soLuong);
                } else if (line.startsWith("]")) {
                    hoaDon.themChiTietHoaDon(chiTietHoaDon);
                }
            }
            if (hoaDon != null) {
                danhSachHoaDon.add(hoaDon);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Không tìm thấy tệp: " + tenTep);
        } catch (IOException e) {
            System.err.println("Lỗi đọc tệp: " + tenTep);
        } catch (NumberFormatException e) {
            System.err.println("Lỗi định dạng số");
        }
    }



    public void ghiDanhSachVaoFile() {
        String tenTep = "C:\\Users\\Admin\\Desktop\\DOAN NHATHUOC\\Input\\hoadon.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tenTep, true))) {
            for (HoaDon hoaDon : danhSachHoaDon) {
                // Kiểm tra xem mã hóa đơn đã tồn tại trong tệp hay chưa
                if (!kiemTraMaHoaDonTonTaiTrongTep(hoaDon.getMaHoaDon(), tenTep)) {
                	 String hoaDonString = hoaDon.toString();
                    writer.write(hoaDonString); // Ghi thông tin hóa đơn vào file
                    writer.newLine(); // Thêm dòng mới
                }
            }
            System.out.println("Đã ghi danh sách hóa đơn vào file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
 // Cập nhật thông tin vào tệp
    public void capNhatTep() {
        String tenTep = "C:\\Users\\Admin\\Desktop\\DOAN NHATHUOC\\Input\\hoadon.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tenTep))) {
            for (HoaDon hoaDon : danhSachHoaDon) {
                writer.write(hoaDon.toString()); // Ghi thông tin hóa đơn vào file
                writer.newLine(); // Thêm dòng mới
            }
            System.out.println("Đã cập nhật tệp hóa đơn.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // tìm hóa đơn theo mã
    private HoaDon timHoaDonTheoMa(String maHoaDon) {
        for (HoaDon hoaDon : danhSachHoaDon) {
            if (hoaDon.getMaHoaDon().equals(maHoaDon)) {
                return hoaDon;
            }
        }
        return null;
    }
   
    
    public void sua() {
        Scanner scanner = new Scanner(System.in);

        String maHoaDonCanSua;
        HoaDon hoaDonCanSua = null;

        do {
            System.out.print("Nhập mã hóa đơn cần sửa (hoặc nhập 'q' để thoát): ");
            maHoaDonCanSua = scanner.nextLine();

            // Kiểm tra nếu người dùng chọn thoát
            if (maHoaDonCanSua.equalsIgnoreCase("q")) {
                System.out.println("Hủy sửa hóa đơn.");
                return;
            }

            // Tìm hóa đơn trong danh sách
            hoaDonCanSua = timHoaDonTheoMa(maHoaDonCanSua);

            // Nếu không tìm thấy hóa đơn thì thông báo 
            if (hoaDonCanSua == null) {
                System.out.println("Không tìm thấy hóa đơn có mã " + maHoaDonCanSua);
            }

        } while (hoaDonCanSua == null);

        // Hiển thị thông tin hóa đơn trước khi sửa
        System.out.println("Thông tin hóa đơn trước khi sửa:");
        System.out.println(hoaDonCanSua.toString());

        // Hiển thị menu sửa
        hienThiMenuSuaThongTin(hoaDonCanSua);

        int luaChon;
        do {
            // Yêu cầu người dùng nhập lựa chọn
            System.out.print("Nhập lựa chọn (0 để thoát): ");
            luaChon = scanner.nextInt();
            scanner.nextLine(); 

            switch (luaChon) {
                case 1:
                    // Sửa ngày tạo hóa đơn
                    System.out.print("Nhập ngày tạo hóa đơn mới: ");
                    String ngayMoi = scanner.nextLine();
                    hoaDonCanSua.setNgayTaoHoaDon(ngayMoi);
                    break;
                case 2:
                    // Sửa tên khách hàng
                    System.out.print("Nhập tên khách hàng mới: ");
                    String tenKhachHangMoi = scanner.nextLine();
                    hoaDonCanSua.getKhachHang().setTenKhachHang(tenKhachHangMoi);
                    break;
                case 3:
                    // Sửa tên nhân viên
                    System.out.print("Nhập tên nhân viên mới: ");
                    String tenNhanVienMoi = scanner.nextLine();
                    hoaDonCanSua.getNhanVienTaoHoaDon().setTen(tenNhanVienMoi);
                    break;
                case 4:
                    // Sửa chi tiết hóa đơn
                	hoaDonCanSua.suaChiTietHoaDon(danhSachSanPham);
                    break;
                case 0:
                    // Thoát khỏi menu sửa
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        } while (luaChon != 0);

        // Cập nhật tệp sau khi sửa nếu cần
        capNhatTep();
        System.out.println("Hóa đơn đã được sửa.");
    }
    

    
 // Hàm hiển thị menu sửa thông tin hóa đơn
    private void hienThiMenuSuaThongTin(HoaDon hoaDon) {
    	System.out.println("Chọn thông tin cần sửa:");
        System.out.println("===== Menu Sửa Thông Tin Hóa Đơn =====");
        System.out.println("1. Sửa Ngày Tạo Hóa Đơn");
        System.out.println("2. Sửa Tên Khách Hàng");
        System.out.println("3. Sửa Tên Nhân Viên Tạo Hóa Đơn");
        System.out.println("4. Sửa Chi Tiết Hóa Đơn");
        System.out.println("0. Thoát");
        System.out.println("=====================================");
    }
    
    public void xoa() {
        Scanner scanner = new Scanner(System.in);

        String maHoaDonCanXoa;
        HoaDon hoaDonCanXoa = null;

        do {
            System.out.print("Nhập mã hóa đơn cần xóa (hoặc nhập 'q' để thoát): ");
            maHoaDonCanXoa = scanner.nextLine();

            if (maHoaDonCanXoa.equalsIgnoreCase("q")) {
                System.out.println("Hủy xóa hóa đơn.");
                return;
            }

            // Tìm hóa đơn trong danh sách
            hoaDonCanXoa = timHoaDonTheoMa(maHoaDonCanXoa);

            // Nếu không tìm thấy hóa đơn thì thông báo
            if (hoaDonCanXoa == null) {
                System.out.println("Không tìm thấy hóa đơn có mã " + maHoaDonCanXoa);
            }

        } while (hoaDonCanXoa == null);

        // Hiển thị thông tin hóa đơn trước khi xóa
        System.out.println("Thông tin hóa đơn trước khi xóa:");
        System.out.println(hoaDonCanXoa.toString());

        // Xác nhận xóa từ người dùng
        System.out.print("Bạn có chắc chắn muốn xóa hóa đơn này? (có/không): ");
        String xacNhan = scanner.nextLine().toLowerCase();

        if (xacNhan.equals("có")) {
            // Xóa hóa đơn khỏi danh sách
            danhSachHoaDon.remove(hoaDonCanXoa);

            // Cập nhật vào tệp
            capNhatTep();

            System.out.println("Hóa đơn đã được xóa khỏi danh sách.");
        } else {
            System.out.println("Hủy xóa hóa đơn.");
        }
    }
   
    public void timKiem() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập từ khóa tìm kiếm: ");
        String tuKhoa = scanner.nextLine();

        String tenTep = "C:\\Users\\Admin\\Desktop\\DOAN NHATHUOC\\Input\\hoadon.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(tenTep))) {
            String line;
            int lineNumber = 0;
            boolean hienThiKetQua = false;

            while ((line = reader.readLine()) != null) {
                lineNumber++;

                if (line.contains(tuKhoa)) {
                    // Nếu dòng chứa từ khóa thì hiển thị kết quả 
                    System.out.println("Hóa đơn được tìm thấy ở dòng " + lineNumber + " trong tệp:");
                    System.out.println(line);
                    
                   
                    for (int i = 0; i < 14 && (line = reader.readLine()) != null; i++) {
                        System.out.println(line);
                    }
                    
                    hienThiKetQua = true;
                    break; 
                }
            }

            if (!hienThiKetQua) {
                System.out.println("Không tìm thấy hóa đơn nào chứa từ khóa '" + tuKhoa + "'.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
