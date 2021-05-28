package mainstore.api;

import java.util.List;

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

import mainstore.model.NhaCungCap;
import mainstore.service.NhaCungCapService;

@RestController
@RequestMapping(path = "/suppliers", produces = "application/json")
@CrossOrigin(origins = "*")
public class NhaCungCapController {
	
	@Autowired
	private NhaCungCapService nccService;
	
	// lấy toàn bộ ncc
	@GetMapping
	public List<NhaCungCap> getAllNhaCungCap(){
		return nccService.getListNhaCungCap();
	}
		
	// lấy ncc theo id
	@GetMapping("/{id}")
	public NhaCungCap getNhaCungCap(@PathVariable("id") Integer id) {
		NhaCungCap ncc = nccService.getNhaCungCap(id);
		if(ncc != null) {
			return ncc;
		}
		return new NhaCungCap();
			
	}
		
		
	// tìm kiếm mặt hàng theo tên
	@GetMapping("/search/{name}")
	public List<NhaCungCap> searchNhaCungCap(@PathVariable("name") String name) {
		return nccService.searchNhaCungCap(name);
			
	}
		
	// thêm nhà cung cấp
	@PostMapping(consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public NhaCungCap addNhaCungCap(@RequestBody NhaCungCap ncc) {
		if (nccService.saveNhaCungCap(ncc)) {
			return ncc;
		}
		return new NhaCungCap();	
	}
}
