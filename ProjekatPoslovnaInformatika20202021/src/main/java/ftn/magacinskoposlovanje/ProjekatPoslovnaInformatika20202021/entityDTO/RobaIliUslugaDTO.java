package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.entityDTO;

public class RobaIliUslugaDTO {

	private int sifra;
	private String naziv;
	private Integer idJedinicaMere;
	private String jedinicaMere;
	private double cena;
	
	public int getSifra() {
		return sifra;
	}
	public void setSifra(int sifra) {
		this.sifra = sifra;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public String getJedinicaMere() {
		return jedinicaMere;
	}
	public void setJedinicaMere(String jedinicaMere) {
		this.jedinicaMere = jedinicaMere;
	}
	public Integer getIdJedinicaMere() {
		return idJedinicaMere;
	}
	public void setIdJedinicaMere(Integer idJedinicaMere) {
		this.idJedinicaMere = idJedinicaMere;
	}
	
	public double getCena() {
		return cena;
	}
	public void setCena(double cena) {
		this.cena = cena;
	}
}
