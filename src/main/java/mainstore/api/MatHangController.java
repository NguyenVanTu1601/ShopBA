package mainstore.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import mainstore.model.HangNhap;
import mainstore.model.MatHang;
import mainstore.service.MatHangService;

@RestController
@RequestMapping(path = "/goods", produces = "application/json")
@CrossOrigin(origins = "*")
public class MatHangController {

	@Autowired
	private MatHangService matHangService;
	
	// lấy toàn bộ mặt hàng
	@GetMapping
	public List<MatHang> getAllMatHang(){
		List<MatHang> listMH =  matHangService.getListMatHang();
		if(!listMH.isEmpty()) {
			return listMH;
		}
		return new ArrayList<MatHang>();
	}
	
	// lấy mặt hàng theo id
	@GetMapping("/{id}")
	public MatHang getMatHang(@PathVariable("id") Integer id) {
		if(id == null) {
			return null;
		}
		Optional<MatHang> option = matHangService.getMatHang(id);
		MatHang matHang = option.orElse(null);
		if (matHang != null) {
			return matHang;
		}
		return null;
		
		
	}
	
	// tìm kiếm mặt hàng theo tên
	@GetMapping("/search/{name}")
	public List<MatHang> searchMatHang(@PathVariable("name") String name) {
		if(name == null) {
			return null;
		}
		if (name.equals("")) {
			return matHangService.getListMatHang();
		}
		
		List<MatHang> listMH = matHangService.searchMatHang(name);
		if(!listMH.isEmpty()) {
			return listMH;
		}
		return new ArrayList<MatHang>();
		
	}
	
	// thêm mặt hàng
	@PostMapping(consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public MatHang addMatHang(@RequestBody MatHang matHang) {
		if (matHang == null) {
			return null;
		}
		
		// Kiểm tra mặt hàng có tên bị trùng hay không
		List<MatHang> list = matHangService.searchMatHang(matHang.getTenMatHang());
		for (MatHang mh : list) {
			if (mh.getTenMatHang().equals(matHang.getTenMatHang())) { // nếu trùng return null
				return null;
			}
		}
		
		matHang = matHangService.saveMatHang(matHang);
		if(matHang != null) {
			return matHang;
		}
		return null;
	}
	
	// sửa mặt hàng
	@PostMapping(consumes="application/json", path = "/edit")
	@ResponseStatus(HttpStatus.CREATED)
	public MatHang editMatHang(@RequestBody MatHang matHang) {
		if (matHang == null) {
			return null;
		}
		
		matHang = matHangService.saveMatHang(matHang);
		if(matHang != null) {
			return matHang;
		}
		return null;
	}
	
	// xóa mặt hàng
	@DeleteMapping("/delete/{id}")
	public void deleteMatHang(@PathVariable(name = "id") int id) {
		matHangService.deleteMatHang(id);
	           
	}
	
	//cập nhật lại số lượng khi nhập hàng
	@PostMapping(consumes="application/json", path = "/update")
	@ResponseStatus(HttpStatus.CREATED)
	public void updateMatHang(@RequestBody HangNhap hangNhap) {
		matHangService.updateMatHang(hangNhap);
	}
	
}
