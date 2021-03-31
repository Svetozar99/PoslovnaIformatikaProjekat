package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface;

import java.util.List;

import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.entityDTO.PoslovnaGodinaDTO;

public interface PoslovnaGodinaServiceInterface {

	public List<PoslovnaGodinaDTO> findAll();
	
	public PoslovnaGodinaDTO findOne(Integer id);
	
	public PoslovnaGodinaDTO save(PoslovnaGodinaDTO poslovnaGodina);
	
	public void remove(Integer id);
	
	public List<PoslovnaGodinaDTO> findByPreduzece_id(Integer id);
	
	public PoslovnaGodinaDTO findByBrojGodine(Integer brojGodine);
	
	public PoslovnaGodinaDTO findById(Integer id);
	
	public void update(PoslovnaGodinaDTO pgdto);
}
