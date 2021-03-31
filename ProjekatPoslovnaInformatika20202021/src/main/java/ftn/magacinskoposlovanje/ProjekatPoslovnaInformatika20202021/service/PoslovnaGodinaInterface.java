package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.entityDTO.PoslovnaGodinaDTO;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.PoslovnaGodina;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.Preduzece;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.repository.PoslovnaGodinaRepository;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.repository.PreduzeceRepository;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface.PoslovnaGodinaServiceInterface;

@Service
public class PoslovnaGodinaInterface implements PoslovnaGodinaServiceInterface {

	@Autowired
	PoslovnaGodinaRepository poslovnaGodinaRepository;
	
	@Autowired
	PreduzeceRepository predrepos;
	
	@Override
	public List<PoslovnaGodinaDTO> findAll() {
		List<PoslovnaGodina> pg = poslovnaGodinaRepository.findAll();
		
		List<PoslovnaGodinaDTO> pgDTO = new ArrayList<PoslovnaGodinaDTO>();
		for(PoslovnaGodina p: pg) {
			pgDTO.add(new PoslovnaGodinaDTO(p));
		}
		return pgDTO;
	}

	@Override
	public PoslovnaGodinaDTO findOne(Integer id) {
		PoslovnaGodina pg = poslovnaGodinaRepository.findOneByBrojGodine(id);
		PoslovnaGodinaDTO pdto = new PoslovnaGodinaDTO(pg);
		return pdto;
	}

	@Override
	public PoslovnaGodinaDTO save(PoslovnaGodinaDTO poslovnaGodinaDTO) {
		Preduzece p = predrepos.findOneByIdPreduzeca(poslovnaGodinaDTO.getPreduzece());
		PoslovnaGodina po = new PoslovnaGodina();
		po.setBrojGodine(poslovnaGodinaDTO.getBrojGodine());
		po.setZakljucena(poslovnaGodinaDTO.getZakljucena());
		po.setPreduzece(p);
		
		po = poslovnaGodinaRepository.save(po);
		
		PoslovnaGodinaDTO dto = new PoslovnaGodinaDTO(po);
		return dto;
	}

	@Override
	public void remove(Integer id) {
		poslovnaGodinaRepository.deleteById(id);

	}

	@Override
	public List<PoslovnaGodinaDTO> findByPreduzece_id(Integer id) {
		List<PoslovnaGodina> pgs = poslovnaGodinaRepository.findByPreduzece_idPreduzeca(id);
		
		List<PoslovnaGodinaDTO> pgdtos = new ArrayList<PoslovnaGodinaDTO>();
		for(PoslovnaGodina p: pgs) {
			PoslovnaGodinaDTO pdto = new PoslovnaGodinaDTO(p);
			pgdtos.add(pdto);
		}
		return pgdtos;
	}

	@Override
	public PoslovnaGodinaDTO findByBrojGodine(Integer brojBodine) {
		PoslovnaGodina pg = poslovnaGodinaRepository.findOneByBrojGodine(brojBodine);
		return new PoslovnaGodinaDTO(pg);
	}

	@Override
	public PoslovnaGodinaDTO findById(Integer id) {
		PoslovnaGodina pg = poslovnaGodinaRepository.findOneByIdGodine(id);
		
		return new PoslovnaGodinaDTO(pg);
	}

	@Override
	public void update(PoslovnaGodinaDTO pgdto) {
		PoslovnaGodina poslovnaGodina = poslovnaGodinaRepository.findOneByBrojGodine(pgdto.getBrojGodine());
		Preduzece preduzece = predrepos.findOneByIdPreduzeca(pgdto.getPreduzece());
		
		poslovnaGodina.setBrojGodine(pgdto.getBrojGodine());
		poslovnaGodina.setZakljucena(pgdto.getZakljucena());
		poslovnaGodina.setPreduzece(preduzece);
	}

}
