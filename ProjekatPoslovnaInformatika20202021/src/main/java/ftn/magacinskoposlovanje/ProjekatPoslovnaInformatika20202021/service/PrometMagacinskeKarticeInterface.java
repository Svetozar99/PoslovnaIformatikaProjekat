package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.decimal4j.util.DoubleRounder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.entityDTO.PrometMagacinskeKarticeDTO;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.MagacinskaKartica;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.PrometMagacinskeKartice;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.PrometniDokument;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.Smer;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.Status;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.repository.MagacinskaKarticaRepository;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.repository.PrometMagacinskeKarticeRepository;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.repository.PrometniDokumentRepository;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface.PrometMagacinskeKarticeServiceInterface;

@Service
@Transactional
public class PrometMagacinskeKarticeInterface implements PrometMagacinskeKarticeServiceInterface {

	@Autowired
	PrometMagacinskeKarticeRepository prometMagacinskeKarticeRepository;
	
	@Autowired
	MagacinskaKarticaRepository magkartrepos;
	
	@Autowired
	PrometniDokumentRepository promdocrepos;
	
	@Override
	public List<PrometMagacinskeKarticeDTO> findAll() {
		List<PrometMagacinskeKartice> prometiMagacinskeKartice = prometMagacinskeKarticeRepository.findAll();
		
		List<PrometMagacinskeKarticeDTO> karticeDTOs= new ArrayList<PrometMagacinskeKarticeDTO>();
		for(PrometMagacinskeKartice p: prometiMagacinskeKartice) {
			karticeDTOs.add(new PrometMagacinskeKarticeDTO(p));
		}
		return karticeDTOs;
	}

	@Override
	public PrometMagacinskeKarticeDTO findOne(String redniBroj) {
		PrometMagacinskeKartice p = prometMagacinskeKarticeRepository.findOneByRedniBroj(redniBroj);
		return new PrometMagacinskeKarticeDTO(p);
	}

	@Override
	public PrometMagacinskeKarticeDTO save(Integer id,PrometMagacinskeKarticeDTO pDTO) {
		MagacinskaKartica mk = magkartrepos.findOneById(id);
		PrometMagacinskeKartice p = new PrometMagacinskeKartice();
		p.setRedniBroj(pDTO.getRedniBroj());
		p.setVrstaPrometa(pDTO.getVrstaPrometa());
		p.setSmer(pDTO.getSmer());
		p.setKolicina(pDTO.getKolicina());
		p.setCena(pDTO.getCena());
		p.setVrednost(pDTO.getVrednost());
		p.setDokument("dokument1");
		p.setDatumPrometa(pDTO.getDatum());
		p.setMagacinskaKartica(mk);
		prometMagacinskeKarticeRepository.save(p);
		PrometMagacinskeKarticeDTO pd = new PrometMagacinskeKarticeDTO(p);
		return pd;
	} 

	@Override
	public void remove(Integer id) {
		prometMagacinskeKarticeRepository.deleteById(id);
	}

	@Override
	public List<PrometMagacinskeKarticeDTO> findByMagacinskaKartica(Integer id) {
		System.out.println("Trazim prometne dokumete za karticu");
		MagacinskaKartica magacinskaKartica = magkartrepos.findOneById(id);
		List<PrometMagacinskeKartice> prometiMagacinskeKartice = prometMagacinskeKarticeRepository.findByMagacinskaKartica_id(id);
		
		List<PrometMagacinskeKarticeDTO> karticeDTOs= new ArrayList<PrometMagacinskeKarticeDTO>();
		for(PrometMagacinskeKartice p: prometiMagacinskeKartice) {
			PrometMagacinskeKarticeDTO dto = new PrometMagacinskeKarticeDTO(p);
			dto.setJedinicaMere(magacinskaKartica.getRobaIliUsluga().getJedinicaMere().getSkraceniNaziv());
			karticeDTOs.add(dto);
		}
		return karticeDTOs;
	}

	@Override
	public PrometMagacinskeKarticeDTO findByRedniBroj(String redniBroj) throws Exception {
		System.out.println("\n\n\tRedni broj: "+redniBroj);
		PrometMagacinskeKartice prmkst = new PrometMagacinskeKartice();
		List<PrometMagacinskeKartice> prometi = prometMagacinskeKarticeRepository.findByRedniBroj(redniBroj);
		System.out.println("\nBroj prometa u listi: "+prometi.size());
		if(prometi.size() == 0) {
			throw new Exception("Nemate nijedan promet za ovu karticu!");
		}else {
			for (PrometMagacinskeKartice promet : prometi) {
				System.out.println(promet.toString());
				MagacinskaKartica mk = magkartrepos.findOneById(promet.getMagacinskaKartica().getId());
				
				if(promet.getSmer().equals(Smer.U)) {
					System.out.println("\nUlaz");
					mk.setPrometUlazaKolicinski(mk.getPrometUlazaKolicinski()-promet.getKolicina());
					
					mk.setPrometUlazaVrednosno(mk.getPrometUlazaVrednosno()-promet.getVrednost());
					
					double ukupnaKolicina = mk.getPocetnoStanjeKolicinski()+mk.getPrometUlazaKolicinski()-mk.getPrometIzlazaKolicinski();
					mk.setUkupnaKolicina(ukupnaKolicina);
					
					double ukupnaVrednost = mk.getPocetnoStanjeVrednosno()+mk.getPrometUlazaVrednosno()-mk.getPrometIzlazaVrednosno();
					mk.setUkupnaVrednost(ukupnaVrednost);
					
					if(mk.getUkupnaKolicina()!=0) {
						mk.setCena(DoubleRounder.round(mk.getUkupnaVrednost()/mk.getUkupnaKolicina(), 2));
					}else {
						mk.setCena(0);
					}
//					MagacinskaKarticaDTO kkdto = new MagacinskaKarticaDTO(mk);
					mk = magkartrepos.save(mk);

					prmkst.setRedniBroj(redniBroj);
					prmkst.setVrstaPrometa(promet.getVrstaPrometa());
					prmkst.setSmer(promet.getSmer());
					prmkst.setKolicina(-promet.getKolicina());
					prmkst.setCena(promet.getCena());
					prmkst.setVrednost(-promet.getVrednost());
					prmkst.setMagacinskaKartica(mk);
					prmkst.setDatumPrometa(new Date());
					prmkst.setDokument(promet.getDokument());
					prmkst = prometMagacinskeKarticeRepository.save(prmkst);
				}else if(promet.getSmer().equals(Smer.I)){
					System.out.println("\nStorniram otpremnicu ili magacin koji je imao izlaz!");
					mk.setPrometIzlazaKolicinski(mk.getPrometIzlazaKolicinski()-promet.getKolicina());
					mk.setPrometIzlazaVrednosno(mk.getPrometIzlazaVrednosno()-promet.getVrednost());
					double ukupnaKolicina = mk.getPocetnoStanjeKolicinski()+mk.getPrometUlazaKolicinski()-mk.getPrometIzlazaKolicinski();
					mk.setUkupnaKolicina(ukupnaKolicina);
					
					double ukupnaVrednost = mk.getPocetnoStanjeVrednosno()+mk.getPrometUlazaVrednosno()-mk.getPrometIzlazaVrednosno();
					mk.setUkupnaVrednost(ukupnaVrednost);
					if(mk.getUkupnaKolicina()!=0) {
						mk.setCena(DoubleRounder.round(mk.getUkupnaVrednost()/mk.getUkupnaKolicina(), 2));
					}else {
						mk.setCena(0);
					}
					mk = magkartrepos.save(mk);
					
					
					prmkst.setRedniBroj(redniBroj);
					prmkst.setVrstaPrometa(promet.getVrstaPrometa());
					prmkst.setSmer(promet.getSmer());
					prmkst.setKolicina(-promet.getKolicina());
					prmkst.setCena(promet.getCena());
					prmkst.setVrednost(-promet.getVrednost());
					prmkst.setMagacinskaKartica(mk);
					prmkst.setDatumPrometa(new Date());
					prmkst.setDokument(promet.getDokument());
					System.out.println("\nLinija koda pre samog cuvanja prometa magacinske kartice");
					prmkst = prometMagacinskeKarticeRepository.save(prmkst);
				}
			}
		}
		
		
		List<PrometniDokument> prometniDokumenti = promdocrepos.findByRedniBroj(redniBroj);
		System.out.println("\n"+prometniDokumenti.size());
		for (PrometniDokument prometniDokument : prometniDokumenti) {
			prometniDokument.setStatus(Status.S);
			promdocrepos.save(prometniDokument);
		}
		PrometMagacinskeKarticeDTO pdto = new PrometMagacinskeKarticeDTO(prmkst);
		return pdto;
	}

}
