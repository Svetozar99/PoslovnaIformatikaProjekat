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

	public MagacinskaKarticaDTO() {
		super();
	}
	
	public MagacinskaKarticaDTO(MagacinskaKartica kartica) {
		this(kartica.getId(), kartica.getPocetnoStanjeKolicinski(), kartica.getPrometUlazaKolicinski(),
				kartica.getPrometIzlazaKolicinski(), kartica.getUkupnaKolicina(), kartica.getPocetnoStanjeVrednosno(),
				kartica.getPrometUlazaVrednosno(), kartica.getPrometIzlazaVrednosno(),
				kartica.getUkupnaVrednost(), kartica.getCena());
	}
	
	public MagacinskaKarticaDTO(Integer id, double pocetnoStanjeKolicinski, double prometUlazaKolicinski,
			double prometIzlazaKolicinski, double ukupnaKolicina, double pocetnoStanjeVrednosno,
			double prometUlazaVrednosno, double prometIzlazaVrednosno, double ukupnaVrednost, double cena) {
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

}
