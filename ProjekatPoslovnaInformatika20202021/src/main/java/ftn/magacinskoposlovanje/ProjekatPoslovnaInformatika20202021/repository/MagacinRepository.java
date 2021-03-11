package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.Magacin;

public interface MagacinRepository extends JpaRepository<Magacin, Integer>{
	List<Magacin> findByPreduzece_idPreduzeca(Integer id);
}
