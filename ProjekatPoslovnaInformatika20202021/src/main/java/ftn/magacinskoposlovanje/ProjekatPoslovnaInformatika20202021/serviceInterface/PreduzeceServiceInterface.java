package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface;

import java.util.List;

import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.entityDTO.PreduzeceDTO;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.Preduzece;

public interface PreduzeceServiceInterface {

	public List<PreduzeceDTO> findAll();
	
	public Preduzece findOne(Integer preduzeceId);
	
	public PreduzeceDTO save(PreduzeceDTO preduzeceDTO);
	
	public void remove(Integer id);
	
	public PreduzeceDTO findById(Integer preduzeceId) throws Exception;
	
	public PreduzeceDTO update(Integer id,PreduzeceDTO pdto) throws Exception;
	
}
