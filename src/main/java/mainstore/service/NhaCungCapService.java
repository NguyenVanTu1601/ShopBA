package mainstore.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mainstore.model.NhaCungCap;
import mainstore.repository.NhaCungCapRepository;

@Service
@Transactional
public class NhaCungCapService {
	
	@Autowired
	private NhaCungCapRepository nccRepository;
	
	
	// lấy danh sách các nha cung cấp
	public List<NhaCungCap> getListNhaCungCap() {
	    return (List<NhaCungCap>) nccRepository.getListNhaCungCap();
	}
	
	// lấy 1 nhà cung cấp theo id
	public Optional<NhaCungCap> getNhaCungCap(int id) {
		
        return nccRepository.findById(id);  
     
	}
	
	
	// tìm kiếm ncc
	public List<NhaCungCap> searchNhaCungCap(String name) {
	    return (List<NhaCungCap>) nccRepository.searchNhaCungCap(name);
	}
	
	// thêm mới ncc
	public NhaCungCap saveNhaCungCap(NhaCungCap ncc) {
		ncc = nccRepository.save(ncc);
		return ncc;
	}
}
