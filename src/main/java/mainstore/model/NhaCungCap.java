package mainstore.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "tblNhaCungCap")
public class NhaCungCap {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idNhaCungCap;
	
	private String tenNhaCungCap;
	private String tenChuCuaHang;
	private String diaChi;
	private String soDienThoai;
	private int active;
	
	@PrePersist
	void createActive() {
		this.active = 1;
	}
	public NhaCungCap() {}
	
	public NhaCungCap(String tenNhaCungCap, String tenChuCuaHang, 
			String diaChi, String soDienThoai, int active) {
		this.tenChuCuaHang = tenChuCuaHang;
		this.tenNhaCungCap = tenNhaCungCap;
		this.diaChi = diaChi;
		this.soDienThoai = soDienThoai;
		this.active = active;
	}
	public NhaCungCap(int idNhaCungCap, String tenNhaCungCap, String tenChuCuaHang, 
			String diaChi, String soDienThoai, int active) {
		this.idNhaCungCap = idNhaCungCap;
		this.tenChuCuaHang = tenChuCuaHang;
		this.tenNhaCungCap = tenNhaCungCap;
		this.diaChi = diaChi;
		this.soDienThoai = soDienThoai;
		this.active = active;
	}
}
