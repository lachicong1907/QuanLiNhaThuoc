package QuanLyNhaThuoc;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		DanhSachSanPham danhSachSanPham = new DanhSachSanPham();
		danhSachSanPham.taiDanhSachTuFileText(); // Load data from file
		danhSachSanPham.hienThiDanhSach();

		QuanLyDSSanPham sp = new QuanLyDSSanPham();
		QuanLyDSHoaDon hd = new QuanLyDSHoaDon();
		QuanLyDSKhachHang kh = new QuanLyDSKhachHang();
		QuanLyDSNhanVien nv = new QuanLyDSNhanVien();

		int luaChon;
		do {
			System.out.println("Chọn một số để tiếp tục:");
			System.out.println("1. Quản lý sản phẩm");
			System.out.println("2. Quản lý hóa đơn");
			System.out.println("3. Quản lý khách hàng");
			System.out.println("4. Quản lý nhân viên");
			System.out.println("0. Thoát");
			System.out.print("Nhập lựa chọn của bạn: ");
			luaChon = scanner.nextInt();

			switch (luaChon) {
				case 1:
					sp.menu();
					break;
				case 2:
					hd.menu();
					break;
				case 3:
					kh.menu();
					break;
				case 4:
					nv.menu();
					break;
				case 0:
					System.out.println("Chương trình kết thúc.");
					break;
				default:
					System.out.println("Lựa chọn không hợp lệ, vui lòng nhập lại.");
			}
		} while (luaChon != 0);

		scanner.close();
	}
}
