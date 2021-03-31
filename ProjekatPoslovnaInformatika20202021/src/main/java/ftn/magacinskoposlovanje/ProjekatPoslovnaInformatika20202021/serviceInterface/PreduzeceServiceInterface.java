package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface;

import java.util.List;

import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.Preduzece;

public interface PreduzeceServiceInterface {

	public List<Preduzece> findAll();
	
	public Preduzece findOne(Integer preduzeceId);
	
	public Preduzece save(Preduzece preduzece);
	
	public void remove(Integer id);
	
	public Preduzece findById(Integer preduzeceId);
	
}
