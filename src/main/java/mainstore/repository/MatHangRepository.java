package mainstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mainstore.model.MatHang;

@Repository
public interface MatHangRepository extends CrudRepository<MatHang, Integer>{

	// Xóa một mặt hàng = cách cập nhật active = 0
	@Modifying // ko có dòng này thì k delete được!!!
	@Transactional
	@Query(value = "update tbl_mat_hang mh set mh.active = 0 where mh.id_mat_hang = ?1", 
			nativeQuery = true)
	void deleteMatHang(int idMatHang);
	
	// lấy danh sách mặt hàng có active = 1
	@Transactional
	@Query(value = "Select * from tbl_mat_hang mh where mh.active = 1 ", 
			nativeQuery = true)
	List<MatHang> getListMatHang();
	
	
	// tìm kiếm mặt hàng theo tên, và có active = 1
	@Transactional
	@Query(value = "Select * from tbl_mat_hang mh where mh.active = 1 and mh.ten_mat_hang like %?1% ", 
			nativeQuery = true)
	List<MatHang> searchMatHang(String name);
	
	// tìm mặt hàng theo id và có active = 1
	@Transactional
	@Query(value = "Select * from tbl_mat_hang mh where mh.active = 1 and mh.id_mat_hang = ?1 ", 
			nativeQuery = true)
	MatHang searchMatHangById(int id);
	
	// cập nhật số lượng hàng khi nhập hàng
	@Modifying
	@Transactional
	@Query(value = "Update tbl_mat_hang mh set mh.so_luong = mh.so_luong + ?2 "
			+ "where mh.active = 1 and mh.id_mat_hang = ?1 ", 
			nativeQuery = true)
	void updateMatHangById(int id, int number);
}
