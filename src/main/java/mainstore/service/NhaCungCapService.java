package mainstore.service;

import java.util.List;

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
	public NhaCungCap getNhaCungCap(int id) {
		try {
        	return nccRepository.findById(id).get();
        
        }catch(Exception e) {
        	System.out.print("Error from NCC : " + e.getMessage());
        }
        return new NhaCungCap();
	}
	
	
	// tìm kiếm ncc
	public List<NhaCungCap> searchNhaCungCap(String name) {
	    return (List<NhaCungCap>) nccRepository.searchNhaCungCap(name);
	}
	
	// thêm mới ncc
	public boolean saveNhaCungCap(NhaCungCap ncc) {
		try {
			nccRepository.save(ncc);
			return true;
		}catch(Exception e) {
			System.out.print("Error : " + e.getMessage());
		}
		return false;
	}
}
