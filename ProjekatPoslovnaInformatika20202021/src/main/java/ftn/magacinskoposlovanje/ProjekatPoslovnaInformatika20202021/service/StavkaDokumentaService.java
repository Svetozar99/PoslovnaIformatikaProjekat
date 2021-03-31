package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.service;

import java.util.List;

import org.decimal4j.util.DoubleRounder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.entityDTO.StavkaDokumentaDTO;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.MagacinskaKartica;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.PrometMagacinskeKartice;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.PrometniDokument;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.RobaIliUsluga;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.Smer;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.StavkaDokumenta;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.VrstaDokumenta;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.VrstaPrometa;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.repository.MagacinskaKarticaRepository;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.repository.PrometMagacinskeKarticeRepository;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.repository.PrometniDokumentRepository;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.repository.RobaIliUslugaRepository;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.repository.StavkaDokumentaRepository;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface.MagacinskaKarticaServiceInterface;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface.PrometniDokumentServiceInterface;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface.StavkaDokumentaServiceInterface;

@Service
public class StavkaDokumentaService implements StavkaDokumentaServiceInterface {

	@Autowired
	StavkaDokumentaRepository stavkaDokumentaRepository;
	
	@Autowired
	PrometniDokumentRepository dokumentR;
	
	@Autowired
	RobaIliUslugaRepository robaUslugaR;
	
	@Autowired
	MagacinskaKarticaRepository karticaR;
	
	@Autowired
	PrometMagacinskeKarticeRepository prometR;
	
	@Autowired
	MagacinskaKarticaServiceInterface karticaS;
	
	@Override
	public List<StavkaDokumenta> findAll() {
		return stavkaDokumentaRepository.findAll();
	}

	@Override
	public StavkaDokumenta findOne(Integer id) {
		return stavkaDokumentaRepository.getOne(id);
	}

	@Override
	public List<StavkaDokumentaDTO> save(List<StavkaDokumentaDTO> dtos) throws Exception {
		System.out.println("\n\taddStavke");
		MagacinskaKartica kartica = new MagacinskaKartica();
		MagacinskaKartica kartica2 = new MagacinskaKartica();
		for (StavkaDokumentaDTO stavkaDokumentaDTO : dtos) {
			
			if(stavkaDokumentaDTO.getRobaUsluga()==null) {
				continue;
			}
			
			PrometMagacinskeKartice prometKartice = new PrometMagacinskeKartice();
			PrometMagacinskeKartice prometKartice2 = new PrometMagacinskeKartice();
			
			PrometniDokument pd = dokumentR.findOneById(stavkaDokumentaDTO.getPrometniDokument());
			
			RobaIliUsluga ru = robaUslugaR.findOneBySifra(stavkaDokumentaDTO.getRobaUsluga());
			StavkaDokumenta stavkaDokumenta = new StavkaDokumenta();
			
			stavkaDokumenta.setIdStavke(0);
			stavkaDokumenta.setKolicina(stavkaDokumentaDTO.getKolicina());
			stavkaDokumenta.setCena(stavkaDokumentaDTO.getCena());
			stavkaDokumenta.setVrednost(stavkaDokumentaDTO.getVrednost());
			stavkaDokumenta.setPrometniDokument(pd);
			stavkaDokumenta.setRobaIliUsluga(ru);
			stavkaDokumenta = stavkaDokumentaRepository.save(stavkaDokumenta);
			stavkaDokumentaDTO.setId(stavkaDokumenta.getIdStavke());
			//Postavljanje id za promet magacinske kartice
			System.out.println("Redni broj: "+pd.getRedniBroj());
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
				System.out.println("\n"+pd.getVrstaDokumenta().label+"\n");
				//Trazenje kartice
				kartica = karticaS.findOneByRobaIliUslugaAndPoslovnaGodinaAndMagacin(ru.getSifra(), pd.getPoslovnaGodina().getBrojGodine(), pd.getUlazniMagacin().getSifraMagacina());
				System.out.println("\n"+kartica.toString()+"\n");
				//Postavljanje ukupne cene
				double ukupnaCena = (kartica.getUkupnaVrednost()+stavkaDokumenta.getVrednost())/(kartica.getUkupnaKolicina()+stavkaDokumenta.getKolicina());
				System.out.println("\n"+"2"+"\n");
				kartica.setCena(DoubleRounder.round(ukupnaCena, 2));
				//Postavljanje kolicine
				kartica.setPrometUlazaKolicinski(kartica.getPrometUlazaKolicinski()+stavkaDokumenta.getKolicina());
				double ukupnaKolicina = kartica.getPocetnoStanjeKolicinski()+kartica.getPrometUlazaKolicinski()-kartica.getPrometIzlazaKolicinski();
				System.out.println("\nUkupna kolicina:"+ukupnaKolicina+"\n");
				kartica.setUkupnaKolicina(ukupnaKolicina);
				
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
				kartica = karticaR.save(kartica);
				
				//Postavljanje magacinske kartice za promet
				prometKartice.setMagacinskaKartica(kartica);

				//Cuvanje prometa
				prometR.save(prometKartice);
			}else if(pd.getVrstaDokumenta().equals(VrstaDokumenta.OT)) {
				System.out.println("\nOtpremnica\n");
				//Trazenje kartice
				kartica = karticaS.findOneByRobaIliUslugaAndPoslovnaGodinaAndMagacin(ru.getSifra(), pd.getPoslovnaGodina().getBrojGodine(), pd.getIzlazniMagacin().getSifraMagacina());
				
				//Postavljanje ukupne cene
				double ukupnaCena = (kartica.getUkupnaVrednost()+stavkaDokumenta.getVrednost())/(kartica.getUkupnaKolicina()+stavkaDokumenta.getKolicina());
				kartica.setCena(DoubleRounder.round(ukupnaCena, 2));
				
				//Postavljanje kolicine
				kartica.setPrometIzlazaKolicinski(kartica.getPrometIzlazaKolicinski()+stavkaDokumenta.getKolicina());
				double ukupnaKolicina = kartica.getPocetnoStanjeKolicinski()+kartica.getPrometUlazaKolicinski()-kartica.getPrometIzlazaKolicinski();

				kartica.setUkupnaKolicina(ukupnaKolicina);
				
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
				kartica = karticaR.save(kartica);
				
				//Postavljanje magacinske kartice za promet
				prometKartice.setMagacinskaKartica(kartica);
				//Cuvanje prometa
				prometR.save(prometKartice);
			}else if(pd.getVrstaDokumenta().equals(VrstaDokumenta.MM)){
				System.out.println("\nMedjumagacinksi\n");
				//Trazenje kartice za prvi magacin
				kartica = karticaS.findOneByRobaIliUslugaAndPoslovnaGodinaAndMagacin(ru.getSifra(), pd.getPoslovnaGodina().getBrojGodine(), pd.getUlazniMagacin().getSifraMagacina());
				//Trazenje kartice za drugi magacin
				kartica2 = karticaS.findOneByRobaIliUslugaAndPoslovnaGodinaAndMagacin(ru.getSifra(), pd.getPoslovnaGodina().getBrojGodine(), pd.getIzlazniMagacin().getSifraMagacina());
				
				//Postavljanje ukupne cene
				double ukupnaCena = (kartica.getUkupnaVrednost()+stavkaDokumenta.getVrednost())/(kartica.getUkupnaKolicina()+stavkaDokumenta.getKolicina());
				kartica.setCena(DoubleRounder.round(ukupnaCena, 2));
				double ukupnaCena2 = (kartica2.getUkupnaVrednost()+stavkaDokumenta.getVrednost())/(kartica2.getUkupnaKolicina()+stavkaDokumenta.getKolicina());
				kartica2.setCena(DoubleRounder.round(ukupnaCena2, 2));
				
				//Postavljenje kolicine za magacine
				double izlazKolicinski = kartica.getPrometIzlazaKolicinski()+stavkaDokumenta.getKolicina();
				kartica.setPrometIzlazaKolicinski(izlazKolicinski);
				double ulazKolicinski = kartica2.getPrometUlazaKolicinski()+stavkaDokumenta.getKolicina();
				kartica2.setPrometUlazaKolicinski(ulazKolicinski);
				double ukupnaKolicina = kartica.getPocetnoStanjeKolicinski()+kartica.getPrometUlazaKolicinski()-kartica.getPrometIzlazaKolicinski();

				kartica.setUkupnaKolicina(ukupnaKolicina);
					
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
				kartica = karticaR.save(kartica);
				kartica2 = karticaR.save(kartica2);
				
				//Postavljanje magacinske kartice za promet
				prometKartice.setMagacinskaKartica(kartica);
				prometKartice2.setMagacinskaKartica(kartica2);

				//Cuvanje prometa
				prometR.save(prometKartice);
				prometR.save(prometKartice2);
				System.out.println("\nSacuvao promete\n");
			}
		}
		return dtos;
	}

	@Override
	public void remove(Integer id) {
		stavkaDokumentaRepository.deleteById(id);

	}

	@Override
	public StavkaDokumenta findById(Integer id) {
		return stavkaDokumentaRepository.findByIdStavke(id);
	}

	@Override
	public List<StavkaDokumenta> findByPrometniDokument_id(Integer id) {
		return stavkaDokumentaRepository.findByPrometniDokument_id(id);
	}

	@Override
	public List<StavkaDokumenta> findByRobaIliUsluga_sifra(Integer id) {
		return stavkaDokumentaRepository.findByRobaIliUsluga_sifra(id);
	}

}
