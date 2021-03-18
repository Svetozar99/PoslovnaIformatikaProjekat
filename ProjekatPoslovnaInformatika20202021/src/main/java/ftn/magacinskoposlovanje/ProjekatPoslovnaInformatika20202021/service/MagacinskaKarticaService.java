package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.service;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.crypto.spec.PSource;

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
	public MagacinskaKartica findOneByRobaIliUslugaAndPoslovnaGodinaAndMagacin(Integer robaIliUslugaId,
			Integer poslovnaGodinaId, Integer sifraMagacina) throws Exception{
		RobaIliUsluga robaIliUsluga = robaIliUslugaRepository.findOneBySifra(robaIliUslugaId);
		PoslovnaGodina poslovnaGodina = poslovnaGodinaRepository.findOneByBrojGodine(poslovnaGodinaId);

		Magacin magacin = magacinRepository.findOneBySifraMagacina(sifraMagacina);
		
		MagacinskaKartica kartica = magacinskaKarticaRepository.findOneByRobaIliUsluga_sifraAndPoslovnaGodina_brojGodineAndMagacin_sifraMagacina(robaIliUslugaId, poslovnaGodinaId, sifraMagacina);
		
		if(kartica == null) {
			
			System.out.println("za ovu robu ili uslugu i poslovnu godinu ne postoji ni jedna magacinska kartica");
			kartica = new MagacinskaKartica();
			kartica.setPocetnoStanjeKolicinski(0);
			kartica.setPrometUlazaKolicinski(0);
			kartica.setPrometIzlazaKolicinski(0);
			kartica.setUkupnaKolicina(0);
			kartica.setPocetnoStanjeVrednosno(0);
			kartica.setPrometIzlazaKolicinski(0);
			kartica.setPrometUlazaVrednosno(0);
			kartica.setUkupnaVrednost(0);
			kartica.setCena(0);
			kartica.setMagacin(magacin);
			kartica.setRobaIliUsluga(robaIliUsluga);
			if(poslovnaGodina == null) {
				poslovnaGodina = new PoslovnaGodina();
				Date date = new Date();
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				poslovnaGodina.setBrojGodine(calendar.get(Calendar.YEAR));
				poslovnaGodina.setPreduzece(magacin.getPreduzece());
				poslovnaGodina.setZakljucena(false);
				poslovnaGodina = poslovnaGodinaRepository.save(poslovnaGodina);
			}
			kartica.setPoslovnaGodina(poslovnaGodina);
			kartica = magacinskaKarticaRepository.save(kartica);
		}
		return kartica;
	}

	@Override
	public MagacinskaKartica findOneByMagacin_sifraMagacinaAndRobaIliUsluga_sifra(Integer sifraMagacina,Integer sifraRobeIliUsluge) {
		return magacinskaKarticaRepository.findOneByMagacin_sifraMagacinaAndRobaIliUsluga_sifra(sifraMagacina,sifraRobeIliUsluge);
	}

	@Override
	public List<MagacinskaKartica> findByRobaIliUsluga_sifraAndPoslovnaGodina_brojGodineAndMagacin_sifraMagacina(
			Integer sifraRobeUsluge, Integer brojGodine, Integer sifraMagacina) {// TODO Auto-generated method stub
		return magacinskaKarticaRepository.findByRobaIliUsluga_sifraAndPoslovnaGodina_brojGodineAndMagacin_sifraMagacina(sifraRobeUsluge, brojGodine, sifraMagacina);
	}

	@Override
	public List<MagacinskaKartica> findByMagacin_sifraMagacinaAndRobaIliUsluga_sifra(Integer sifraMagacina,
			Integer sifraRobeIliUsluge) {
		// TODO Auto-generated method stub
		return magacinskaKarticaRepository.findByMagacin_sifraMagacinaAndRobaIliUsluga_sifra(sifraMagacina, sifraRobeIliUsluge);
	}

	@Override
	public List<MagacinskaKartica> findByMagacin_sifraMagacinaAndPoslovnaGodina_brojGodine(Integer sifraMagacina,
			Integer brojGodine) {
		// TODO Auto-generated method stub
		return magacinskaKarticaRepository.findByMagacin_sifraMagacinaAndPoslovnaGodina_brojGodine(sifraMagacina, brojGodine);
	}

	@Override
	public List<MagacinskaKartica> findByPoslovnaGodina_brojGodineAndRobaIliUsluga_sifra(Integer brojGodine,
			Integer sifraRobeIliUsluge) {
		// TODO Auto-generated method stub
		return magacinskaKarticaRepository.findByPoslovnaGodina_brojGodineAndRobaIliUsluga_sifra(brojGodine, sifraRobeIliUsluge);
	}

	@Override
	public List<MagacinskaKartica> findByMagacin_sifraMagacina(Integer sifraMagacina) {
		// TODO Auto-generated method stub
		return magacinskaKarticaRepository.findByMagacin_sifraMagacina(sifraMagacina);
	}

	@Override
	public List<MagacinskaKartica> findByPoslovnaGodina_brojGodine(Integer brojGodine) {
		// TODO Auto-generated method stub
		return magacinskaKarticaRepository.findByPoslovnaGodina_brojGodine(brojGodine);
	}

	@Override
	public List<MagacinskaKartica> findByRobaIliUsluga_sifra(Integer sifraRobeIliUsluge) {
		// TODO Auto-generated method stub
		return magacinskaKarticaRepository.findByRobaIliUsluga_sifra(sifraRobeIliUsluge);
	}

}
