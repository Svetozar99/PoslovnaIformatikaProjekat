package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model;

import javax.persistence.Column;
import javax.persistence.Entity;
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

	@Column(name = "rbr", nullable = false)
	@NonNull private int rbr;
	
	@Column(name = "popisana_kolicina", nullable = false)
	@NonNull private double popisanaKolicina;

	@ManyToOne
	@JoinColumn(name="popisni_dokument_id", referencedColumnName="brojPopisa", nullable=true)
	private PopisniDokument popisniDokument;
}