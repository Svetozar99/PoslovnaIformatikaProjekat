package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.entityDTO.JedinicaMereDTO;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.entityDTO.PrometniDokumentDTO;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.MagacinskaKartica;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.PoslovnaGodina;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.PoslovniPartner;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.Preduzece;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.PrometniDokument;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.Status;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.VrstaDokumenta;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface.MagacinskaKarticaServiceInterface;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface.PoslovnaGodinaServiceInterface;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface.PoslovniPartnerServiceInterface;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface.PreduzeceServiceInterface;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface.PrometniDokumentServiceInterface;

@RestController
@RequestMapping(value = "api/prometni-dokument")
public class PrometniDokumentController {

	@Autowired
	private PrometniDokumentServiceInterface prometniDokumentServiceInterface;
	
	@Autowired
	private PoslovniPartnerServiceInterface poslovniPartnerServiceInterface;
	
	@Autowired
	private PoslovnaGodinaServiceInterface poslovnaGodinaServiceInterface;
	
	@Autowired
	private MagacinskaKarticaServiceInterface magacinskaKarticaServiceInterface;
	
	@PostMapping
	public ResponseEntity<PrometniDokumentDTO> addPrometniDokument(@RequestBody PrometniDokumentDTO dto){
		System.out.println("\n\tPost!");
		PrometniDokument prometniDokument = new PrometniDokument();
		PoslovniPartner poslovniPartner = poslovniPartnerServiceInterface.findOneBySifraPartnera(dto.getIdDobavljaca());
		PoslovnaGodina poslovnaGodina = poslovnaGodinaServiceInterface.findByBrojGodine(1);
		MagacinskaKartica magacinskaKartica = magacinskaKarticaServiceInterface.findOneById(1);
		if(dto.getVrstaDokumenta().equals(VrstaDokumenta.PR.toString())) {
			prometniDokument.setRedniBroj(dto.getPrijamnicaBr());
			prometniDokument.setVrstaDokumenta(VrstaDokumenta.PR);
			prometniDokument.setDatum(dto.getDatumIzdavanja());
			prometniDokument.setStatus(Status.P);
			prometniDokument.setPoslovniPartner(poslovniPartner);
			prometniDokument.setPoslovnaGodina(poslovnaGodina);
			prometniDokument.setMagacinskaKartica(magacinskaKartica);
		}
		prometniDokument = prometniDokumentServiceInterface.save(prometniDokument);
		return new ResponseEntity<JedinicaMereDTO>(new PrometniDokumentDTO(prometniDokument), HttpStatus.OK);
	}
}
