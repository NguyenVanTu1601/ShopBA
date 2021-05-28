package mainstore.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mainstore.model.NhanVien;
import mainstore.repository.NhanVienRepository;

@Service
@Transactional
public class NhanVienService{

	@Autowired
    private NhanVienRepository nhanVienRepository;
	
	public NhanVien checkLogin(NhanVien nhanVien) {
		nhanVien = nhanVienRepository.findByUsernameAndPassword(nhanVien.getTenDangNhap(), 
				nhanVien.getMatKhau());
		if(nhanVien != null) {
			return nhanVien;
		}
		return new NhanVien();
	}
	
}
