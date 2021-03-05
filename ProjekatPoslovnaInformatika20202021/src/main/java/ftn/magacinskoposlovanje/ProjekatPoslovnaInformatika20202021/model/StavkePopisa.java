package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
@Entity
@Table(name = "stavke_popisa")
public class StavkePopisa {

	@Id
	@Column(name = "rbr", nullable = false)
	private int rbr;
	
	@Column(name = "popisana_kolicina", nullable = false)
	private double popisanaKolicina;

	@ManyToOne
	@JoinColumn(name="popisni_dokument_id", referencedColumnName="broj_popisa", nullable=false)
	private PopisniDokument popisniDokument;
	
	@ManyToOne
	@JoinColumn(name="roba_usluga_id", referencedColumnName="sifra", nullable=false)
	private RobaIliUsluga robaIliUsluga;
}
