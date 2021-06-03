package mainstore;

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

import mainstore.api.NhaCungCapController;
import mainstore.model.MatHang;
import mainstore.model.NhaCungCap;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback(true)
public class NhaCungCapControllerJunitTest {
	
	@Autowired
	
	NhaCungCapController nhaCungCapController;

	/* GetAll */
	// Trường hợp ko có nhà cung cấp nào trong csdl
	@Test
	public void testGetAllNhaCungCap_Empty() {

		// init - delete all nhà cung cấp
		int sizeDB = nhaCungCapController.getAllNhaCungCap().size();
		for (int i = 1; i <= sizeDB; i++) {
			nhaCungCapController.deleteNhaCungCap(i);
		}

		// test
		List<NhaCungCap> listNhaCungCap = nhaCungCapController.getAllNhaCungCap();
		Assertions.assertEquals(listNhaCungCap, new ArrayList<NhaCungCap>());
	}
	
	// Trường hợp có nhà cung cấp trong csdl
		@Test
		public void testGetAllNhaCungCap_Exists() {
			List<NhaCungCap> listNhaCungCapTest = nhaCungCapController.getAllNhaCungCap();
			Assertions.assertEquals(2, listNhaCungCapTest.size());
		}
	// Get nhà cung cấp by id
		// Id nhà cung cấp không có trong csdl
		@Test
		public void testGetById_NotExists() {
			NhaCungCap ncc = nhaCungCapController.getNhaCungCap(50);
			Assertions.assertEquals(ncc, null);
		}
		
		// id = null
		@Test
		public void testGetById_Null() {
			NhaCungCap ncc = nhaCungCapController.getNhaCungCap(null);
			Assertions.assertEquals(ncc, null);
		}
		// id có trong csdl
		@Test
		public void testGetById_Exists() {
			NhaCungCap ncc = nhaCungCapController.getNhaCungCap(1);
			NhaCungCap nccTest = new NhaCungCap(1, "Vinamilk", "Vinmart","Hà Nội", "0383097963", 1);
			Assertions.assertEquals(ncc.getIdNhaCungCap(), nccTest.getIdNhaCungCap());
		}
	
		// get nhà cung cấp by name
		// tên tồn tại
		@Test
		public void testFindByName_Exist() {
			List<NhaCungCap> listNhaCungCap = nhaCungCapController.searchNhaCungCap("Coco");
			NhaCungCap ncc = listNhaCungCap.get(0);
			NhaCungCap nccTest = new NhaCungCap(2, "Coco", "Circle", "Nam Định", "0373900635", 1);
			Assertions.assertEquals(ncc, nccTest);

		}

		// tên = null
		@Test
		public void testFindByName_Null() {
			List<NhaCungCap> listNhaCungCap  = nhaCungCapController.searchNhaCungCap(null);
			Assertions.assertEquals(listNhaCungCap , null);

		}

		// tên để trống
		@Test
		public void testFindByName_Empty() {
			List<NhaCungCap> listNhaCungCap  = nhaCungCapController.searchNhaCungCap("");
			List<NhaCungCap> listNhaCungCaps = nhaCungCapController.getAllNhaCungCap();
			Assertions.assertEquals(listNhaCungCap .size(), listNhaCungCaps.size());

		}

		// tên ko tổn tại trong csdl
		@Test
		public void testFindByName_NotExist() {
			List<NhaCungCap> listNhaCungCap  = nhaCungCapController.searchNhaCungCap("anh");
			Assertions.assertEquals(listNhaCungCap , new ArrayList<NhaCungCap>());

		}

		
	/* thêm nhà cung cấp */
	// trường hợp nhà cung cấp null
	@Test
	public void addNhaCungCap_NULL() {
		NhaCungCap ncc = null;
		ncc = nhaCungCapController.addNhaCungCap(ncc);
		Assertions.assertEquals(ncc, null);
	}
	// trường hợp nhà cung cấp chưa có trong csdl
			@Test
			public void addNhaCungCapSuccess() {

				NhaCungCap ncc = new NhaCungCap("Thăng Long", "Vinmart", "Nam Định","0373900635", 1);
				ncc = nhaCungCapController.addNhaCungCap(ncc);
				NhaCungCap nhaCungCapTest = new NhaCungCap(28,"Thăng Long", "Vinmart", "Nam Định","0373900635", 1);
				Assertions.assertEquals(ncc.getIdNhaCungCap(), nhaCungCapTest.getIdNhaCungCap());
				Assertions.assertEquals(ncc.getTenNhaCungCap(), nhaCungCapTest.getTenNhaCungCap());
			Assertions.assertEquals(ncc.getTenChuCuaHang(), nhaCungCapTest.getTenChuCuaHang());
				Assertions.assertEquals(ncc.getDiaChi(), nhaCungCapTest.getDiaChi());
				Assertions.assertEquals(ncc.getSoDienThoai(), nhaCungCapTest.getSoDienThoai());
				Assertions.assertEquals(ncc.getActive(), nhaCungCapTest.getActive());
			}
	
//			// trường hợp thêm nha cung cấp đã có tên trong csdl
			@Test
			public void addNhaCungCap_Exsits() {
				NhaCungCap ncc = new NhaCungCap("Vinamilk", "Vinmart", "Hà Nội", "0383097963", 1);
				ncc = nhaCungCapController.addNhaCungCap(ncc);
				Assertions.assertEquals(ncc, null);
			}
			
}
