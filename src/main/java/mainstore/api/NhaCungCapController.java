package mainstore.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

import mainstore.model.MatHang;
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
		
		Optional<NhaCungCap> option = nccService.getNhaCungCap(id);
		NhaCungCap nhaCungCap = option.orElse(null);
		if (nhaCungCap != null) {
			return nhaCungCap;
		}
		return null;
			
	}
		
		
	// tìm kiếm mặt hàng theo tên
	@GetMapping("/search/{name}")
	public List<NhaCungCap> searchNhaCungCap(@PathVariable("name") String name) {
		if (name == null) {
			return null;
		}
		List<NhaCungCap> listNhaCungCaps = nccService.searchNhaCungCap(name);
		if (!listNhaCungCaps.isEmpty()) {
			return listNhaCungCaps;
		}
		return new ArrayList<NhaCungCap>();	
		
		
	}
		
	// thêm nhà cung cấp
	@PostMapping(consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public NhaCungCap addNhaCungCap(@RequestBody NhaCungCap ncc) {
		ncc = nccService.saveNhaCungCap(ncc);
		if (ncc != null) {
			return ncc;
		}
		return new NhaCungCap();	
	}
}
