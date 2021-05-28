package mainstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mainstore.model.NhaCungCap;

@Repository
public interface NhaCungCapRepository extends CrudRepository<NhaCungCap, Integer> {
	
	// lấy danh sách tất cả nhà cung cấp
	@Transactional
	@Query(value = "Select * from tbl_nha_cung_cap ncc where ncc.active = 1 ", 
			nativeQuery = true)
	List<NhaCungCap> getListNhaCungCap();
	
	// tìm kiếm gần đúng nhà cung cấp
	@Transactional
	@Query(value = "Select * from tbl_nha_cung_cap ncc where ncc.active = 1 "
			+ "and ncc.ten_nha_cung_cap like %?1% ", nativeQuery = true)
	List<NhaCungCap> searchNhaCungCap(String name);
	
	// lấy ncc theo id và active = 1
}
