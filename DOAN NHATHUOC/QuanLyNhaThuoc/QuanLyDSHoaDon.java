package QuanLyNhaThuoc;


import java.util.Scanner;

public class QuanLyDSHoaDon {
    private DanhSachHoaDon danhSachHoaDon;
    private DanhSachSanPham danhSachSanPham;

    public QuanLyDSHoaDon() {
        this.danhSachHoaDon = new DanhSachHoaDon();
        this.danhSachSanPham = new DanhSachSanPham();
        danhSachSanPham.taiDanhSachTuFileText();
        danhSachSanPham.hienThiDanhSach();
        danhSachHoaDon.setDanhSachSanPham(danhSachSanPham);
    }
    

    public void menu() {
        Scanner scanner = new Scanner(System.in);
        int luaChon;

        do {
            System.out.println("------ Quản lý danh sách hóa đơn ------");
            System.out.println("1. Thêm hóa đơn");
            System.out.println("2. Hiển thị danh sách hóa đơn");
            System.out.println("3. Tải danh sách từ tệp");
            System.out.println("4. Ghi danh sách vào tệp");
            System.out.println("5. Sửa thông tin của hóa đơn");
            System.out.println("6. Xóa hóa đơn");
            System.out.println("7. Tìm kiếm hóa đơn");
            System.out.println("0. Thoát");
            System.out.print("Nhập lựa chọn của bạn: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Vui lòng nhập một số nguyên.");
                scanner.next();
            }
            luaChon = scanner.nextInt();
            scanner.nextLine(); 

            switch (luaChon) {
                case 1:
                    danhSachHoaDon.them();
                    break;
                case 2:
                    danhSachHoaDon.hienThiDanhSach();
                    break;
                case 3:
                    danhSachHoaDon.taiDanhSachTuFileText();
                    break;
                case 4:
                	 danhSachHoaDon.ghiDanhSachVaoFile();
                    break;
                case 5:
                    danhSachHoaDon.sua();
                    break;
                case 6:
                    danhSachHoaDon.xoa();
                    break;
                case 7:
                    danhSachHoaDon.timKiem();
                    break;
                case 0:
                    System.out.println("Chương trình kết thúc.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        } while (luaChon != 0);
    }

    
}

