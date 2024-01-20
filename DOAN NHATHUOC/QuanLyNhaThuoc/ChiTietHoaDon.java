package QuanLyNhaThuoc;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChiTietHoaDon {
    private DanhSachSanPham danhSachSanPham;
    private List<SanPham> chiTietHoaDon;

    public ChiTietHoaDon() {
        this.chiTietHoaDon = new ArrayList<>();
    }
    
    public ChiTietHoaDon(DanhSachSanPham danhSachSanPham) {
        this.danhSachSanPham = danhSachSanPham;
        this.chiTietHoaDon = new ArrayList<>();
    }

    public DanhSachSanPham getDanhSachSanPham() {
        return danhSachSanPham;
    }
    
    public void setDanhSachSanPham(DanhSachSanPham danhSachSanPham) {
  		this.danhSachSanPham = danhSachSanPham;
  	}

    public List<SanPham> getChiTietHoaDon() {
        return chiTietHoaDon;
    }

    public void setChiTietHoaDon(List<SanPham> chiTietHoaDon) {
        this.chiTietHoaDon = chiTietHoaDon;
    }
    

	// Tính tổng tiền của hóa đơn
    public double tinhTongTien() {
        double tongTien = 0.0;
        for (SanPham sanPham : chiTietHoaDon) {
            tongTien += sanPham.giaBan() * sanPham.getSoLuong();
        }
        return tongTien;
    }

    
    public void nhapChiTietHoaDon(DanhSachSanPham danhSachSanPham) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Nhập mã sản phẩm hoặc nhấn Enter để kết thúc:");
            String maSanPham = scanner.nextLine().trim();

            if (maSanPham.isEmpty()) {
                // Nếu người dùng nhấn Enter mà không nhập mã sản phẩm thì thoát khỏi vòng lặp
                break;
            }

            // Kiểm tra xem mã sản phẩm có tồn tại trong danh sách không
            if (danhSachSanPham.kiemTraTonTaiSanPham(maSanPham)) {
                // Kiểm tra xem mã sản phẩm đã tồn tại trong hóa đơn chưa
                if (!kiemTraTonTaiSanPhamTrongHoaDon(maSanPham)) {
                    // Lấy thông tin sản phẩm từ danh sách
                    SanPham sanPham = danhSachSanPham.timSanPhamTheoMa(maSanPham);

                    // Nhập số lượng sản phẩm
                    System.out.println("Nhập số lượng sản phẩm:");
                    int soLuong = 0;
                    while (true) {
                        try {
                            soLuong = Integer.parseInt(scanner.nextLine());
                            if (soLuong > 0) {
                                break;
                            } else {
                                System.out.println("Số lượng phải lớn hơn 0. Vui lòng nhập lại.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Vui lòng nhập một số nguyên.");
                        }
                    }

                    // Thêm sản phẩm vào chi tiết hóa đơn
                    themSanPhamVaoHoaDon(maSanPham,soLuong);
                } else {
                    System.out.println("Sản phẩm đã tồn tại trong hóa đơn. Vui lòng chọn sản phẩm khác.");
                }
            } else {
                System.out.println("Sản phẩm không tồn tại trong danh sách.");
            }
        }

        System.out.println("Thông tin chi tiết hóa đơn đã nhập.");
    }
   
    private boolean kiemTraTonTaiSanPhamTrongHoaDon(String maSanPham) {
        for (SanPham sp : chiTietHoaDon) {
            if (sp.getMaSanPham().equals(maSanPham)) {
                return true;
            }
        }
        return false;
    }

    protected void themSanPhamVaoHoaDon(String maSanPham, int soLuong) {
        SanPham sanPham = danhSachSanPham.timSanPhamTheoMa(maSanPham);

        if (sanPham != null) {
            sanPham.setSoLuong(soLuong);
            chiTietHoaDon.add(sanPham);
        } else {
            System.out.println("Không tìm thấy sản phẩm với mã " + maSanPham);
        }
    }

    public void xuatChiTietHoaDon() {
        for (SanPham sanPham : chiTietHoaDon) {
            System.out.println("Mã sản phẩm: " + sanPham.getMaSanPham());
            System.out.println("Tên sản phẩm: " + sanPham.getTenSanPham());
            System.out.println("Số lượng: " + sanPham.getSoLuong());
            System.out.println("Thành tiền: " + String.format("%,.0f ₫", sanPham.giaBan() * sanPham.getSoLuong()));
        }
        System.out.println("Tổng cộng: " + String.format("%,.0f ₫", tinhTongTien()));
    }
        
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (SanPham sanPham : chiTietHoaDon) {
            result.append("Mã sản phẩm: ").append(sanPham.getMaSanPham()).append("\n");
            result.append("Tên sản phẩm: ").append(sanPham.getTenSanPham()).append("\n");
            result.append("Số lượng: ").append(sanPham.getSoLuong()).append("\n");
            result.append("Thành tiền: ").append(String.format("%,.0f ₫", sanPham.giaBan() * sanPham.getSoLuong())).append("\n");
        }
        result.append("Tổng cộng: ").append(String.format("%,.0f ₫", tinhTongTien())).append("\n");
        return result.toString();  
    }


}
