package mainstore;

import static org.junit.jupiter.api.Assertions.*;

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
import mainstore.api.NhaCungCapController;
import mainstore.model.MatHang;
import mainstore.model.NhaCungCap;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class SaveMatHangJunit {

	@Autowired
	NhaCungCapController nccController;
	
//	@Test
//	public void deleteSuccess() {
//		matHangController.deleteProduct(24);
//		MatHang matHang = matHangController.getMatHang(14);
//		MatHang matHangTest = new MatHang(24, "Đồ dùng học tập","Bút bi",100,2500,3000,0);
//		Assertions.assertEquals(matHang, matHangTest);
//	}
	
	@Test
	public void saveSuccess() {
		NhaCungCap ncc = new NhaCungCap("Đồ Gia Dụng Mỹ Kim", "Trần Thanh Hải",
				"155, Trần Phú, Hà Đông" ,"021154254",1);
		ncc = nccController.addNhaCungCap(ncc);
		NhaCungCap test = new NhaCungCap(6,"Đồ Gia Dụng Mỹ Kim", "Trần Thanh Hải",
				"155, Trần Phú, Hà Đông" ,"021154254",1);
		Assertions.assertEquals(ncc, test);
	}
	

}
