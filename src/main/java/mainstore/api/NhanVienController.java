package mainstore.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import mainstore.model.NhanVien;
import mainstore.service.NhanVienService;

@RestController
@RequestMapping(path = "/staffs", produces = "application/json")
@CrossOrigin(origins = "*")
public class NhanVienController {
	
	@Autowired
	private NhanVienService nhanVienService;
	
	// checkLogin
	@PostMapping(consumes="application/json", path = "/login")
	@ResponseStatus(HttpStatus.CREATED)
	public NhanVien checkLogin(@RequestBody NhanVien nv) {
		
		// khi thiếu thông tin đăng nhập
		if(nv.getTenDangNhap() == null) {
			return new NhanVien();
		}
		if(nv.getMatKhau() == null) {
			return new NhanVien();
		}
		
		// trường hợp sai thông tin, ko trả về kq
		nv = nhanVienService.checkLogin(nv);
		if(nv == null) {
			return new NhanVien();
		}
		
		return nv;
	}
}
