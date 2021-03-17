package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.PrometniDokument;

public interface PrometniDokumentServiceInterface {

	List<PrometniDokument> findAll();
	PrometniDokument save(PrometniDokument prometniDokument);
	PrometniDokument findOneById(Integer id);
	
	Integer findByMaxid();
}
