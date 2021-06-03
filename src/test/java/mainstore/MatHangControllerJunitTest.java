package mainstore;



import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import mainstore.api.MatHangController;
import mainstore.model.HangNhap;
import mainstore.model.MatHang;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback(true)
class MatHangControllerJunitTest {

	@Autowired
	MatHangController matHangController;

	/* GetAll */
	// Trường hợp ko có mặt hàng nào trong csdl
	@Test
	public void testGetAllMatHang_Empty() {

		// init - delete all mathang
		int sizeDB = matHangController.getAllMatHang().size();
		for (int i = 1; i <= sizeDB; i++) {
			matHangController.deleteMatHang(i);
		}

		// test
		List<MatHang> lisMatHangs = matHangController.getAllMatHang();
		Assertions.assertEquals(lisMatHangs, new ArrayList<MatHang>());
	}

	// Trường hợp có mặt hàng trong csdl
	@Test
	public void testGetAllMatHang_Exists() {
		List<MatHang> lisMatHangTest = matHangController.getAllMatHang();
		Assertions.assertEquals(13, lisMatHangTest.size());
	}

	// Get mặt hàng by id
	// Id mặt hàng không có trong csdl
	@Test
	public void testGetById_NotExists() {
		MatHang matHang = matHangController.getMatHang(20);
		Assertions.assertEquals(matHang, null);
	}

	// id = null
	@Test
	public void testGetById_Null() {
		MatHang matHang = matHangController.getMatHang(null);
		Assertions.assertEquals(matHang, null);
	}

	// id có trong csdl
	@Test
	public void testGetById_Exists() {
		MatHang matHang = matHangController.getMatHang(1);
		MatHang matHangTest = new MatHang(1, "Đồ dùng học tập", "Bút bi", 100, 2500, 3000, 1);
		Assertions.assertEquals(matHang.getIdMatHang(), matHangTest.getIdMatHang());
	}

	// get mặt hàng by name
	// tên tồn tại
	@Test
	public void testFindByName_Exist() {
		List<MatHang> listMatHang = matHangController.searchMatHang("Sách");
		MatHang matHang = listMatHang.get(0);
		MatHang matHangTest = new MatHang(2, "Sách", "Sách Tiếng Anh", 5, 15500, 17000, 1);
		Assertions.assertEquals(matHang.getIdMatHang(), matHangTest.getIdMatHang());
		Assertions.assertEquals(matHang.getTenMatHang(), matHangTest.getTenMatHang());
		Assertions.assertEquals(matHang.getSoLuong(), matHangTest.getSoLuong());
		Assertions.assertEquals(matHang.getGiaBan(), matHangTest.getGiaBan());
		Assertions.assertEquals(matHang.getGiaNhap(), matHangTest.getGiaNhap());
		Assertions.assertEquals(matHang.getActive(), matHangTest.getActive());
		

	}

	// tên = null
	@Test
	public void testFindByName_Null() {
		List<MatHang> listMatHang = matHangController.searchMatHang(null);
		Assertions.assertEquals(listMatHang, null);

	}

	// tên để trống
	@Test
	public void testFindByName_Empty() {
		List<MatHang> listMatHang = matHangController.searchMatHang("");
		List<MatHang> listMatHangTestHangs = matHangController.getAllMatHang();
		Assertions.assertEquals(listMatHang.size(), listMatHangTestHangs.size());

	}

	// tên ko tổn tại
	@Test
	public void testFindByName_NotExist() {
		List<MatHang> listMatHang = matHangController.searchMatHang("Búa");
		Assertions.assertEquals(listMatHang, new ArrayList<MatHang>());

	}

	/* thêm mặt hàng */
	// trường hợp mặt hàng null
	@Test
	public void addMatHang_NULL() {
		MatHang matHang = null;
		matHang = matHangController.addMatHang(matHang);
		Assertions.assertEquals(matHang, null);
	}

	// trương hợp mặt hàng chưa có trong csdl
	@Test
	public void addMatHangSuccess() {

		MatHang matHang = new MatHang("Đồ dùng học tập", "Tẩy bút chì", 0, 2500, 3000, 1);
		matHang = matHangController.addMatHang(matHang);
		MatHang matHangTest = new MatHang(61, "Đồ dùng học tập", "Tẩy bút chì", 0, 2500, 3000, 1);
		Assertions.assertEquals(matHang.getIdMatHang(), matHangTest.getIdMatHang());
		Assertions.assertEquals(matHang.getLoaiMatHang(), matHangTest.getLoaiMatHang());
		Assertions.assertEquals(matHang.getTenMatHang(), matHangTest.getTenMatHang());
		Assertions.assertEquals(matHang.getSoLuong(), matHangTest.getSoLuong());
		Assertions.assertEquals(matHang.getGiaBan(), matHangTest.getGiaBan());
		Assertions.assertEquals(matHang.getGiaNhap(), matHangTest.getGiaNhap());
		Assertions.assertEquals(matHang.getActive(), matHangTest.getActive());

	}

	// trường hợp thêm mặt hàng đã có tên trong csdl
	@Test
	public void addMatHang_Exsits() {
		MatHang matHang = new MatHang("Đồ dùng học tập", "Bút bi", 0, 2500, 3000, 1);
		matHang = matHangController.addMatHang(matHang);
		Assertions.assertEquals(matHang, null);
	}

	// Sửa mặt hàng 1 từ bút bi thành tẩy bút chỉ
	@Test
	public void editMatHangSuccess() {
		MatHang matHang = new MatHang(1, "Đồ dùng học tập", "Tẩy bút chì", 100, 2700, 3000, 1);
		matHang = matHangController.addMatHang(matHang);
		MatHang matHangTest = new MatHang(1, "Đồ dùng học tập", "Tẩy bút chì", 100, 2700, 3000, 1);
		Assertions.assertEquals(matHang.getIdMatHang(), matHangTest.getIdMatHang());
		Assertions.assertEquals(matHang.getTenMatHang(), matHangTest.getTenMatHang());
		Assertions.assertEquals(matHang.getSoLuong(), matHangTest.getSoLuong());
		Assertions.assertEquals(matHang.getGiaNhap(), matHangTest.getGiaNhap());
		Assertions.assertEquals(matHang.getGiaBan(), matHangTest.getGiaBan());
		Assertions.assertEquals(matHang.getActive(), matHangTest.getActive());
		Assertions.assertEquals(matHang.getLoaiMatHang(), matHangTest.getLoaiMatHang());
		
	}

	// xóa mặt hàng
	// mặt hàng có tồn tại
	@Test
	public void deleteSuccess() {
		matHangController.deleteMatHang(1);
		MatHang matHang = matHangController.getMatHang(1);
		Assertions.assertEquals(matHang.getActive(), 0);
		Assertions.assertEquals(matHang.getIdMatHang(), 1);
	}

	// cập nhật số lượng khi thêm mặt hàng
	@Test
	public void updateAmount() {
		MatHang matHang = new MatHang(11, "Đồ dùng học tập", "Bút xóa", 8, 6500, 7000, 1);
		HangNhap hangNhap = new HangNhap(2, 6800, matHang);
		matHangController.updateMatHang(hangNhap);

		MatHang matHangTest = matHangController.getMatHang(11);
		Assertions.assertEquals(11, matHangTest.getIdMatHang());
		Assertions.assertEquals("Đồ dùng học tập", matHangTest.getLoaiMatHang());
		Assertions.assertEquals("Bút xóa", matHangTest.getTenMatHang());
		Assertions.assertEquals(10, matHangTest.getSoLuong());
		Assertions.assertEquals(6500, matHangTest.getGiaNhap());
		Assertions.assertEquals(7000, matHangTest.getGiaBan());
		Assertions.assertEquals(1, matHangTest.getActive());

	}
}
