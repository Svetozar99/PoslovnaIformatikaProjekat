package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model;

public enum VrstaDokumenta {

	PR("Primka"),
	OT("Otpremnica"),
	MM("Medjumagacinski promet");
	
	public final String label;

    private VrstaDokumenta(String label) {
        this.label = label;
    }
}
