package mainstore.api;

import static org.junit.jupiter.api.Assertions.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import mainstore.model.DonNhapHang;
import mainstore.model.HangNhap;
import mainstore.model.MatHang;
import mainstore.model.NhaCungCap;
import mainstore.model.NhanVien;
import mainstore.repository.DonNhapHangRepository;
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class DonNhapHangControllerTest {

	@Autowired
	private DonNhapHangRepository dnhHangRepository;
	
	@Autowired
	TestEntityManager entityManager;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Test
	@Rollback(true)
	void testAddDonNhapHang_Null() {
		DonNhapHang dnh = null;
		DonNhapHang dnh2 = dnhHangRepository.save(dnh);
		assertEquals(null,dnh2);
	}
	
	@Test
	@Rollback(true)
	void testAddDonNhapHang_ListHangNhap_Empty() {
		DonNhapHang dnh = new DonNhapHang();
		dnh.setIdDonNhap(16);
		
		
		NhaCungCap ncp = new NhaCungCap(1,"BOOK STORE","Nguyen Van A",
				"HN","0123456789",1);
		
		dnh.setNhaCungCap(ncp);
		
		NhanVien nv = new NhanVien("NV01","tuan123","tuan123","152267908","DoVanTuan",
				"10/11/1999","HN","Nhân viên quản lý",1);
		dnh.setNhanVien(nv);
		
		dnh.setListHangNhap(new ArrayList<>());
		
		DonNhapHang dnh2 = dnhHangRepository.save(dnh);
		Assertions.assertEquals(dnh2.getListHangNhap().size(),0);
	}

	@Test
	@Rollback(true)
	void testAddDonNhapHangSuccess() {
		DonNhapHang dnh = new DonNhapHang();
		dnh.setIdDonNhap(16);
		
		//dnh.setTongTien(500);
		
		NhaCungCap ncp = new NhaCungCap(1,"BOOK STORE","Nguyen Van A",
				"HN","0123456789",1);
		dnh.setNhaCungCap(ncp);
		
		NhanVien nv = new NhanVien("NV01","tuan123","tuan123","152267908","DoVanTuan",
				"10/11/1999","HN","Nhân viên quản lý",1);
		dnh.setNhanVien(nv);
		
		ArrayList<HangNhap> list = new ArrayList<HangNhap>();
		MatHang mh = new MatHang(1,"Sách","123",1,12,13,1);
		HangNhap hNhap = new HangNhap(1, 1, 12, mh);
		list.add(hNhap);
		dnh.setListHangNhap(list);
		
		DonNhapHang dnh2 = dnhHangRepository.save(dnh);
		Assertions.assertTrue(dnh2.getIdDonNhap() > 0);
	}
	
	@Test
	void testGetDNHbyIDSuccess() {
		int  keyID = 2;
		DonNhapHang dnh = entityManager.find(DonNhapHang.class, keyID);
		assertEquals(keyID, dnh.getIdDonNhap());
	}
	@Test
	void testGetDNHbyIDFailed() {
		int  keyID = -11;
		DonNhapHang dnh = entityManager.find(DonNhapHang.class, keyID);
		assertEquals(null, dnh);
	}
	
	

}
