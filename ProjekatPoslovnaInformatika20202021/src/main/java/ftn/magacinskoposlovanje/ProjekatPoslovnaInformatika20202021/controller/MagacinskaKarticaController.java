package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.controller;

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
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@RestController
@RequestMapping(value = "api/magacinska-kartica")
public class MagacinskaKarticaController {
	
	@Autowired
	private MagacinskaKarticaServiceInterface magaKarticaServiceInterface;
	
	@GetMapping
	public ResponseEntity<List<MagacinskaKarticaDTO>> getMagacinskeKartice(){
		
		return ResponseEntity.ok().body(magaKarticaServiceInterface.findAll());
	}
	
	@GetMapping(value = "{sifraMagacina}/{sifraRobeIliUsluge}/{brojPoslovneGodine}")
	public ResponseEntity<List<MagacinskaKarticaDTO>> getMagKartByRobaIliUslua(@PathVariable("sifraMagacina") Integer sifraMagacina, @PathVariable("sifraRobeIliUsluge") Integer sifraRobeIliUsluge, 
			@PathVariable("brojPoslovneGodine") Integer brojPoslovneGodine){
		
		return ResponseEntity.ok().body(magaKarticaServiceInterface.findByRobaIliUsluga_sifraAndPoslovnaGodina_brojGodineAndMagacin_sifraMagacina(sifraRobeIliUsluge, brojPoslovneGodine, sifraMagacina));
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<MagacinskaKarticaDTO> getOneMagKar(@PathVariable("id") Integer id){
		return ResponseEntity.ok().body(magaKarticaServiceInterface.findOneById(id));
	}
	
	@PutMapping
	public ResponseEntity<MagacinskaKarticaDTO> nivelacija(@RequestBody MagacinskaKarticaDTO dto){
		
		return ResponseEntity.ok().body(magaKarticaServiceInterface.nivelacija(dto));
	}
	
	@GetMapping(value = "/report/{ovde-treba-da-bude-id-kartice}")
	public ResponseEntity getReport(@PathVariable("ovde-treba-da-bude-id-kartice") String redniBroj){
		return ResponseEntity.ok().body(magaKarticaServiceInterface.report(redniBroj));
	}
}
