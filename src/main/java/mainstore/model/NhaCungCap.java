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
	public int getIdNhaCungCap() {
		return idNhaCungCap;
	}
	public void setIdNhaCungCap(int idNhaCungCap) {
		this.idNhaCungCap = idNhaCungCap;
	}
	public String getTenNhaCungCap() {
		return tenNhaCungCap;
	}
	public void setTenNhaCungCap(String tenNhaCungCap) {
		this.tenNhaCungCap = tenNhaCungCap;
	}
	public String getTenChuCuaHang() {
		return tenChuCuaHang;
	}
	public void setTenChuCuaHang(String tenChuCuaHang) {
		this.tenChuCuaHang = tenChuCuaHang;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String getSoDienThoai() {
		return soDienThoai;
	}
	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	
	
}
