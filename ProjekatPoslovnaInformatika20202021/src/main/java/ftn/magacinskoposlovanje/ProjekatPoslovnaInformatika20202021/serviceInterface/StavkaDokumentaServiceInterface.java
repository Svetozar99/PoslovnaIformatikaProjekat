package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface;

import java.util.List;

import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.StavkaDokumenta;

public interface StavkaDokumentaServiceInterface {

	public List<StavkaDokumenta> findAll();
	
	public StavkaDokumenta findOne(Integer id);
	
	public StavkaDokumenta save(StavkaDokumenta stavkaDokumenta);
	
	public void remove(Integer id);
	
	public StavkaDokumenta findById(Integer id);
	
	public List<StavkaDokumenta> findByPrometniDokument_id(Integer id);
	
	public List<StavkaDokumenta> findByRobaIliUsluga_sifra(Integer id);
}
