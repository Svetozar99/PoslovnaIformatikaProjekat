package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.entityDTO;

import java.io.Serializable;

import javax.persistence.Column;

import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.MagacinskaKartica;

public class MagacinskaKarticaDTO implements Serializable{
	
	private Integer id;
	
	private double pocetnoStanjeKolicinski;
	
	private double prometUlazaKolicinski;
	
	private double prometIzlazaKolicinski;
	
	private double ukupnaKolicina;
	
	private double pocetnoStanjeVrednosno;
	
	private double prometUlazaVrednosno;
	
	private double prometIzlazaVrednosno;
	
	private double ukupnaVrednost;
	
	private double cena;
	
	private Integer magacin;
	
	private String nazivMagacina;
	
	private Integer poslovnaGodina;
	
	private Integer brojPoslovneGodine;
	
	private Integer robaIliUsluga;
	
	private String nazivRobeIliUsluge;

	public MagacinskaKarticaDTO() {
		super();
	}
	
	
	
	public MagacinskaKarticaDTO(Integer id, double pocetnoStanjeKolicinski, double prometUlazaKolicinski,
			double prometIzlazaKolicinski, double ukupnaKolicina, double pocetnoStanjeVrednosno,
			double prometUlazaVrednosno, double prometIzlazaVrednosno, double ukupnaVrednost, double cena,
			Integer magacin, String nazivMagacina, Integer poslovnaGodina, Integer brojPoslovneGodine,
			Integer robaIliUsluga, String nazivRobeIliUsluge) {
		super();
		this.id = id;
		this.pocetnoStanjeKolicinski = pocetnoStanjeKolicinski;
		this.prometUlazaKolicinski = prometUlazaKolicinski;
		this.prometIzlazaKolicinski = prometIzlazaKolicinski;
		this.ukupnaKolicina = ukupnaKolicina;
		this.pocetnoStanjeVrednosno = pocetnoStanjeVrednosno;
		this.prometUlazaVrednosno = prometUlazaVrednosno;
		this.prometIzlazaVrednosno = prometIzlazaVrednosno;
		this.ukupnaVrednost = ukupnaVrednost;
		this.cena = cena;
		this.magacin = magacin;
		this.nazivMagacina = nazivMagacina;
		this.poslovnaGodina = poslovnaGodina;
		this.brojPoslovneGodine = brojPoslovneGodine;
		this.robaIliUsluga = robaIliUsluga;
		this.nazivRobeIliUsluge = nazivRobeIliUsluge;
	}

	public MagacinskaKarticaDTO(MagacinskaKartica kartica) {
		this(kartica.getId(), kartica.getPocetnoStanjeKolicinski(), kartica.getPrometUlazaKolicinski(),
				kartica.getPrometIzlazaKolicinski(), kartica.getUkupnaKolicina(), kartica.getPocetnoStanjeVrednosno(),
				kartica.getPrometUlazaVrednosno(), kartica.getPrometIzlazaVrednosno(),
				kartica.getUkupnaVrednost(), kartica.getCena(), kartica.getMagacin().getSifraMagacina(), kartica.getMagacin().getNazivMagacina(),
				kartica.getPoslovnaGodina().getId(), kartica.getPoslovnaGodina().getBrojGodine(),
				kartica.getRobaIliUsluga().getSifra(), kartica.getRobaIliUsluga().getNaziv());
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public double getPocetnoStanjeKolicinski() {
		return pocetnoStanjeKolicinski;
	}



	public void setPocetnoStanjeKolicinski(double pocetnoStanjeKolicinski) {
		this.pocetnoStanjeKolicinski = pocetnoStanjeKolicinski;
	}



	public double getPrometUlazaKolicinski() {
		return prometUlazaKolicinski;
	}



	public void setPrometUlazaKolicinski(double prometUlazaKolicinski) {
		this.prometUlazaKolicinski = prometUlazaKolicinski;
	}



	public double getPrometIzlazaKolicinski() {
		return prometIzlazaKolicinski;
	}



	public void setPrometIzlazaKolicinski(double prometIzlazaKolicinski) {
		this.prometIzlazaKolicinski = prometIzlazaKolicinski;
	}



	public double getUkupnaKolicina() {
		return ukupnaKolicina;
	}



	public void setUkupnaKolicina(double ukupnaKolicina) {
		this.ukupnaKolicina = ukupnaKolicina;
	}



	public double getPocetnoStanjeVrednosno() {
		return pocetnoStanjeVrednosno;
	}



	public void setPocetnoStanjeVrednosno(double pocetnoStanjeVrednosno) {
		this.pocetnoStanjeVrednosno = pocetnoStanjeVrednosno;
	}



	public double getPrometUlazaVrednosno() {
		return prometUlazaVrednosno;
	}



	public void setPrometUlazaVrednosno(double prometUlazaVrednosno) {
		this.prometUlazaVrednosno = prometUlazaVrednosno;
	}



	public double getPrometIzlazaVrednosno() {
		return prometIzlazaVrednosno;
	}



	public void setPrometIzlazaVrednosno(double prometIzlazaVrednosno) {
		this.prometIzlazaVrednosno = prometIzlazaVrednosno;
	}



	public double getUkupnaVrednost() {
		return ukupnaVrednost;
	}



	public void setUkupnaVrednost(double ukupnaVrednost) {
		this.ukupnaVrednost = ukupnaVrednost;
	}



	public double getCena() {
		return cena;
	}



	public void setCena(double cena) {
		this.cena = cena;
	}



	public Integer getMagacin() {
		return magacin;
	}



	public void setMagacin(Integer magacin) {
		this.magacin = magacin;
	}



	public String getNazivMagacina() {
		return nazivMagacina;
	}



	public void setNazivMagacina(String nazivMagacina) {
		this.nazivMagacina = nazivMagacina;
	}



	public Integer getPoslovnaGodina() {
		return poslovnaGodina;
	}



	public void setPoslovnaGodina(Integer poslovnaGodina) {
		this.poslovnaGodina = poslovnaGodina;
	}



	public Integer getBrojPoslovneGodine() {
		return brojPoslovneGodine;
	}



	public void setBrojPoslovneGodine(Integer brojPoslovneGodine) {
		this.brojPoslovneGodine = brojPoslovneGodine;
	}



	public Integer getRobaIliUsluga() {
		return robaIliUsluga;
	}



	public void setRobaIliUsluga(Integer robaIliUsluga) {
		this.robaIliUsluga = robaIliUsluga;
	}



	public String getNazivRobeIliUsluge() {
		return nazivRobeIliUsluge;
	}



	public void setNazivRobeIliUsluge(String nazivRobeIliUsluge) {
		this.nazivRobeIliUsluge = nazivRobeIliUsluge;
	}
	
}
