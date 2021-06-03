package mainstore;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import mainstore.api.NhanVienController;
import mainstore.model.NhanVien;
import mainstore.service.NhanVienService;
 
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback(true)
public class NhanVienControllerJunitTest {
	
	@Autowired
	NhanVienController nhanVienController;
	NhanVienService nhanvienService;
	
	@Test
	public void testcheckLoginSuccess() {
		NhanVien nv = new NhanVien("truong", "123456");
		
		nv = nhanVienController.checkLogin(nv);
		Assertions.assertEquals(nv, nv);
	}
	
	@Test
	public void testcheckLoginEmptyTenDangNhap() {
		NhanVien nv = new NhanVien(null, "123456");
		
		nv = nhanVienController.checkLogin(nv);
		Assertions.assertEquals(nv, null);
	}
	
	@Test
	public void testcheckLoginsEmptyMatKhau() {
		NhanVien nv = new NhanVien("truong", null);
		
		nv = nhanVienController.checkLogin(nv);
		Assertions.assertEquals(nv, null);
	}
	
	@Test
	public void testcheckLoginsEmptyTenDangNhapAndMatKhau() {
		NhanVien nv = new NhanVien(null, null);
		
		nv = nhanVienController.checkLogin(nv);
		Assertions.assertEquals(nv, null);
	}
	
	@Test
	public void testcheckLoginsWrongInfo() {
		NhanVien nv = null;
		
		nv = nhanvienService.checkLogin(nv);
		Assertions.assertEquals(nv, null);
	}
	

}
