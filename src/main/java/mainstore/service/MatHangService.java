package mainstore.service;

import java.util.List;
import java.util.Optional;

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
	public Optional<MatHang> getMatHang(int id) {
		return matHangRepository.findById(id);
		
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
	public MatHang saveMatHang(MatHang matHang) {
		matHang = matHangRepository.save(matHang);
		return matHang;
	}
	
	// cập nhật số lượng mặt hàng sau nhập hàng
	public void updateMatHang(HangNhap hangNhap) {
		matHangRepository.updateMatHangById(hangNhap.getMatHang().getIdMatHang(), hangNhap.getSoLuong());
	}
}
