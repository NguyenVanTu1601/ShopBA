package mainstore.api;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import mainstore.model.DonNhapHang;
import mainstore.model.HangNhap;
import mainstore.model.MatHang;
import mainstore.model.NhaCungCap;
import mainstore.model.NhanVien;

class DonNhapHangControllerTest {
	
	@Autowired
	DonNhapHangController dnhController;

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
	void testGetDNHbyIDSuccess() {
		int  keyID = 1;
		DonNhapHang dnh = dnhController.getDonNhapHang(keyID);
		assertEquals(keyID, dnh.getIdDonNhap());
	}
	@Test
	void testGetDNHbyIDFailed() {
		int  keyID = -11;
		DonNhapHang dnh = dnhController.getDonNhapHang(keyID);
		assertEquals(keyID, dnh.getIdDonNhap());
	}
	@Test
	void testAddDonNhapHangSuccess() {
		DonNhapHang dnh = new DonNhapHang();
		dnh.setIdDonNhap(4);
		NhaCungCap ncp = new NhaCungCap(7,"BOOK STORE","Nguyen Van A",
				"HN","0123456789",1);
		dnh.setNhaCungCap(ncp);
		ArrayList<HangNhap> list = new ArrayList<>();
		MatHang mh = new MatHang(1,"Sách","Dến mèn",2,20000,25000,1);
		HangNhap hn = new HangNhap(2,2,25000,mh);
		list.add(hn);
		dnh.setListHangNhap(list);
		NhanVien nv = new NhanVien("NV01","tuan123","tuan123","152267908","DoVanTuan",
				"10/11/1999","HN","Nhân viên quản lý",1);
		dnh.setNhanVien(nv);
		dnh.setTongTien(50000);
		assertEquals(dnh.getIdDonNhap(), dnhController.addDonNhapHang(dnh).getIdDonNhap());
	}
	@Test
	void testAddDonNhapHangFailed() {
		DonNhapHang dnh = new DonNhapHang();
		assertNull(dnh);
	}
	

}
