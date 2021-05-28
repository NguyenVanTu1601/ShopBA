package mainstore.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mainstore.model.DonNhapHang;

@Repository
public interface DonNhapHangRepository extends CrudRepository<DonNhapHang, Integer>{
	
}
