package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.controller;

import java.util.Calendar;
import java.util.Date;
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
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.Magacin;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.MagacinskaKartica;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.PoslovnaGodina;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.PoslovniPartner;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.Preduzece;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.PrometniDokument;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.Status;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.VrstaDokumenta;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface.MagacinServiceInterface;
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
	private PreduzeceServiceInterface preduzeceServiceInterface;
	
	@Autowired
	private MagacinServiceInterface magacinServiceInterface;
	
	@PostMapping
	public ResponseEntity<PrometniDokumentDTO> addPrometniDokument(@RequestBody PrometniDokumentDTO dto){
		System.out.println("\n\tPost!");
		System.out.println(dto.toString());
		
		PoslovniPartner poslovniPartner = new PoslovniPartner();
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		PoslovnaGodina poslovnaGodina = poslovnaGodinaServiceInterface.findByBrojGodine(calendar.get(Calendar.YEAR));
		Preduzece preduzece = new Preduzece();
		Magacin ulazniMagacin = magacinServiceInterface.findOne(dto.getSifraMagacina1());
		Magacin izlazniMagacin = magacinServiceInterface.findOne(dto.getSifraMagacina2());
		
		PrometniDokument prometniDokument = new PrometniDokument();
		prometniDokument.setRedniBroj(dto.getBrojPrometnogDokumenta());
		prometniDokument.setDatum(dto.getDatumIzdavanja());
		prometniDokument.setStatus(Status.P);
		prometniDokument.setPoslovnaGodina(poslovnaGodina);
		
		if(dto.getVrstaDokumenta().equals(VrstaDokumenta.PR.toString())) {
			poslovniPartner = poslovniPartnerServiceInterface.findOneBySifraPartnera(dto.getSifraPoslovnogPartnera());
			preduzece = preduzeceServiceInterface.findOne(dto.getIdPreduzeca());
			prometniDokument.setVrstaDokumenta(VrstaDokumenta.PR);
			prometniDokument.setPoslovniPartner(poslovniPartner);
			prometniDokument.setPreduzece(preduzece);
			prometniDokument.setUlazniMagacin(ulazniMagacin);
		}else if(dto.getVrstaDokumenta().equals(VrstaDokumenta.OT.toString())) {
			poslovniPartner = poslovniPartnerServiceInterface.findOneBySifraPartnera(dto.getSifraPoslovnogPartnera());
			preduzece = preduzeceServiceInterface.findOne(dto.getIdPreduzeca());
			prometniDokument.setVrstaDokumenta(VrstaDokumenta.OT);
			prometniDokument.setPoslovniPartner(poslovniPartner);
			prometniDokument.setPreduzece(preduzece);
			prometniDokument.setIzlazniMagacin(izlazniMagacin);
		}
		else if(dto.getVrstaDokumenta().equals(VrstaDokumenta.MM.toString())) {
			prometniDokument.setVrstaDokumenta(VrstaDokumenta.MM);
			prometniDokument.setIzlazniMagacin(izlazniMagacin);
			prometniDokument.setUlazniMagacin(ulazniMagacin);
		}
		prometniDokument = prometniDokumentServiceInterface.save(prometniDokument);
		dto.setId(prometniDokument.getId());
		return new ResponseEntity<PrometniDokumentDTO>(dto, HttpStatus.OK);
	}
}
