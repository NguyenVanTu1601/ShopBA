package mainstore.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import mainstore.model.DonNhapHang;
import mainstore.service.DonNhapHangService;

@RestController
@RequestMapping(path = "/orders", produces = "application/json")
@CrossOrigin(origins = "*")
public class DonNhapHangController {
	
	@Autowired
	private DonNhapHangService dnhService;
	
	// lấy đơn hàng theo id
	@GetMapping("/{id}")
	public DonNhapHang getDonNhapHang(@PathVariable("id") Integer id) {
		DonNhapHang donNhapHang = dnhService.getDonNhapHang(id);
		if(donNhapHang != null) {
			return donNhapHang;
		}
		return new DonNhapHang();
			
	}
	
	// thêm đơn hàng
	@PostMapping(consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public DonNhapHang addDonNhapHang(@RequestBody DonNhapHang donNhapHang) {
		//không có đơn hàng
		if(donNhapHang == null) {
			return null;
		}
		
		// Đơn hàng trống
		if(donNhapHang.getListHangNhap().size() == 0) {
			return new DonNhapHang();
		}
		
		// tồn tại đơn hàng
		donNhapHang = dnhService.addDonNhapHang(donNhapHang);
		if(donNhapHang != null) {
			return donNhapHang;
		}
		return new DonNhapHang();
			
	}
	
}
