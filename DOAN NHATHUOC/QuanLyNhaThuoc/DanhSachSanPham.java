package QuanLyNhaThuoc;
import java.util.ArrayList;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
public class DanhSachSanPham implements IQuanLy {
	private List<SanPham> danhSachSanPham;

	public DanhSachSanPham() {
		danhSachSanPham = new ArrayList<>();
	}

	public List<SanPham> getDanhSachSanPham() {
		return danhSachSanPham;
	}

	public void them() {
		Scanner scanner = new Scanner(System.in);

		int loaiSanPham;
		SanPham sanPham;

		while (true) {
			System.out.println("Nhập loại sản phẩm (1: Thuoc, 2: ThucPhamChucNang): ");
			try {
				loaiSanPham = Integer.parseInt(scanner.nextLine());
				if (loaiSanPham == 1 || loaiSanPham == 2) {
					break; // Loại sản phẩm hợp lệ, thoát khỏi vòng lặp
				} else {
					System.out.println("Loại sản phẩm không hợp lệ. Vui lòng nhập lại.");
				}
			} catch (NumberFormatException e) {
				System.out.println("Vui lòng nhập một số nguyên.");
			}
		}

		sanPham = (loaiSanPham == 1) ? new Thuoc() : new ThucPhamChucNang();

		sanPham.nhapThongTin(danhSachSanPham);
		danhSachSanPham.add(sanPham);

	}

	public void hienThiDanhSach() {
		if (danhSachSanPham.isEmpty()) {
			System.out.println("Danh sách sản phẩm trống!!!");
		} else {
			System.out.println("Danh sách sản phẩm: ");
			for (SanPham sanPham : danhSachSanPham) {
				System.out.println(sanPham.toString());
				System.out.println();
			}
		}
	}

	public void taiDanhSachTuFileText() {
		String tenTep = "C:\\Users\\Admin\\Desktop\\DOAN NHATHUOC\\Input\\sanpham.txt";
		try (BufferedReader br = new BufferedReader(new FileReader(tenTep))) {
			String line;
			while ((line = br.readLine()) != null) {
				if (line.startsWith("Mã sản phẩm: TP")) {
					ThucPhamChucNang thucPham = parseThucPhamChucNang(line, br);
					danhSachSanPham.add(thucPham);
				} else if (line.startsWith("Mã sản phẩm: T")) {
					Thuoc thuoc = parseThuoc(line, br);
					danhSachSanPham.add(thuoc);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Thuoc parseThuoc(String line, BufferedReader br) throws IOException {
		Thuoc thuoc = new Thuoc();
		thuoc.setMaSanPham(line.split(":")[1].trim());
		thuoc.setTenSanPham(br.readLine().split(":")[1].trim());
		thuoc.setGiaNhap(Double.parseDouble(br.readLine().split(":")[1].trim().replace(" ₫", "").replace(".", "")));
		thuoc.setSoLuong(Integer.parseInt(br.readLine().split(":")[1].trim()));
		thuoc.setNhaCungCap(br.readLine().split(":")[1].trim());
		thuoc.setThanhPhan(br.readLine().split(":")[1].trim());
		thuoc.setCongDung(br.readLine().split(":")[1].trim());
		thuoc.setGiaBan(thuoc.giaBan());
		return thuoc;
	}

	private ThucPhamChucNang parseThucPhamChucNang(String line, BufferedReader br) throws IOException {
		ThucPhamChucNang thucPham = new ThucPhamChucNang();
		thucPham.setMaSanPham(line.split(":")[1].trim());
		thucPham.setTenSanPham(br.readLine().split(":")[1].trim());
		thucPham.setGiaNhap(Double.parseDouble(br.readLine().split(":")[1].trim().replace(" ₫", "").replace(".", "")));
		thucPham.setSoLuong(Integer.parseInt(br.readLine().split(":")[1].trim()));
		thucPham.setNhaCungCap(br.readLine().split(":")[1].trim());
		thucPham.setThanhPhan(br.readLine().split(":")[1].trim());
		thucPham.setCongDung(br.readLine().split(":")[1].trim());

		thucPham.setGiaBan(thucPham.giaBan());
		return thucPham;
	}


	public void ghiDanhSachVaoFile() {
		String tenTep = "C:\\Users\\Admin\\Desktop\\DOAN NHATHUOC\\Input\\sanpham.txt";
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(tenTep, true))) {
			for (SanPham sanPham : danhSachSanPham) {
				// Kiểm tra xem mã sản phẩm đã tồn tại trong tệp chưa
				if (!kiemTraMaSanPhamTonTaiTrongTep(sanPham.getMaSanPham(), tenTep)) {
					writer.write(sanPham.toString());
					writer.newLine();

				}
			}
			System.out.println("Danh sách đã được ghi vào file.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Hàm kiểm tra mã sản phẩm đã tồn tại trong tệp hay chưa
	private boolean kiemTraMaSanPhamTonTaiTrongTep(String maSanPham, String tenTep) {
		try {
			List<String> lines = Files.readAllLines(Paths.get(tenTep));
			for (String line : lines) {
				if (line.contains("Mã sản phẩm: " + maSanPham)) {
					System.out.println("Sản phẩm có mã " + maSanPham + " đã tồn tại trong tệp.");
					return true;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	// Kiểm tra xem một sản phẩm có tồn tại trong danh sách không
	public boolean kiemTraTonTaiSanPham(String maSanPham) {
		for (SanPham sp : danhSachSanPham) {
			if (sp.getMaSanPham().equals(maSanPham)) {
				return true;
			}
		}
		return false;
	}


	public void sua() {
		Scanner scanner = new Scanner(System.in);
		String maSanPhamCanSua;
		SanPham sanPhamCanSua = null;

		System.out.println("Nhập mã sản phẩm cần sửa (hoặc nhập 'q' để thoát): ");
		maSanPhamCanSua = scanner.nextLine();

		if (maSanPhamCanSua.equalsIgnoreCase("q")) {
			return;
		}

		sanPhamCanSua = timSanPhamTheoMa(maSanPhamCanSua);

		if (sanPhamCanSua == null) {
			System.out.println("Không tìm thấy sản phẩm có mã " + maSanPhamCanSua);
			return;
		}

		boolean tiepTucSua = true;

		do {
			System.out.println("Thông tin sản phẩm trước khi sửa:");
			System.out.println(sanPhamCanSua.toString());

			hienThiMenuSuaThongTin(sanPhamCanSua);

			System.out.println("Nhập lựa chọn của bạn: ");
			int luaChon = Integer.parseInt(scanner.nextLine());

			switch (luaChon) {
				case 1:
					System.out.println("Nhập tên mới: ");
					sanPhamCanSua.setTenSanPham(scanner.nextLine());
					break;
				case 2:
					System.out.println("Nhập giá nhập mới: ");
					while (!scanner.hasNextDouble()) {
						System.out.println("Giá nhập phải là số. Vui lòng nhập lại: ");
						scanner.next();
					}
					sanPhamCanSua.setGiaNhap(scanner.nextDouble());
					scanner.nextLine(); // Xóa bộ đệm sau khi nhập số
					sanPhamCanSua.setGiaBan(sanPhamCanSua.giaBan());
					break;
				case 3:
					System.out.println("Nhập số lượng mới: ");
					while (!scanner.hasNextInt()) {
						System.out.println("Số lượng phải là số. Vui lòng nhập lại: ");
						scanner.next();
					}
					sanPhamCanSua.setSoLuong(scanner.nextInt());
					scanner.nextLine(); // Xóa bộ đệm sau khi nhập số
					sanPhamCanSua.setGiaBan(sanPhamCanSua.giaBan());
					break;
				case 4:
					System.out.println("Nhập nhà cung cấp mới: ");
					sanPhamCanSua.setNhaCungCap(scanner.nextLine());
					break;
				case 5:
					System.out.println("Nhập thành phần mới: ");
					sanPhamCanSua.setThanhPhan(scanner.nextLine());
					break;
				case 6:
					System.out.println("Nhập công dụng mới: ");
					sanPhamCanSua.setCongDung(scanner.nextLine());
					break;
				default:
					System.out.println("Lựa chọn không hợp lệ. Không có thông tin nào được sửa.");
					return;
			}

			System.out.println("Thông tin sản phẩm sau khi cập nhật:");
			System.out.println(sanPhamCanSua.toString());

			System.out.println("Bạn muốn tiếp tục sửa thông tin khác không? (có/không): ");
			String tiepTucLuaChon = scanner.nextLine().toLowerCase();
			tiepTucSua = tiepTucLuaChon.equals("có") || tiepTucLuaChon.equals("co");
		} while (tiepTucSua);

		capNhatTep();
		System.out.println("Thông tin sản phẩm đã được cập nhật.");
	}

	private void hienThiMenuSuaThongTin(SanPham sanPham) {
		if (sanPham == null) {
			System.out.println("Sản phẩm không tồn tại.");
			return;
		}
		System.out.println("Chọn thông tin cần sửa:");

		if (sanPham instanceof Thuoc) {
			System.out.println("1. Tên sản phẩm");
			System.out.println("2. Giá nhập");
			System.out.println("3. Số lượng");
			System.out.println("4. Nhà cung cấp");
			System.out.println("5. Thành phần");
			System.out.println("6. Công dụng");
		} else if (sanPham instanceof ThucPhamChucNang) {
			System.out.println("1. Tên sản phẩm");
			System.out.println("2. Giá nhập");
			System.out.println("3. Số lượng");
			System.out.println("4. Nhà cung cấp");
			System.out.println("5. Thành phần");
			System.out.println("6. Công dụng");

		}
	}

	// Tìm sản phẩm theo mã
	protected SanPham timSanPhamTheoMa(String maSanPham) {
		for (SanPham sanPham : danhSachSanPham) {
			if (sanPham.getMaSanPham().equals(maSanPham)) {
				return sanPham;
			}
		}
		return null;
	}


	private void capNhatTep() {
		String tenTep = "C:\\Users\\Admin\\Desktop\\DOAN NHATHUOC\\Input\\sanpham.txt";
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(tenTep))) {
			for (SanPham sanPham : danhSachSanPham) {
				bw.write(sanPham.toString());
				bw.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void xoa() {
		Scanner scanner = new Scanner(System.in);
		String maSanPhamCanXoa;
		SanPham sanPhamCanXoa = null;

		System.out.print("Nhập mã sản phẩm cần xóa (hoặc nhập 'q' để thoát): ");
		maSanPhamCanXoa = scanner.nextLine();

		if (maSanPhamCanXoa.equalsIgnoreCase("q")) {
			System.out.println("Hủy xóa sản phẩm.");
			return;
		}

		sanPhamCanXoa = timSanPhamTheoMa(maSanPhamCanXoa);

		if (sanPhamCanXoa == null) {
			System.out.println("Không tìm thấy sản phẩm có mã " + maSanPhamCanXoa);
			return;
		}

		System.out.println("Thông tin sản phẩm trước khi xóa:");
		System.out.println(sanPhamCanXoa.toString());

		System.out.print("Bạn có chắc chắn muốn xóa sản phẩm này? (có/không): ");
		String xacNhan = scanner.nextLine().toLowerCase();

		if (xacNhan.equals("có") || xacNhan.equals("co")) {
			danhSachSanPham.remove(sanPhamCanXoa);
			capNhatTep();
			System.out.println("Sản phẩm đã được xóa khỏi danh sách.");
		} else {
			System.out.println("Hủy xóa sản phẩm.");
		}
	}

	public void timKiem() {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Nhập từ khóa cần tìm kiếm: ");
		String tuKhoa = scanner.nextLine();

		List<String> lines; // Danh sách chứa các dòng trong tệp
		boolean timThay = false; // Biến kiểm tra xem có tìm thấy sản phẩm hay không

		try {
			lines = Files.readAllLines(Paths.get("C:\\Users\\Admin\\Desktop\\DOAN NHATHUOC\\Input\\sanpham.txt"));
			for (int i = 0; i < lines.size(); i++) {
				String line = lines.get(i);
				if (line.contains(tuKhoa)) {
					// Hiển thị thông tin sản phẩm được tìm thấy
					System.out.println("Sản phẩm được tìm thấy trong tệp, dòng " + (i + 1) + ":");
					// Print the line containing the keyword
					System.out.println(line);
					// In thêm thông tin sản phẩm
					int soDong = 0; // Số dòng thông tin sản phẩm
					while (soDong < 8 && (i + soDong + 1) < lines.size()) { // Giả sử mỗi sản phẩm chiếm 8 dòng
						System.out.println(lines.get(i + soDong + 1));
						soDong++;
					}
					System.out.println(); // In thêm dòng trống để ngăn cách giữa các sản phẩm
					timThay = true;
					i += soDong; // Cập nhật chỉ số i để bỏ qua các dòng đã in
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (!timThay) {
			System.out.println("Không tìm thấy sản phẩm nào chứa từ khóa '" + tuKhoa + "' trong tệp.");
		}
	}

	public void xuatDanhSach(){
		if (danhSachSanPham.isEmpty()){
			System.out.println("Danh sach san pham rong ");
		}else {
			for (SanPham sanPham: danhSachSanPham){
				System.out.println(sanPham.toString());
				System.out.println();
			}
		}
	}
	public void ghiDanhSach() throws IOException {
		String tenTep =	"C:\\Users\\Admin\\Desktop\\DOAN NHATHUOC\\Input\\sanpham.txt";
		BufferedWriter bw = new BufferedWriter(new FileWriter(tenTep));
		for (SanPham sanPham: danhSachSanPham){
			if (!kiemTraMaSanPhamTonTaiTrongTep(sanPham.getMaSanPham(), tenTep));
			bw.write(sanPham.toString());
			bw.newLine();
		}
	}
}