package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.entityDTO;

import java.util.Date;

public class PrometniDokumentDTO {

	private Integer idDobavljaca;
	private Integer idKupca;
	private Integer idProdavca;
	private Integer idMagacina1;
	private Integer idMagacina2;
	private String mestoIzdavanjaRobe;
	private String nacinOtpreme;
	private Integer prijamnicaBr;
	private Integer otpremnicaBr;
	private Integer medjumagacinskiPrometBr;
	private Date datumIzdavanja;
	private String vrstaDokumenta;
	private String robuIzdao;
	private String robuPrimio;
	public Integer getIdDobavljaca() {
		return idDobavljaca;
	}
	public void setIdDobavljaca(Integer idDobavljaca) {
		this.idDobavljaca = idDobavljaca;
	}
	public Integer getIdKupca() {
		return idKupca;
	}
	public void setIdKupca(Integer idKupca) {
		this.idKupca = idKupca;
	}
	public Integer getIdProdavca() {
		return idProdavca;
	}
	public void setIdProdavca(Integer idProdavca) {
		this.idProdavca = idProdavca;
	}
	public Integer getIdMagacina1() {
		return idMagacina1;
	}
	public void setIdMagacina1(Integer idMagacina1) {
		this.idMagacina1 = idMagacina1;
	}
	public Integer getIdMagacina2() {
		return idMagacina2;
	}
	public void setIdMagacina2(Integer idMagacina2) {
		this.idMagacina2 = idMagacina2;
	}
	public String getMestoIzdavanjaRobe() {
		return mestoIzdavanjaRobe;
	}
	public void setMestoIzdavanjaRobe(String mestoIzdavanjaRobe) {
		this.mestoIzdavanjaRobe = mestoIzdavanjaRobe;
	}
	public String getNacinOtpreme() {
		return nacinOtpreme;
	}
	public void setNacinOtpreme(String nacinOtpreme) {
		this.nacinOtpreme = nacinOtpreme;
	}
	public Integer getPrijamnicaBr() {
		return prijamnicaBr;
	}
	public void setPrijamnicaBr(Integer prijamnicaBr) {
		this.prijamnicaBr = prijamnicaBr;
	}
	public Integer getOtpremnicaBr() {
		return otpremnicaBr;
	}
	public void setOtpremnicaBr(Integer otpremnicaBr) {
		this.otpremnicaBr = otpremnicaBr;
	}
	public Integer getMedjumagacinskiPrometBr() {
		return medjumagacinskiPrometBr;
	}
	public void setMedjumagacinskiPrometBr(Integer medjumagacinskiPrometBr) {
		this.medjumagacinskiPrometBr = medjumagacinskiPrometBr;
	}
	public Date getDatumIzdavanja() {
		return datumIzdavanja;
	}
	public void setDatumIzdavanja(Date datumIzdavanja) {
		this.datumIzdavanja = datumIzdavanja;
	}
	public String getVrstaDokumenta() {
		return vrstaDokumenta;
	}
	public void setVrstaDokumenta(String vrstaDokumenta) {
		this.vrstaDokumenta = vrstaDokumenta;
	}
	public String getRobuIzdao() {
		return robuIzdao;
	}
	public void setRobuIzdao(String robuIzdao) {
		this.robuIzdao = robuIzdao;
	}
	public String getRobuPrimio() {
		return robuPrimio;
	}
	public void setRobuPrimio(String robuPrimio) {
		this.robuPrimio = robuPrimio;
	}
}
