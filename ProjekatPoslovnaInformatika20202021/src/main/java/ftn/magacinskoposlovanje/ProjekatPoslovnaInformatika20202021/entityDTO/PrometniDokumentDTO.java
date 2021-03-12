package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.entityDTO;

import java.util.Date;

import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.PrometniDokument;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.VrstaDokumenta;

public class PrometniDokumentDTO {

	private Integer sifraPoslovnogPartnera;
	private Integer idPreduzeca;
	private Integer idMagacina1;
	private Integer idMagacina2;
	private String mestoIzdavanjaRobe;
	private String nacinOtpreme;
	private Integer brojPrometnogDokumenta;
	private Date datumIzdavanja;
	private String vrstaDokumenta;
	private String robuIzdao;
	private String robuPrimio;
	
	public PrometniDokumentDTO(Integer idPoslovnogPartnera, Integer idPreduzeca, Integer idProdavca, Integer idMagacina1,
			Integer idMagacina2, String mestoIzdavanjaRobe, String nacinOtpreme, Integer brojPrometnogDokumenta,
			Integer otpremnicaBr, Integer medjumagacinskiPrometBr, Date datumIzdavanja, String vrstaDokumenta,
			String robuIzdao, String robuPrimio) {
		super();
		this.sifraPoslovnogPartnera = idPoslovnogPartnera;
		this.idPreduzeca = idPreduzeca;
		this.idMagacina1 = idMagacina1;
		this.idMagacina2 = idMagacina2;
		this.mestoIzdavanjaRobe = mestoIzdavanjaRobe;
		this.nacinOtpreme = nacinOtpreme;
		this.brojPrometnogDokumenta = brojPrometnogDokumenta;
		this.datumIzdavanja = datumIzdavanja;
		this.vrstaDokumenta = vrstaDokumenta;
		this.robuIzdao = robuIzdao;
		this.robuPrimio = robuPrimio;
	}
	
	public PrometniDokumentDTO(PrometniDokument prometniDokument) {
		this(prometniDokument.getPoslovniPartner().getSifraPartnera(),prometniDokument.getPreduzece().getIdPreduzeca(),prometniDokument.getMagacin().getId,);
	}
	
	public Integer getIdPoslovnogPartnera() {
		return sifraPoslovnogPartnera;
	}
	public void setIdPoslovnogPartnera(Integer idPoslovnogPartnera) {
		this.sifraPoslovnogPartnera = idPoslovnogPartnera;
	}
	public Integer getIdPreduzeca() {
		return idPreduzeca;
	}
	public void setIdPreduzeca(Integer idPreduzeca) {
		this.idPreduzeca = idPreduzeca;
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
