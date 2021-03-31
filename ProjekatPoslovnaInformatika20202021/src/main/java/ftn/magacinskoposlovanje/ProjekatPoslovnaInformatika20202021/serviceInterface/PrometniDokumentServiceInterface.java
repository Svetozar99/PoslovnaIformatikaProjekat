package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;

import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.entityDTO.PrometniDokumentDTO;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.PrometniDokument;

public interface PrometniDokumentServiceInterface {

	List<PrometniDokument> findAll();
	PrometniDokumentDTO save(PrometniDokumentDTO prometniDokument) throws Exception;
	PrometniDokument findOneById(Integer id);
	PrometniDokument findOneByRedniBroj(String redniBroj);
	String findByMaxid();
	List<PrometniDokument> findByRedniBroj(String redniBroj);
	
	public ResponseEntity report(String broj);
}
