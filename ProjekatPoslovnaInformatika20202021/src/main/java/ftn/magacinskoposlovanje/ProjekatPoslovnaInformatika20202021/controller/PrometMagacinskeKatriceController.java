package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.entityDTO.MagacinskaKarticaDTO;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.entityDTO.PreduzeceDTO;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.entityDTO.PrometMagacinskeKarticeDTO;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.MagacinskaKartica;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.Preduzece;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.PrometMagacinskeKartice;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.PrometniDokument;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.Status;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface.MagacinskaKarticaServiceInterface;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface.PrometMagacinskeKarticeServiceInterface;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface.PrometniDokumentServiceInterface;

@RestController
@RequestMapping(value = "api/promet-magacinske-kartice")
public class PrometMagacinskeKatriceController {
	
	@Autowired
	private PrometMagacinskeKarticeServiceInterface prometMagacinskeKarticeService;
	
	@Autowired
	private MagacinskaKarticaServiceInterface magacinskaKarticaServiceInterface;
	
	@Autowired
	private PrometniDokumentServiceInterface prometniDokumentServiceInterface;
	
	@GetMapping
	public ResponseEntity<List<PrometMagacinskeKarticeDTO>> getPrometiMagKart(){
		List<PrometMagacinskeKartice> prometiMagacinskeKartice = prometMagacinskeKarticeService.findAll();
		
		List<PrometMagacinskeKarticeDTO> karticeDTOs= new ArrayList<PrometMagacinskeKarticeDTO>();
		for(PrometMagacinskeKartice p: prometiMagacinskeKartice) {
			karticeDTOs.add(new PrometMagacinskeKarticeDTO(p));
		}
		return new ResponseEntity<List<PrometMagacinskeKarticeDTO>>(karticeDTOs, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{idKartice}")
	public ResponseEntity<List<PrometMagacinskeKarticeDTO>> getPrometiMagKartByKartica(@PathVariable ("idKartice") Integer idKartice){
		System.out.println("Trazim prometne dokumete za karticu");
		MagacinskaKartica magacinskaKartica = magacinskaKarticaServiceInterface.findOneById(idKartice);
		List<PrometMagacinskeKartice> prometiMagacinskeKartice = prometMagacinskeKarticeService.findByMagacinskaKartica(idKartice);
		
		List<PrometMagacinskeKarticeDTO> karticeDTOs= new ArrayList<PrometMagacinskeKarticeDTO>();
		for(PrometMagacinskeKartice p: prometiMagacinskeKartice) {
			PrometMagacinskeKarticeDTO dto = new PrometMagacinskeKarticeDTO(p);
			dto.setJedinicaMere(magacinskaKartica.getRobaIliUsluga().getJedinicaMere().getSkraceniNaziv());
			karticeDTOs.add(dto);
		}
		return new ResponseEntity<List<PrometMagacinskeKarticeDTO>>(karticeDTOs, HttpStatus.OK);
	}
	
	@GetMapping(value = "storniraj/{redniBroj}")
	public ResponseEntity<PrometMagacinskeKarticeDTO> getOnePromet(@PathVariable("redniBroj") String redniBroj) throws Exception{
		System.out.println("\n\n\tRedni broj: "+redniBroj);
		PrometMagacinskeKartice promMK = prometMagacinskeKarticeService.findOne(redniBroj);
		if(promMK == null) {
			return new ResponseEntity<PrometMagacinskeKarticeDTO>(HttpStatus.NOT_FOUND);
		}
		
		MagacinskaKartica mk = promMK.getMagacinskaKartica();
		
		mk.setUkupnaKolicina(mk.getUkupnaKolicina() - promMK.getKolicina());
		mk.setUkupnaVrednost(mk.getUkupnaVrednost() - promMK.getVrednost());
		mk = magacinskaKarticaServiceInterface.save(mk);
		PrometMagacinskeKartice prmkst = new PrometMagacinskeKartice();
		prmkst.setRedniBroj(redniBroj);
		prmkst.setVrstaPrometa(promMK.getVrstaPrometa());
		prmkst.setSmer(promMK.getSmer());
		prmkst.setKolicina(mk.getUkupnaKolicina()-promMK.getKolicina());
		prmkst.setCena(promMK.getCena());
		prmkst.setVrednost(mk.getUkupnaVrednost()-promMK.getVrednost());
		prmkst.setMagacinskaKartica(mk);
		prmkst.setDatumPrometa(promMK.getDatumPrometa());
		prmkst.setDokument(promMK.getDokument());
		prmkst = prometMagacinskeKarticeService.save(prmkst);
		
		PrometniDokument prometniDokument = prometniDokumentServiceInterface.findOneByRedniBroj(redniBroj);
		prometniDokument.setStatus(Status.S);
		prometniDokumentServiceInterface.save(prometniDokument);
		return new ResponseEntity<PrometMagacinskeKarticeDTO>(new PrometMagacinskeKarticeDTO(prmkst), HttpStatus.OK);
	}
	
	
}
