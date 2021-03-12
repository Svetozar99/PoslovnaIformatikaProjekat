package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.entityDTO;

import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.JedinicaMere;

public class JedinicaMereDTO {
	
	public Integer id;
	public String naziv;
	public String skraceniNaziv;
	
	public JedinicaMereDTO() {
		super();
	}
	
	public JedinicaMereDTO(JedinicaMere jedinicaMere) {
		super();
		this.id = jedinicaMere.getId();
		this.naziv = jedinicaMere.getNaziv();
		this.skraceniNaziv = jedinicaMere.getSkraceniNaziv();
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
	public String getSkraceniNaziv() {
		return skraceniNaziv;
	}
	public void setSkraceniNaziv(String skraceniNaziv) {
		this.skraceniNaziv = skraceniNaziv;
	}
}
