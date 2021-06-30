package libreria.servicios;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import libreria.entidades.Cliente;
import libreria.persistencia.ClienteJpaController;
import libreria.persistencia.exceptions.NonexistentEntityException;

public class ClienteService {

	Scanner reader = new Scanner(System.in);
	ClienteJpaController clienteJpa = new ClienteJpaController();

	public void crearCliente(Cliente cliente) {
		try {
			clienteJpa.create(cliente);
		} catch (Exception ex) {
			Logger.getLogger(ClienteService.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void eliminarCliente(Long id) {
		try {
			clienteJpa.destroy(id);
		} catch (NonexistentEntityException ex) {
			Logger.getLogger(ClienteService.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void buscarClienteId(Long id) {
		Cliente cliente = clienteJpa.findCliente(id);
		System.out.println("\nResultado de la Busqueda: ");
		System.out.println(cliente.toString());
	}

	public void modificarNombre(Long id) throws Exception {
		Cliente cliente = clienteJpa.findCliente(id);
		System.out.println("\nResultado de la Busqueda: ");
		System.out.println(cliente.toString());
		System.out.println("Introduce un nuevo nombre para el usuario dni: "+cliente.getDocumento());
		System.out.print("=>");
		String nuevoNombre = reader.nextLine();
		clienteJpa.edit(cliente,nuevoNombre);
		System.out.println("Cliente Actualizado Correctamente");

	}

	public void mostrarListaCliente(List<Cliente> cli) {
		for (Cliente cliente : cli) {
			System.out.println(cliente.toString());
		}
	}

	public void traerClientesDesdeMySql() {
		List<Cliente> listaClientes = clienteJpa.findClienteEntities();
		mostrarListaCliente(listaClientes);
	}

	public void buscarClientePorNombre(String nombreCliente) {
		Cliente cliente = clienteJpa.ClienteBuscadoPorNombre(nombreCliente);
		System.out.println("\nResultado de la Busqueda");
		System.out.println(cliente.toString());

	}
}//fin class
