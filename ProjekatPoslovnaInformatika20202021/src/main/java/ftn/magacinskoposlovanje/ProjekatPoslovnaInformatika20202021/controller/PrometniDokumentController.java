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
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.entityDTO.PrometniDokumentDTO;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.Magacin;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.PoslovnaGodina;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.PoslovniPartner;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.Preduzece;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.PrometniDokument;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.Status;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.VrstaDokumenta;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface.MagacinServiceInterface;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface.PoslovnaGodinaServiceInterface;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface.PoslovniPartnerServiceInterface;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface.PreduzeceServiceInterface;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface.PrometniDokumentServiceInterface;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@RestController
@RequestMapping(value = "api/prometni-dokument")
public class PrometniDokumentController {

	@Autowired
	private PrometniDokumentServiceInterface prometniDokumentServiceInterface;
	
	@Autowired
	private PoslovniPartnerServiceInterface poslovniPartnerServiceInterface;
	
	@Autowired
	private PoslovnaGodinaServiceInterface poslovnaGodinaServiceInterface;
	
	@Autowired
	private PreduzeceServiceInterface preduzeceServiceInterface;
	
	@Autowired
	private MagacinServiceInterface magacinServiceInterface;
	
	@PostMapping
	public ResponseEntity<PrometniDokumentDTO> addPrometniDokument(@RequestBody PrometniDokumentDTO dto){
		System.out.println("\n\tPost!");
		System.out.println(dto.toString());
		
		PoslovniPartner poslovniPartner = new PoslovniPartner();
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		PoslovnaGodina poslovnaGodina = poslovnaGodinaServiceInterface.findByBrojGodine(calendar.get(Calendar.YEAR));
		Preduzece preduzece = preduzeceServiceInterface.findOne(dto.getIdPreduzeca());
		if(poslovnaGodina == null) {
			PoslovnaGodina godina = poslovnaGodinaServiceInterface.findByBrojGodine(calendar.get(Calendar.YEAR)-1);
			godina.setZakljucena(true);
			poslovnaGodinaServiceInterface.save(godina);
			poslovnaGodina = new PoslovnaGodina();
			poslovnaGodina.setBrojGodine(calendar.get(Calendar.YEAR));
			poslovnaGodina.setPreduzece(preduzece);
			poslovnaGodina.setZakljucena(false);
			poslovnaGodina = poslovnaGodinaServiceInterface.save(poslovnaGodina);
		}
		Magacin ulazniMagacin = magacinServiceInterface.findOne(dto.getSifraMagacina1());
		Magacin izlazniMagacin = magacinServiceInterface.findOne(dto.getSifraMagacina2());
		
		PrometniDokument prometniDokument = new PrometniDokument();
		prometniDokument.setRedniBroj(dto.getBrojPrometnogDokumenta());
		prometniDokument.setDatum(dto.getDatumIzdavanja());
		prometniDokument.setStatus(Status.P);
		prometniDokument.setPoslovnaGodina(poslovnaGodina);
		
		if(dto.getVrstaDokumenta().equals(VrstaDokumenta.PR.toString())) {
			poslovniPartner = poslovniPartnerServiceInterface.findOneBySifraPartnera(dto.getSifraPoslovnogPartnera());
			
			prometniDokument.setVrstaDokumenta(VrstaDokumenta.PR);
			prometniDokument.setPoslovniPartner(poslovniPartner);
			prometniDokument.setPreduzece(preduzece);
			prometniDokument.setUlazniMagacin(ulazniMagacin);
		}else if(dto.getVrstaDokumenta().equals(VrstaDokumenta.OT.toString())) {
			poslovniPartner = poslovniPartnerServiceInterface.findOneBySifraPartnera(dto.getSifraPoslovnogPartnera());
			
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
		prometniDokument = prometniDokumentServiceInterface.save(prometniDokument);
		dto.setId(prometniDokument.getId());
		return new ResponseEntity<PrometniDokumentDTO>(dto, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<String> getFormatBroj(){
		
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		
		Integer id = prometniDokumentServiceInterface.findByMaxid();
		
		if(id == null) {
			id = 0;
		}
		String brojDokumenta = "";
		int trenutnaGodina = calendar.get(Calendar.YEAR);
		brojDokumenta = id+1 + "-" + trenutnaGodina;
		return new ResponseEntity<String>(brojDokumenta, HttpStatus.OK);
	}
	
	@GetMapping(value = "/report/{redniBroj}")
	public ResponseEntity getReport(@PathVariable("redniBroj") String redniBroj){
		String connectionUrl = "jdbc:mysql://localhost/magacinsko";
		
		JasperPrint jp;
		ByteArrayInputStream bis;
		try {
			File file = new File("src\\main\\resources\\OtpremnicaReport.jasper");
			InputStream is = new FileInputStream(file);
			Map<String, Object> param = new HashMap();
			param.put("redniBroj", redniBroj);
			Connection conn = DriverManager.getConnection(connectionUrl , "root", "root");
			jp = JasperFillManager.fillReport(is,
					param, conn);
			bis = new ByteArrayInputStream(JasperExportManager.exportReportToPdf(jp));
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Disposition", "inline; filename=otpremnica_"+redniBroj+".pdf");

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
