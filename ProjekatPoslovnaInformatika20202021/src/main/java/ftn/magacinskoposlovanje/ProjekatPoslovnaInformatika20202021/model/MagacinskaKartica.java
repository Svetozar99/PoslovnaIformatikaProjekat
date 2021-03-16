package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "magacinska_kartica")
public class MagacinskaKartica {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Integer id;
	
	@Column(name = "pocetno_stanje_kolicinski", nullable = false)
	private double pocetnoStanjeKolicinski; 
	
	@Column(name = "promet_ulaza_kolicinski", nullable = false)
	private double prometUlazaKolicinski;
	
	@Column(name = "promet_izlaza_kolicinski", nullable = false)
	private double prometIzlazaKolicinski;
	
	@Column(name = "ukupna_kolicina", nullable = false)
	private double ukupnaKolicina;
	
	@Column(name = "pocetno_stanje_vrednosno", nullable = false)
	private double pocetnoStanjeVrednosno;
	
	@Column(name = "promet_ulaza_vrednosno", nullable = false)
	private double prometUlazaVrednosno;
	
	@Column(name = "promet_izlaza_vrednosno", nullable = false)
	private double prometIzlazaVrednosno;
	
	@Column(name = "ukupna_vrednost", nullable = false)
	private double ukupnaVrednost;
	
	@Column(name = "cena", nullable = false)
	private double cena;
	
	@ManyToOne
	@JoinColumn(name="magacin", referencedColumnName="sifra_magacina", nullable=false)
	private Magacin magacin;
	
	@ManyToOne
	@JoinColumn(name="poslovna_godina", referencedColumnName="id_godine", nullable=false)
	private PoslovnaGodina poslovnaGodina;
	
	@ManyToOne
	@JoinColumn(name="roba_ili_usluga", referencedColumnName="sifra", nullable=false)
	private RobaIliUsluga robaIliUsluga;

	@OneToMany(cascade={ALL}, fetch=LAZY, mappedBy="idPrometa")
	private List<PrometMagacinskeKartice> prometMagacinskeKartice = new ArrayList<PrometMagacinskeKartice>();

	public MagacinskaKartica() {
		super();
	}
	
	public MagacinskaKartica(Integer id, double pocetnoStanjeKolicinski, double prometUlazaKolicinski,
			double prometIzlazaKolicinski, double ukupnaKolicina, double pocetnoStanjeVrednosno,
			double prometUlazaVrednosno, double prometIzlazaVrednosno, double ukupnaVrednost, double cena,
			Magacin magacin, PoslovnaGodina poslovnaGodina, RobaIliUsluga robaIliUsluga,
			List<PrometMagacinskeKartice> prometMagacinskeKartice) {
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
		this.poslovnaGodina = poslovnaGodina;
		this.robaIliUsluga = robaIliUsluga;
		this.prometMagacinskeKartice = prometMagacinskeKartice;
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

	public Magacin getMagacin() {
		return magacin;
	}

	public void setMagacin(Magacin magacin) {
		this.magacin = magacin;
	}

	public PoslovnaGodina getPoslovnaGodina() {
		return poslovnaGodina;
	}

	public void setPoslovnaGodina(PoslovnaGodina poslovnaGodina) {
		this.poslovnaGodina = poslovnaGodina;
	}

	public RobaIliUsluga getRobaIliUsluga() {
		return robaIliUsluga;
	}

	public void setRobaIliUsluga(RobaIliUsluga robaIliUsluga) {
		this.robaIliUsluga = robaIliUsluga;
	}

	public List<PrometMagacinskeKartice> getPrometMagacinskeKartice() {
		return prometMagacinskeKartice;
	}

	public void setPrometMagacinskeKartice(List<PrometMagacinskeKartice> prometMagacinskeKartice) {
		this.prometMagacinskeKartice = prometMagacinskeKartice;
	}

	@Override
	public String toString() {
		return "MagacinskaKartica [id=" + id + ", pocetnoStanjeKolicinski=" + pocetnoStanjeKolicinski
				+ ", prometUlazaKolicinski=" + prometUlazaKolicinski + ", prometIzlazaKolicinski="
				+ prometIzlazaKolicinski + ", ukupnaKolicina=" + ukupnaKolicina + ", pocetnoStanjeVrednosno="
				+ pocetnoStanjeVrednosno + ", prometUlazaVrednosno=" + prometUlazaVrednosno + ", prometIzlazaVrednosno="
				+ prometIzlazaVrednosno + ", ukupnaVrednost=" + ukupnaVrednost + ", cena=" + cena + ", magacin="
				+ magacin + ", poslovnaGodina=" + poslovnaGodina + ", robaIliUsluga=" + robaIliUsluga + "]";
	}
	
	
}
