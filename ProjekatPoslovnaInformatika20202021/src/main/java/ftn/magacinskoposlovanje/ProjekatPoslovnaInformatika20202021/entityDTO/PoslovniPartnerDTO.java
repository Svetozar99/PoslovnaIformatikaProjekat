package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.entityDTO;

import javax.persistence.Column;

import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.PoslovniPartner;

public class PoslovniPartnerDTO {

	private int sifraPartnera;
	private String nazivPartnera;
	private String adresa;
	private String brojTelefona;
	private String email;
	private int pIB;
	private int mIB;
	private int idPreduzeca;
	private String nazivPreduzeca;
	
	public PoslovniPartnerDTO() {
		super();
	}
	
	public PoslovniPartnerDTO(int sifraPartnera, String nazivPartnera, String adresa, String brojTelefona, String email,
			int pIB, int mIB, int idPreduzeca, String nazivPreduzeca) {
		super();
		this.sifraPartnera = sifraPartnera;
		this.nazivPartnera = nazivPartnera;
		this.adresa = adresa;
		this.brojTelefona = brojTelefona;
		this.email = email;
		this.pIB = pIB;
		this.mIB = mIB;
		this.idPreduzeca = idPreduzeca;
		this.nazivPreduzeca = nazivPreduzeca;
	}

	public PoslovniPartnerDTO(PoslovniPartner poslovniPartner) {
		this(poslovniPartner.getSifraPartnera(), poslovniPartner.getNazivPartnera(), poslovniPartner.getAdresa(), poslovniPartner.getBrojTelefona(), 
				poslovniPartner.getEmail(), poslovniPartner.getPIB(), poslovniPartner.getMIB(),poslovniPartner.getPreduzece().getIdPreduzeca(),poslovniPartner.getPreduzece().getNazivPreduzeca());
		System.out.println(this.toString());
	}

	public int getSifraPartnera() {
		return sifraPartnera;
	}

	public void setSifraPartnera(int sifraPartnera) {
		this.sifraPartnera = sifraPartnera;
	}

	public String getNazivPartnera() {
		return nazivPartnera;
	}

	public void setNazivPartnera(String nazivPartnera) {
		this.nazivPartnera = nazivPartnera;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getBrojTelefona() {
		return brojTelefona;
	}

	public void setBrojTelefona(String brojTelefona) {
		this.brojTelefona = brojTelefona;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPIB() {
		return pIB;
	}

	public void setPIB(int pIB) {
		this.pIB = pIB;
	}

	public int getMIB() {
		return mIB;
	}

	public void setMIB(int mIB) {
		this.mIB = mIB;
	}

	public int getIdPreduzeca() {
		return idPreduzeca;
	}

	public void setIdPreduzeca(int idPreduzeca) {
		this.idPreduzeca = idPreduzeca;
	}

	public String getNazivPreduzeca() {
		return nazivPreduzeca;
	}

	public void setNazivPreduzeca(String nazivPreduzeca) {
		this.nazivPreduzeca = nazivPreduzeca;
	}

	@Override
	public String toString() {
		return "PoslovniPartnerDTO [sifraPartnera=" + sifraPartnera + ", nazivPartnera=" + nazivPartnera + ", adresa="
				+ adresa + ", brojTelefona=" + brojTelefona + ", email=" + email + ", pIB=" + pIB + ", mIB=" + mIB
				+ ", idPreduzeca=" + idPreduzeca + ", nazivPreduzeca=" + nazivPreduzeca + "]";
	}
	
	
}
