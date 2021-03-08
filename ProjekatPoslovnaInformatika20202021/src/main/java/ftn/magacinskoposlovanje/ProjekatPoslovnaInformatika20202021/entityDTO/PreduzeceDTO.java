package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.entityDTO;

import java.io.Serializable;

import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.Preduzece;

public class PreduzeceDTO implements Serializable{

	private Integer id;
	
	private String naziv;
	
	private String adresa;
	
	private String telefon;
	
	private int pIB;
	
	private int mIB;

	public PreduzeceDTO() {
		super();
	}


	public PreduzeceDTO(Integer id, String naziv, String adresa, String telefon, int pIB, int mIB) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.adresa = adresa;
		this.telefon = telefon;
		this.pIB = pIB;
		this.mIB = mIB;
	}

	public PreduzeceDTO(Preduzece preduzece) {
		this(preduzece.getIdPreduzeca(), preduzece.getNazivPreduzeca(), preduzece.getAdresa(), preduzece.getTelefon(), preduzece.getPIB(), preduzece.getMIB());
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	
	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}


	public int getpIB() {
		return pIB;
	}


	public void setpIB(int pIB) {
		this.pIB = pIB;
	}


	public int getmIB() {
		return mIB;
	}


	public void setmIB(int mIB) {
		this.mIB = mIB;
	}

	
}
