package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.MagacinskaKartica;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.PoslovnaGodina;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.RobaIliUsluga;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.repository.MagacinskaKarticaRepository;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.repository.PoslovnaGodinaRepository;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.repository.RobaIliUslugaRepository;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface.MagacinskaKarticaServiceInterface;

@Service
public class MagacinskaKarticaService implements MagacinskaKarticaServiceInterface {

	@Autowired
	MagacinskaKarticaRepository magacinskaKarticaRepository;
	
	@Autowired
	RobaIliUslugaRepository robaIliUslugaRepository;
	
	@Autowired
	PoslovnaGodinaRepository poslovnaGodinaRepository;
	
	@Override
	public List<MagacinskaKartica> findAll() {
		return magacinskaKarticaRepository.findAll();
	}

	@Override
	public MagacinskaKartica save(MagacinskaKartica magacinskaKartica) {
		return magacinskaKarticaRepository.save(magacinskaKartica);
	}

	@Override
	public MagacinskaKartica findOneById(Integer id) {
		return magacinskaKarticaRepository.findOneById(id);
	}

	@Override
	public MagacinskaKartica findOneByRobaIliUslugaAndPoslovnaGodina(Integer robaIliUslugaId,
			Integer poslovnaGodinaId){
		RobaIliUsluga robaIliUsluga = robaIliUslugaRepository.findOneBySifra(robaIliUslugaId);
		PoslovnaGodina poslovnaGodina = poslovnaGodinaRepository.findOneByIdGodine(poslovnaGodinaId);
		
		List<MagacinskaKartica> magacinskeKarticeRiliL = robaIliUsluga.getMagacinskeKartice();
		List<MagacinskaKartica> magacinskeKarticePg = poslovnaGodina.getMagacinskeKartice();

		for(MagacinskaKartica mkpg: magacinskeKarticePg) {
			for(MagacinskaKartica mkru: magacinskeKarticeRiliL) {
				if(mkpg.getId() == mkru.getId()) {
					System.out.println(mkpg.getId() + "---- magacinske kartice u poslovnoj godini");
					System.out.println(mkru.getId() + "---- magacinske kartice od robe ili usluge");
					return mkru;
					
				}
			}
		}
		return null;
	}

}
