package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.PrometniDokument;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.VrstaDokumenta;

public interface PrometniDokumentRepository extends JpaRepository<PrometniDokument, Integer> {
	List<PrometniDokument> findByVrstaDokumenta(VrstaDokumenta vrstaDokumenta);

	PrometniDokument findOneById(Integer Id); 
}
