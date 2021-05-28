package mainstore;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import mainstore.api.NhaCungCapController;
import mainstore.api.NhanVienController;
import mainstore.model.NhaCungCap;
import mainstore.model.NhanVien;
import mainstore.repository.NhanVienRepository;
import mainstore.service.NhaCungCapService;
import mainstore.service.NhanVienService;

@SpringBootTest
class StoreManagerBaApplicationTests {
	@Test
	public void testCheckLoginSuccessNV() {
		NhanVienService instance = new NhanVienService();
		NhanVien nv = new NhanVien("ductruong1706","123456");
		
		NhanVien result = instance.checkLogin(nv);
		boolean actual = true;
		
		assertEquals(result, actual);
	}
	
	@Test
	public void testCheckLoginEmptytenDangnhap() {
		NhanVienService instance = new NhanVienService();
		NhanVien nv = new NhanVien("","123456");
		
		NhanVien result = instance.checkLogin(nv);
		boolean actual = true;
		
		assertEquals(result, actual);
	}
	@Test
	public void testCheckLoginEmptymatKhau() {
		NhanVienService instance = new NhanVienService();
		NhanVien nv = new NhanVien("ductruong1706","");
		
		NhanVien result = instance.checkLogin(nv);
		boolean actual = true;
		
		assertEquals(result, actual);
	}
	@Test
	public void testCheckLoginWrongtenDangnhap() {
		NhanVienService instance = new NhanVienService();
		NhanVien nv = new NhanVien("abcxyz","");
		
		NhanVien result = instance.checkLogin(nv);
		boolean actual = true;
		
		assertEquals(result, actual);
	}
	@Test
	public void testCheckLoginWrongmatKhau() {
		NhanVienService instance = new NhanVienService();
		NhanVien nv = new NhanVien("ductruong1706","123456");
		
		NhanVien result = instance.checkLogin(nv);
		boolean actual = true;
		
		assertEquals(result, actual);
	}
	
	@Test
	public void testCheckLoginWrongtenDangnhapAndmatKhau() {
		NhanVienService instance = new NhanVienService();
		NhanVien nv = new NhanVien("abcxyz","123456789");
		
		NhanVien result = instance.checkLogin(nv);
		boolean actual = true;
		
		assertEquals(result, actual);
	}
	
	
	@Test
	void contextLoads() {
	}
	
	
	

}
