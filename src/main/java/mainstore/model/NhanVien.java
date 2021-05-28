package mainstore.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "tblNhanVien")

public class NhanVien implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	private String idNhanVien;
	private String tenDangNhap;
	private String matKhau;
	private String CMND;
	private String hoTen;
	private String ngaySinh;
	private String diaChi;
	private String viTriCongViec;
	private int active;
	
	@PrePersist
	void createActive() {
		this.active = 1;
	}
	public NhanVien() {}
}