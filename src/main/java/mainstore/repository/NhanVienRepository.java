package mainstore.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import mainstore.model.NhanVien;

@Repository
public interface NhanVienRepository extends CrudRepository<NhanVien, String>{
	
	// check login
	@Transactional
	@Query(value="Select * from tbl_nhan_vien where ten_dang_nhap = ?1 "
			+ "and mat_khau = ?2 and active = 1", nativeQuery=true) 
	NhanVien findByUsernameAndPassword(String username, String password);
}
