package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface;

import java.util.List;

import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.entityDTO.StavkaDokumentaDTO;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.StavkaDokumenta;

public interface StavkaDokumentaServiceInterface {

	public List<StavkaDokumenta> findAll();
	
	public StavkaDokumenta findOne(Integer id);
	
	public List<StavkaDokumentaDTO> save(List<StavkaDokumentaDTO> stavke, Integer idDokumenta) throws Exception;
	
	public void remove(Integer id);
	
	public StavkaDokumenta findById(Integer id);
	
	public List<StavkaDokumenta> findByPrometniDokument_id(Integer id);
	
	public List<StavkaDokumenta> findByRobaIliUsluga_sifra(Integer id);
}
