package libreria.interfaz;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import libreria.entidades.Autor;
import libreria.entidades.Cliente;
import libreria.entidades.Editorial;
import libreria.entidades.Libro;
import libreria.servicios.AutorService;
import libreria.servicios.ClienteService;
import libreria.servicios.EditorialService;
import libreria.servicios.LibroService;
import libreria.servicios.PrestamoService;

public class MenuLibreria {

	//////////////////servicios//////////////
	/*AUTOR*/
	AutorService autService = new AutorService();
	/*CLIENTE*/
	ClienteService cliService = new ClienteService();
	/*LIBRO*/
	LibroService libService = new LibroService();
	/*EDITORIAL*/
	EditorialService editService = new EditorialService();
	/*PRESTAMO*/
	PrestamoService presService = new PrestamoService();

	Scanner sc = new Scanner(System.in).useDelimiter("\n");
	Scanner reader = new Scanner(System.in);

	public void menuOpciones() throws InputMismatchException {
		boolean done = true;
		do {
			try {
				System.out.println("\n***SISTEMA DE GESTION DE BIBLIOTECA**");
				System.out.println("      [======== M E N U ========] "
						+ "\n           • 1=> ABM DATOS"
						+ "\n           • 2=> PRESTAR"
						+ "\n           • 3=> DEVOLVER"
						+ "\n           • 4=> CONSULTAS"
						+ "\n           • 5=> Salir");
				System.out.print("      Seleccione una opcion =>");

				int opcion = sc.nextInt();

				switch (opcion) {
					case 1:
						ABMdatos();
						break;
					case 2:
						/*PRESTAR UN LIBRO*/
						PrestarUnLibroHoy();
						break;
					case 3:
						/*DEVOLVER LIBRO*/
						break;
					case 4:
						consultasLibreria();
						break;
					case 5:
						finalizarPrograma();
						done = false;
						break;
					default:
						System.out.println("Esa opcion no esta disponible.");
						break;
				}
			} catch (InputMismatchException ex) {
				ex.getStackTrace();
				System.out.println("Error! debe introducir un numero");
				/*con este scanner evitamos que el codigo entre en un bucle infinito*/
				sc.nextLine();
			}

		} while (done);
	}

	private void ABMdatos() {
		System.out.println("\n|||ALTAS, BAJAS Y MODIFICACION DE ENTIDADES|||");
		System.out.println("==Elija una Opcion==");
		System.out.println("1) Dar de alta una Entidad."
				+ "\n2) Eliminar una Entidad."
				+ "\n3) Modificar una Entidad."
				+ "\n4) <= Volver al menu Principal");
		System.out.print("Opcion =>");
		int opcion = sc.nextInt();
		switch (opcion) {
			case 1:
				AltaDeEntidad();
				break;
			case 2:
				EliminarUnaEntidad();
				break;
			case 3:
				modificarDatosEntidades();
				break;
			case 4:
				//menu principal
				menuOpciones();
			default:
				System.out.println("Opcion No Disponible");

		}

	}

	private void AltaDeEntidad() {
		System.out.println("==Elija una Opcion==");
		System.out.println("1) Cargar un Cliente."
				+ "\n2) Cargar un Libro."
				+ "\n3) Cargar un Autor."
				+ "\n4) Cargar una Editorial."
				+ "\n5) Volver atras. <=");
		System.out.print("Opcion =>");
		int opcion = sc.nextInt();
		switch (opcion) {
			case 1:
				Cliente cliente = new Cliente();
				System.out.println("|||CARGAR UN CLIENTE|||");
				System.out.print("Ingrese DNI =>");
				cliente.setDocumento(sc.nextLong());
				System.out.print("Ingrese Nombre Cliente =>");
				cliente.setNombre(reader.nextLine());
				System.out.print("Ingrese Apellido Cliente =>");
				cliente.setApellido(reader.nextLine());
				System.out.print("Ingrese Direccion Cliente =>");
				cliente.setDomicilio(reader.nextLine());
				System.out.print("Ingrese Telefono Cliente =>");
				cliente.setTelefono(reader.nextLine());
				/*una buena idea aca seria mandar todos los datos a Cliente servicio
				para validar toda la inforamacion, si los datos cargados son correctos
				enviar el mensaje de autor cargado correctamente si no lanzar una excepcion*/
				cliService.crearCliente(cliente);
				System.out.println("***Cliente Cargado Exitosamente!***");
				break;
			case 2:
				Libro libro = new Libro();
				System.out.println("|||CARGAR UN LIBRO|||");
				System.out.print("Ingrese ISBN del Libro =>");
				libro.setIsbn(sc.nextLong());
				System.out.print("Ingrese Titulo =>");
				libro.setTitulo(reader.nextLine());
				System.out.print("Ingrese Año de Publicacion =>");
				libro.setAnio(sc.nextInt());
				System.out.print("Ingrese Cantidad de Libros Adquiridos =>");
				libro.setEjemplares(sc.nextInt());
				libService.crearLibro(libro);
				System.out.println("***Libro Cargado Exitosamente!***");
				break;
			case 3:
				Autor autor = new Autor();
				System.out.println("\n|||CARGAR UN AUTOR|||");
				System.out.print("Ingrese ID autor =>");
				autor.setId(reader.nextLine());
				System.out.print("Ingrese Nombre autor =>");
				autor.setNombre(reader.nextLine());
				autService.crearAutor(autor);
				System.out.println("Autor Cargado Exitosamente!");
				break;
			case 4:
				Editorial editorial = new Editorial();
				System.out.println("\n|||DAR DE ALTA UNA EDITORIAL|||");
				System.out.print("Ingrese ID Editorial =>");
				editorial.setId(reader.nextLine());
				System.out.print("Ingrese Nombre de la Editorial =>");
				editorial.setNombre(reader.nextLine());
				editService.crearEditorial(editorial);
				System.out.println("***Editorial creada Exitosamente!***");
				break;
			case 5:
				/*volver atras*/
				ABMdatos();
				break;
			default:
				System.out.println("Opcion Invalida, Intente Nuevamente");

		}
	}

	public void EliminarUnaEntidad() {
		System.out.println("\n|||ENTIDADES|||");
		System.out.println("Elija que desea ELiminar");
		System.out.println("1) Cliente."
				+ "\n2) Libro."
				+ "\n3) Autor."
				+ "\n4) Editorial."
				+ "\n5) Volver atras <=");
		System.out.print("Opcion =>");
		int opcion = sc.nextInt();
		switch (opcion) {
			case 1:
				/*eliminar clientes*/
				System.out.print("Introduce el DNI del Cliente que Desea Eliminar =>");
				Long id = sc.nextLong();
				cliService.eliminarCliente(id);
				System.out.println("El cliente se Elimino Exitosamente!");
				break;
			case 2:
				/*eliminar libros*/
				System.out.print("Introduce el ISBN del Libro que desea Eliminar =>");
				Long isbn = sc.nextLong();
				libService.eliminarLibro(isbn);
				break;
			case 3:
				/*eliminar autores*/
				System.out.print("Introduce ID del autor =>");
				String idAutor = reader.nextLine();
				autService.eliminarAutor(idAutor);
				break;
			case 4:
				/*eliminar editoriales*/
				System.out.print("Introduce el ID de la Editorial=>");
				String idEditorial = reader.nextLine();
				editService.eliminarEditorial(idEditorial);

				break;
			case 5:
				/*menu anterior*/
				ABMdatos();
				break;
			default:
				System.out.println("Opcion no disponible");
		}
	}

	private void consultasLibreria() {
		System.out.println("\n||CONSULTAS||");
		System.out.println("==Elija una Opcion==");
		System.out.println("1) ver Lista de Entidades."
				+ "\n2) Buscar Libro por ISBN."
				+ "\n3) Buscar Libro por Titulo."
				+ "\n4) Buscar Cliente."
				+ "\n5) Volver al Menu Principal <=");
		System.out.print("Opcion =>");
		int opcion = sc.nextInt();
		switch (opcion) {
			case 1:
				consultasEntidades();
				break;
			case 2:
				/*buscar libro por ISBN*/
				System.out.print("Introduce el N° ISBN que desea Buscar =>");
				Long idISBN = sc.nextLong();
				libService.buscarLibroISBN(idISBN);
				break;
			case 3:
				/*buscar libro por titulo*/
				System.out.print("Introduce el nombre del libro =>");
				String nombreLibro = reader.nextLine();
				libService.buscarLibroPorNombre(nombreLibro);
				break;
			case 4:
				/*buscar cliente por Nombre*/
				System.out.print("Introduce el Nombre del cliente =>");
				String nombreCliente = reader.nextLine();
				cliService.buscarClientePorNombre(nombreCliente);
				break;
			case 5:
				//menu principal
				menuOpciones();
				break;

		}
	}

	private void consultasEntidades() {

		System.out.println("\n|||CONSULTAS ENTIDADES|||");
		System.out.println("Elija que Lista desea ver");
		System.out.println("1) Clientes."
				+ "\n2) Libros."
				+ "\n3) Autores."
				+ "\n4) Editoriales."
				+ "\n5  Volver Atras <=");
		System.out.print("Opcion =>");
		int opcion = sc.nextInt();
		switch (opcion) {
			case 1:
				System.out.println("\nResultado de la Busqueda: ");
				cliService.traerClientesDesdeMySql();
				break;
			case 2:
				System.out.println("\nResultado de la Busqueda: ");
				libService.traerLibrosDesdeMySql();
				break;
			case 3:
				System.out.println("\nResultado de la Busqueda: ");
				autService.traerAutoresDesdeMySql();
				break;
			case 4:
				System.out.println("\nResultado de la Busqueda: ");
				editService.traerEditorialDesdeMysql();
				break;
			case 5:
				consultasLibreria();
				break;

		}
	}

	public void modificarDatosEntidades() {
		sc.nextLine();
		System.out.println("\n|||MODIFICAR DATOS ENTIDADES|||");
		System.out.println("Elija que Entidad desea modificar");
		System.out.println("1) Clientes."
				+ "\n2) Libros."
				+ "\n3) Autores."
				+ "\n4) Editoriales."
				+ "\n5  Volver Atras <=");
		System.out.print("Opcion =>");
		int opcion = sc.nextInt();
		switch (opcion) {
			case 1:
				try {
					System.out.print("Introduce el DNI del Cliente => ");
					long dni = sc.nextInt();
					cliService.modificarNombre(dni);
				} catch (Exception ex) {
					Logger.getLogger(MenuLibreria.class.getName()).log(Level.SEVERE, null, ex);
				}
				break;
			case 2:
				break;
		}
	}

	public String PrestarUnLibroHoy() {
		System.out.println("\n|||PRESTAR UN LIBRO|||");
		System.out.println("\nLista de Libros Disponibles");
		libService.traerLibrosDesdeMySql();

		System.out.print("\nTipee el Libro que desea Alquilar =>");
		String libroSolicitado = reader.nextLine();

		presService.prestarUnLibro(libroSolicitado);
		return libroSolicitado;
	}

	public void finalizarPrograma() {
		System.out.println("Gracias Por Utilizar SoftechNologic");
	}

}//fin class
