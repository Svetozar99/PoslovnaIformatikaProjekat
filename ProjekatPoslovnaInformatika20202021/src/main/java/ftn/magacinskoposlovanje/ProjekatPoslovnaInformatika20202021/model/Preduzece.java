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
@Table(name = "preduzece")
public class Preduzece {

	public Preduzece(Preduzece p) {
		// TODO Auto-generated constructor stub
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_preduzeca", nullable = false, unique = true)
	private Integer idPreduzeca;
	
	@Column(name = "naziv_preduzeca", nullable = false)
	private String nazivPreduzeca;
	
	@Column(name = "adresa", nullable = false)
	private String adresa;
	
	@Column(name = "telefon", nullable = false)
	private String telefon;
	
	@Column(name = "PIB", nullable = false, unique = true)
	private int PIB;
	
	@Column(name = "MIB", nullable = false)
	private int MIB;
	
	@OneToMany(cascade={ALL}, fetch=LAZY, mappedBy="preduzece")
	private List<PoslovnaGodina> poslovneGodine = new ArrayList<PoslovnaGodina>();
	
	@OneToMany(cascade={ALL}, fetch=LAZY, mappedBy="preduzece")
	private List<Magacin> magacini = new ArrayList<Magacin>();
	
	@OneToMany(cascade={ALL}, fetch=LAZY, mappedBy="preduzece")
	private List<PoslovniPartner> poslovniPartneri = new ArrayList<PoslovniPartner>();

	@OneToMany(cascade={ALL}, fetch=LAZY, mappedBy="preduzece")
	private List<PrometniDokument> prometniDokument = new ArrayList<PrometniDokument>();

	public Preduzece() {
		super();
	}
	
	public Preduzece(Integer idPreduzeca, String nazivPreduzeca, String adresa, String telefon, int pIB, int mIB,
			List<PoslovnaGodina> poslovneGodine, List<Magacin> magacini, List<PoslovniPartner> poslovniPartneri,
			List<PrometniDokument> prometniDokument) {
		super();
		this.idPreduzeca = idPreduzeca;
		this.nazivPreduzeca = nazivPreduzeca;
		this.adresa = adresa;
		this.telefon = telefon;
		PIB = pIB;
		MIB = mIB;
		this.poslovneGodine = poslovneGodine;
		this.magacini = magacini;
		this.poslovniPartneri = poslovniPartneri;
		this.prometniDokument = prometniDokument;
	}

	public Integer getIdPreduzeca() {
		return idPreduzeca;
	}

	public void setIdPreduzeca(Integer idPreduzeca) {
		this.idPreduzeca = idPreduzeca;
	}

	public String getNazivPreduzeca() {
		return nazivPreduzeca;
	}

	public void setNazivPreduzeca(String nazivPreduzeca) {
		this.nazivPreduzeca = nazivPreduzeca;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public int getPIB() {
		return PIB;
	}

	public void setPIB(int pIB) {
		PIB = pIB;
	}

	public int getMIB() {
		return MIB;
	}

	public void setMIB(int mIB) {
		MIB = mIB;
	}

	public List<PoslovnaGodina> getPoslovneGodine() {
		return poslovneGodine;
	}

	public void setPoslovneGodine(List<PoslovnaGodina> poslovneGodine) {
		this.poslovneGodine = poslovneGodine;
	}

	public List<Magacin> getMagacini() {
		return magacini;
	}

	public void setMagacini(List<Magacin> magacini) {
		this.magacini = magacini;
	}

	public List<PoslovniPartner> getPoslovniPartneri() {
		return poslovniPartneri;
	}

	public void setPoslovniPartneri(List<PoslovniPartner> poslovniPartneri) {
		this.poslovniPartneri = poslovniPartneri;
	}

	public List<PrometniDokument> getPrometniDokument() {
		return prometniDokument;
	}

	public void setPrometniDokument(List<PrometniDokument> prometniDokument) {
		this.prometniDokument = prometniDokument;
	}
	
	
}
