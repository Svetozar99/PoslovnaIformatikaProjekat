package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.entityDTO;

import java.io.Serializable;

import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.PoslovnaGodina;

public class PoslovnaGodinaDTO implements Serializable{

	private Integer id;
	
	private Integer brojGodine;
	
	private Boolean zakljucena;
	
	private Integer preduzece;
	
	private String nazivPreduzeca;

	public PoslovnaGodinaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PoslovnaGodinaDTO(Integer id, Integer brojGodine, Boolean zakljucena, Integer preduzece, String nazivPreduzeca) {
		super();
		this.id = id;
		this.brojGodine = brojGodine;
		this.zakljucena = zakljucena;
		this.preduzece = preduzece;
		this.nazivPreduzeca = nazivPreduzeca;
	}

	public PoslovnaGodinaDTO(PoslovnaGodina godina) {
		this(godina.getId(),godina.getBrojGodine(), godina.isZakljucena(), godina.getPreduzece().getIdPreduzeca(), godina.getPreduzece().getNazivPreduzeca());
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getBrojGodine() {
		return brojGodine;
	}

	public void setBrojGodine(Integer brojGodine) {
		this.brojGodine = brojGodine;
	}

	public Boolean getZakljucena() {
		return zakljucena;
	}

	public void setZakljucena(Boolean zakljucena) {
		this.zakljucena = zakljucena;
	}

	public Integer getPreduzece() {
		return preduzece;
	}

	public void setPreduzece(Integer preduzece) {
		this.preduzece = preduzece;
	}

	public String getNazivPreduzeca() {
		return nazivPreduzeca;
	}

	public void setNazivPreduzeca(String nazivPreduzeca) {
		this.nazivPreduzeca = nazivPreduzeca;
	}
}
