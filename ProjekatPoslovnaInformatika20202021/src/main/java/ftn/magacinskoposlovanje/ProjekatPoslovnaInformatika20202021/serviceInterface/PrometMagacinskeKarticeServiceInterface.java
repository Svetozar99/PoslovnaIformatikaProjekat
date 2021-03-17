package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface;

import java.util.List;

import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.PrometMagacinskeKartice;

public interface PrometMagacinskeKarticeServiceInterface {

public List<PrometMagacinskeKartice> findAll();
	
	public PrometMagacinskeKartice findOne(Integer id);
	
	public PrometMagacinskeKartice save(PrometMagacinskeKartice prometMagacinskeKartice);
	
	public void remove(Integer id);
	
	public List<PrometMagacinskeKartice> findByMagacinskaKartica(Integer id);
}
