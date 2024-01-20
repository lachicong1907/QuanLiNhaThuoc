package QuanLyNhaThuoc;

import java.util.Scanner;

public class QuanLyDSNhanVien {
    private DanhSachNhanVien danhSachNhanVien;

    public QuanLyDSNhanVien() {
        danhSachNhanVien = new DanhSachNhanVien();
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);

        int luaChon;
        do {
        	System.out.println("===== Quản Lý Nhà Thuốc =====");
        	System.out.println("1. Thêm nhân viên");
        	System.out.println("2. Hiển thị danh sách nhân viên");
            System.out.println("3. Tải danh sách nhân viên từ file");
            System.out.println("4. Ghi danh sách vào file");
            System.out.println("5. Sửa thông tin của nhân viên");
            System.out.println("6. Xóa nhân viên");
            System.out.println("7. Tìm kiếm nhân viên");
        	System.out.println("0. Thoát");
        	System.out.print("Nhập lựa chọn của bạn: ");

           
            luaChon = scanner.nextInt();
            scanner.nextLine(); 

            switch (luaChon) {
                case 1:
                    danhSachNhanVien.them();
                    break;
                case 2:
                    danhSachNhanVien.hienThiDanhSach();
                    break;
                case 3:
                   
                	danhSachNhanVien.taiDanhSachTuFileText();
                    break;
                case 4:
                	danhSachNhanVien.ghiDanhSachVaoFile();
                    break;
                case 5:
                	danhSachNhanVien.sua();;
                    break;
                case 6:
                    danhSachNhanVien.xoa();
                    break;
                case 7:
                    danhSachNhanVien.timKiem();
                    break;
                case 0:
                    System.out.println("Thoát chương trình quản lý danh sách nhân viên.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        } while (luaChon != 0);
    }
}

