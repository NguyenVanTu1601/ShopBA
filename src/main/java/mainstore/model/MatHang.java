package mainstore.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "tblMatHang")
public class MatHang {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idMatHang;
	private String loaiMatHang;
	private String tenMatHang;
	private int soLuong;
	private int giaNhap;
	private int giaBan;
	private int active;
	
	
	public MatHang() {
		
	}


	public MatHang(int idMatHang, String loaiMatHang, String tenMatHang, 
			int soLuong, int giaNhap, int giaBan, int active) {
		this.idMatHang = idMatHang;
		this.loaiMatHang = loaiMatHang;
		this.tenMatHang = tenMatHang;
		this.soLuong = soLuong;
		this.giaBan = giaBan;
		this.giaNhap = giaNhap;
		this.active = active;
	}

	public MatHang(String loaiMatHang, String tenMatHang, 
			int soLuong, int giaNhap, int giaBan, int active) {
		this.loaiMatHang = loaiMatHang;
		this.tenMatHang = tenMatHang;
		this.soLuong = soLuong;
		this.giaBan = giaBan;
		this.giaNhap = giaNhap;
		this.active = active;
	}


	public int getIdMatHang() {
		return idMatHang;
	}


	public void setIdMatHang(int idMatHang) {
		this.idMatHang = idMatHang;
	}


	public String getLoaiMatHang() {
		return loaiMatHang;
	}


	public void setLoaiMatHang(String loaiMatHang) {
		this.loaiMatHang = loaiMatHang;
	}


	public String getTenMatHang() {
		return tenMatHang;
	}


	public void setTenMatHang(String tenMatHang) {
		this.tenMatHang = tenMatHang;
	}


	public int getSoLuong() {
		return soLuong;
	}


	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}


	public int getGiaNhap() {
		return giaNhap;
	}


	public void setGiaNhap(int giaNhap) {
		this.giaNhap = giaNhap;
	}


	public int getGiaBan() {
		return giaBan;
	}


	public void setGiaBan(int giaBan) {
		this.giaBan = giaBan;
	}


	public int getActive() {
		return active;
	}


	public void setActive(int active) {
		this.active = active;
	}
	
	
}
