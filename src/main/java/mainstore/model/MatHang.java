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
	
	
}
