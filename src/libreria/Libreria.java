package libreria;

import libreria.interfaz.MenuLibreria;

/**
 *
 * @author Jonathan
 */
public class Libreria {

	public static void main(String[] args) {
		MenuLibreria menu = new MenuLibreria(); 
		menu.menuOpciones();
		
		

//		////////////AUTOR/////////////////1
//		AutorService autService = new AutorService();
//		Autor autor = new Autor("Guionista", "Florencia Bonelli");
//		autService.crearAutor(autor);
//		Autor autor2 = new Autor("Novelista", "Garcia Marquez");
//		autService.crearAutor(autor2);
//		autService.traerAutoresDesdeMySql();
//		System.out.println("-----------------------------------------------------");
//
//		////////////LIBRO/////////////////
//		LibroService libService = new LibroService();
//		Libro libro = new Libro(33454568, "La Joya", 1988, 1000, 250);
//		libService.crearLibro(libro);
//		Libro libro2 = new Libro(32132456, "Harry Potter", 2000, 5000, 2500);
//		libService.crearLibro(libro2);
//		libService.traerLibrosDesdeMySql();
//		System.out.println("-----------------------------------------------------");
//
//		////////////EDITORIAL/////////////////
//		EditorialService editService = new EditorialService();
//		Editorial editorial = new Editorial("AA251KW", "EL GRAFICO");
//		editService.crearEditorial(editorial);
//		editService.traerEditorialDesdeMysql();
//		System.out.println("-----------------------------------------------------");
//
//		////////////CLIENTE/////////////////
//		ClienteService cliService = new ClienteService();
//		Cliente cliente = new Cliente(33887141L, "Jonathan", "Bolatti", "Francisco Baigorri", "0358-155131021");
//		cliService.crearCliente(cliente);
//		cliService.traerClientesDesdeMySql();

	}

}
