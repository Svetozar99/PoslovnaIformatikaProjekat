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
@Table(name = "poslovna_godina")
public class PoslovnaGodina {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "broj_godine", nullable = false, unique = true)
	private Integer brojGodine;
	
	@Column(name = "zakljucena", nullable = false)
	private Boolean zakljucena;
	
	@OneToMany(cascade={ALL}, fetch=LAZY, mappedBy="poslovnaGodina")
	private List<PopisniDokument> popisniDokumenti = new ArrayList<PopisniDokument>();
	
	@OneToMany(cascade={ALL}, fetch=LAZY, mappedBy="poslovnaGodina")
	private List<MagacinskaKartica> magacinskeKartice = new ArrayList<MagacinskaKartica>();
	
	@OneToMany(cascade={ALL}, fetch=LAZY, mappedBy="poslovnaGodina")
	private List<PrometniDokument> prometniDokumenti = new ArrayList<PrometniDokument>();
	
	
	@ManyToOne
	@JoinColumn(name="preduzece", referencedColumnName="id_preduzeca", nullable=false)
	private Preduzece preduzece;


	public PoslovnaGodina() {
		super();
		// TODO Auto-generated constructor stub
	}


	public PoslovnaGodina(int brojGodine, boolean zakljucena, List<PopisniDokument> popisniDokumenti,
			List<MagacinskaKartica> magacinskeKartice, List<PrometniDokument> prometniDokumenti, Preduzece preduzece) {
		super();
		this.brojGodine = brojGodine;
		this.zakljucena = zakljucena;
		this.popisniDokumenti = popisniDokumenti;
		this.magacinskeKartice = magacinskeKartice;
		this.prometniDokumenti = prometniDokumenti;
		this.preduzece = preduzece;
	}


	public int getBrojGodine() {
		return brojGodine;
	}


	public void setBrojGodine(int brojGodine) {
		this.brojGodine = brojGodine;
	}


	public boolean isZakljucena() {
		return zakljucena;
	}


	public void setZakljucena(boolean zakljucena) {
		this.zakljucena = zakljucena;
	}


	public List<PopisniDokument> getPopisniDokumenti() {
		return popisniDokumenti;
	}


	public void setPopisniDokumenti(List<PopisniDokument> popisniDokumenti) {
		this.popisniDokumenti = popisniDokumenti;
	}


	public List<MagacinskaKartica> getMagacinskeKartice() {
		return magacinskeKartice;
	}


	public void setMagacinskeKartice(List<MagacinskaKartica> magacinskeKartice) {
		this.magacinskeKartice = magacinskeKartice;
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
