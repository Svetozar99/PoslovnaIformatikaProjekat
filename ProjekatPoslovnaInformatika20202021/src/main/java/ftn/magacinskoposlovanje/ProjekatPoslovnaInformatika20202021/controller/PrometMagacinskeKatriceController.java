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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.entityDTO.PrometMagacinskeKarticeDTO;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface.PrometMagacinskeKarticeServiceInterface;

import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface.PrometniDokumentServiceInterface;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;


@RestController
@RequestMapping(value = "api/promet-magacinske-kartice")
public class PrometMagacinskeKatriceController {
	
	@Autowired
	private PrometMagacinskeKarticeServiceInterface prometMagacinskeKarticeService;
	
	@GetMapping
	public ResponseEntity<List<PrometMagacinskeKarticeDTO>> getPrometiMagKart(){
		
		return ResponseEntity.ok().body(prometMagacinskeKarticeService.findAll());
	}
	
	@GetMapping(value = "/{idKartice}")
	public ResponseEntity<List<PrometMagacinskeKarticeDTO>> getPrometiMagKartByKartica(@PathVariable ("idKartice") Integer idKartice){
		
		return ResponseEntity.ok().body(prometMagacinskeKarticeService.findByMagacinskaKartica(idKartice));
	}
	
	@GetMapping(value = "storniraj/{redniBroj}")
	public ResponseEntity<PrometMagacinskeKarticeDTO> getOnePromet(@PathVariable("redniBroj") String redniBroj) throws Exception{
		
		return ResponseEntity.ok().body(prometMagacinskeKarticeService.findByRedniBroj(redniBroj));
	}
	
}
