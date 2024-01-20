package QuanLyNhaThuoc;

import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DanhSachKhachHang  {
    private List<KhachHang> danhSachKhachHang;

    public DanhSachKhachHang() {
        danhSachKhachHang = new ArrayList<>();
    }

    public void them() {
        KhachHang khachHang = new KhachHang();
        khachHang.nhapThongTin(danhSachKhachHang);
        danhSachKhachHang.add(khachHang);
       
    }

    public void hienThiDanhSach() {
        if (danhSachKhachHang.isEmpty()) {
            System.out.println("Danh sách khách hàng trống.");
        } else {
            System.out.println("Danh sách khách hàng:");
            for (KhachHang khachHang : danhSachKhachHang) {
                khachHang.xuatThongTin();
            }
        }
    }
   
    public void ghiDanhSachVaoFile() {
        String tenTep = "C:\\Users\\Admin\\Desktop\\DOAN NHATHUOC\\Input\\khachhang.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tenTep, true))) {
            for (KhachHang khachHang : danhSachKhachHang) {
                if (!kiemTraMaKhachHangTonTaiTrongTep(khachHang.getMaKhachHang(), tenTep)) {
                    writer.write(khachHang.toString()+ "\n");
                }
            }
            System.out.println("Danh sách khách hàng đã được ghi vào tệp: " + tenTep);
        } catch (IOException e) {
            System.out.println("Đã xảy ra lỗi khi ghi vào tệp: " + tenTep);
            e.printStackTrace();
        }
    }
    
    private boolean kiemTraMaKhachHangTonTaiTrongTep(String maKhachHang, String tenTep) {
        try (BufferedReader reader = new BufferedReader(new FileReader(tenTep))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("Mã khách hàng: " + maKhachHang)) {
                    System.out.println("Khách hàng có mã " + maKhachHang + " đã tồn tại trong tệp.");
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Lỗi khi đọc từ tệp: " + tenTep);
            e.printStackTrace();
        }
        return false;
    }

   
    public void taiDanhSachTuFileText() {
        String tenTep = "C:\\Users\\Admin\\Desktop\\DOAN NHATHUOC\\Input\\khachhang.txt";
        danhSachKhachHang.clear(); 

        try (BufferedReader reader = new BufferedReader(new FileReader(tenTep))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Mã khách hàng:")) {
                    String maKhachHang = line.split(":")[1].trim();
                    String tenKhachHang = reader.readLine().split(":")[1].trim();
                    String ngayThangNamSinh = reader.readLine().split(":")[1].trim();

                    KhachHang khachHang = new KhachHang();
                    khachHang.setMaKhachHang(maKhachHang);
                    khachHang.setTenKhachHang(tenKhachHang);
                    khachHang.setNgayThangNamSinh(ngayThangNamSinh);

                    danhSachKhachHang.add(khachHang);

                    reader.readLine();
                }
            }

            System.out.println("Đọc danh sách khách hàng từ file thành công.");
        } catch (IOException e) {
            System.out.println("Đã xảy ra lỗi khi đọc danh sách khách hàng từ file.");
            e.printStackTrace();
        }
    }
    
    public void sua() {
        Scanner scanner = new Scanner(System.in);

        String maKhachHangCanSua;
        KhachHang khachHangCanSua = null;

        do {
            // Nhập mã khách hàng cần sửa
            System.out.println("Nhập mã khách hàng cần sửa (hoặc nhập 'q' để thoát): ");
            maKhachHangCanSua = scanner.nextLine();

            // Nếu nhập q thì thoát ra
            if (maKhachHangCanSua.equalsIgnoreCase("q")) {
                return;
            }

            // Tìm khách hàng trong danh sách
            khachHangCanSua = timKhachHangTheoMa(maKhachHangCanSua);

            // Hiển thị thông tin khách hàng
            if (khachHangCanSua != null) {
                System.out.println("Thông tin khách hàng trước khi sửa:");
                khachHangCanSua.xuatThongTin();
            } else {
                System.out.println("Không tìm thấy khách hàng có mã " + maKhachHangCanSua);
            }

        } while (khachHangCanSua == null);

        // Hiển thị menu sửa thông tin
        hienThiMenuSuaThongTin(khachHangCanSua);

        // Nhập lựa chọn từ người dùng
        int luaChon;
        boolean daCapNhat = false;

        while (true) {
            try {
                System.out.println("Nhập lựa chọn của bạn: ");
                luaChon = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập lại.");
            }
        }

        switch (luaChon) {
            case 1:
                // Sửa tên khách hàng
                System.out.println("Nhập tên mới: ");
                String tenMoi = scanner.nextLine();
                khachHangCanSua.setTenKhachHang(tenMoi);
                daCapNhat = true;
                break;
            case 2:
                // Sửa ngày tháng năm sinh
                System.out.println("Nhập ngày tháng năm sinh mới: ");
                String ngayThangNamSinhMoi = scanner.nextLine();
                khachHangCanSua.setNgayThangNamSinh(ngayThangNamSinhMoi);
                daCapNhat = true;
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ. Không có thông tin nào được sửa.");
                return;
        }

        // Cập nhật thông tin đã sửa vào tệp
        if (daCapNhat) {
            capNhatTep();
            System.out.println("Danh sách đã được cập nhật vào file.");
        }

        System.out.println("Thông tin khách hàng đã được cập nhật:");
        khachHangCanSua.xuatThongTin();
    }
    
    public void hienThiMenuSuaThongTin(KhachHang khachHangCanSua) {
        System.out.println("Menu sửa thông tin:");
        System.out.println("1. Sửa tên khách hàng");
        System.out.println("2. Sửa ngày tháng năm sinh");
        System.out.println("0. Thoát");
        
        System.out.println("Chọn một số để sửa thông tin:");
    }
    
    public KhachHang timKhachHangTheoMa(String maKhachHang) {
        for (KhachHang khachHang : danhSachKhachHang) {
            if (khachHang.getMaKhachHang().equalsIgnoreCase(maKhachHang)) {
                return khachHang;
            }
        }
        return null;
    }

    public void capNhatTep() {
        String tenTep = "C:\\Users\\Admin\\Desktop\\DOAN NHATHUOC\\Input\\khachhang.txt";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(tenTep))) {
            for (KhachHang khachHang : danhSachKhachHang) {
                // Ghi thông tin của mỗi khách hàng vào tệp văn bản
                bw.write(khachHang.toString());
                bw.newLine(); // Xuống dòng cho khách hàng tiếp theo
            }
            System.out.println("Danh sách khách hàng đã được cập nhật vào tệp: " + tenTep);
        } catch (IOException e) {
            System.out.println("Lỗi khi ghi vào tệp: " + tenTep);
            e.printStackTrace();
        }
    }
    
    public void xoa() {
        if (danhSachKhachHang.isEmpty()) {
            System.out.println("Danh sách khách hàng trống!!!");
            return;
        }

        Scanner scanner = new Scanner(System.in);

        String maKhachHangCanXoa;
        KhachHang khachHangCanXoa = null;

        do {
            System.out.print("Nhập mã khách hàng cần xóa (hoặc nhập 'q' để thoát): ");
            maKhachHangCanXoa = scanner.nextLine();

            // Kt nếu người dùng chọn thoát
            if (maKhachHangCanXoa.equalsIgnoreCase("q")) {
                System.out.println("Hủy xóa khách hàng.");
                return;
            }

            // Tìm khách hàng trong danh sách
            khachHangCanXoa = timKhachHangTheoMa(maKhachHangCanXoa);

            // Nếu không tìm thấy khách hàng thì thông báo
            if (khachHangCanXoa == null) {
                System.out.println("Không tìm thấy khách hàng có mã " + maKhachHangCanXoa);
            }

        } while (khachHangCanXoa == null);

        // Hiển thị thông tin khách hàng trước khi xóa
        System.out.println("Thông tin khách hàng trước khi xóa:");
        System.out.println(khachHangCanXoa.toString());

        // Xác nhận xóa từ người dùng
        System.out.print("Bạn có chắc chắn muốn xóa khách hàng này? (có/không): ");
        String xacNhan = scanner.nextLine().toLowerCase();

        if (xacNhan.equals("có")) {
            // Xóa khách hàng khỏi danh sách
            danhSachKhachHang.remove(khachHangCanXoa);

            // Cập nhật vào tệp
            capNhatTep();

            System.out.println("Khách hàng đã được xóa khỏi danh sách.");
        } else {
            System.out.println("Hủy xóa khách hàng.");
        }
    }
    public void timKiem() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Nhập từ khóa tìm kiếm: ");
        String tuKhoa = scanner.nextLine().toLowerCase();

        int lineNumber = 0; // Dòng hiện tại trong tệp

        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Admin\\Desktop\\DOAN NHATHUOC\\Input\\khachhang.txt"))) {
            String line;
            boolean ketQuaTimThay = false;

            while ((line = reader.readLine()) != null) {
                lineNumber++;

                // Nếu dòng chứa từ khóa tìm kiếm, hiển thị thông tin 
                if (line.toLowerCase().contains(tuKhoa)) {
                    ketQuaTimThay = true;
                    System.out.println("Khách hàng được tìm thấy ở dòng " + lineNumber + " trong tệp:");
                    System.out.println(line);
                    for (int i = 0; i < 2; i++) {
                        System.out.println(reader.readLine());
                    }
                }
            }

            if (!ketQuaTimThay) {
                System.out.println("Không tìm thấy khách hàng với từ khóa: " + tuKhoa);
            }
        } catch (IOException e) {
            System.out.println("Đã xảy ra lỗi khi đọc tệp khách hàng.");
            e.printStackTrace();
        }
    }
    





}
