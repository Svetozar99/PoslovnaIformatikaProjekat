package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.entityDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.PrometniDokument;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.VrstaDokumenta;

public class PrometniDokumentDTO {

	private Integer id;
	private Integer sifraPoslovnogPartnera;
	private Integer idPreduzeca;
	private Integer sifraMagacina1;
	private Integer sifraMagacina2;
	private String brojPrometnogDokumenta;
	private Date datumIzdavanja;
	private String vrstaDokumenta;
	private List<StavkaDokumentaDTO> stavkeDTO = new ArrayList<StavkaDokumentaDTO>();
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSifraPoslovnogPartnera() {
		return sifraPoslovnogPartnera;
	}
	public void setSifraPoslovnogPartnera(Integer sifraPoslovnogPartnera) {
		this.sifraPoslovnogPartnera = sifraPoslovnogPartnera;
	}
	public Integer getIdPreduzeca() {
		return idPreduzeca;
	}
	public void setIdPreduzeca(Integer idPreduzeca) {
		this.idPreduzeca = idPreduzeca;
	}
	public Integer getSifraMagacina1() {
		return sifraMagacina1;
	}
	public void setSifraMagacina1(Integer sifraMagacina1) {
		this.sifraMagacina1 = sifraMagacina1;
	}
	public Integer getSifraMagacina2() {
		return sifraMagacina2;
	}
	public void setSifraMagacina2(Integer sifraMagacina2) {
		this.sifraMagacina2 = sifraMagacina2;
	}
	public String getBrojPrometnogDokumenta() {
		return brojPrometnogDokumenta;
	}
	public void setBrojPrometnogDokumenta(String brojPrometnogDokumenta) {
		this.brojPrometnogDokumenta = brojPrometnogDokumenta;
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
	
	public List<StavkaDokumentaDTO> getStavkeDTO() {
		return stavkeDTO;
	}
	public void setStavkeDTO(List<StavkaDokumentaDTO> stavkeDTO) {
		this.stavkeDTO = stavkeDTO;
	}
	@Override
	public String toString() {
		return "PrometniDokumentDTO [id=" + id + ", sifraPoslovnogPartnera=" + sifraPoslovnogPartnera + ", idPreduzeca="
				+ idPreduzeca + ", sifraMagacina1=" + sifraMagacina1 + ", sifraMagacina2=" + sifraMagacina2
				+ ", brojPrometnogDokumenta=" + brojPrometnogDokumenta + ", datumIzdavanja=" + datumIzdavanja
				+ ", vrstaDokumenta=" + vrstaDokumenta + "]";
	}
	
	
}
