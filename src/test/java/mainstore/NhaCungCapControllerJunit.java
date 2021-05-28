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
import org.springframework.test.context.junit4.SpringRunner;

import mainstore.api.NhaCungCapController;
import mainstore.model.NhaCungCap;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class NhaCungCapControllerJunit {

		@Autowired
		NhaCungCapController nhaCungCapController;
	
		// Test số lượng nhà cung cấp nhận được khi gọi API
		@Test
		public void testNumberListGetAll() {
			List<NhaCungCap> listNhaCungCap = nhaCungCapController.getAllNhaCungCap();
			Assertions.assertEquals(listNhaCungCap.size(), 6);
		}
		
		
		// Test giá trị đầu tiên của list nhận được
		@Test
		public void testFirstValueGetAll() {
			List<NhaCungCap> listNhaCungCap = nhaCungCapController.getAllNhaCungCap();
			NhaCungCap nhaCungCap = listNhaCungCap.get(0);
			NhaCungCap nhaCungCapTest = new NhaCungCap(1,"Nhà Sách Thuận An", "Nguyễn Văn Thuận",
						"Số 26 Đường Trần Phú, Hà Đông", "0365882920",1);
			
			Assertions.assertEquals(nhaCungCap, nhaCungCapTest);
			
		}
		
		
		// test giá trị cuối cùng của list
		@Test
		public void testFinalValueGetAll() {
			List<NhaCungCap> listNhaCungCap = nhaCungCapController.getAllNhaCungCap();
			NhaCungCap nhaCungCap = listNhaCungCap.get(5);
			NhaCungCap nhaCungCapTest = new NhaCungCap(6,"Đồ Gia Dụng Mỹ Kim", "Trần Thanh Hải",
						"155, Trần Phú, Hà Đông", "021154254",1);
		
			Assertions.assertEquals(nhaCungCap, nhaCungCapTest);
		}
		
		
		
		// test giá trị ở giữa của list
		@Test
		public void testMidValueGetAll() {
			List<NhaCungCap> listNhaCungCap = nhaCungCapController.getAllNhaCungCap();
			NhaCungCap nhaCungCap = listNhaCungCap.get(2);
			NhaCungCap nhaCungCapTest = new NhaCungCap(3,"Đồ Gia Dụng Ánh Sáng", "Nguyễn Quang Sáng",
						"Ngõ 255, Nguyễn Văn Trỗi, Hà Đông, Hà Nội", "035666589",1);
			
			Assertions.assertEquals(nhaCungCap, nhaCungCapTest);
		}

		
		/* Test function tìm kiếm nhà cung cấp theo tên */
		// test trường hợp không tồn tại bản ghi có tên thỏa mãn 
		@Test
		public void testFindByName_NotExist() {
			
			List<NhaCungCap> listNhaCungCap = nhaCungCapController.searchNhaCungCap("Báo");
			
			Assertions.assertEquals(listNhaCungCap, new ArrayList<NhaCungCap>());
		}
		
		// Test trường hợp tên tên nhà cung cấp trống
		@Test
		public void testFindByName_Null() {
			
			List<NhaCungCap> listNhaCungCap = nhaCungCapController.searchNhaCungCap("");
			
			Assertions.assertEquals(listNhaCungCap, new ArrayList<NhaCungCap>());
		}
		
		// test giá trị đầu trả về của mảng kết quả tìm kiếm
		@Test
	    public void testFindByNameExist_FirstValue() {
			List<NhaCungCap> listNhaCungCap = nhaCungCapController.searchNhaCungCap("Sách");
			NhaCungCap nhaCungCap = listNhaCungCap.get(0);
			NhaCungCap nhaCungCapTest = new NhaCungCap(1,"Nhà Sách Thuận An", "Nguyễn Văn Thuận",
					"Số 26 Đường Trần Phú, Hà Đông", "0365882920",1);
			
			Assertions.assertEquals(nhaCungCap, nhaCungCapTest);
		
		}

		
		// test giá trị cuối trả về của mảng kết quả
		@Test
	    public void testFindByNameExist_FinalValue() {

			List<NhaCungCap> listNhaCungCap = nhaCungCapController.searchNhaCungCap("Sách");
			NhaCungCap nhaCungCap = listNhaCungCap.get(listNhaCungCap.size() - 1);
			NhaCungCap nhaCungCapTest = new NhaCungCap(4,"Nhà Sách Phương Nam", "Đinh Quang Hùng",
					"Phố 19/12, P. Trần Hưng Đạo, Q. Hoàn Kiếm, TP. Hà Nội", "021445578",1);
			
			Assertions.assertEquals(nhaCungCap, nhaCungCapTest);
			
		}
		
		/* Lấy nhà cung cấp theo id */
		// id không tồn tại
		@Test
		public void testGetById_NotExists() {
			NhaCungCap nhaCungCap = nhaCungCapController.getNhaCungCap(7);
			NhaCungCap nhaCungCapTest = new NhaCungCap();
			Assertions.assertEquals(nhaCungCap, nhaCungCapTest);
		}
		
		
		// id của bản ghi đầu tiên
		@Test
		public void testGetById_FirstRecord() {
			NhaCungCap nhaCungCap = nhaCungCapController.getNhaCungCap(1);
			NhaCungCap nhaCungCapTest = new NhaCungCap(1,"Nhà Sách Thuận An", "Nguyễn Văn Thuận",
					"Số 26 Đường Trần Phú, Hà Đông", "0365882920",1);
			Assertions.assertEquals(nhaCungCap, nhaCungCapTest);
		}
		
		// id của bản ghi giữa
		@Test
		public void testGetById_MidRecord() {
			NhaCungCap nhaCungCap = nhaCungCapController.getNhaCungCap(3);
			NhaCungCap nhaCungCapTest = new NhaCungCap(3,"Đồ Gia Dụng Ánh Sáng", "Nguyễn Quang Sáng",
						"Ngõ 255, Nguyễn Văn Trỗi, Hà Đông, Hà Nội", "035666589",1);
			
			Assertions.assertEquals(nhaCungCap, nhaCungCapTest);
		}
		
		// id của bản ghi cuối
		@Test
		public void testGetById_LastRecord() {
			NhaCungCap nhaCungCap = nhaCungCapController.getNhaCungCap(6);
			NhaCungCap nhaCungCapTest = new NhaCungCap(6,"Đồ Gia Dụng Mỹ Kim", "Trần Thanh Hải",
						"155, Trần Phú, Hà Đông", "021154254",1);
		
			Assertions.assertEquals(nhaCungCap, nhaCungCapTest);
		}
}
