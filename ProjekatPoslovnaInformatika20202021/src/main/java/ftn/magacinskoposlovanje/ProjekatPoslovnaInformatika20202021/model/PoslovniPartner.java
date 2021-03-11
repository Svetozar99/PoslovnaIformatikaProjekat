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

@Entity
@Table(name = "poslovni_partner")
public class PoslovniPartner {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sifra_partnera", nullable = false)
	private int sifraPartnera;
	
	@Column(name = "naziv_partnera", nullable = false)
	private String nazivPartnera;
	
	@Column(name = "adresa_partnera", nullable = false)
	private String adresa;
	
	@Column(name = "broj_telefona", nullable = false)
	private String brojTelefona;
	
	@Column(name = "email", nullable = false)
	private String email;
	
	@Column(name = "PIB", nullable = false, unique = true)
	private int PIB;
	
	@Column(name = "MIB", nullable = false)
	private int MIB;
	
	@OneToMany(cascade={ALL}, fetch=LAZY, mappedBy="poslovniPartner")
	private List<PrometniDokument> prometniDokumenti = new ArrayList<PrometniDokument>();
	
	@ManyToOne
	@JoinColumn(name="preduzece", referencedColumnName="id_preduzeca", nullable=false)
	private Preduzece preduzece;

	public PoslovniPartner() {
		super();
	}

	public PoslovniPartner(int sifraPartnera, String nazivPartnera, String adresa, String brojTelefona, String email,
			int pIB, int mIB, List<PrometniDokument> prometniDokumenti, Preduzece preduzece) {
		super();
		this.sifraPartnera = sifraPartnera;
		this.nazivPartnera = nazivPartnera;
		this.adresa = adresa;
		this.brojTelefona = brojTelefona;
		this.email = email;
		PIB = pIB;
		MIB = mIB;
		this.preduzece = preduzece;
	}

	public int getSifraPartnera() {
		return sifraPartnera;
	}

	public void setSifraPartnera(int sifraPartnera) {
		this.sifraPartnera = sifraPartnera;
	}

	public String getNazivPartnera() {
		return nazivPartnera;
	}

	public void setNazivPartnera(String nazivPartnera) {
		this.nazivPartnera = nazivPartnera;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getBrojTelefona() {
		return brojTelefona;
	}

	public void setBrojTelefona(String brojTelefona) {
		this.brojTelefona = brojTelefona;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public List<PrometniDokument> getPrometniDokumenti() {
		return prometniDokumenti;
	}

	public void setPrometniDokumenti(List<PrometniDokument> prometniDokumenti) {
		this.prometniDokumenti = prometniDokumenti;
	}

	public Preduzece getPreduzece() {
		return preduzece;
	}

	public void setPreduzece(Preduzece preduzece) {
		this.preduzece = preduzece;
	}
}
