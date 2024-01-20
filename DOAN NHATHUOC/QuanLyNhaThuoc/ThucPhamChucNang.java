package QuanLyNhaThuoc;
import java.util.List;
import java.util.Scanner;
public class ThucPhamChucNang extends SanPham {
    private double giaBan; 

    public ThucPhamChucNang() {
        super();
        this.giaBan = 0.0; 
    }

    public ThucPhamChucNang(String maSanPham, String tenSanPham, double giaNhap, int soLuong,
                            String nhaCungCap, String thanhPhan, String congDung) {
        super(maSanPham, tenSanPham, giaNhap, soLuong, nhaCungCap, thanhPhan, congDung);
        this.giaBan = giaBan(); 
    }

    public double getGiaBan() {
        return giaBan;
    }

    @Override
    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }

    @Override
    public void nhapThongTin(List<SanPham> danhSachSanPham) {
        super.nhapThongTin(danhSachSanPham);
        Scanner scanner = new Scanner(System.in);

        setGiaBan(giaBan());
    }

    @Override
    public void xuatThongTin() {
        super.xuatThongTin();
        String giaBanChuoi = String.format("%,.0f ₫", getGiaBan());
        System.out.println("Giá bán: " + giaBanChuoi);
    }

    @Override
    public double giaBan() {
//        return getGiaNhap() * 0.96;
    	double chietKhau = 0.04; // 4%
        double thue = 0.1; // 10% tax
        return (getGiaNhap() - (getGiaNhap() * chietKhau)) + (getGiaNhap() * thue);
    }

    @Override
    public String toString() {
        String giaBanChuoi = String.format("%,.0f ₫", getGiaBan());

        return super.toString() +
                "\nGiá bán: " + giaBanChuoi + "\n";
    }

}
