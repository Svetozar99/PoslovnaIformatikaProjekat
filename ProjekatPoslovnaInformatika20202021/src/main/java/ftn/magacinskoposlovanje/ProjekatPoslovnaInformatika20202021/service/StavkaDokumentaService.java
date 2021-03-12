package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.StavkaDokumenta;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.repository.StavkaDokumentaRepository;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface.StavkaDokumentaServiceInterface;

@Service
public class StavkaDokumentaService implements StavkaDokumentaServiceInterface {

	@Autowired
	StavkaDokumentaRepository stavkaDokumentaRepository;
	
	@Override
	public List<StavkaDokumenta> findAll() {
		return stavkaDokumentaRepository.findAll();
	}

	@Override
	public StavkaDokumenta findOne(Integer id) {
		return stavkaDokumentaRepository.getOne(id);
	}

	@Override
	public StavkaDokumenta save(StavkaDokumenta stavkaDokumenta) {
		return stavkaDokumentaRepository.save(stavkaDokumenta);
	}

	@Override
	public void remove(Integer id) {
		stavkaDokumentaRepository.deleteById(id);

	}

	@Override
	public StavkaDokumenta findById(Integer id) {
		return stavkaDokumentaRepository.findByIdStavke(id);
	}

	@Override
	public List<StavkaDokumenta> findByPrometniDokument_id(Integer id) {
		return stavkaDokumentaRepository.findByPrometniDokument_id(id);
	}

	@Override
	public List<StavkaDokumenta> findByRobaIliUsluga_sifra(Integer id) {
		return stavkaDokumentaRepository.findByRobaIliUsluga_sifra(id);
	}

}
