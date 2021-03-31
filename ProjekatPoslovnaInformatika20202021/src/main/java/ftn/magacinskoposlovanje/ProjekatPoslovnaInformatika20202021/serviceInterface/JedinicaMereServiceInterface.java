package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface;

import java.util.List;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.entityDTO.JedinicaMereDTO;
public interface JedinicaMereServiceInterface {

	public List<JedinicaMereDTO> findAll();
	public JedinicaMereDTO findOneById(Integer id);
	public JedinicaMereDTO save(JedinicaMereDTO jedinicaMereDTO);
	public void remove(Integer idJediniceMere);
	public JedinicaMereDTO update(Integer id, JedinicaMereDTO jedinicaMereDTO);
}
