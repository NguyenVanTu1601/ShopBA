package mainstore.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mainstore.model.DonNhapHang;
import mainstore.repository.DonNhapHangRepository;

@Service
@Transactional
public class DonNhapHangService {
	
	@Autowired
	private DonNhapHangRepository dnhRepository;
	
	public DonNhapHang addDonNhapHang(DonNhapHang donNhapHang) {
		return dnhRepository.save(donNhapHang);
	}
	
	
	public DonNhapHang getDonNhapHang(int id) {
		return dnhRepository.findById(id).get();
	}
	
}
