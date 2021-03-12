package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.StavkaDokumenta;

public interface StavkaDokumentaRepository extends JpaRepository<StavkaDokumenta, Integer> {

	StavkaDokumenta findByIdStavke(Integer id);
	
	List<StavkaDokumenta> findByPrometniDokument_id(Integer id); 
	
	List<StavkaDokumenta> findByRobaIliUsluga_sifra(Integer id);
}
