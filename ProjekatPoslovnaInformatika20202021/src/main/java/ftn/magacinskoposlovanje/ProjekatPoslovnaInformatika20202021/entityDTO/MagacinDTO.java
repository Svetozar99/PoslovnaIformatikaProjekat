package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.entityDTO;

import java.io.Serializable;

import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.Magacin;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.Preduzece;

public class MagacinDTO implements Serializable{
	
	private Integer id;
	private String naziv;
	private Preduzece preduzece;
	
	public MagacinDTO() {
		super();
	}

	public MagacinDTO(Integer id, String naziv, Preduzece preduzece) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.preduzece = preduzece;
	}
	
	public MagacinDTO(Magacin magacin) {
		this(magacin.getSifraMagacina(), magacin.getNazivMagacina(), magacin.getPreduzece());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Preduzece getPreduzece() {
		return preduzece;
	}

	public void setPreduzece(Preduzece preduzece) {
		this.preduzece = preduzece;
	}

}
