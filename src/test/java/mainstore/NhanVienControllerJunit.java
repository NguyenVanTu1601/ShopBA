package mainstore;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import junit.framework.Assert;
import mainstore.api.MatHangController;
import mainstore.api.NhanVienController;
import mainstore.model.MatHang;
import mainstore.model.NhanVien;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class NhanVienControllerJunit {

	@Autowired
	NhanVienController nhanvienController;
	
	@Test
	public void testHaveAccount() {
		NhanVien nv = new NhanVien();
		nv.setTenDangNhap("tunguyen1601");
		nv.setMatKhau("123456");
		nv = nhanvienController.checkLogin(nv);
		NhanVien nhanVienTest = new NhanVien();
		
		Assert.assertEquals(nv.getCMND(), "152267908");
	}
}
