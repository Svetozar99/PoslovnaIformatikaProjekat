package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.entityDTO.MagacinDTO;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.Magacin;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.Preduzece;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.repository.MagacinRepository;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.repository.PreduzeceRepository;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface.MagacinServiceInterface;

@Service
public class MagacinService implements MagacinServiceInterface{

	@Autowired
	MagacinRepository magacinRepository;
	
	@Autowired
	PreduzeceRepository predrepos;

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
		Magacin magacin = magacinRepository.findOneById(magacinId);
		return new MagacinDTO(magacin);
	}

	@Override
	public MagacinDTO save(MagacinDTO magacinDTO) {
		Preduzece p = predrepos.findOneByIdPreduzeca(magacinDTO.getPreduzece());
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
		return null;
	}

	@Override
	public MagacinDTO update(Integer id, MagacinDTO dto) {
		Magacin magacin = magacinRepository.findOneBySifraMagacina(id);
		Preduzece preduzece = predrepos.findOneById(dto.getPreduzece());
		magacin.setNazivMagacina(dto.getNaziv());	
		magacin.setPreduzece(preduzece);
		magacin = magacinRepository.save(magacin);
		return new MagacinDTO(magacin);
	}
	
	

}
