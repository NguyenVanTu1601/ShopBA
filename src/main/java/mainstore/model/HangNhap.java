package mainstore.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "tblHangNhap")
public class HangNhap{


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idHangNhap;
	
	private int soLuong;
	private int donGia;
	
//	@ManyToOne
//    @JoinColumn(name = "idDonNhap")
//    private DonNhapHang donNhapHang;
	
	@ManyToOne
    @JoinColumn(name = "idMathang")
    private MatHang matHang;
	
	public HangNhap() {}
}
