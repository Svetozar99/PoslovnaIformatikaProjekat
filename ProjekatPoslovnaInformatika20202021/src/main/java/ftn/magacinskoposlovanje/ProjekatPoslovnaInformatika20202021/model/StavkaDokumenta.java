package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "stavka_dokumenta")
public class StavkaDokumenta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_stavke", nullable = false, unique = true)
	public Integer idStavke;
	
	@Column(name = "kolicina", nullable = false)
	public Double kolicina;
	
	@Column(name = "cena", nullable = false)
	public Double cena;
	
	@Column(name = "vrednost", nullable = false)
	public Double vrednost; //racuna se kao kolicina * cena
	
	@ManyToOne
	@JoinColumn(name="prometni_dokument", referencedColumnName="id", nullable=false)
	private PrometniDokument prometniDokument;
	
	@ManyToOne
	@JoinColumn(name="roba_usluga", referencedColumnName="sifra", nullable=false)
	private RobaIliUsluga robaIliUsluga;

	public StavkaDokumenta() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StavkaDokumenta(int idStavke, double kolicina, double cena, double vrednost,
			PrometniDokument prometniDokument, RobaIliUsluga robaIliUsluga) {
		super();
		this.idStavke = idStavke;
		this.kolicina = kolicina;
		this.cena = cena;
		this.vrednost = vrednost;
		this.prometniDokument = prometniDokument;
		this.robaIliUsluga = robaIliUsluga;
	}

	public int getIdStavke() {
		return idStavke;
	}

	public void setIdStavke(int idStavke) {
		this.idStavke = idStavke;
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

	public PrometniDokument getPrometniDokument() {
		return prometniDokument;
	}

	public void setPrometniDokument(PrometniDokument prometniDokument) {
		this.prometniDokument = prometniDokument;
	}

	public RobaIliUsluga getRobaIliUsluga() {
		return robaIliUsluga;
	}

	public void setRobaIliUsluga(RobaIliUsluga robaIliUsluga) {
		this.robaIliUsluga = robaIliUsluga;
	}
}
