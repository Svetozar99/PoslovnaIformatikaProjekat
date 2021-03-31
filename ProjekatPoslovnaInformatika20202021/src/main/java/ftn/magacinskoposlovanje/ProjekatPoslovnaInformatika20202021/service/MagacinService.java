package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.entityDTO.MagacinDTO;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.Magacin;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.Preduzece;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.repository.MagacinRepository;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.repository.PreduzeceRepository;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface.MagacinServiceInterface;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface.PreduzeceServiceInterface;

@Service
@Transactional(rollbackFor=Exception.class)
public class MagacinService implements MagacinServiceInterface{

	@Autowired
	MagacinRepository magacinRepository;
	
	@Autowired
	PreduzeceServiceInterface predrepos;

	@Override
	public List<MagacinDTO> findAll() {
		List<Magacin> magacini = magacinRepository.findAll();
		
		List<MagacinDTO> magacinDTO = new ArrayList<MagacinDTO>();
		for(Magacin mag: magacini) {
			magacinDTO.add(new MagacinDTO(mag));
		}
		return magacinDTO;
	}

	@Override
	public MagacinDTO findOne(Integer magacinId) {
		Magacin magacin = magacinRepository.findOneBySifraMagacina(magacinId);
		return new MagacinDTO(magacin);
	}

	@Override
	public MagacinDTO save(MagacinDTO magacinDTO) {
		Integer id = magacinDTO.getPreduzece();
		Preduzece p = predrepos.findOne(id);
		Magacin m = new Magacin();
		m.setNazivMagacina(magacinDTO.getNaziv());	
		m.setPreduzece(p);
		
		m = magacinRepository.save(m);
		return new MagacinDTO(m);
	}

	@Override
	public void remove(Integer magacinId) {
		// TODO Auto-generated method stub
		magacinRepository.deleteById(magacinId);
	}

	@Override
	public List<MagacinDTO> findByPreduzece_id(Integer id) {
		List<Magacin> magacini = magacinRepository.findByPreduzece_idPreduzeca(id);
		List<MagacinDTO> dtos = new ArrayList<MagacinDTO>();
		for (Magacin m : magacini) {
			MagacinDTO dto = new MagacinDTO(m);
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public MagacinDTO findBySifra(Integer id) {
		// TODO Auto-generated method stub
		Magacin m = magacinRepository.findOneBySifraMagacina(id);
		return new MagacinDTO(m);
	}

	@Override
	public MagacinDTO update(Integer id, MagacinDTO dto) {
		Magacin magacin = magacinRepository.findOneBySifraMagacina(id);
		Preduzece preduzece = magacin.getPreduzece();
		magacin.setNazivMagacina(dto.getNaziv());	
		magacin.setPreduzece(preduzece);
		magacin = magacinRepository.save(magacin);
		return new MagacinDTO(magacin);
	}
	
	

}
