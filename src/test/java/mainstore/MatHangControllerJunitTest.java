package mainstore;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import mainstore.api.MatHangController;
import mainstore.model.MatHang;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@Rollback(true)
class MatHangControllerJunitTest {

	private static int SOLUONG = 13;
	private static String TEXT_SEARCH = "Sách";
	private static String TEXT_SEARCH_NOT_EXISTS = "Búa";
	
	@Autowired
	MatHangController matHangController;
	
	/* Test function get list mặt hàng */
	
	// Test số lượng mặt hàng nhận được khi gọi API
	@Test
	public void testNumberListGetAll() {
		List<MatHang> listMatHang = matHangController.getAllMatHang();
		Assertions.assertEquals(listMatHang.size(), SOLUONG);
	}
	
	
	// Test giá trị đầu tiên của list nhận được
	@Test
	public void testFirstValueGetAll() {
		List<MatHang> listMatHang = matHangController.getAllMatHang();
		MatHang matHang = listMatHang.get(0);
		MatHang matHangTest = new MatHang(1, "Đồ dùng học tập","Bút bi",100,2500,3000,1);
		Assertions.assertEquals(matHang, matHangTest);
		
	}
	
	// test giá trị thứ 2 của list nhận được
	@Test
	public void testSecondValueGetAll() {
		List<MatHang> listMatHang = matHangController.getAllMatHang();
		MatHang matHang = listMatHang.get(1);
		MatHang matHangTest = new MatHang(2, "Sách","Sách Tiếng Anh",4,15000,17000,1);
		Assertions.assertEquals(matHang, matHangTest);
	}
	
	// test giá trị cuối cùng của list
	@Test
	public void testFinalValueGetAll() {
		List<MatHang> listMatHang = matHangController.getAllMatHang();
		MatHang matHang = listMatHang.get(12);
		MatHang matHangTest = new MatHang(13, "Sách","Sách Ngữ Văn",10,17000,17500,1);
		Assertions.assertEquals(matHang, matHangTest);
	}
	
	// test giá trị gần cuối của list - khác nhau chữ hóa và chữ hoá -> có thể làm test fauil cho vui
	@Test
	public void testNearFinalValueGetAll() {
		List<MatHang> listMatHang = matHangController.getAllMatHang();
		MatHang matHang = listMatHang.get(11);
		MatHang matHangTest = new MatHang(12, "Sách","Sách Hoá Học",10,12800,13000,1);
		Assertions.assertEquals(matHang, matHangTest);
	}
	
	// test giá trị ở giữa của list
	@Test
	public void testMidValueGetAll() {
		List<MatHang> listMatHang = matHangController.getAllMatHang();
		MatHang matHang = listMatHang.get(7);
		MatHang matHangTest = new MatHang(8, "Đồ dùng học tập","Bút chì",120,3500,4000,1);
		Assertions.assertEquals(matHang, matHangTest);
	}

	
	/* Test function tìm kiếm mặt hàng theo tên */
	// test trường hợp không tồn tại bản ghi có tên thỏa mãn 
	@Test
	public void testFindByName_NotExist() {
		
		List<MatHang> listMatHang = matHangController.searchMatHang(TEXT_SEARCH_NOT_EXISTS);
		
		Assertions.assertEquals(listMatHang, new ArrayList<MatHang>());
	}
	
	// Test trường hợp tên mặt hàng trống
	@Test
	public void testFindByName_Null() {
		
		List<MatHang> listMatHang = matHangController.searchMatHang("");
		
		Assertions.assertEquals(listMatHang, new ArrayList<MatHang>());
	}
	
	// test giá trị đầu trả về của mảng kết quả tìm kiếm
	@Test
    public void testFindByNameExist_FirstValue() {
		List<MatHang> listMatHang = matHangController.searchMatHang(TEXT_SEARCH);
		MatHang matHang = listMatHang.get(0);
		MatHang matHangTest = new MatHang(2, "Sách","Sách Tiếng Anh",4,15000,17000,1);
		Assertions.assertEquals(matHang, matHangTest);
	
	}
	
//	// test giá trị thứ 2 của mảng kết quả tìm kiếm
//	@Test
//    public void testFindByNameExist_SecondValue() {
//		List<MatHang> listMatHang = matHangController.searchMatHang(TEXT_SEARCH);
//		MatHang matHang = listMatHang.get(1);
//		MatHang matHangTest = new MatHang(3, "Sách","Sách Lịch Sử",13,12000,13000,1);
//		Assertions.assertEquals(matHang, matHangTest);
//	}
		
	
	// test giá trị cuối trả về của mảng kết quả
	@Test
    public void testFindByNameExist_FinalValue() {

		List<MatHang> listMatHang = matHangController.searchMatHang(TEXT_SEARCH);
		MatHang matHang = listMatHang.get(4);
		MatHang matHangTest = new MatHang(13, "Sách","Sách Ngữ Văn",10,17000,17500,1);
		Assertions.assertEquals(matHang, matHangTest);
		
	}
//	// test giá trị trước kết quả cuối của mảng
//	@Test
//    public void testFindByNameExist_NearFinalValue() {
//		List<MatHang> listMatHang = matHangController.searchMatHang(TEXT_SEARCH);
//		MatHang matHang = listMatHang.get(3);
//		MatHang matHangTest = new MatHang(12, "Sách","Sách Hoá Học",10,12800,13000,1);
//		Assertions.assertEquals(matHang, matHangTest);
//	}
	
	// test giá trị ở giữa
	@Test
    public void testFindByNameExist_MidValue() {
		List<MatHang> listMatHang = matHangController.searchMatHang(TEXT_SEARCH);
		MatHang matHang = listMatHang.get(2);
		MatHang matHangTest = new MatHang(10, "Sách","Sách Địa Lý",13,13000,14000,1);
		Assertions.assertEquals(matHang, matHangTest);
	}
	
	/* Tìm mặt hàng theo id */
	// id không tồn tại
	@Test
	public void testGetById_NotExists() {
		MatHang matHang = matHangController.getMatHang(15);
		MatHang matHangTest = new MatHang();
		Assertions.assertEquals(matHang, matHangTest);
	}
	
	// id của bản ghi đầu tiên
	@Test
	public void testGetById_FirstRecord() {
		MatHang matHang = matHangController.getMatHang(1);
		MatHang matHangTest = new MatHang(1, "Đồ dùng học tập","Bút bi",100,2500,3000,1);
		Assertions.assertEquals(matHang, matHangTest);
	}
	
	// id của bản ghi giữa
	@Test
	public void testGetById_MidRecord() {
		MatHang matHang = matHangController.getMatHang(8);
		MatHang matHangTest = new MatHang(8, "Đồ dùng học tập","Bút chì",120,3500,4000,1);
		Assertions.assertEquals(matHang, matHangTest);
	}
	
	// id của bản ghi cuối
	@Test
	public void testGetById_LastRecord() {
		MatHang matHang = matHangController.getMatHang(13);
		MatHang matHangTest = new MatHang(13, "Sách","Sách Ngữ Văn",10,17000,17500,1);
		Assertions.assertEquals(matHang, matHangTest);
	}
	
	/* Xóa mặt hàng */
	// Xóa một mặt hàng với id có trong csdl
	@Test
	public void deleteSuccess() {
		matHangController.deleteProduct(1);
		MatHang matHang = matHangController.getMatHang(1);
		MatHang matHangTest = new MatHang(1, "Đồ dùng học tập","Bút bi",100,2500,3000,0);
		Assertions.assertEquals(matHang, matHangTest);
	}
	
	// Xóa mặt hàng với id không có trong csdl
	@Test
	public void deleteFail() {
		matHangController.deleteProduct(14);
		MatHang matHang = matHangController.getMatHang(14);
		MatHang matHangTest = new MatHang();
		Assertions.assertEquals(matHang, matHangTest);
	}
	
	/* Thêm mặt hàng */
	// lưu một mặt hàng đã có trong csdl với id khác
	@Test
	@Rollback(true)
	public void saveExistsRecord() {
		MatHang matHang = new MatHang("Đồ dùng học tập","Bút bi",0,2500,3000,1);
		matHang = matHangController.addMatHang(matHang);
		MatHang matHangTest = new MatHang(1, "Đồ dùng học tập","Bút bi",100,2500,3000,0);
		Assertions.assertEquals(matHang, matHangTest);
	}
	
	
	
	// Lưu mặt hàng chưa có trong csdl
	@Test
	@Rollback(true)
	public void saveNewRecord() {
		MatHang matHang = new MatHang("Đồ dùng học tập","Tẩy bút chì",0,2500,3000,1);
		matHang = matHangController.addMatHang(matHang);
		MatHang matHangTest = new MatHang(14, "Đồ dùng học tập","Tẩy bút chì",0,2500,3000,1);
		Assertions.assertEquals(matHang, matHangTest);
		
	}
	/* Sửa mặt hàng */
	@Test
	public void editFirstRecord() {
		MatHang matHang = new MatHang(1, "Đồ dùng học tập","Tẩy bút chì",100,2700,3000,1);
		matHang = matHangController.addMatHang(matHang);
		MatHang matHangTest = new MatHang(1, "Đồ dùng học tập","Tẩy bút chì",100,2700,3000,1);
		Assertions.assertEquals(matHang, matHangTest);
	}
	
	@Test
	public void editMidRecord() {
		MatHang matHang = new MatHang(8, "Sách","Sách Vật Lý",120,15200,16000,1);
		matHang = matHangController.addMatHang(matHang);
		MatHang matHangTest = new MatHang(8, "Sách","Sách Vật Lý",120,15200,16000,1);
		Assertions.assertEquals(matHang, matHangTest);
	}
	
	@Test
	public void editLastRecord() {
		MatHang matHang = new MatHang(13, "Sách","Sách Toán",10,17000,17500,1);
		matHang = matHangController.addMatHang(matHang);
		MatHang matHangTest = new MatHang(13, "Sách","Sách Toán",10,17000,17500,1);
		Assertions.assertEquals(matHang, matHangTest);
	}
	
	// Sửa mặt hàng id không tồn tại
	@Test
	public void editNotExistsRecord() {
		MatHang matHang = new MatHang(15, "Đồ dùng học tập","Tẩy bút chì",0,2500,3000,1);
		matHang = matHangController.addMatHang(matHang);
		MatHang matHangTest = new MatHang(15, "Đồ dùng học tập","Tẩy bút chì",0,2500,3000,1);
		Assertions.assertEquals(matHang, matHangTest);
	}
}
