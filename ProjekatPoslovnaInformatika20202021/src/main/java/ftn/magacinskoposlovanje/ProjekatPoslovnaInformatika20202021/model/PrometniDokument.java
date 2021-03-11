package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

import java.util.ArrayList;
import java.util.Date;
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
@Table(name = "prometni_dokument")
public class PrometniDokument {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private int id;
	
	@Column(name = "redni_broj", nullable = false)
	private int redniBroj;
	
	@Column(name = "vrsta_dokumenta", nullable = false)
	private VrstaDokumenta vrstaDokumenta; //prepravio da bude enumeracija
	
	@Column(name = "datum", nullable = false)
	private Date datum;
	
	@Column(name = "status", nullable = false)
	private Status status = Status.F; //prepravio da bude enumeracija
	
	@OneToMany(cascade={ALL}, fetch=LAZY, mappedBy="prometniDokument")
	private List<StavkaDokumenta> stavkaDokumenta = new ArrayList<StavkaDokumenta>();
	
	@ManyToOne
	@JoinColumn(name="poslovni_partner", referencedColumnName="sifra_partnera", nullable=true)
	private PoslovniPartner poslovniPartner;
	
	@ManyToOne
	@JoinColumn(name="poslovna_godina", referencedColumnName="broj_godine", nullable=false)
	private PoslovnaGodina poslovnaGodina;
	
	@ManyToOne
	@JoinColumn(name="sifra_magacina", referencedColumnName="sifra_magacina", nullable=true)
	private Magacin magacin;
	
	@ManyToOne
	@JoinColumn(name="id_preduzeca", referencedColumnName="id_preduzeca", nullable=true)
	private Preduzece preduzece;
	
	@ManyToOne
	@JoinColumn(name="magacinska_kartica_id", referencedColumnName="id", nullable=false)
	private MagacinskaKartica magacinskaKartica;
	
	public PrometniDokument() {
		super();
	}

	public PrometniDokument(int id, int redniBroj, VrstaDokumenta vrstaDokumenta, Date datum, Status status,
			List<StavkaDokumenta> stavkaDokumenta, PoslovniPartner poslovniPartner, PoslovnaGodina poslovnaGodina,
			RobaIliUsluga robaIliUsluga, Magacin magacin, Preduzece preduzece, MagacinskaKartica magacinskaKartica) {
		super();
		this.id = id;
		this.redniBroj = redniBroj;
		this.vrstaDokumenta = vrstaDokumenta;
		this.datum = datum;
		this.status = status;
		this.stavkaDokumenta = stavkaDokumenta;
		this.poslovniPartner = poslovniPartner;
		this.poslovnaGodina = poslovnaGodina;
		this.magacin = magacin;
		this.preduzece = preduzece;
		this.magacinskaKartica = magacinskaKartica;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRedniBroj() {
		return redniBroj;
	}

	public void setRedniBroj(int redniBroj) {
		this.redniBroj = redniBroj;
	}

	public VrstaDokumenta getVrstaDokumenta() {
		return vrstaDokumenta;
	}

	public void setVrstaDokumenta(VrstaDokumenta vrstaDokumenta) {
		this.vrstaDokumenta = vrstaDokumenta;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<StavkaDokumenta> getStavkaDokumenta() {
		return stavkaDokumenta;
	}

	public void setStavkaDokumenta(List<StavkaDokumenta> stavkaDokumenta) {
		this.stavkaDokumenta = stavkaDokumenta;
	}

	public PoslovniPartner getPoslovniPartner() {
		return poslovniPartner;
	}

	public void setPoslovniPartner(PoslovniPartner poslovniPartner) {
		this.poslovniPartner = poslovniPartner;
	}

	public PoslovnaGodina getPoslovnaGodina() {
		return poslovnaGodina;
	}

	public void setPoslovnaGodina(PoslovnaGodina poslovnaGodina) {
		this.poslovnaGodina = poslovnaGodina;
	}

	public Magacin getMagacin() {
		return magacin;
	}

	public void setMagacin(Magacin magacin) {
		this.magacin = magacin;
	}

	public Preduzece getPreduzece() {
		return preduzece;
	}

	public void setPreduzece(Preduzece preduzece) {
		this.preduzece = preduzece;
	}

	public MagacinskaKartica getMagacinskaKartica() {
		return magacinskaKartica;
	}

	public void setMagacinskaKartica(MagacinskaKartica magacinskaKartica) {
		this.magacinskaKartica = magacinskaKartica;
	}
}
