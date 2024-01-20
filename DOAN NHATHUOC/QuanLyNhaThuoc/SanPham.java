package QuanLyNhaThuoc;
import java.util.List;
import java.util.Scanner;
public abstract class SanPham {
    private String maSanPham;
    private String tenSanPham;
    private double giaNhap; // giá nhập thuốc
    private int soLuong;
    private String nhaCungCap; // nhà sản xuất
    private String thanhPhan;
    private String congDung;

    private static int soLuongSanPham = 0;

    public static int getSoLuongSanPham() {
        return soLuongSanPham;
    }

    public static void tangSoLuongSanPham() {
        soLuongSanPham++;
    }
    
    public SanPham() {
        this.maSanPham = "";
        this.tenSanPham = "";
        this.giaNhap = 0.0;
        this.soLuong = 0;
        this.nhaCungCap = "";
        this.thanhPhan = "";
        this.congDung = "";
    }
    public SanPham(String maSanPham, String tenSanPham, double giaNhap, int soLuong,
                    String nhaCungCap, String thanhPhan, String congDung) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.giaNhap = giaNhap;
        this.soLuong = soLuong;
        this.nhaCungCap = nhaCungCap;
        this.thanhPhan = thanhPhan;
        this.congDung = congDung;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return this.tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public double getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(double giaNhap) {
        this.giaNhap = giaNhap;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getNhaCungCap() {
        return nhaCungCap;
    }

    public void setNhaCungCap(String nhaCungCap) {
        this.nhaCungCap = nhaCungCap;
    }

    public String getThanhPhan() {
        return thanhPhan;
    }

    public void setThanhPhan(String thanhPhan) {
        this.thanhPhan = thanhPhan;
    }

    public String getCongDung() {
        return congDung;
    }

    public void setCongDung(String congDung) {
        this.congDung = congDung;
    }
    public void nhapThongTin(List<SanPham> danhSachSanPham) {
        Scanner scanner = new Scanner(System.in);

        do {
            if (this instanceof Thuoc) {
                do {
                    System.out.println("Nhập mã sản phẩm (bắt đầu bằng 'T' + số, ghi hoa): ");
                    maSanPham = scanner.nextLine().trim();

                    if (!(maSanPham.startsWith("T") && maSanPham.substring(1).matches("\\d+"))) {
                        System.out.println("Mã sản phẩm không hợp lệ. Vui lòng nhập lại.");
                    }
                } while (!(maSanPham.startsWith("T") && maSanPham.substring(1).matches("\\d+")));
            } else if (this instanceof ThucPhamChucNang) {
                do {
                    System.out.println("Nhập mã sản phẩm (bắt đầu bằng 'TP' + số, ghi hoa): ");
                    maSanPham = scanner.nextLine().trim();

                    if (!(maSanPham.startsWith("TP") && maSanPham.substring(2).matches("\\d+"))) {
                        System.out.println("Mã sản phẩm không hợp lệ. Vui lòng nhập lại.");
                    }
                } while (!(maSanPham.startsWith("TP") && maSanPham.substring(2).matches("\\d+")));
            }
        } while (!kiemTraMaSanPham(maSanPham, danhSachSanPham));

        System.out.println("Nhập tên sản phẩm: ");
        tenSanPham = scanner.nextLine();

        System.out.println("Nhập giá nhập: ");
        do {
            while (!scanner.hasNextDouble()) {
                System.out.println("Giá nhập phải là số. Vui lòng nhập lại: ");
                scanner.next();
            }
            giaNhap = scanner.nextDouble();
            if (giaNhap < 0) {
                System.out.println("Giá nhập không được âm. Vui lòng nhập lại: ");
            }
        } while (giaNhap < 0);

        scanner.nextLine();
        System.out.println("Nhập số lượng: ");
        do {
            while (!scanner.hasNextInt()) {
                System.out.println("Số lượng phải là số. Vui lòng nhập lại: ");
                scanner.next();
            }
            soLuong = scanner.nextInt();
            if (soLuong < 0) {
                System.out.println("Số lượng không được âm. Vui lòng nhập lại: ");
            }
        } while (soLuong < 0);

        scanner.nextLine(); 

        System.out.println("Nhập nhà cung cấp: ");
        nhaCungCap = scanner.nextLine();

        System.out.println("Nhập thành phần: ");
        thanhPhan = scanner.nextLine();

        System.out.println("Nhập công dụng: ");
        congDung = scanner.nextLine();
        
        tangSoLuongSanPham();
    }
    private boolean kiemTraMaSanPham(String maSanPham, List<SanPham> danhSachSanPham) {
        for (SanPham sanPham : danhSachSanPham) {
            if (sanPham.getMaSanPham().equals(maSanPham)) {
                System.out.println("Mã sản phẩm đã tồn tại. Vui lòng nhập lại.");
                return false;
            }
        }
        return true;
    }
    public void xuatThongTin() {
        System.out.println("Mã sản phẩm: " + maSanPham);
        System.out.println("Tên sản phẩm: " + tenSanPham);
        String giaNhapChuoi = String.format("%,.0f ₫", giaNhap);
        System.out.println("Giá nhập: " + giaNhapChuoi);
        System.out.println("Số lượng: " + soLuong);
        System.out.println("Nhà cung cấp: " + nhaCungCap);
        System.out.println("Thành phần: " + thanhPhan);
        System.out.println("Công dụng: " + congDung);
    }
    
    public String toString() {
        return "Mã sản phẩm: " + maSanPham + "\n" +
               "Tên sản phẩm: " + tenSanPham + "\n" +
               "Giá nhập: " + String.format("%,.0f ₫", giaNhap) + "\n" +
               "Số lượng: " + soLuong + "\n" +
               "Nhà cung cấp: " + nhaCungCap + "\n" +
               "Thành phần: " + thanhPhan + "\n" +
               "Công dụng: " + congDung ;
    }
    public abstract double giaBan();
    public abstract void setGiaBan(double giaBan);
}
