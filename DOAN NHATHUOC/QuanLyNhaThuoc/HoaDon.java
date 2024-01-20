package QuanLyNhaThuoc;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class HoaDon {
    private String maHoaDon;
    private String ngayTaoHoaDon;
    private KhachHang khachHang;
    private NhanVien nhanVienTaoHoaDon;
    private List<ChiTietHoaDon> danhSachChiTietHoaDon;
	private double tongCong; 
    private double thanhTien;
    
    public HoaDon()
    {
    	 danhSachChiTietHoaDon = new ArrayList<>();
    }
    public HoaDon(String maHoaDon,String ngayTaoHoaDon, KhachHang khachHang, NhanVien nhanVienTaoHoaDon) {
        this.maHoaDon = maHoaDon;
        this.ngayTaoHoaDon = ngayTaoHoaDon;
        this.khachHang = khachHang;
        this.nhanVienTaoHoaDon = nhanVienTaoHoaDon;
        this.danhSachChiTietHoaDon = new ArrayList<>();
    }
    public double getThanhTien() {
		return thanhTien;
	}
    
	public void setThanhTien(double thanhTien) {
		this.thanhTien = thanhTien;
	}


	public String getMaHoaDon() {
		return maHoaDon;
	}


	public void setMaHoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
	}


	public String getNgayTaoHoaDon() {
		return ngayTaoHoaDon;
	}


	public void setNgayTaoHoaDon(String ngayTaoHoaDon) {
		this.ngayTaoHoaDon = ngayTaoHoaDon;
	}


	public KhachHang getKhachHang() {
		return khachHang;
	}


	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}


	public NhanVien getNhanVienTaoHoaDon() {
		return nhanVienTaoHoaDon;
	}


	public void setNhanVienTaoHoaDon(NhanVien nhanVienTaoHoaDon) {
		this.nhanVienTaoHoaDon = nhanVienTaoHoaDon;
	}


	public List<ChiTietHoaDon> getChiTietHoaDonList() {
		return danhSachChiTietHoaDon;
	}


	public void setChiTietHoaDonList(List<ChiTietHoaDon>danhSachChiTietHoaDon) {
		this.danhSachChiTietHoaDon = danhSachChiTietHoaDon;
	}


	public Double getTongCong() {
		return tongCong;
	}


	public void setTongCong(double tongCong) {
		this.tongCong = tongCong;
	}
	
	// Thêm chi tiết hóa đơn vào danh sách
    public void themChiTietHoaDon(ChiTietHoaDon chiTietHoaDon) {
    	danhSachChiTietHoaDon.add(chiTietHoaDon);
    }

    // Tính tổng tiền của hóa đơn
    public double tinhTongTienHoaDon() {
        double tongTien = 0.0;
        for (ChiTietHoaDon chiTiet : danhSachChiTietHoaDon) {
            tongTien += chiTiet.tinhTongTien();
        }
        return tongTien;
    }
    

    private boolean kiemTraMaHoaDon(String maHoaDon, List<HoaDon> danhSachHoaDon) {
        for (HoaDon hoaDon : danhSachHoaDon) {
            if (hoaDon.getMaHoaDon().equals(maHoaDon)) {
                System.out.println("Mã hóa đơn đã tồn tại. Vui lòng nhập lại.");
                return false;
            }
        }
        return true;
    }
    
 // Nhập thông tin hóa đơn
    public void nhapThongTin(DanhSachSanPham danhSachSanPham, List<HoaDon> danhSachHoaDon) {
        Scanner scanner = new Scanner(System.in);

     // Yêu cầu người dùng nhập mã khách hàng và kiểm tra trùng lặp
        do {
            System.out.println("Nhập mã hóa đơn: ");
            maHoaDon = scanner.nextLine();
        } while (!kiemTraMaHoaDon(maHoaDon, danhSachHoaDon));

        System.out.println("Nhập ngày tạo hóa đơn (dd/MM/yyyy): ");
        ngayTaoHoaDon = scanner.nextLine();

        // Nhập thông tin khách hàng
        System.out.print("Nhập tên khách hàng: ");
        khachHang = new KhachHang();
        khachHang.setTenKhachHang(scanner.nextLine());

        // Nhập thông tin nhân viên tạo hóa đơn
        System.out.print("Nhập tên nhân viên tạo hóa đơn: ");
        nhanVienTaoHoaDon = new NhanVien();
        nhanVienTaoHoaDon.setTen(scanner.nextLine());
        
        ChiTietHoaDon chiTiet = new ChiTietHoaDon(danhSachSanPham);
         chiTiet.nhapChiTietHoaDon(danhSachSanPham);
         themChiTietHoaDon(chiTiet);
   }
 // Hiển thị thông tin hóa đơn
    public void xuatHoaDon() {
        System.out.println("Mã hóa đơn: " + maHoaDon);
        System.out.println("Ngày tạo hóa đơn: " + ngayTaoHoaDon);
        if (khachHang != null) {
            System.out.println("Tên khách hàng: " + khachHang.getTenKhachHang());
        } else {
            System.out.println("Khách hàng chưa được thiết lập.");
        }
        System.out.println("Nhân viên tạo hóa đơn: " + nhanVienTaoHoaDon.getTen());
        for (ChiTietHoaDon chiTiet : danhSachChiTietHoaDon) {
            chiTiet.xuatChiTietHoaDon();
        }
    }
    
    public void suaChiTietHoaDon(DanhSachSanPham danhSachSanPham) {
        Scanner scanner = new Scanner(System.in);

        // Hiển thị danh sách chi tiết hóa đơn
        xuatHoaDon();

        // Nhập mã sản phẩm cần sửa
        System.out.println("Nhập mã sản phẩm cần sửa trong chi tiết hóa đơn:");
        String maSanPham = scanner.nextLine().trim();

        // Kiểm tra xem mã sản phẩm có tồn tại trong hóa đơn không
        boolean found = false;
        for (ChiTietHoaDon chiTiet : danhSachChiTietHoaDon) {
            for (SanPham sanPham : chiTiet.getChiTietHoaDon()) {
                if (sanPham.getMaSanPham().equals(maSanPham)) {
                    // Hiển thị thông tin sản phẩm
                    System.out.println("Thông tin sản phẩm cần sửa:");
                    System.out.println("Mã sản phẩm: " + sanPham.getMaSanPham());
                    System.out.println("Tên sản phẩm: " + sanPham.getTenSanPham());
                    System.out.println("Số lượng: " + sanPham.getSoLuong());
                    System.out.println("Thành tiền: " + String.format("%,.0f ₫", sanPham.giaBan() * sanPham.getSoLuong()));

                    // Nhập số lượng mới
                    System.out.println("Nhập số lượng mới:");
                    int soLuongMoi;
                    while (true) {
                        try {
                            soLuongMoi = Integer.parseInt(scanner.nextLine());
                            if (soLuongMoi > 0) {
                                break;
                            } else {
                                System.out.println("Số lượng phải lớn hơn 0. Vui lòng nhập lại.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Vui lòng nhập một số nguyên.");
                        }
                    }

                    // Cập nhật số lượng sản phẩm trong chi tiết hóa đơn
                    sanPham.setSoLuong(soLuongMoi);

                    System.out.println("Chi tiết hóa đơn đã được cập nhật.");

                    found = true;
                    break;
                }
            }
        }

        if (!found) {
            System.out.println("Không tìm thấy sản phẩm với mã " + maSanPham + " trong chi tiết hóa đơn.");
        }
    }

   
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Mã hóa đơn: ").append(maHoaDon).append("\n");
        result.append("Ngày tạo hóa đơn: ").append(ngayTaoHoaDon).append("\n");
        result.append("Khách hàng: ").append(khachHang.getTenKhachHang()).append("\n");
        result.append("Nhân viên tạo hóa đơn: ").append(nhanVienTaoHoaDon.getTen()).append("\n");


        result.append(danhSachChiTietHoaDon.toString());
        return result.toString();
    }
    
    
  
}
