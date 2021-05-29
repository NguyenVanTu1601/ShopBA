package mainstore;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javassist.expr.NewArray;
import mainstore.api.MatHangController;
import mainstore.model.HangNhap;
import mainstore.model.MatHang;

@SpringBootTest
@Transactional
@Rollback(true)
class TestJunit {

	// Đã check db = cách nếu insert 1 ng vào thì lấy luôn ng đó ra + so sánh vs 1 mẫu có sẵn
	// nếu trùng nhau thì là đã ngon
	@Autowired
	MatHangController matHangController;
	
		// getAllMatHang() th ko có mặt hàng nào
		@Test
		public void getAllMatHang_Empty() {
			
			// init - delete all mathang
			for (int i = 0; i < 14; i ++) {
				matHangController.deleteMatHang(i);
			}
			
			// test
			List<MatHang> lisMatHangs = matHangController.getAllMatHang();
			Assertions.assertEquals(lisMatHangs, new ArrayList<MatHang>());
		}
		
		// lấy danh sách mặt hàng tồn tại trong csdl
		public void getAllMatHang_Exists() {
			List<MatHang> lisMatHangTest = matHangController.getAllMatHang();
			Assertions.assertEquals(13, lisMatHangTest.size());
		}
		
		// get by id
		// id ko có
		@Test
		public void testGetById_FirstRecord() {
			MatHang matHang = matHangController.getMatHang(20);
			Assertions.assertEquals(matHang, null);
		}
		
		// id = null
		@Test
		public void testGetById_SecondRecord() {
			MatHang matHang = matHangController.getMatHang(null);
			Assertions.assertEquals(matHang, null);
		}
		
		//id tồn tại
		@Test
		public void testGetById_Exists() {
			MatHang matHang = matHangController.getMatHang(1);
			MatHang matHangTest = new MatHang(1, "Đồ dùng học tập","Bút bi",100,2500,3000,1);
			Assertions.assertEquals(matHang.getIdMatHang(), matHangTest.getIdMatHang());
		}
		
		
		// get by nam
		// tên tồn tại
		@Test
	    public void testFindByNameExist_FirstValue() {
			List<MatHang> listMatHang = matHangController.searchMatHang("Sách");
			MatHang matHang = listMatHang.get(0);
			MatHang matHangTest = new MatHang(2, "Sách","Sách Tiếng Anh",4,15000,17000,1);
			Assertions.assertEquals(matHang, matHangTest);
		
		}
		
		// tên bằng null
		
		@Test
		public void testFindByName_Null() {
			List<MatHang> listMatHang = matHangController.searchMatHang(null);
			Assertions.assertEquals(listMatHang, null);
			
		}
		// tìm mới tên để trống
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
		
		// thêm mặt hàng
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
			
			MatHang matHang = new MatHang("Đồ dùng học tập","Tẩy bút chì",0,2500,3000,1);
			matHang = matHangController.addMatHang(matHang);
			MatHang matHangTest = new MatHang(14, "Đồ dùng học tập","Tẩy bút chì",0,2500,3000,1);
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
			MatHang matHang = new MatHang("Đồ dùng học tập","Bút bi",0,2500,3000,1);
			matHang = matHangController.addMatHang(matHang);
			Assertions.assertEquals(matHang, null);
		}
		
		// Sửa mặt hàng 1 từ bút bi thành tẩy bút chỉ
		@Test
		public void editMatHangSuccess() {
			MatHang matHang = new MatHang(1, "Đồ dùng học tập","Tẩy bút chì",100,2700,3000,1);
			matHang = matHangController.addMatHang(matHang);
			MatHang matHangTest = new MatHang(1, "Đồ dùng học tập","Tẩy bút chì",100,2700,3000,1);
			Assertions.assertEquals(matHang, matHangTest);
		}
		
		// xóa mặt hàng
		// mặt hàng có tồn tại
		@Test
		public void deleteSuccess_idExists() {
			matHangController.deleteMatHang(1);
			MatHang matHang = matHangController.getMatHang(1);
			Assertions.assertEquals(matHang.getActive(), 0);
			Assertions.assertEquals(matHang.getIdMatHang(), 1);
		}
		
		// cập nhật số lượng khi thêm mặt hàng
		@Test
		public void updateAmount() {
			MatHang matHang = new MatHang(11, "Đồ dùng học tập","Bút xóa", 8,6800,7000,1);
			HangNhap hangNhap = new HangNhap(2,6800,matHang);
			matHangController.updateMatHang(hangNhap);
			
			MatHang matHangTest = matHangController.getMatHang(11);
			Assertions.assertEquals(11, matHangTest.getIdMatHang());
			Assertions.assertEquals("Đồ dùng học tập",matHangTest.getLoaiMatHang());
			Assertions.assertEquals("Bút xóa", matHangTest.getTenMatHang());
			Assertions.assertEquals(10, matHangTest.getSoLuong());
			Assertions.assertEquals(6800, matHangTest.getGiaNhap());
			Assertions.assertEquals(7000, matHangTest.getGiaBan());
			Assertions.assertEquals(1, matHangTest.getActive());
			
		}
}
