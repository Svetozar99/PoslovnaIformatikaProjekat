package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.decimal4j.util.DoubleRounder;
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
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.Smer;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.Status;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.VrstaPrometa;
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
		List<PrometMagacinskeKartice> prometi = prometMagacinskeKarticeService.findByRedniBroj(redniBroj);
		System.out.println("\nBroj prometa u listi: "+prometi.size());
		if(prometi.size() == 0) {
			return new ResponseEntity<PrometMagacinskeKarticeDTO>(HttpStatus.NOT_FOUND);
		}else {
			for (PrometMagacinskeKartice promet : prometi) {
				System.out.println(promet.toString());
				MagacinskaKartica mk = magacinskaKarticaServiceInterface.findOneById(promet.getMagacinskaKartica().getId());
				
				if(promet.getSmer().equals(Smer.U)) {
					System.out.println("\nUlaz");
					mk.setPrometUlazaKolicinski(mk.getPrometUlazaKolicinski()-promet.getKolicina());
					
					mk.setPrometUlazaVrednosno(mk.getPrometUlazaVrednosno()-promet.getVrednost());
					
					double ukupnaKolicina = mk.getPocetnoStanjeKolicinski()+mk.getPrometUlazaKolicinski()-mk.getPrometIzlazaKolicinski();
					mk.setUkupnaKolicina(ukupnaKolicina);
					
					double ukupnaVrednost = mk.getPocetnoStanjeVrednosno()+mk.getPrometUlazaVrednosno()-mk.getPrometIzlazaVrednosno();
					mk.setUkupnaVrednost(ukupnaVrednost);
					
					if(mk.getUkupnaKolicina()!=0) {
						mk.setCena(DoubleRounder.round(mk.getUkupnaVrednost()/mk.getUkupnaKolicina(), 2));
					}else {
						mk.setCena(0);
					}
					
					mk = magacinskaKarticaServiceInterface.save(mk);
					
					PrometMagacinskeKartice prmkst = new PrometMagacinskeKartice();
					prmkst.setRedniBroj(redniBroj);
					prmkst.setVrstaPrometa(promet.getVrstaPrometa());
					prmkst.setSmer(promet.getSmer());
					prmkst.setKolicina(-promet.getKolicina());
					prmkst.setCena(promet.getCena());
					prmkst.setVrednost(-promet.getVrednost());
					prmkst.setMagacinskaKartica(mk);
					prmkst.setDatumPrometa(new Date());
					prmkst.setDokument(promet.getDokument());
					prmkst = prometMagacinskeKarticeService.save(prmkst);
				}else if(promet.getSmer().equals(Smer.I)){
					System.out.println("\nStorniram otpremnicu ili magacin koji je imao izlaz!");
					mk.setPrometIzlazaKolicinski(mk.getPrometIzlazaKolicinski()-promet.getKolicina());
					mk.setPrometIzlazaVrednosno(mk.getPrometIzlazaVrednosno()-promet.getVrednost());
					double ukupnaKolicina = mk.getPocetnoStanjeKolicinski()+mk.getPrometUlazaKolicinski()-mk.getPrometIzlazaKolicinski();
					mk.setUkupnaKolicina(ukupnaKolicina);
					
					double ukupnaVrednost = mk.getPocetnoStanjeVrednosno()+mk.getPrometUlazaVrednosno()-mk.getPrometIzlazaVrednosno();
					mk.setUkupnaVrednost(ukupnaVrednost);
					if(mk.getUkupnaKolicina()!=0) {
						mk.setCena(DoubleRounder.round(mk.getUkupnaVrednost()/mk.getUkupnaKolicina(), 2));
					}else {
						mk.setCena(0);
					}
					mk = magacinskaKarticaServiceInterface.save(mk);
					
					PrometMagacinskeKartice prmkst = new PrometMagacinskeKartice();
					prmkst.setRedniBroj(redniBroj);
					prmkst.setVrstaPrometa(promet.getVrstaPrometa());
					prmkst.setSmer(promet.getSmer());
					prmkst.setKolicina(-promet.getKolicina());
					prmkst.setCena(promet.getCena());
					prmkst.setVrednost(-promet.getVrednost());
					prmkst.setMagacinskaKartica(mk);
					prmkst.setDatumPrometa(new Date());
					prmkst.setDokument(promet.getDokument());
					System.out.println("\nLinija koda pre samog cuvanja prometa magacinske kartice");
					prmkst = prometMagacinskeKarticeService.save(prmkst);
				}
			}
		}
		
		
		List<PrometniDokument> prometniDokumenti = prometniDokumentServiceInterface.findByRedniBroj(redniBroj);
		System.out.println("\n"+prometniDokumenti.size());
		for (PrometniDokument prometniDokument : prometniDokumenti) {
			prometniDokument.setStatus(Status.S);
			prometniDokumentServiceInterface.save(prometniDokument);
		}
		return ResponseEntity.ok().build();
	}
	
	
}
