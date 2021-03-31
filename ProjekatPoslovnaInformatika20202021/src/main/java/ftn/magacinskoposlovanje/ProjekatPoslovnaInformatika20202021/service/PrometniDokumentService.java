package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.entityDTO.PrometniDokumentDTO;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.Magacin;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.PoslovnaGodina;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.PoslovniPartner;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.Preduzece;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.PrometniDokument;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.Status;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.VrstaDokumenta;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.repository.MagacinRepository;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.repository.PoslovnaGodinaRepository;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.repository.PoslovniPartnerRepository;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.repository.PreduzeceRepository;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.repository.PrometniDokumentRepository;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface.PrometniDokumentServiceInterface;

@Service
public class PrometniDokumentService implements PrometniDokumentServiceInterface {

	@Autowired
	PrometniDokumentRepository prometniDokumentRepository;
	
	@Autowired
	PoslovnaGodinaRepository poslovnaGodRep;
	
	@Autowired
	PreduzeceRepository preduzeceR;
	
	@Autowired
	MagacinRepository magacinRep;
	
	@Autowired
	PoslovniPartnerRepository partnerRep;
	
	@Override
	public List<PrometniDokument> findAll() {
		return prometniDokumentRepository.findAll();
	}

	@Override
	public PrometniDokumentDTO save(PrometniDokumentDTO dto) {
		System.out.println("\n\tPost!");
		System.out.println(dto.toString());
		
		PoslovniPartner poslovniPartner = new PoslovniPartner();
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		PoslovnaGodina poslovnaGodina = poslovnaGodRep.findOneByBrojGodine(calendar.get(Calendar.YEAR));
		Preduzece preduzece = preduzeceR.findOneByIdPreduzeca(dto.getIdPreduzeca());
		if(poslovnaGodina == null) {
			PoslovnaGodina godina = poslovnaGodRep.findOneByBrojGodine(calendar.get(Calendar.YEAR)-1);
			godina.setZakljucena(true);
			poslovnaGodRep.save(godina);
			poslovnaGodina = new PoslovnaGodina();
			poslovnaGodina.setBrojGodine(calendar.get(Calendar.YEAR));
			poslovnaGodina.setPreduzece(preduzece);
			poslovnaGodina.setZakljucena(false);
			poslovnaGodina = poslovnaGodRep.save(poslovnaGodina);
		}
		Magacin ulazniMagacin = magacinRep.findOneBySifraMagacina(dto.getSifraMagacina1());
		Magacin izlazniMagacin = magacinRep.findOneBySifraMagacina(dto.getSifraMagacina2());
		
		PrometniDokument prometniDokument = new PrometniDokument();
		prometniDokument.setRedniBroj(dto.getBrojPrometnogDokumenta());
		prometniDokument.setDatum(dto.getDatumIzdavanja());
		prometniDokument.setStatus(Status.P);
		prometniDokument.setPoslovnaGodina(poslovnaGodina);
		
		if(dto.getVrstaDokumenta().equals(VrstaDokumenta.PR.toString())) {
			poslovniPartner = partnerRep.findOneBySifraPartnera(dto.getSifraPoslovnogPartnera());
			
			prometniDokument.setVrstaDokumenta(VrstaDokumenta.PR);
			prometniDokument.setPoslovniPartner(poslovniPartner);
			prometniDokument.setPreduzece(preduzece);
			prometniDokument.setUlazniMagacin(ulazniMagacin);
		}else if(dto.getVrstaDokumenta().equals(VrstaDokumenta.OT.toString())) {
			poslovniPartner = partnerRep.findOneBySifraPartnera(dto.getSifraPoslovnogPartnera());
			
			prometniDokument.setVrstaDokumenta(VrstaDokumenta.OT);
			prometniDokument.setPoslovniPartner(poslovniPartner);
			prometniDokument.setPreduzece(preduzece);
			prometniDokument.setIzlazniMagacin(izlazniMagacin);
		}
		else if(dto.getVrstaDokumenta().equals(VrstaDokumenta.MM.toString())) {
			prometniDokument.setVrstaDokumenta(VrstaDokumenta.MM);
			prometniDokument.setIzlazniMagacin(izlazniMagacin);
			prometniDokument.setUlazniMagacin(ulazniMagacin);
		}
		prometniDokument = prometniDokumentRepository.save(prometniDokument);
		dto.setId(prometniDokument.getId());
		return dto;
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
