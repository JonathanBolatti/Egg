/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.persistencia;

import java.io.Serializable;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import libreria.entidades.Cliente;
import libreria.persistencia.exceptions.NonexistentEntityException;
import libreria.persistencia.exceptions.PreexistingEntityException;

/**
 *
 * @author Jonathan
 */
public class ClienteJpaController implements Serializable {

	public ClienteJpaController(EntityManagerFactory emf) {
		this.emf = emf;
	}
	private EntityManagerFactory emf = null;

	public ClienteJpaController() {
		emf = Persistence.createEntityManagerFactory("LibreriaPU");
	}

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public void create(Cliente cliente) throws PreexistingEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			em.persist(cliente);
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findCliente(cliente.getDocumento()) != null) {
				throw new PreexistingEntityException("Cliente " + cliente + " already exists.", ex);
			}
			throw ex;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public void edit(Cliente cliente, String nombreNuevo) throws NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			if (cliente == null) {
				throw new NonexistentEntityException(" la Persona buscada no existe ");
			} else {

				//cliente = (Cliente) em.createQuery("SELECT c FROM Cliente c WHERE c.nombre = :nombre").
				//		setParameter("nombre", cliente).getSingleResult();

				cliente.setNombre(nombreNuevo);

				cliente = em.merge(cliente);
				em.getTransaction().commit();
			}
		} catch (NonexistentEntityException ex) {
			throw ex;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

//	public void edit(Cliente cliente, String nuevoNombre) throws NonexistentEntityException, Exception {
//		try {
//
//			EntityManager em = null;
//
//			System.out.println("entro al edit");
//
//			cliente = (Cliente) em.createQuery("SELECT c FROM Cliente c WHERE c.nombre  = :nombre").
//					setParameter("nombre", cliente).getSingleResult();
//
//			cliente.setNombre(nuevoNombre);
//
//			em.getTransaction().begin();
//			em.merge(cliente);
//			em.getTransaction().commit();
//
//		} catch (Exception ex) {
//			System.out.println("Error de sobreCarga de Datos");
//		}
//	}
	public void destroy(Long id) throws NonexistentEntityException {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Cliente cliente;
			try {
				cliente = em.getReference(Cliente.class, id);
				cliente.getDocumento();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.", enfe);
			}
			em.remove(cliente);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public List<Cliente> findClienteEntities() {
		return findClienteEntities(true, -1, -1);
	}

	public List<Cliente> findClienteEntities(int maxResults, int firstResult) {
		return findClienteEntities(false, maxResults, firstResult);
	}

	private List<Cliente> findClienteEntities(boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Cliente.class));
			Query q = em.createQuery(cq);
			if (!all) {
				q.setMaxResults(maxResults);
				q.setFirstResult(firstResult);
			}
			return q.getResultList();
		} finally {
			em.close();
		}
	}

	public Cliente findCliente(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Cliente.class, id);
		} finally {
			em.close();
		}
	}

	public int getClienteCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Cliente> rt = cq.from(Cliente.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

	public Cliente ClienteBuscadoPorNombre(String nombreCliente) {
		EntityManager em = getEntityManager();
		return (Cliente) em.createQuery("Select c from Cliente c where c.nombre = :nombre")
				.setParameter("nombre", nombreCliente).getSingleResult();
	}

}
