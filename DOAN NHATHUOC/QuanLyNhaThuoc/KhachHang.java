package QuanLyNhaThuoc;

import java.util.List;
import java.util.Scanner;

public class KhachHang {
    private String maKhachHang;
    private String tenKhachHang;
    private String ngayThangNamSinh;

    public KhachHang() {
        this.maKhachHang = "";
        this.tenKhachHang = "";
        this.ngayThangNamSinh = "";
    }

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getNgayThangNamSinh() {
        return ngayThangNamSinh;
    }

    public void setNgayThangNamSinh(String ngayThangNamSinh) {
        this.ngayThangNamSinh = ngayThangNamSinh;
    }

    // Kt mã khách hàng tồn tại trong danh sách
    private boolean kiemTraMaKhachHang(String maKhachHang, List<KhachHang> danhSachKhachHang) {
        for (KhachHang khachHang : danhSachKhachHang) {
            if (khachHang.getMaKhachHang().equals(maKhachHang)) {
                System.out.println("Mã khách hàng đã tồn tại. Vui lòng nhập lại.");
                return true;
            }
        }
        return false;
    }
    
    public void nhapThongTin(List<KhachHang> danhSachKhachHang) {
        Scanner scanner = new Scanner(System.in);
        boolean maKhachHangDaTonTai;

        do {
            System.out.print("Nhập mã khách hàng: ");
            this.maKhachHang = scanner.nextLine();

            // Kt xem mã khách hàng đã tồn tại trong danh sách hay chưa
            maKhachHangDaTonTai = kiemTraMaKhachHang(this.maKhachHang, danhSachKhachHang);

            if (maKhachHangDaTonTai) {
                System.out.println("Mã khách hàng đã tồn tại trong danh sách. Vui lòng nhập lại.");
            }

        } while (maKhachHangDaTonTai);

        System.out.print("Nhập tên khách hàng: ");
        this.tenKhachHang = scanner.nextLine();

        System.out.print("Nhập ngày tháng năm sinh: ");
        this.ngayThangNamSinh = scanner.nextLine();
    }

    // Hàm xuất thông tin ra màn hình
    public void xuatThongTin() {
        System.out.println("Mã khách hàng: " + getMaKhachHang());
        System.out.println("Tên khách hàng: " + getTenKhachHang());
        System.out.println("Ngày tháng năm sinh: " + getNgayThangNamSinh() + "\n");
    }
    
    
    public String toString() {
        return "Mã khách hàng: " + maKhachHang + "\n" +
               "Tên khách hàng: " + tenKhachHang + "\n" +
               "Ngày tháng năm sinh: " + ngayThangNamSinh + "\n";
    }


}
