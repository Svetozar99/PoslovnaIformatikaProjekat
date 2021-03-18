package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.PrometMagacinskeKartice;

public interface PrometMagacinskeKarticeRepository extends JpaRepository<PrometMagacinskeKartice, Integer>{
	List<PrometMagacinskeKartice> findByMagacinskaKartica_id(Integer id);
}
