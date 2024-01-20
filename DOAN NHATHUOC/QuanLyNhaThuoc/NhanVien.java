package QuanLyNhaThuoc;

import java.util.List;
import java.util.Scanner;

public class NhanVien {
    private String id;
    private String ten;
    private String vitri;
    private double luong;
    


    public NhanVien(){
        id= "";
        ten ="";
        vitri = "";
        luong =0;
    }


    public NhanVien(String id, String ten, String vitri, double luong) {
        this.id = id;
        this.ten = ten;
        this.vitri = vitri;
        this.luong = luong;
    }

    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getVitri() {
        return vitri;
    }

    public void setVitri(String vitri) {
        this.vitri = vitri;
    }

    public double getLuong() {
        return luong;
    }

    public void setLuong(double luong) {
        this.luong = luong;
    }
    
    public static boolean kiemTraMaNhanVien(String id, List<NhanVien> danhSachNhanVien) {
        for (NhanVien nhanVien : danhSachNhanVien) {
            if (nhanVien.getId().equals(id)) {
                return true; // Mã nhân viên đã tồn tại trong danh sách
            }
        }
        return false; // Mã nhân viên chưa tồn tại trong danh sách
    }

    // Nhập thông tin từ bàn phím
    public void nhapThongTin(List<NhanVien> danhSachNhanVien) {
        Scanner scanner = new Scanner(System.in);
        boolean maNhanVienDaTonTai;

        do {
            System.out.print("Nhập ID: ");
            this.id = scanner.nextLine();

            // Kiểm tra xem mã nhân viên đã tồn tại trong danh sách hay chưa
            maNhanVienDaTonTai = kiemTraMaNhanVien(this.id, danhSachNhanVien);

            if (maNhanVienDaTonTai) {
                System.out.println("Ma nhan vien da ton tai trong danh sach. Vui long nhap lai.");
            }

        } while (maNhanVienDaTonTai);

        System.out.print("Nhập họ và tên: ");
        this.ten = scanner.nextLine();

        System.out.print("Nhập vị trí: ");
        this.vitri = scanner.nextLine();

        System.out.print("Nhập lương: ");
        this.luong = scanner.nextDouble();
    }


 // Xuất thông tin ra màn hình
    public void xuat() {
        System.out.println("Mã nhân viên: " + id);
        System.out.println("Họ và tên: " + ten);
        System.out.println("Vị trí: " + vitri);
        System.out.println("Lương: " + String.format("%,.0f ₫", luong) + "\n");
    }



    @Override
    public String toString() {
        return "Mã nhân viên: " + id +
               "\nHọ và tên: " + ten +
               "\nVị trí: " + vitri +
               "\nLương: " + String.format("%,.0f ₫", luong) + "\n";
    }





}