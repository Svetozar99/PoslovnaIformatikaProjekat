package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.PrometniDokument;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.repository.PrometniDokumentRepository;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface.PrometniDokumentServiceInterface;

@Service
public class PrometniDokumentService implements PrometniDokumentServiceInterface {

	@Autowired
	PrometniDokumentRepository prometniDokumentRepository;
	
	@Override
	public List<PrometniDokument> findAll() {
		return prometniDokumentRepository.findAll();
	}

	@Override
	public PrometniDokument save(PrometniDokument prometniDokument) {
		return prometniDokumentRepository.save(prometniDokument);
	}

	@Override
	public PrometniDokument findOneById(Integer id) {
		return prometniDokumentRepository.findOneById(id);
	}

	@Override
	public Integer findByMaxid() {
		// TODO Auto-generated method stub
		return prometniDokumentRepository.najveciID();
	}

	@Override
	public PrometniDokument findOneByRedniBroj(String redniBroj) {
		// TODO Auto-generated method stub
		return prometniDokumentRepository.findOneByRedniBroj(redniBroj);
	}

	@Override
	public List<PrometniDokument> findByRedniBroj(String redniBroj) {
		// TODO Auto-generated method stub
		return prometniDokumentRepository.findByRedniBroj(redniBroj);
	}

}
