package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.entityDTO;

import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.RobaIliUsluga;

public class RobaIliUslugaDTO {

	private int sifra;
	private String naziv;
	private Integer idJedinicaMere;
	private String jedinicaMere;
	private double cena;
	
	
	
	public RobaIliUslugaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RobaIliUslugaDTO(int sifra, String naziv, Integer idJedinicaMere, String jedinicaMere, double cena) {
		super();
		this.sifra = sifra;
		this.naziv = naziv;
		this.idJedinicaMere = idJedinicaMere;
		this.jedinicaMere = jedinicaMere;
		this.cena = cena;
	}

	public RobaIliUslugaDTO(RobaIliUsluga r) {
		this(r.getSifra(), r.getNaziv(), r.getJedinicaMere().getId(), r.getJedinicaMere().getNaziv(), r.getCena());
	}
	
	public int getSifra() {
		return sifra;
	}
	public void setSifra(int sifra) {
		this.sifra = sifra;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public String getJedinicaMere() {
		return jedinicaMere;
	}
	public void setJedinicaMere(String jedinicaMere) {
		this.jedinicaMere = jedinicaMere;
	}
	public Integer getIdJedinicaMere() {
		return idJedinicaMere;
	}
	public void setIdJedinicaMere(Integer idJedinicaMere) {
		this.idJedinicaMere = idJedinicaMere;
	}
	
	public double getCena() {
		return cena;
	}
	public void setCena(double cena) {
		this.cena = cena;
	}
}
