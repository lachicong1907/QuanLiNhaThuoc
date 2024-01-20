package QuanLyNhaThuoc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DanhSachNhanVien {
    private List<NhanVien> danhSachNhanVien;

    public DanhSachNhanVien() {
        danhSachNhanVien = new ArrayList<>();
    }

    // Thêm một nhân viên vào danh sách
    public void them() {
    	NhanVien nhanVien = new NhanVien();
    	nhanVien .nhapThongTin(danhSachNhanVien);
    	danhSachNhanVien.add(nhanVien);

        
    }
    
    public void hienThiDanhSach() {
        if (danhSachNhanVien.isEmpty()) {
            System.out.println("Danh sách nhân viên trống!!!");
        } else {
            System.out.println("Danh sách nhân viên: ");
            for (NhanVien nhanVien : danhSachNhanVien) {
                nhanVien.xuat();
            }
        }
    }
    
    public void ghiDanhSachVaoFile() {
        String tenFile = "C:\\Users\\Admin\\Desktop\\DOAN NHATHUOC\\Input\\nhanvien.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tenFile, true))) {
            for (NhanVien nhanVien : danhSachNhanVien) {
                if (!kiemTraMaNhanVienTonTaiTrongTep(nhanVien.getId(), tenFile)) {
                    writer.write(nhanVien.toString() + "\n");
                }
            }
            System.out.println("Ghi danh sách nhân viên vào file thành công.");
        } catch (IOException e) {
            System.out.println("Đã xảy ra lỗi khi ghi danh sách nhân viên vào file.");
            e.printStackTrace();
        }
    }
    
    private boolean kiemTraMaNhanVienTonTaiTrongTep(String maNhanVien, String tenTep) {
        try (BufferedReader reader = new BufferedReader(new FileReader(tenTep))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("Mã nhân viên: " + maNhanVien)) {
                    System.out.println("Nhân viên có ID " + maNhanVien + " đã tồn tại trong tệp.");
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    
    public void taiDanhSachTuFileText() {
        String tenFile = "C:\\Users\\Admin\\Desktop\\DOAN NHATHUOC\\Input\\nhanvien.txt";
        danhSachNhanVien.clear(); // Clear the existing list

        try (BufferedReader reader = new BufferedReader(new FileReader(tenFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Mã nhân viên:")) {
                    String maNhanVien = line.split(":")[1].trim();
                    String hoTen = reader.readLine().split(":")[1].trim();
                    String viTri = reader.readLine().split(":")[1].trim();
                    double luong = Double.parseDouble(reader.readLine().split(":")[1].trim().replace(" ₫", "").replace(".", "").trim());

                    NhanVien nhanVien = new NhanVien(maNhanVien, hoTen, viTri, luong);
                    danhSachNhanVien.add(nhanVien);
                    reader.readLine();
                }
            }

            System.out.println("Đọc danh sách nhân viên từ file thành công.");
        } catch (IOException e) {
            System.out.println("Đã xảy ra lỗi khi đọc danh sách nhân viên từ file.");
            e.printStackTrace();
        }
    }



    public void sua() {
        Scanner scanner = new Scanner(System.in);

        String idCanSua;
        NhanVien nhanVienCanSua = null;

        do {
            // Nhập ID của nhân viên cần sửa
            System.out.println("Nhập ID của nhân viên cần sửa (hoặc nhập 'q' để thoát): ");
            idCanSua = scanner.nextLine();

            // Kiểm tra nếu người dùng chọn thoát
            if (idCanSua.equalsIgnoreCase("q")) {
                return;
            }

            // Tìm nhân viên trong danh sách
            nhanVienCanSua = timNhanVienTheoID(idCanSua);

            // Hiển thị thông tin nhân viên
            if (nhanVienCanSua != null) {
                System.out.println("Thông tin nhân viên trước khi sửa:");
                nhanVienCanSua.xuat();
            } else {
                System.out.println("Không tìm thấy nhân viên có ID " + idCanSua);
            }

        } while (nhanVienCanSua == null);

        // Hiển thị menu sửa thông tin
        hienThiMenuSuaThongTin(nhanVienCanSua);

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

        // Sửa thông tin tương ứng với lựa chọn
        switch (luaChon) {
            case 1:
                // Sửa tên nhân viên
                System.out.println("Nhập tên mới: ");
                String tenMoi = scanner.nextLine();
                nhanVienCanSua.setTen(tenMoi);
                daCapNhat = true;
                break;
            case 2:
                // Sửa vị trí
                System.out.println("Nhập vị trí mới: ");
                String vitriMoi = scanner.nextLine();
                nhanVienCanSua.setVitri(vitriMoi);
                daCapNhat = true;
                break;
            case 3:
                // Sửa lương
                System.out.println("Nhập lương mới: ");
                double luongMoi = Double.parseDouble(scanner.nextLine());
                nhanVienCanSua.setLuong(luongMoi);
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

        System.out.println("Thông tin nhân viên đã được cập nhật:");
        nhanVienCanSua.xuat();
    }
    
    private void hienThiMenuSuaThongTin(NhanVien nhanVien) {
        System.out.println("===== Menu Sửa Thông Tin Nhân Viên =====");
        System.out.println("1. Sửa Tên");
        System.out.println("2. Sửa Vị Trí");
        System.out.println("3. Sửa Lương");
        System.out.println("0. Quay lại");
        System.out.println("====================================");

        System.out.println("Thông tin nhân viên cần sửa:");
        nhanVien.xuat();
    }

 // Hàm tìm nhân viên theo ID
    private NhanVien timNhanVienTheoID(String id) {
        for (NhanVien nhanVien : danhSachNhanVien) {
            if (nhanVien.getId().equals(id)) {
                return nhanVien;
            }
        }
        return null; // Không tìm thấy nhân viên có ID tương ứng
    }
    
    private void capNhatTep() {
        String tenTep = "C:\\Users\\Admin\\Desktop\\DOAN NHATHUOC\\Input\\nhanvien.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tenTep))) {
            for (NhanVien nhanVien : danhSachNhanVien) {
                writer.write(nhanVien.toString());
                writer.newLine();
            }
            System.out.println("Danh sách nhân viên đã được cập nhật vào file.");
        } catch (IOException e) {
            System.out.println("Đã xảy ra lỗi khi cập nhật tệp: " + tenTep);
            e.printStackTrace();
        }
    }
    
    public void xoa() {
        if (danhSachNhanVien.isEmpty()) {
            System.out.println("Danh sách nhân viên trống!!!");
            return;
        }

        Scanner scanner = new Scanner(System.in);

        String idCanXoa;
        NhanVien nhanVienCanXoa = null;

        do {
            System.out.print("Nhập ID của nhân viên cần xóa (hoặc nhập 'q' để thoát): ");
            idCanXoa = scanner.nextLine();

            // Kt nếu người dùng chọn thoát
            if (idCanXoa.equalsIgnoreCase("q")) {
                System.out.println("Hủy xóa nhân viên.");
                return;
            }

            // Tìm nhân viên trong danh sách
            nhanVienCanXoa = timNhanVienTheoID(idCanXoa);

            // Nếu không tìm thấy nhân viên, thông báo và lặp lại
            if (nhanVienCanXoa == null) {
                System.out.println("Không tìm thấy nhân viên có ID " + idCanXoa);
            }

        } while (nhanVienCanXoa == null);

        // Hiển thị thông tin nhân viên trước khi xóa
        System.out.println("Thông tin nhân viên trước khi xóa:");
        System.out.println(nhanVienCanXoa.toString());

        // Xác nhận xóa từ người dùng
        System.out.print("Bạn có chắc chắn muốn xóa nhân viên này? (có/không): ");
        String xacNhan = scanner.nextLine().toLowerCase();

        if (xacNhan.equals("có")) {
            // Xóa nhân viên khỏi danh sách
            danhSachNhanVien.remove(nhanVienCanXoa);

            // Cập nhật vào tệp
            capNhatTep();

            System.out.println("Nhân viên đã được xóa khỏi danh sách.");
        } else {
            System.out.println("Hủy xóa nhân viên.");
        }
    }

    public void timKiem() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Nhập từ khóa tìm kiếm: ");
        String tuKhoa = scanner.nextLine().toLowerCase();

        int lineNumber = 0; // Dòng hiện tại trong tệp

        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Admin\\Desktop\\DOAN NHATHUOC\\Input\\nhanvien.txt"))) {
            String line;
            boolean ketQuaTimThay = false;

            while ((line = reader.readLine()) != null) {
                lineNumber++;

                // Nếu dòng chứa từ khóa tìm kiếm thì hiển thị thông tin
                if (line.toLowerCase().contains(tuKhoa)) {
                    ketQuaTimThay = true;
                    System.out.println("Nhân viên được tìm thấy ở dòng " + lineNumber + " trong tệp:");
                    System.out.println(line);
                    for (int i = 0; i < 2; i++) {
                        System.out.println(reader.readLine());
                    }
                }
            }

            if (!ketQuaTimThay) {
                System.out.println("Không tìm thấy nhân viên với từ khóa: " + tuKhoa);
            }
        } catch (IOException e) {
            System.out.println("Đã xảy ra lỗi khi đọc tệp nhân viên.");
            e.printStackTrace();
        }
    }

    
}
