package mainstore.api;

import java.util.List;

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
		return matHangService.getListMatHang();
	}
	
	// lấy mặt hàng theo id
	@GetMapping("/{id}")
	public MatHang getMatHang(@PathVariable("id") Integer id) {
		MatHang matHang = matHangService.getMatHang(id);
		if(matHang != null) {
			return matHang;
		}
		return new MatHang();
		
	}
	
	// tìm kiếm mặt hàng theo tên
	@GetMapping("/search/{name}")
	public List<MatHang> searchMatHang(@PathVariable("name") String name) {
		return matHangService.searchMatHang(name);
		
	}
	
	// thêm mặt hàng
	@PostMapping(consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public MatHang addMatHang(@RequestBody MatHang matHang) {
		if (matHangService.saveMatHang(matHang)) {
			return matHang;
		}
		return new MatHang();
	}
	
	// xóa mặt hàng
	@DeleteMapping("/delete/{id}")
	public void deleteProduct(@PathVariable(name = "id") int id) {
		matHangService.deleteMatHang(id);
	           
	}
	
	@PostMapping(consumes="application/json", path = "/update")
	@ResponseStatus(HttpStatus.CREATED)
	public void updateMatHang(@RequestBody HangNhap hangNhap) {
		matHangService.updateMatHang(hangNhap);
	}
	
}
