package mainstore.model;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "tblDonNhapHang")
public class DonNhapHang{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idDonNhap;
	
	private long tongTien;
	
	@ManyToOne(fetch = FetchType.EAGER )
    @JoinColumn(name = "idNhanVien")
    private NhanVien nhanVien;
	
	@ManyToOne(fetch = FetchType.EAGER )
    @JoinColumn(name = "idNhaCungCap")
    private NhaCungCap nhaCungCap;
	
//	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE}, 
//				fetch = FetchType.EAGER)
//	@JoinTable(name = "tblHangNhap",
//	        joinColumns = @JoinColumn(name = "idDonNhap"),
//	        inverseJoinColumns = @JoinColumn(name = "idMatHang")
//	    )
//	private List<MatHang> listHangNhap = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
	@JoinColumn(name = "idDonNhap" ,referencedColumnName = "idDonNhap")
	private List<HangNhap> listHangNhap = new ArrayList<>();
	
	public void addHangNhap(HangNhap hangNhap) {
		listHangNhap.add(hangNhap);
	}
	
	public DonNhapHang() {
		
	}

	public int getIdDonNhap() {
		return idDonNhap;
	}

	public void setIdDonNhap(int idDonNhap) {
		this.idDonNhap = idDonNhap;
	}

	public long getTongTien() {
		return tongTien;
	}

	public void setTongTien(long tongTien) {
		this.tongTien = tongTien;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public NhaCungCap getNhaCungCap() {
		return nhaCungCap;
	}

	public void setNhaCungCap(NhaCungCap nhaCungCap) {
		this.nhaCungCap = nhaCungCap;
	}

	public List<HangNhap> getListHangNhap() {
		return listHangNhap;
	}

	public void setListHangNhap(List<HangNhap> listHangNhap) {
		this.listHangNhap = listHangNhap;
	}
	
	
	
}
