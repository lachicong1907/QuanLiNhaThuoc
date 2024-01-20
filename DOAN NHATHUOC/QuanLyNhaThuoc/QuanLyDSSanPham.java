package QuanLyNhaThuoc;
import java.util.Scanner;

public class QuanLyDSSanPham {

    private DanhSachSanPham danhSachSanPham;

    public QuanLyDSSanPham() {
        danhSachSanPham = new DanhSachSanPham();
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);
        int luaChon;

        do {
        	System.out.println("===== Quản Lý Nhà Thuốc =====");
        	System.out.println("1. Thêm sản phẩm");
        	System.out.println("2. Hiển thị danh sách sản phẩm");
            System.out.println("3. Tải danh sách sản phẩm từ file");
            System.out.println("4. Ghi danh sách vào file");
            System.out.println("5. Sửa thông tin của sản phẩm");
            System.out.println("6. Xóa sản phẩm");
            System.out.println("7. Tìm kiếm sản phẩm");
            System.out.println("8. Số lượng sản phẩm");
        	System.out.println("0. Thoát");
        	System.out.print("Nhập lựa chọn của bạn: ");
        	luaChon = scanner.nextInt();
        	scanner.nextLine();  // Đọc dòng thừa

        	switch (luaChon) {
        	    case 1:
        	        danhSachSanPham.them();
        	        break;
        	    case 2:
        	        danhSachSanPham.hienThiDanhSach();
        	        break;
        	    case 3:
        	    	danhSachSanPham.taiDanhSachTuFileText();
                    break;
        	    case 4:
                    danhSachSanPham.ghiDanhSachVaoFile();
                    break; 
        	    case 5:
                    danhSachSanPham.sua();
                    break;
        	    case 6:
                    danhSachSanPham.xoa();
                    break; 
        	    case 7:
	                danhSachSanPham.timKiem();
	                break; 
        	    case 8:
        	    	SanPham.tangSoLuongSanPham();
        	        System.out.println("Số lượng sản phẩm đã tăng lên: " + SanPham.getSoLuongSanPham());
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

