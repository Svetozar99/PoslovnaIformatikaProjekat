package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Table(name = "promet_magacinske_kartice")
public class PrometMagacinskeKartice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_prometa", nullable = false, unique = true)
	private Integer idPrometa;
	
	@Column(name = "vrsta_prometa", nullable = false)
	private VrstaPrometa vrstaPrometa;//mozda treba enumeracija // prepravljeno da bude enumeracija
	
	@Column(name = "smer", nullable = false)
	private Smer smer;//i ovdje isto // prepravljeno da bude enumeracija
	
	@Column(name = "kolicina", nullable = false)
	private double kolicina;
	
	@Column(name = "cena", nullable = false)
	private double cena;
	
	@Column(name = "vrednost", nullable = false)
	private double vrednost;
	
	@Column(name = "dokument", nullable = false)
	private String dokument;
	
	@Column(name = "datum_prometa", nullable = false)
	private Date datumPrometa;
	
	@ManyToOne
	@JoinColumn(name="magacinska_kartica", referencedColumnName="id", nullable=false)
	private MagacinskaKartica magacinskaKartica;
	
	public PrometMagacinskeKartice() {
		super();
	}

	public PrometMagacinskeKartice(Integer idPrometa, VrstaPrometa vrstaPrometa, Smer smer, double kolicina,
			double cena, double vrednost, String dokument, Date datumPrometa, MagacinskaKartica magacinskaKartica) {
		super();
		this.idPrometa = idPrometa;
		this.vrstaPrometa = vrstaPrometa;
		this.smer = smer;
		this.kolicina = kolicina;
		this.cena = cena;
		this.vrednost = vrednost;
		this.dokument = dokument;
		this.datumPrometa = datumPrometa;
		this.magacinskaKartica = magacinskaKartica;
	}

	public Integer getIdPrometa() {
		return idPrometa;
	}

	public void setIdPrometa(Integer idPrometa) {
		this.idPrometa = idPrometa;
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

	public double getVrednost() {
		return vrednost;
	}

	public void setVrednost(double vrednost) {
		this.vrednost = vrednost;
	}

	public String getDokument() {
		return dokument;
	}

	public void setDokument(String dokument) {
		this.dokument = dokument;
	}

	public Date getDatumPrometa() {
		return datumPrometa;
	}

	public void setDatumPrometa(Date datumPrometa) {
		this.datumPrometa = datumPrometa;
	}

	public MagacinskaKartica getMagacinskaKartica() {
		return magacinskaKartica;
	}

	public void setMagacinskaKartica(MagacinskaKartica magacinskaKartica) {
		this.magacinskaKartica = magacinskaKartica;
	}

	@Override
	public String toString() {
		return "PrometMagacinskeKartice [idPrometa=" + idPrometa + ", vrstaPrometa=" + vrstaPrometa + ", smer=" + smer
				+ ", kolicina=" + kolicina + ", cena=" + cena + ", vrednost=" + vrednost + ", dokument=" + dokument
				+ ", datumPrometa=" + datumPrometa + ", magacinskaKartica=" + magacinskaKartica + "]";
	}
	
	
}
