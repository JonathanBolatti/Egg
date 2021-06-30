package libreria.servicios;

import java.util.Scanner;
import libreria.entidades.Libro;
import libreria.persistencia.LibroJpaController;

public class PrestamoService extends Libro{
	
Scanner reader = new Scanner(System.in); 
Scanner sc = new Scanner(System.in); 
LibroService libroService = new LibroService();
LibroJpaController libroJpa = new LibroJpaController();

	
	public void prestarUnLibro(String libroSolicitado) {
		
		Libro libro = libroJpa.libroBuscadoPorNombre(libroSolicitado);
		System.out.println("\nResultado de la Busqueda: ");
		System.out.println(libro.toString());
		 
				
			if (libro.equals(libroSolicitado) && libro.getEjemplares() > 0) {
				System.out.println("El libro esta en stock, hay " + libro.getEjemplares() + " unidades");
				System.out.print("Cuantos Libros desea Alquilar? =>");
				int unidades = sc.nextInt();
			

				if (unidades <= libro.getEjemplares()) {
					Integer libro1 = libro.getEjemplares() - unidades;
					libro.setEjemplares(libro1);
					libro.setPrestados(unidades);
					System.out.println("ahora quedan " + libro.getEjemplares() + " unidades en Stock");
					
				}
				if (unidades > libro.getEjemplares()) {
					System.out.println("Cantidad seleccionda Mayor al Stock Actual");
				}

			} else if (libro.getEjemplares() == 0) {
				System.out.println("El libro no esta en Stock");

			}
		}

	}//fin class


