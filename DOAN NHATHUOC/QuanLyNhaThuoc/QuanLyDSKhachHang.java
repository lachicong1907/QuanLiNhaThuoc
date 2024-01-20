package QuanLyNhaThuoc;

import java.util.Scanner;

public class QuanLyDSKhachHang {
    private DanhSachKhachHang danhSachKhachHang;

    public QuanLyDSKhachHang() {
        danhSachKhachHang = new DanhSachKhachHang();
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);

        int luaChon;
        do {
            System.out.println("----- Menu Quản Lý Danh Sách Khách Hàng -----");
            System.out.println("1. Thêm khách hàng");
            System.out.println("2. Hiển thị danh sách khách hàng");
            System.out.println("3. Tải danh sách khách hàng từ tệp");
            System.out.println("4. Ghi danh sách khách hàng vào tệp");
            System.out.println("5. Sửa thông tin của khách hàng");
            System.out.println("6. Xóa khách hàng");
            System.out.println("7. Tìm kiếm khách hàng");
            System.out.println("0. Thoát");

            System.out.print("Nhập lựa chọn của bạn: ");
            luaChon = scanner.nextInt();
            scanner.nextLine(); 

            switch (luaChon) {
                case 1:
                    danhSachKhachHang.them();
                    break;
                case 2:
                    danhSachKhachHang.hienThiDanhSach();
                    break;
                case 3:
                    danhSachKhachHang.taiDanhSachTuFileText();
                    break;
                case 4:
                    danhSachKhachHang.ghiDanhSachVaoFile();
                    break;
                case 5:
                    danhSachKhachHang.sua();
                    break;
                case 6:
                    danhSachKhachHang.xoa();
                    break;
                case 7:
                    danhSachKhachHang.timKiem();
                    break;
                case 0:
                    System.out.println("Thoát chương trình quản lý danh sách khách hàng.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        } while (luaChon != 0);
    }


   
}
