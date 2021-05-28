package mainstore.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mainstore.model.HangNhap;
import mainstore.model.MatHang;
import mainstore.repository.MatHangRepository;

@Service
@Transactional
public class MatHangService {

	@Autowired
	private MatHangRepository matHangRepository;
	
	// lấy danh sách mặt hàng còn tồn tại
	public List<MatHang> getListMatHang() {
	    return (List<MatHang>) matHangRepository.getListMatHang();
	}
	
	// lấy mặt hàng theo id
	public MatHang getMatHang(int id) {
		try {
        	return matHangRepository.findById(id).get();
        
        }catch(Exception e) {
        	System.out.print("Error from Staff : " + e.getMessage());
        }
        return new MatHang();
	}
	
	// Lấy danh sách mặt hàng theo tên gần đúng
	public List<MatHang> searchMatHang(String name) {
	    return (List<MatHang>) matHangRepository.searchMatHang(name);
	}
	
	
	// Xóa một mặt hàng
	public void deleteMatHang(int id) {
		matHangRepository.deleteMatHang(id);
	}
	
	// lưu thông tin mặt hàng (thêm/sửa)
	public boolean saveMatHang(MatHang matHang) {
		try {
			matHangRepository.save(matHang);
			return true;
		}catch(Exception e) {
			System.out.print("Error : " + e.getMessage());
		}
		return false;
	}
	
	// cập nhật số lượng mặt hàng sau nhập hàng
	public void updateMatHang(HangNhap hangNhap) {
		matHangRepository.updateMatHangById(hangNhap.getMatHang().getIdMatHang(), hangNhap.getSoLuong());
	}
}
