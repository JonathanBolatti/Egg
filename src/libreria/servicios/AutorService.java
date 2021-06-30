package libreria.servicios;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import libreria.entidades.Autor;
import libreria.persistencia.AutorJpaController;
import libreria.persistencia.exceptions.NonexistentEntityException;

public class AutorService {
	
	AutorJpaController autorJpa = new AutorJpaController(); 
	
	
	public void crearAutor(Autor autor){
		try {
			autorJpa.create(autor);
		} catch (Exception ex) {
			Logger.getLogger(AutorService.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public void eliminarAutor(String id){
		try {
			autorJpa.destroy(id);
		} catch (NonexistentEntityException ex) {
			Logger.getLogger(AutorService.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	
	public void mostrarListaAutores (List<Autor> autor){
		for(Autor aut : autor){
			System.out.println(aut.toString());
		}
	}
	
	public void traerAutoresDesdeMySql(){
		List<Autor>listaAutores = autorJpa.findAutorEntities(); 
		mostrarListaAutores(listaAutores);
	}

}//fin class
