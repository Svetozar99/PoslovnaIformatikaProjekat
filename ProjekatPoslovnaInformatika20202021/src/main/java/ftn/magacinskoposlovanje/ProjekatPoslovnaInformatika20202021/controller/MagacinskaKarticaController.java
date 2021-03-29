package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.entityDTO.MagacinskaKarticaDTO;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.MagacinskaKartica;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.PrometMagacinskeKartice;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.Smer;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.VrstaPrometa;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface.MagacinskaKarticaServiceInterface;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface.PrometMagacinskeKarticeServiceInterface;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@RestController
@RequestMapping(value = "api/magacinska-kartica")
public class MagacinskaKarticaController {
	
	@Autowired
	private MagacinskaKarticaServiceInterface magaKarticaServiceInterface;
	
	@Autowired
	private PrometMagacinskeKarticeServiceInterface prometMagacinskeKarticeServiceInterface;

	@GetMapping
	public ResponseEntity<List<MagacinskaKarticaDTO>> getMagacinskeKartice(){
		List<MagacinskaKartica> magacinskaKarticas = magaKarticaServiceInterface.findAll();
		
		List<MagacinskaKarticaDTO> karicaDTOs = new ArrayList<MagacinskaKarticaDTO>();
		for(MagacinskaKartica m : magacinskaKarticas) {
			karicaDTOs.add(new MagacinskaKarticaDTO(m));
		}
		return new ResponseEntity<List<MagacinskaKarticaDTO>>(karicaDTOs, HttpStatus.OK);
	}
	
	@GetMapping(value = "{sifraMagacina}/{sifraRobeIliUsluge}/{brojPoslovneGodine}")
	public ResponseEntity<List<MagacinskaKarticaDTO>> getMagKartByRobaIliUslua(@PathVariable("sifraMagacina") Integer sifraMagacina, @PathVariable("sifraRobeIliUsluge") Integer sifraRobeIliUsluge, 
			@PathVariable("brojPoslovneGodine") Integer brojPoslovneGodine){
		List<MagacinskaKartica> kartice = new ArrayList<MagacinskaKartica>();
		List<MagacinskaKarticaDTO> dtos = new ArrayList<MagacinskaKarticaDTO>();
		if(sifraMagacina==0 && sifraRobeIliUsluge==0 && brojPoslovneGodine==0) {
			kartice = magaKarticaServiceInterface.findAll();
		}else if(sifraMagacina!=0 && sifraRobeIliUsluge!=0 && brojPoslovneGodine!=0) {
			kartice = magaKarticaServiceInterface.findByRobaIliUsluga_sifraAndPoslovnaGodina_brojGodineAndMagacin_sifraMagacina(sifraRobeIliUsluge, brojPoslovneGodine, sifraMagacina);
		}else if(sifraMagacina!=0 && sifraRobeIliUsluge!=0) {
			kartice = magaKarticaServiceInterface.findByMagacin_sifraMagacinaAndRobaIliUsluga_sifra(sifraMagacina, sifraRobeIliUsluge);
		}else if(sifraRobeIliUsluge!=0 && brojPoslovneGodine!=0) {
			kartice = magaKarticaServiceInterface.findByPoslovnaGodina_brojGodineAndRobaIliUsluga_sifra(brojPoslovneGodine, sifraRobeIliUsluge);
		}else if(sifraMagacina!=0 &&brojPoslovneGodine!=0) {
			kartice = magaKarticaServiceInterface.findByMagacin_sifraMagacinaAndPoslovnaGodina_brojGodine(sifraMagacina, brojPoslovneGodine);
		}else if(sifraMagacina!=0) {
			kartice = magaKarticaServiceInterface.findByMagacin_sifraMagacina(sifraMagacina);
		}
		else if(sifraRobeIliUsluge!=0) {
			kartice = magaKarticaServiceInterface.findByRobaIliUsluga_sifra(sifraRobeIliUsluge);
		}
		else if(brojPoslovneGodine!=0) {
			kartice = magaKarticaServiceInterface.findByPoslovnaGodina_brojGodine(brojPoslovneGodine);
		}
		for (MagacinskaKartica k : kartice) {
			dtos.add(new MagacinskaKarticaDTO(k));
		}
		return ResponseEntity.ok(dtos);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<MagacinskaKarticaDTO> getOneMagKar(@PathVariable("id") Integer id){
		
		MagacinskaKartica mk = magaKarticaServiceInterface.findOneById(id);
		
		return new ResponseEntity<MagacinskaKarticaDTO>(new MagacinskaKarticaDTO(mk),HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<MagacinskaKarticaDTO> nivelacija(@RequestBody MagacinskaKarticaDTO dto){
		
		MagacinskaKartica kartica = magaKarticaServiceInterface.findOneById(dto.getId());
		
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
		
		promet = prometMagacinskeKarticeServiceInterface.save(promet);
		kartica = magaKarticaServiceInterface.save(kartica);
		
		return ResponseEntity.ok().build();
	}
	
	public ResponseEntity getReport(){
		String connectionUrl = "jdbc:mysql://localhost/magacinsko";
		JasperPrint jp;
		ByteArrayInputStream bis;
		try {
			jp = JasperFillManager.fillReport(
				getClass().getResource("/jasper/ZaposleniPoRadnimMestima.jasper").openStream(),
				null, DriverManager.getConnection(connectionUrl , "username", "password"));
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
