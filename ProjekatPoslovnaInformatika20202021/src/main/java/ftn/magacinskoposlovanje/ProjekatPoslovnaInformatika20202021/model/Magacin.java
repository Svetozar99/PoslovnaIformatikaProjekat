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
@Table(name = "magacin")
public class Magacin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sifra_magacina", nullable = false, unique = true)
	private int sifraMagacina;
	
	@Column(name = "naziv_magacina", nullable = false)
	private String nazivMagacina;
	
	@OneToMany(cascade={ALL}, fetch=LAZY, mappedBy="magacin")
	private List<MagacinskaKartica> magacinskeKartice = new ArrayList<MagacinskaKartica>();
	
	@OneToMany(cascade={ALL}, fetch=LAZY, mappedBy="ulazniMagacin")
	private List<PrometniDokument> prometniDokumentiUlaz = new ArrayList<PrometniDokument>();
	
	@OneToMany(cascade={ALL}, fetch=LAZY, mappedBy="izlazniMagacin")
	private List<PrometniDokument> prometniDokumentiIzlaz = new ArrayList<PrometniDokument>();
	
	@ManyToOne
	@JoinColumn(name="preduzece", referencedColumnName="id_preduzeca", nullable=false)
	private Preduzece preduzece;
	
	public Magacin() {
		super();
	}

	public Magacin(int sifraMagacina, String nazivMagacina, Preduzece preduzece) {
		super();
		this.sifraMagacina = sifraMagacina;
		this.nazivMagacina = nazivMagacina;
		this.preduzece = preduzece;
	}

	public int getSifraMagacina() {
		return sifraMagacina;
	}

	public void setSifraMagacina(int sifraMagacina) {
		this.sifraMagacina = sifraMagacina;
	}

	public String getNazivMagacina() {
		return nazivMagacina;
	}

	public void setNazivMagacina(String nazivMagacina) {
		this.nazivMagacina = nazivMagacina;
	}

	public List<MagacinskaKartica> getMagacinskeKartice() {
		return magacinskeKartice;
	}

	public void setMagacinskeKartice(List<MagacinskaKartica> magacinskeKartice) {
		this.magacinskeKartice = magacinskeKartice;
	}

	public List<PrometniDokument> getPrometniDokumentiUlaz() {
		return prometniDokumentiUlaz;
	}

	public void setPrometniDokumentiUlaz(List<PrometniDokument> prometniDokumentiUlaz) {
		this.prometniDokumentiUlaz = prometniDokumentiUlaz;
	}

	public List<PrometniDokument> getPrometniDokumentiIzlaz() {
		return prometniDokumentiIzlaz;
	}

	public void setPrometniDokumentiIzlaz(List<PrometniDokument> prometniDokumentiIzlaz) {
		this.prometniDokumentiIzlaz = prometniDokumentiIzlaz;
	}

	public Preduzece getPreduzece() {
		return preduzece;
	}

	public void setPreduzece(Preduzece preduzece) {
		this.preduzece = preduzece;
	}
}
