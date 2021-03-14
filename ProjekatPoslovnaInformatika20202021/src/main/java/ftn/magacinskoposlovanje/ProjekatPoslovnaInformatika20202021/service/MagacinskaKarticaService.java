package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.Magacin;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.MagacinskaKartica;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.PoslovnaGodina;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.RobaIliUsluga;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.repository.MagacinRepository;
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
	
	@Autowired
	MagacinRepository magacinRepository;
	
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

		Magacin magacin = magacinRepository.findOneBySifraMagacina(1);
		
		List<MagacinskaKartica> getAllMagKart = magacinskaKarticaRepository.findAll();
		
		List<MagacinskaKartica> magacinskeKarticeRiliL = robaIliUsluga.getMagacinskeKartice();
		List<MagacinskaKartica> magacinskeKarticePg = poslovnaGodina.getMagacinskeKartice();

		int i = getAllMagKart.size();
		System.out.println(i + "broj magacinskih kartica u bazi");
		
		if(magacinskeKarticeRiliL.isEmpty()) {
			System.out.println("za ovu robu ili uslugu ne postoji ni jedna magacinska kartica");
			MagacinskaKartica m = new MagacinskaKartica();
//			m.setId(i+1);
			m.setPocetnoStanjeKolicinski(0);
			m.setPrometUlazaKolicinski(0);
			m.setPrometIzlazaKolicinski(0);
			m.setUkupnaKolicina(0);
			m.setPocetnoStanjeVrednosno(0);
			m.setPrometIzlazaKolicinski(0);
			m.setPrometUlazaVrednosno(0);
			m.setUkupnaVrednost(0);
			m.setCena(120);
			m.setMagacin(magacin);
			m.setRobaIliUsluga(robaIliUsluga);
			m.setPoslovnaGodina(poslovnaGodina);
			
			magacinskeKarticeRiliL.add(m);
			magacinskaKarticaRepository.save(m);
		}
		if(magacinskeKarticePg.isEmpty()) {
			System.out.println("za ovu poslovnu godinu ne postoji nijedna magacinska kartica");
			MagacinskaKartica m = new MagacinskaKartica();
//			m.setId(i+1);
			m.setPocetnoStanjeKolicinski(0);
			m.setPrometUlazaKolicinski(0);
			m.setPrometIzlazaKolicinski(0);
			m.setUkupnaKolicina(0);
			m.setPocetnoStanjeVrednosno(0);
			m.setPrometIzlazaKolicinski(0);
			m.setPrometUlazaVrednosno(0);
			m.setUkupnaVrednost(0);
			m.setCena(120);
			m.setMagacin(magacin);
			m.setRobaIliUsluga(robaIliUsluga);
			m.setPoslovnaGodina(poslovnaGodina);
			
			magacinskeKarticePg.add(m);
			magacinskaKarticaRepository.save(m);
		}
		
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
