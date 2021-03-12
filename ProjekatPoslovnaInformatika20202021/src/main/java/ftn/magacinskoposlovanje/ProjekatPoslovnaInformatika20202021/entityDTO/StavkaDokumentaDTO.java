package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.entityDTO;

import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.StavkaDokumenta;

public class StavkaDokumentaDTO {

	private Integer id;
	
	private Double kolicina;
	
	private Double cena;
	
	private Double vrednost;
	
	private Integer prometniDokument;
	
	private Integer robaUsluga;
	
	private String nazivRobeUsluge;

	public StavkaDokumentaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StavkaDokumentaDTO(Integer id, Double kolicina, Double cena, Double vrednost, Integer prometniDokument,
			Integer robaUsluga, String nazivRobeUsluge) {
		super();
		this.id = id;
		this.kolicina = kolicina;
		this.cena = cena;
		this.vrednost = vrednost;
		this.prometniDokument = prometniDokument;
		this.robaUsluga = robaUsluga;
		this.nazivRobeUsluge = nazivRobeUsluge;
	}

	public StavkaDokumentaDTO(StavkaDokumenta sd) {
		this(sd.getIdStavke(), sd.getKolicina(), sd.getCena(), sd.getVrednost(), sd.getPrometniDokument().getId(), sd.getRobaIliUsluga().getSifra(), sd.getRobaIliUsluga().getNaziv());
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getKolicina() {
		return kolicina;
	}

	public void setKolicina(Double kolicina) {
		this.kolicina = kolicina;
	}

	public Double getCena() {
		return cena;
	}

	public void setCena(Double cena) {
		this.cena = cena;
	}

	public Double getVrednost() {
		return vrednost;
	}

	public void setVrednost(Double vrednost) {
		this.vrednost = vrednost;
	}

	public Integer getPrometniDokument() {
		return prometniDokument;
	}

	public void setPrometniDokument(Integer prometniDokument) {
		this.prometniDokument = prometniDokument;
	}

	public Integer getRobaUsluga() {
		return robaUsluga;
	}

	public void setRobaUsluga(Integer robaUsluga) {
		this.robaUsluga = robaUsluga;
	}

	public String getNazivRobeUsluge() {
		return nazivRobeUsluge;
	}

	public void setNazivRobeUsluge(String nazivRobeUsluge) {
		this.nazivRobeUsluge = nazivRobeUsluge;
	}
}
