package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.entityDTO.PrometniDokumentDTO;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.Preduzece;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.PrometniDokument;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.Status;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.VrstaDokumenta;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface.PreduzeceServiceInterface;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface.PrometniDokumentServiceInterface;

@RestController
@RequestMapping(value = "api/prometni-dokument")
public class PrometniDokumentController {

	@Autowired
	private PrometniDokumentServiceInterface prometniDokumentServiceInterface;
	
	@Autowired
	private PreduzeceServiceInterface preduzeceServiceInterface;
	
	@PostMapping
	public ResponseEntity<PrometniDokument> addPrometniDokument(@RequestBody PrometniDokumentDTO dto){
		PrometniDokument prometniDokument = new PrometniDokument();
		Preduzece poslovniPartner = preduzeceServiceInterface.findById(dto.getIdDobavljaca());
		if(dto.getVrstaDokumenta().equals(VrstaDokumenta.PR.toString())) {
			prometniDokument.setRedniBroj(dto.getPrijamnicaBr());
			prometniDokument.setVrstaDokumenta(VrstaDokumenta.PR);
			prometniDokument.setDatum(dto.getDatumIzdavanja());
			prometniDokument.setStatus(Status.P);
		}
		return null;
	}
}
