package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.entityDTO.MagacinDTO;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.entityDTO.StavkaDokumentaDTO;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.Magacin;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.MagacinskaKartica;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.Preduzece;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.PrometMagacinskeKartice;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.PrometniDokument;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.RobaIliUsluga;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.Smer;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.StavkaDokumenta;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.VrstaDokumenta;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.VrstaPrometa;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.service.PrometMagacinskeKarticeInterface;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface.MagacinskaKarticaServiceInterface;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface.PrometniDokumentServiceInterface;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface.RobaIliUslugaServiceInterface;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface.StavkaDokumentaServiceInterface;

@RestController
@RequestMapping(value = "api/stavka-dokumenta")
public class StavkaDokumentaController {

	@Autowired
	private StavkaDokumentaServiceInterface stavkaDokumentaServiceInterface;
	
	@Autowired
	private PrometniDokumentServiceInterface prometniDokumentServiceInterface;
	
	@Autowired
	private RobaIliUslugaServiceInterface robaIliUslugaServiceInterface;
	
	@Autowired
	private MagacinskaKarticaServiceInterface magacinskaKarticaServiceInterface;
	
	@Autowired
	private PrometMagacinskeKarticeInterface prometMagacinskeKarticeInterface;
	
	@GetMapping
	public ResponseEntity<List<StavkaDokumentaDTO>> getStavkeDokumenta(){
		List<StavkaDokumenta> stavkaDokumentas = stavkaDokumentaServiceInterface.findAll();
		
		List<StavkaDokumentaDTO> stavkaDokumentaDTO = new ArrayList<StavkaDokumentaDTO>();
		for(StavkaDokumenta std: stavkaDokumentas) {
			stavkaDokumentaDTO.add(new StavkaDokumentaDTO(std));
		}
		return new ResponseEntity<List<StavkaDokumentaDTO>>(stavkaDokumentaDTO, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<StavkaDokumentaDTO> getStavkaDokumenta(@PathVariable("id") Integer id){
		StavkaDokumenta stavkaDokumenta = stavkaDokumentaServiceInterface.findById(id);
		
		if(stavkaDokumenta == null) {
			return new ResponseEntity<StavkaDokumentaDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<StavkaDokumentaDTO>(new StavkaDokumentaDTO(stavkaDokumenta), HttpStatus.OK);
	}
	
//	@GetMapping(value = "prometni-dokumet/{id}")
//	public ResponseEntity<List<StavkaDokumentaDTO>> getstavkeDocByPromDoc(@PathVariable("id") Integer id){
//		List<StavkaDokumenta> stavkeDokumenta = stavkaDokumentaServiceInterface.findByPrometniDokument_id(id);
//		List<StavkaDokumentaDTO> sdtos = new ArrayList<StavkaDokumentaDTO>();
//		for (StavkaDokumenta sdto : stavkeDokumenta) {
//			StavkaDokumentaDTO s = new StavkaDokumentaDTO(sdto);
//			sdtos.add(s);
//		}
//		return new ResponseEntity<List<StavkaDokumentaDTO>>(sdtos, HttpStatus.OK);
//	}
	
	@GetMapping(value = "roba-ili-usluga/{id}")
	public ResponseEntity<List<StavkaDokumentaDTO>> getStavkeDocByRobaIliUsluga(@PathVariable("id") Integer id){
		List<StavkaDokumenta> stavkeDokumenta = stavkaDokumentaServiceInterface.findByRobaIliUsluga_sifra(id);
		List<StavkaDokumentaDTO> sdtos = new ArrayList<StavkaDokumentaDTO>();
		for (StavkaDokumenta sdto : stavkeDokumenta) {
			StavkaDokumentaDTO s = new StavkaDokumentaDTO(sdto);
			sdtos.add(s);
		}
		return new ResponseEntity<List<StavkaDokumentaDTO>>(sdtos, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<List<StavkaDokumentaDTO>> addStavke(@RequestBody List<StavkaDokumentaDTO> dtos) throws Exception{
		System.out.println("\n\taddStavke");
		MagacinskaKartica kartica = new MagacinskaKartica();
		MagacinskaKartica kartica2 = new MagacinskaKartica();
		for (StavkaDokumentaDTO stavkaDokumentaDTO : dtos) {
			PrometMagacinskeKartice prometKartice = new PrometMagacinskeKartice();
			PrometMagacinskeKartice prometKartice2 = new PrometMagacinskeKartice();
			
			PrometniDokument pd = prometniDokumentServiceInterface.findOneById(stavkaDokumentaDTO.getPrometniDokument());
			
			RobaIliUsluga ru = robaIliUslugaServiceInterface.findOneBySifra(stavkaDokumentaDTO.getRobaUsluga());
			StavkaDokumenta stavkaDokumenta = new StavkaDokumenta();
			System.out.println(stavkaDokumentaDTO.toString());
			stavkaDokumenta.setIdStavke(0);
			stavkaDokumenta.setKolicina(stavkaDokumentaDTO.getKolicina());
			stavkaDokumenta.setCena(stavkaDokumentaDTO.getCena());
			stavkaDokumenta.setVrednost(stavkaDokumentaDTO.getVrednost());
			stavkaDokumenta.setPrometniDokument(pd);
			stavkaDokumenta.setRobaIliUsluga(ru);
			stavkaDokumenta = stavkaDokumentaServiceInterface.save(stavkaDokumenta);
			
			//Postavljanje id za promet magacinske kartice
			prometKartice.setRedniBroj(pd.getRedniBroj());
			prometKartice2.setRedniBroj(pd.getRedniBroj());
			
			//Postavljanje kolicine za promet magacinske kartice
			prometKartice.setKolicina(stavkaDokumenta.getKolicina());
			prometKartice2.setKolicina(stavkaDokumenta.getKolicina());
			
			//Postavljanje cene za promet magacinske kartice
			prometKartice.setCena(stavkaDokumenta.getCena());
			prometKartice2.setCena(stavkaDokumenta.getCena());
			
			//Postavljanje vrednost za promet magacinske kartice
			prometKartice.setVrednost(stavkaDokumenta.getVrednost());
			prometKartice2.setVrednost(stavkaDokumenta.getVrednost());
			
			//Postavljanje vrednost za promet magacinske kartice
			prometKartice.setDatumPrometa(pd.getDatum());
			prometKartice2.setDatumPrometa(pd.getDatum());
			
			if(pd.getVrstaDokumenta().equals(VrstaDokumenta.PR)) {
				//Trazenje kartice
				kartica = magacinskaKarticaServiceInterface.findOneByRobaIliUslugaAndPoslovnaGodinaAndMagacin(ru.getSifra(), pd.getPoslovnaGodina().getBrojGodine(), pd.getUlazniMagacin().getSifraMagacina());
				
				//Postavljanje ukupne cene
				double ukupnaCena = (kartica.getUkupnaVrednost()+stavkaDokumenta.getVrednost())/(kartica.getUkupnaKolicina()+stavkaDokumenta.getKolicina());
				kartica.setCena(ukupnaCena);
				
				//Postavljanje kolicine
				kartica.setPrometUlazaKolicinski(kartica.getPrometUlazaKolicinski()+stavkaDokumenta.getKolicina());
				double ukupnaKolicina = kartica.getPocetnoStanjeKolicinski()+kartica.getPrometUlazaKolicinski()-kartica.getPrometIzlazaKolicinski();
				try {
					kartica.setUkupnaKolicina(ukupnaKolicina);
				} catch (Exception e) {
					throw new ResponseStatusException(
					          HttpStatus.NOT_FOUND, "Nema dovoljno robe u magacinu", e);
				}
				
				//Postavljanje vrednosti
				kartica.setPrometUlazaVrednosno(kartica.getPrometUlazaVrednosno()+stavkaDokumenta.getVrednost());
				double ukupnaVrednost = kartica.getPocetnoStanjeVrednosno()+kartica.getPrometUlazaVrednosno()-kartica.getPrometIzlazaVrednosno();
				kartica.setUkupnaVrednost(ukupnaVrednost);
				
				//Postavljanje vrste prometa za promet magacinske kartice
				prometKartice.setVrstaPrometa(VrstaPrometa.PR);
				
				//Postavljanje smera za promet magacinske kartice
				prometKartice.setSmer(Smer.U);
				
				//Postavljanje naziva dokumenta za promet magacinske kartice
				prometKartice.setDokument(pd.getVrstaDokumenta().label);
				
				//Pisanje u bazu
				kartica = magacinskaKarticaServiceInterface.save(kartica);
				
				//Postavljanje magacinske kartice za promet
				prometKartice.setMagacinskaKartica(kartica);
				System.out.println("\n\n\tCuvam sledeci promet:\n"+prometKartice.toString()+"\n");

				//Cuvanje prometa
				prometMagacinskeKarticeInterface.save(prometKartice);
			}else if(pd.getVrstaDokumenta().equals(VrstaDokumenta.OT)) {
				//Trazenje kartice
				kartica = magacinskaKarticaServiceInterface.findOneByRobaIliUslugaAndPoslovnaGodinaAndMagacin(ru.getSifra(), pd.getPoslovnaGodina().getBrojGodine(), pd.getIzlazniMagacin().getSifraMagacina());
				
				//Postavljanje ukupne cene
				double ukupnaCena = (kartica.getUkupnaVrednost()+stavkaDokumenta.getVrednost())/(kartica.getUkupnaKolicina()+stavkaDokumenta.getKolicina());
				kartica.setCena(ukupnaCena);
				
				//Postavljanje kolicine
				kartica.setPrometIzlazaKolicinski(kartica.getPrometIzlazaKolicinski()+stavkaDokumenta.getKolicina());
				double ukupnaKolicina = kartica.getPocetnoStanjeKolicinski()+kartica.getPrometUlazaKolicinski()-kartica.getPrometIzlazaKolicinski();
				try {
					kartica.setUkupnaKolicina(ukupnaKolicina);
				} catch (Exception e) {
					throw new ResponseStatusException(
					          HttpStatus.NOT_FOUND, "Nema dovoljno robe u magacinu", e);
				}
				
				//Postavljanje vrednosti
				kartica.setPrometIzlazaVrednosno(kartica.getPrometIzlazaVrednosno()+stavkaDokumenta.getVrednost());
				double ukupnaVrednost = kartica.getPocetnoStanjeVrednosno()+kartica.getPrometUlazaVrednosno()-kartica.getPrometIzlazaVrednosno();
				kartica.setUkupnaVrednost(ukupnaVrednost);

				//Postavljanje vrste prometa za promet magacinske kartice
				prometKartice.setVrstaPrometa(VrstaPrometa.OT);
				
				//Postavljanje smera za promet magacinske kartice
				prometKartice.setSmer(Smer.I);
				
				//Postavljanje naziva dokumenta za promet magacinske kartice
				prometKartice.setDokument(pd.getVrstaDokumenta().label);
				
				//Pisanje u bazu
				kartica = magacinskaKarticaServiceInterface.save(kartica);
				
				//Postavljanje magacinske kartice za promet
				prometKartice.setMagacinskaKartica(kartica);
				System.out.println("\n\n\tCuvam sledeci promet:\n"+prometKartice.toString()+"\n");
				//Cuvanje prometa
				prometMagacinskeKarticeInterface.save(prometKartice);
			}else if(pd.getVrstaDokumenta().equals(VrstaDokumenta.MM)){
				//Trazenje kartice za prvi magacin
				kartica = magacinskaKarticaServiceInterface.findOneByRobaIliUslugaAndPoslovnaGodinaAndMagacin(ru.getSifra(), pd.getPoslovnaGodina().getBrojGodine(), pd.getUlazniMagacin().getSifraMagacina());
				//Trazenje kartice za drugi magacin
				kartica2 = magacinskaKarticaServiceInterface.findOneByRobaIliUslugaAndPoslovnaGodinaAndMagacin(ru.getSifra(), pd.getPoslovnaGodina().getBrojGodine(), pd.getIzlazniMagacin().getSifraMagacina());
				
				//Postavljanje ukupne cene
				double ukupnaCena = (kartica.getUkupnaVrednost()+stavkaDokumenta.getVrednost())/(kartica.getUkupnaKolicina()+stavkaDokumenta.getKolicina());
				kartica.setCena(ukupnaCena);
				double ukupnaCena2 = (kartica2.getUkupnaVrednost()+stavkaDokumenta.getVrednost())/(kartica2.getUkupnaKolicina()+stavkaDokumenta.getKolicina());
				kartica2.setCena(ukupnaCena2);
				
				//Postavljenje kolicine za magacine
				double izlazKolicinski = kartica.getPrometIzlazaKolicinski()+stavkaDokumenta.getKolicina();
				kartica.setPrometIzlazaKolicinski(izlazKolicinski);
				double ulazKolicinski = kartica2.getPrometUlazaKolicinski()+stavkaDokumenta.getKolicina();
				kartica2.setPrometUlazaKolicinski(ulazKolicinski);
				double ukupnaKolicina = kartica.getPocetnoStanjeKolicinski()+kartica.getPrometUlazaKolicinski()-kartica.getPrometIzlazaKolicinski();
				try {
					kartica.setUkupnaKolicina(ukupnaKolicina);
				} catch (Exception e) {
					throw new ResponseStatusException(
					          HttpStatus.NOT_FOUND, "Nema dovoljno robe u magacinu", e);
				}
				double ukupnaKolicina2 = kartica2.getPocetnoStanjeKolicinski()+kartica2.getPrometUlazaKolicinski()-kartica2.getPrometIzlazaKolicinski();
				kartica2.setUkupnaKolicina(ukupnaKolicina2);
				
				//Postavljenje vrednosti za magacine
				double izlazVrednosno = kartica.getPrometIzlazaVrednosno()+stavkaDokumenta.getVrednost();
				kartica.setPrometIzlazaVrednosno(izlazVrednosno);
				double ulazVrednosno = kartica2.getPrometUlazaVrednosno()+stavkaDokumenta.getVrednost();
				kartica2.setPrometUlazaVrednosno(ulazVrednosno);
				double ukupnaVrednost = kartica.getPocetnoStanjeVrednosno()+kartica.getPrometUlazaVrednosno()-kartica.getPrometIzlazaVrednosno();
				kartica.setUkupnaVrednost(ukupnaVrednost);
				double ukupnaVrednost2 = kartica2.getPocetnoStanjeVrednosno()+kartica2.getPrometUlazaVrednosno()-kartica2.getPrometIzlazaVrednosno();
				kartica2.setUkupnaVrednost(ukupnaVrednost2);
				
				//Postavljanje vrste prometa za promet magacinske kartice
				prometKartice.setVrstaPrometa(VrstaPrometa.MM);
				prometKartice2.setVrstaPrometa(VrstaPrometa.MM);
				
				//Postavljanje smera za promet magacinske kartice
				prometKartice.setSmer(Smer.I);
				prometKartice2.setSmer(Smer.U);
				
				//Postavljanje naziva dokumenta za promet magacinske kartice
				prometKartice.setDokument(pd.getVrstaDokumenta().label);
				prometKartice2.setDokument(pd.getVrstaDokumenta().label);
				
				//Pisanje magacinski kartica u bazu
				kartica = magacinskaKarticaServiceInterface.save(kartica);
				kartica2 = magacinskaKarticaServiceInterface.save(kartica2);
				
				//Postavljanje magacinske kartice za promet
				prometKartice.setMagacinskaKartica(kartica);
				prometKartice2.setMagacinskaKartica(kartica2);
				System.out.println("\n\n\tCuvam sledeci promet:\n"+prometKartice.toString()+"\n");
				System.out.println("\n\n\tCuvam sledeci promet:\n"+prometKartice2.toString()+"\n");

				//Cuvanje prometa
				prometMagacinskeKarticeInterface.save(prometKartice);
				prometMagacinskeKarticeInterface.save(prometKartice2);
			}
		}
		return new ResponseEntity<List<StavkaDokumentaDTO>>(dtos, HttpStatus.CREATED);
	}
}
