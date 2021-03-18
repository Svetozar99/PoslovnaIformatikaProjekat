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


@Entity
@Table(name = "roba_usluga")
public class RobaIliUsluga {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sifra", nullable = false, unique = true)
	private Integer sifra;
	
	@Column(name = "naziv", nullable = false)
	private String naziv;
	
	@Column(name = "cena", nullable = false)
	private double cena;
	
	@OneToMany(cascade={ALL}, fetch=LAZY, mappedBy="popisniDokument")
	private List<StavkePopisa> stavkePopisa = new ArrayList<StavkePopisa>();
	
	@OneToMany(cascade={ALL}, fetch=LAZY, mappedBy="robaIliUsluga")
	private List<MagacinskaKartica> magacinskeKartice = new ArrayList<MagacinskaKartica>();
	
	@OneToMany(cascade={ALL}, fetch=LAZY, mappedBy="robaIliUsluga")
	private List<StavkaDokumenta> stavkeDokumenta = new ArrayList<StavkaDokumenta>();
	
	@ManyToOne
	@JoinColumn(name="jedinica_mere", referencedColumnName="id_jedinice_mere", nullable=false)
	private JedinicaMere jedinicaMere;

	
	
	
	public RobaIliUsluga(Integer sifra, String naziv, double cena, List<StavkePopisa> stavkePopisa,
			List<MagacinskaKartica> magacinskeKartice, List<StavkaDokumenta> stavkeDokumenta,
			JedinicaMere jedinicaMere) {
		super();
		this.sifra = sifra;
		this.naziv = naziv;
		this.cena = cena;
		this.stavkePopisa = stavkePopisa;
		this.magacinskeKartice = magacinskeKartice;
		this.stavkeDokumenta = stavkeDokumenta;
		this.jedinicaMere = jedinicaMere;
		this.cena = cena;
	}

	public RobaIliUsluga() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getSifra() {
		return sifra;
	}

	public void setSifra(Integer sifra) {
		this.sifra = sifra;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public List<StavkePopisa> getStavkePopisa() {
		return stavkePopisa;
	}

	public void setStavkePopisa(List<StavkePopisa> stavkePopisa) {
		this.stavkePopisa = stavkePopisa;
	}

	public List<MagacinskaKartica> getMagacinskeKartice() {
		return magacinskeKartice;
	}

	public void setMagacinskeKartice(List<MagacinskaKartica> magacinskeKartice) {
		this.magacinskeKartice = magacinskeKartice;
	}

	public List<StavkaDokumenta> getStavkeDokumenta() {
		return stavkeDokumenta;
	}

	public void setStavkeDokumenta(List<StavkaDokumenta> stavkeDokumenta) {
		this.stavkeDokumenta = stavkeDokumenta;
	}

	public JedinicaMere getJedinicaMere() {
		return jedinicaMere;
	}

	public void setJedinicaMere(JedinicaMere jedinicaMere) {
		this.jedinicaMere = jedinicaMere;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}
}
