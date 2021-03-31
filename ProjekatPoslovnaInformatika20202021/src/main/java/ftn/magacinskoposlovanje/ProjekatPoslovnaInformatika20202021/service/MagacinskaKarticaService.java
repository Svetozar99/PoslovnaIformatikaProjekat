package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.entityDTO.MagacinskaKarticaDTO;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.entityDTO.PrometMagacinskeKarticeDTO;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.Magacin;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.MagacinskaKartica;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.PoslovnaGodina;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.PrometMagacinskeKartice;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.PrometniDokument;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.RobaIliUsluga;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.Smer;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.Status;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.VrstaDokumenta;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.VrstaPrometa;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.repository.MagacinRepository;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.repository.MagacinskaKarticaRepository;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.repository.PoslovnaGodinaRepository;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.repository.PrometMagacinskeKarticeRepository;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.repository.RobaIliUslugaRepository;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface.MagacinskaKarticaServiceInterface;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface.PoslovnaGodinaServiceInterface;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface.PrometMagacinskeKarticeServiceInterface;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface.PrometniDokumentServiceInterface;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface.RobaIliUslugaServiceInterface;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Service
public class MagacinskaKarticaService implements MagacinskaKarticaServiceInterface {

	@Autowired
	MagacinskaKarticaRepository magacinskaKarticaRepository;
	
	@Autowired
	RobaIliUslugaServiceInterface robaIliUslugaServiceInterface;
	
	@Autowired
	PoslovnaGodinaServiceInterface poslovnaGodinaServiceInterface;
	
	@Autowired
	MagacinService magacinServiceInterface;
	
	@Autowired
	PrometMagacinskeKarticeServiceInterface prometMagacinskeKarticeServiceInterface;
	
	@Autowired
	PrometniDokumentServiceInterface prometniDokumentServiceInterface;
	
	@Autowired
	RobaIliUslugaRepository riliureposl;
	
	@Autowired
	MagacinRepository magacinrepos;
	
	@Autowired
	PoslovnaGodinaRepository posgodrepos;
	
	@Autowired
	PrometMagacinskeKarticeRepository prrepos;
	
	@Override
	public List<MagacinskaKarticaDTO> findAll() {
		List<MagacinskaKartica> magacinskaKarticas = magacinskaKarticaRepository.findAll();
		
		List<MagacinskaKarticaDTO> karicaDTOs = new ArrayList<MagacinskaKarticaDTO>();
		for(MagacinskaKartica m : magacinskaKarticas) {
			karicaDTOs.add(new MagacinskaKarticaDTO(m));
		}
		
		return karicaDTOs;
	}

	@Override
	public MagacinskaKarticaDTO save(MagacinskaKarticaDTO mkDTO) throws Exception {
		Integer magacinId = mkDTO.getMagacin();
		Magacin magacin = magacinrepos.findOneBySifraMagacina(magacinId);
		
		Integer poslovnaGodinaId = mkDTO.getBrojPoslovneGodine();
		PoslovnaGodina poslovnaGodina = posgodrepos.findOneByBrojGodine(poslovnaGodinaId);
		
		Integer robaId = mkDTO.getPoslovnaGodina();
		RobaIliUsluga robaIliUsluga = riliureposl.findOneBySifra(robaId);
		
		MagacinskaKartica mk = new MagacinskaKartica();
		mk.setPocetnoStanjeKolicinski(mkDTO.getPocetnoStanjeKolicinski());
		mk.setPrometIzlazaKolicinski(mkDTO.getPrometUlazaKolicinski());
		mk.setPrometIzlazaKolicinski(mkDTO.getPrometIzlazaKolicinski());
		mk.setUkupnaKolicina(mkDTO.getUkupnaKolicina());
		mk.setPocetnoStanjeVrednosno(mkDTO.getPocetnoStanjeVrednosno());
		mk.setPrometUlazaVrednosno(mkDTO.getPrometUlazaVrednosno());
		mk.setPrometIzlazaVrednosno(mkDTO.getPrometIzlazaVrednosno());
		mk.setUkupnaVrednost(mkDTO.getUkupnaVrednost());
		mk.setCena(mkDTO.getCena());
		mk.setMagacin(magacin);
		mk.setPoslovnaGodina(poslovnaGodina);
		mk.setRobaIliUsluga(robaIliUsluga);
		
		mk = magacinskaKarticaRepository.save(mk);
		
		return new MagacinskaKarticaDTO(mk);
	}

	@Override
	public MagacinskaKarticaDTO findOneById(Integer id) {
		MagacinskaKartica mk = magacinskaKarticaRepository.findOneById(id);
		return new MagacinskaKarticaDTO(mk);
	}

	@Override
	public MagacinskaKartica findOneByRobaIliUslugaAndPoslovnaGodinaAndMagacin(Integer robaIliUslugaId,
			Integer poslovnaGodinaId, Integer sifraMagacina) throws Exception{
		RobaIliUsluga robaIliUsluga = riliureposl.findOneBySifra(robaIliUslugaId);
		PoslovnaGodina poslovnaGodina = poslovnaGodinaServiceInterface.findByBrojGodine(poslovnaGodinaId);

		Magacin magacin = magacinrepos.findOneBySifraMagacina(sifraMagacina);
		
		MagacinskaKartica kartica = magacinskaKarticaRepository.findOneByRobaIliUsluga_sifraAndPoslovnaGodina_brojGodineAndMagacin_sifraMagacina(robaIliUslugaId, poslovnaGodinaId, sifraMagacina);
		
		if(kartica == null) {
			kartica = new MagacinskaKartica();
			kartica.setPrometUlazaKolicinski(0);
			kartica.setPrometIzlazaKolicinski(0);
			kartica.setUkupnaKolicina(0);
			kartica.setPrometIzlazaKolicinski(0);
			kartica.setPrometUlazaVrednosno(0);
			kartica.setUkupnaVrednost(0);
			kartica.setCena(0);
			kartica.setMagacin(magacin);
			kartica.setRobaIliUsluga(robaIliUsluga);
			
			kartica.setPoslovnaGodina(poslovnaGodina);
			List<MagacinskaKartica> kartice = magacinskaKarticaRepository.findByMagacin_sifraMagacinaAndRobaIliUsluga_sifra(sifraMagacina, robaIliUslugaId);
			if(kartice.size()!=0) {
				System.out.println("Postoji pocetno stanje za ovu robu ili uslugu!");
				MagacinskaKartica kartica2 = kartice.get(kartice.size()-1);
				
				kartica.setPocetnoStanjeKolicinski(kartica2.getUkupnaKolicina());
				kartica.setPocetnoStanjeVrednosno(kartica2.getPocetnoStanjeVrednosno());
				
				kartica = magacinskaKarticaRepository.save(kartica);
				Date date = new Date();
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				
				
				PrometMagacinskeKartice promet = new PrometMagacinskeKartice();
				promet.setRedniBroj(0+"-"+calendar.get(Calendar.YEAR));
				promet.setVrstaPrometa(VrstaPrometa.PS);
				promet.setSmer(Smer.U);
				promet.setKolicina(kartica.getPocetnoStanjeKolicinski());
				promet.setCena(kartica.getCena());
				promet.setVrednost(kartica.getPocetnoStanjeVrednosno());
				promet.setDokument(VrstaPrometa.PS.label);
				promet.setDatumPrometa(new Date());
				promet.setMagacinskaKartica(kartica);
				
				
				
				prometMagacinskeKarticeServiceInterface.save(kartica.getId(),new PrometMagacinskeKarticeDTO(promet));
			}else {
				System.out.println("za ovu robu ili uslugu ili poslovnu godinu ne postoji ni jedna magacinska kartica");
				
				kartica.setPocetnoStanjeKolicinski(0);
				kartica.setPocetnoStanjeVrednosno(0);
				kartica = magacinskaKarticaRepository.save(kartica);
			}
			
			
		}
		return kartica;
	}

	@Override
	public MagacinskaKarticaDTO findOneByMagacin_sifraMagacinaAndRobaIliUsluga_sifra(Integer sifraMagacina,Integer sifraRobeIliUsluge) {
		MagacinskaKartica magacinskeKartice = magacinskaKarticaRepository.findOneByMagacin_sifraMagacinaAndRobaIliUsluga_sifra(sifraMagacina,sifraRobeIliUsluge);
		return new MagacinskaKarticaDTO(magacinskeKartice);
		
	}

	@Override
	public List<MagacinskaKarticaDTO> findByRobaIliUsluga_sifraAndPoslovnaGodina_brojGodineAndMagacin_sifraMagacina(
			Integer sifraRobeUsluge, Integer brojGodine, Integer sifraMagacina) {// TODO Auto-generated method stub
		
		List<MagacinskaKartica> kartice = new ArrayList<MagacinskaKartica>();
		List<MagacinskaKarticaDTO> dtos = new ArrayList<MagacinskaKarticaDTO>();
		if(sifraMagacina==0 && sifraRobeUsluge==0 && brojGodine==0) {
			kartice = magacinskaKarticaRepository.findAll();
		}else if(sifraMagacina!=0 && sifraRobeUsluge!=0 && brojGodine!=0) {
			kartice = magacinskaKarticaRepository.findByRobaIliUsluga_sifraAndPoslovnaGodina_brojGodineAndMagacin_sifraMagacina(sifraRobeUsluge, brojGodine, sifraMagacina);
		}else if(sifraMagacina!=0 && sifraRobeUsluge!=0) {
			kartice = magacinskaKarticaRepository.findByMagacin_sifraMagacinaAndRobaIliUsluga_sifra(sifraMagacina, sifraRobeUsluge);
		}else if(sifraRobeUsluge!=0 && brojGodine!=0) {
			kartice = magacinskaKarticaRepository.findByPoslovnaGodina_brojGodineAndRobaIliUsluga_sifra(brojGodine, sifraRobeUsluge);
		}else if(sifraMagacina!=0 &&brojGodine!=0) {
			kartice = magacinskaKarticaRepository.findByMagacin_sifraMagacinaAndPoslovnaGodina_brojGodine(sifraMagacina, brojGodine);
		}else if(sifraMagacina!=0) {
			kartice = magacinskaKarticaRepository.findByMagacin_sifraMagacina(sifraMagacina);
		}
		else if(sifraRobeUsluge!=0) {
			kartice = magacinskaKarticaRepository.findByRobaIliUsluga_sifra(sifraRobeUsluge);
		}
		else if(brojGodine!=0) {
			kartice = magacinskaKarticaRepository.findByPoslovnaGodina_brojGodine(brojGodine);
		}
		for (MagacinskaKartica k : kartice) {
			dtos.add(new MagacinskaKarticaDTO(k));
		}
		return dtos;
	}

	@Override
	public List<MagacinskaKarticaDTO> findByMagacin_sifraMagacinaAndRobaIliUsluga_sifra(Integer sifraMagacina,
			Integer sifraRobeIliUsluge) {
		List<MagacinskaKartica> magacinskeKartice = magacinskaKarticaRepository.findByMagacin_sifraMagacinaAndRobaIliUsluga_sifra(sifraMagacina, sifraRobeIliUsluge);
		List<MagacinskaKarticaDTO> ms = new ArrayList<MagacinskaKarticaDTO>();
		for(MagacinskaKartica m: magacinskeKartice) {
			ms.add(new MagacinskaKarticaDTO(m));
		}
		return ms;
	}

	@Override
	public List<MagacinskaKarticaDTO> findByMagacin_sifraMagacinaAndPoslovnaGodina_brojGodine(Integer sifraMagacina,
			Integer brojGodine) {
		List<MagacinskaKartica> mks = magacinskaKarticaRepository.findByMagacin_sifraMagacinaAndPoslovnaGodina_brojGodine(sifraMagacina, brojGodine);
		List<MagacinskaKarticaDTO> ms = new ArrayList<MagacinskaKarticaDTO>();
		for(MagacinskaKartica m: mks) {
			ms.add(new MagacinskaKarticaDTO(m));
		}
		return ms;
	}

	@Override
	public List<MagacinskaKarticaDTO> findByPoslovnaGodina_brojGodineAndRobaIliUsluga_sifra(Integer brojGodine,
			Integer sifraRobeIliUsluge) {
		List<MagacinskaKartica> mks = magacinskaKarticaRepository.findByPoslovnaGodina_brojGodineAndRobaIliUsluga_sifra(brojGodine, sifraRobeIliUsluge);
		List<MagacinskaKarticaDTO> ms = new ArrayList<MagacinskaKarticaDTO>();
		for(MagacinskaKartica m: mks) {
			ms.add(new MagacinskaKarticaDTO(m));
		}
		return ms;
	}

	@Override
	public List<MagacinskaKarticaDTO> findByMagacin_sifraMagacina(Integer sifraMagacina) {
		List<MagacinskaKartica> mks = magacinskaKarticaRepository.findByMagacin_sifraMagacina(sifraMagacina);
		List<MagacinskaKarticaDTO> ms = new ArrayList<MagacinskaKarticaDTO>();
		for(MagacinskaKartica m: mks) {
			ms.add(new MagacinskaKarticaDTO(m));
		}
		return ms;
	}

	@Override
	public List<MagacinskaKarticaDTO> findByPoslovnaGodina_brojGodine(Integer brojGodine) {
		List<MagacinskaKartica> mks = magacinskaKarticaRepository.findByPoslovnaGodina_brojGodine(brojGodine);
		List<MagacinskaKarticaDTO> ms = new ArrayList<MagacinskaKarticaDTO>();
		for(MagacinskaKartica m: mks) {
			ms.add(new MagacinskaKarticaDTO(m));
		}
		return ms;
	}

	@Override
	public List<MagacinskaKarticaDTO> findByRobaIliUsluga_sifra(Integer sifraRobeIliUsluge) {
		List<MagacinskaKartica> mks = magacinskaKarticaRepository.findByRobaIliUsluga_sifra(sifraRobeIliUsluge);
		List<MagacinskaKarticaDTO> ms = new ArrayList<MagacinskaKarticaDTO>();
		for(MagacinskaKartica m: mks) {
			ms.add(new MagacinskaKarticaDTO(m));
		}
		return ms;
	}

	@Override
	public MagacinskaKarticaDTO nivelacija(MagacinskaKarticaDTO dto) {
		MagacinskaKartica kartica = magacinskaKarticaRepository.findOneById(dto.getId());
		
		double nivelacija = kartica.getCena()*kartica.getUkupnaKolicina()-kartica.getUkupnaVrednost();
		System.out.println("\nNivelacija: "+nivelacija);
		PrometMagacinskeKartice promet = new PrometMagacinskeKartice();
		
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		
		promet.setRedniBroj(0+"-"+calendar.get(Calendar.YEAR));
		promet.setVrstaPrometa(VrstaPrometa.NI);
		promet.setSmer(Smer.U);
		promet.setKolicina(0);
		promet.setCena(0);
		promet.setVrednost(nivelacija);
		promet.setDokument(VrstaPrometa.NI.label);
		promet.setDatumPrometa(new Date());
		promet.setMagacinskaKartica(kartica);
		
		kartica.setPrometUlazaVrednosno(kartica.getPrometUlazaVrednosno()+promet.getVrednost());
		
		double ukupnaVrednost = kartica.getPocetnoStanjeVrednosno()+kartica.getPrometUlazaVrednosno()-kartica.getPrometIzlazaVrednosno();
		kartica.setUkupnaVrednost(ukupnaVrednost);
//		PrometMagacinskeKarticeDTO prdto = new PrometMagacinskeKarticeDTO(promet);
		promet = prrepos.save(promet);
		kartica = magacinskaKarticaRepository.save(kartica);
		
		return new MagacinskaKarticaDTO(kartica);
	}

	@Override
	public ResponseEntity report(String broj) {
		String connectionUrl = "jdbc:mysql://localhost/magacinsko";
		
		JasperPrint jp;
		ByteArrayInputStream bis;
		try {
			File file = new File("src\\main\\resources\\PrometiReport.jasper");
			InputStream is = new FileInputStream(file);
			Map<String, Object> param = new HashMap();
			param.put("redniBroj", broj);
			Connection conn = DriverManager.getConnection(connectionUrl , "root", "root");
			jp = JasperFillManager.fillReport(is,
					param, conn);
			bis = new ByteArrayInputStream(JasperExportManager.exportReportToPdf(jp));
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Disposition", "inline; filename=citiesreport.pdf");

			return ResponseEntity
		       		.ok()
		       		.headers(headers)
		       		.contentType(MediaType.APPLICATION_PDF)
		       		.body(new InputStreamResource(bis));
		} catch (JRException | IOException | SQLException e) {
			e.printStackTrace();
			throw new ResponseStatusException(
			          HttpStatus.NOT_FOUND, "Neka greska", e);
		}
	}

}
