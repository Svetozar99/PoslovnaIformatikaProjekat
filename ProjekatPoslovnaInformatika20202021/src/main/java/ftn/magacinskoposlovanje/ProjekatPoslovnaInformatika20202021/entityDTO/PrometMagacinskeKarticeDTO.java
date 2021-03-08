package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.entityDTO;

import java.io.Serializable;
import java.util.Date;

import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.PrometMagacinskeKartice;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.Smer;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.VrstaPrometa;

public class PrometMagacinskeKarticeDTO implements Serializable{

	private Integer id;
	
	private VrstaPrometa vrstaPrometa;
	
	private Smer smer;
	
	private double kolicina;
	
	private double cena;
	
	private String dokument;
	
	private Date date;
	
	public PrometMagacinskeKarticeDTO() {
		super();
	}

	public PrometMagacinskeKarticeDTO(Integer id, VrstaPrometa vrstaPrometa, Smer smer, double kolicina, double cena,
			String dokument, Date date) {
		super();
		this.id = id;
		this.vrstaPrometa = vrstaPrometa;
		this.smer = smer;
		this.kolicina = kolicina;
		this.cena = cena;
		this.dokument = dokument;
		this.date = date;
	}
	
	public PrometMagacinskeKarticeDTO(PrometMagacinskeKartice prometMagacinskeKartice) {
		this(prometMagacinskeKartice.getIdPrometa(), prometMagacinskeKartice.getVrstaPrometa(),
				prometMagacinskeKartice.getSmer(), prometMagacinskeKartice.getKolicina(),
				prometMagacinskeKartice.getCena(), prometMagacinskeKartice.getDokument(),
				prometMagacinskeKartice.getDatumPrometa());
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getDokument() {
		return dokument;
	}

	public void setDokument(String dokument) {
		this.dokument = dokument;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
