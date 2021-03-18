package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.entityDTO;

import java.io.Serializable;
import java.util.Date;

import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.PrometMagacinskeKartice;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.Smer;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.VrstaPrometa;

public class PrometMagacinskeKarticeDTO implements Serializable{

	private int id;
	
	private String redniBroj;
	
	private VrstaPrometa vrstaPrometa;
	
	private Smer smer;
	
	private double kolicina;
	
	private double cena;
	
	private double vrednost;
	
	private String jedinicaMere;
	
	public PrometMagacinskeKarticeDTO() {
		super();
	}
	
	public PrometMagacinskeKarticeDTO(int id, String redniBroj, VrstaPrometa vrstaPrometa, Smer smer, double kolicina, double cena,
			double vrednost) {
		super();
		this.id = id;
		this.redniBroj = redniBroj;
		this.vrstaPrometa = vrstaPrometa;
		this.smer = smer;
		this.kolicina = kolicina;
		this.cena = cena;
		this.vrednost = vrednost;
	}

	public PrometMagacinskeKarticeDTO(PrometMagacinskeKartice prometMagacinskeKartice) {
		this(prometMagacinskeKartice.getId(), prometMagacinskeKartice.getRedniBroj(), prometMagacinskeKartice.getVrstaPrometa(),
				prometMagacinskeKartice.getSmer(), prometMagacinskeKartice.getKolicina(),
				prometMagacinskeKartice.getCena(),prometMagacinskeKartice.getVrednost());
		
	}

	public String getRedniBroj() {
		return redniBroj;
	}

	public void setRedniBroj(String redniBroj) {
		this.redniBroj = redniBroj;
	}

	public VrstaPrometa getVrstaPrometa() {
		return vrstaPrometa;
	}

	public void setVrstaPrometa(VrstaPrometa vrstaPrometa) {
		this.vrstaPrometa = vrstaPrometa;
	}

	public Smer getSmer() {
		return smer;
	}

	public void setSmer(Smer smer) {
		this.smer = smer;
	}

	public double getKolicina() {
		return kolicina;
	}

	public void setKolicina(double kolicina) {
		this.kolicina = kolicina;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public String getJedinicaMere() {
		return jedinicaMere;
	}

	public void setJedinicaMere(String jedinicaMere) {
		this.jedinicaMere = jedinicaMere;
	}

	public double getVrednost() {
		return vrednost;
	}

	public void setVrednost(double vrednost) {
		this.vrednost = vrednost;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
