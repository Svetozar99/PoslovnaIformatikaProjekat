package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.entityDTO.RobaIliUslugaDTO;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.JedinicaMere;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.MagacinskaKartica;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.RobaIliUsluga;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.repository.JedinicaMereRepository;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.repository.MagacinskaKarticaRepository;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.repository.RobaIliUslugaRepository;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface.RobaIliUslugaServiceInterface;

@Service
@Transactional
public class RobaIliUslugaService implements RobaIliUslugaServiceInterface{

	@Autowired
	RobaIliUslugaRepository robaIliUslugaRepository;
	
	@Autowired
	MagacinskaKarticaRepository mkrepos;
	
	@Autowired
	JedinicaMereRepository jmrepos;

	@Override
	public List<RobaIliUslugaDTO> findAll() {
		List<RobaIliUsluga> robaIliUsluga = robaIliUslugaRepository.findAll();
		
		List<RobaIliUslugaDTO> dtos = new ArrayList<RobaIliUslugaDTO>();
		for(RobaIliUsluga ru: robaIliUsluga) {
			RobaIliUslugaDTO dto = new RobaIliUslugaDTO();
			dto.setSifra(ru.getSifra());
			dto.setNaziv(ru.getNaziv());
			dto.setJedinicaMere(ru.getJedinicaMere().getSkraceniNaziv());
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public RobaIliUslugaDTO save(RobaIliUslugaDTO robaIliUslugaDTO) {
		JedinicaMere jedinicaMere = jmrepos.findOneById(robaIliUslugaDTO.getIdJedinicaMere());

		RobaIliUsluga robaIliUsluga = new RobaIliUsluga();
		robaIliUsluga.setNaziv(robaIliUslugaDTO.getNaziv());
		robaIliUsluga.setJedinicaMere(jedinicaMere);
		
		robaIliUsluga = robaIliUslugaRepository.save(robaIliUsluga);
		robaIliUslugaDTO.setSifra(robaIliUsluga.getSifra());
		robaIliUslugaDTO.setJedinicaMere(robaIliUsluga.getJedinicaMere().getSkraceniNaziv());
		
		return new RobaIliUslugaDTO(robaIliUsluga);
	}

	@Override
	public RobaIliUslugaDTO findOneBySifra(Integer id) {
		RobaIliUsluga rl = robaIliUslugaRepository.getOne(id);
			
		return new RobaIliUslugaDTO(rl);
	}

	@Override
	public RobaIliUslugaDTO getOne(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer i) {
		// TODO Auto-generated method stub
		robaIliUslugaRepository.deleteById(i);
	}

	@Override
	public List<RobaIliUslugaDTO> findByMagKartica(Integer id) {
		List<RobaIliUsluga> robaIliUsluga = robaIliUslugaRepository.findAll();
		
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		
		List<RobaIliUslugaDTO> dtos = new ArrayList<RobaIliUslugaDTO>();
		for(RobaIliUsluga ru: robaIliUsluga) {
			List<MagacinskaKartica> kartice= mkrepos.findByRobaIliUsluga_sifraAndPoslovnaGodina_brojGodineAndMagacin_sifraMagacina(ru.getSifra(), calendar.get(Calendar.YEAR), id);
			RobaIliUslugaDTO dto = new RobaIliUslugaDTO();
			dto.setSifra(ru.getSifra());
			dto.setNaziv(ru.getNaziv());
			dto.setJedinicaMere(ru.getJedinicaMere().getSkraceniNaziv());
			if(kartice.size() != 0) {
				dto.setCena(kartice.get(0).getCena());
				if(kartice.get(0).getUkupnaKolicina()>0) {
					dtos.add(dto);
				}
			}
		}
		return dtos;
	}

	@Override
	public RobaIliUslugaDTO update(Integer id, RobaIliUslugaDTO dto) {
		RobaIliUsluga rilil = robaIliUslugaRepository.findOneBySifra(id);
		JedinicaMere jm = jmrepos.findOneById(dto.getIdJedinicaMere());
		
		
		rilil.setNaziv(dto.getNaziv());
		rilil.setJedinicaMere(jm);
		rilil.setCena(dto.getCena());
		rilil = robaIliUslugaRepository.save(rilil);
//		robaIliUslugaDTO.setJedinicaMere(rilil.getJedinicaMere().getSkraceniNaziv());
		System.out.println(dto.getIdJedinicaMere()+ " id jedinice mere");
		return new RobaIliUslugaDTO(rilil);
	}
	
	
}
