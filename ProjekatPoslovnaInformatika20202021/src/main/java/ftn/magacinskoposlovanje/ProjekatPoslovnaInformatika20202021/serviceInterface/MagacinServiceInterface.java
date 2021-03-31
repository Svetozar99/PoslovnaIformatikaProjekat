package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface;

import java.util.List;

import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.entityDTO.MagacinDTO;

public interface MagacinServiceInterface {
	
	public List<MagacinDTO> findAll();
	public MagacinDTO findOne(Integer magacinId);
	public MagacinDTO save(MagacinDTO magacinDTO);
	public void remove(Integer magacinId);
	public List<MagacinDTO> findByPreduzece_id(Integer id);
	
	public MagacinDTO findBySifra(Integer id);
	
	public MagacinDTO update(Integer id, MagacinDTO dto);
}
