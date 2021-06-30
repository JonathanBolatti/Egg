package libreria.servicios;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import libreria.entidades.Editorial;
import libreria.persistencia.EditorialJpaController;
import libreria.persistencia.exceptions.NonexistentEntityException;

public class EditorialService {

	EditorialJpaController editorialJpa = new EditorialJpaController();

	public void crearEditorial(Editorial editorial) {
		try {
			editorialJpa.create(editorial);
		} catch (Exception ex) {
			Logger.getLogger(EditorialService.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public void eliminarEditorial(String idEditorial) {
		try {
			editorialJpa.destroy(idEditorial);
		} catch (NonexistentEntityException ex) {
			Logger.getLogger(EditorialService.class.getName()).log(Level.SEVERE, null, ex);
		}
	}


	public void mostrarListaEditorial(List<Editorial> edi) {
		for (Editorial editorial : edi) {
			System.out.println(editorial.toString());
		}
	}
	
	public void traerEditorialDesdeMysql() {
		List<Editorial> ListaEditorial = editorialJpa.findEditorialEntities();
		mostrarListaEditorial(ListaEditorial);
	}

	
}//fin class
