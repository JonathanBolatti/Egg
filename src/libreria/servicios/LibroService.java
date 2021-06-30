package libreria.servicios;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import libreria.entidades.Libro;
import libreria.persistencia.LibroJpaController;
import libreria.persistencia.exceptions.NonexistentEntityException;

public class LibroService {

	LibroJpaController libroJpa = new LibroJpaController();

	public void crearLibro(Libro libro) {
		try {
			libroJpa.create(libro);
		} catch (Exception ex) {
			Logger.getLogger(LibroService.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void eliminarLibro(Long id) {
		try {
			libroJpa.destroy(id);
			
		} catch (NonexistentEntityException ex) {
			Logger.getLogger(LibroService.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	
	public void actulizarLibro(Libro libro){
		try {
//		Libro libro = new Libro(); 
//		libro.setIsbn(isbn);
//		libro.setTitulo(titulo);
//		libro.setAnio(anio);
//		libro.setEjemplares(ejemplares); 
//		libro.setPrestados(prestados);
		libroJpa.edit(libro); 
		
		System.out.println("Actualizado correctamente");
	
		} catch (Exception ex) {
			System.out.println("Mensaje en guardar: "+ ex.getMessage());
			Logger.getLogger(ClienteService.class.getName()).log(Level.SEVERE, null, ex);
		}
		//return null;
	}
	
	public void buscarLibroISBN(Long id) {
		Libro libro = libroJpa.findLibro(id);
		System.out.println("\nResultado de la Busqueda: ");
		System.out.println(libro.toString());
	}

	public void buscarLibroPorNombre(String libroBuscado) {
		Libro libro = libroJpa.libroBuscadoPorNombre(libroBuscado);
		System.out.println("\nResultado de la Busqueda: ");
		System.out.println(libro.toString());
	}

	/////mostrar lista de libros////////
	public void mostrarListaLibros(List<Libro> lib) {
		for (Libro libro : lib) {
			System.out.println(libro.toString());
		}
	}

	///////crear lista de libros//////////
	public void traerLibrosDesdeMySql() {
		List<Libro> listaLibros = libroJpa.findLibroEntities();
		mostrarListaLibros(listaLibros);

	}
}
