/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.persistencia;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import libreria.entidades.Libro;
import libreria.persistencia.exceptions.NonexistentEntityException;
import libreria.persistencia.exceptions.PreexistingEntityException;

/**
 *
 * @author Jonathan
 */
public class LibroJpaController {

	public LibroJpaController(EntityManagerFactory emf) {
		this.emf = emf;
	}
	private EntityManagerFactory emf = null;

	//añadimos el constructor 
	public LibroJpaController() {
		emf = Persistence.createEntityManagerFactory("LibreriaPU");
	}

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public void create(Libro libro) throws PreexistingEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			em.persist(libro);
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findLibro(libro.getIsbn()) != null) {
				throw new PreexistingEntityException("Libro " + libro + " already exists.", ex);
			}
			throw ex;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	//editar un libro//
	public void edit(Libro libro) throws NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			libro = em.merge(libro);
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				long id = libro.getIsbn();
				if (findLibro(id) == null) {
					throw new NonexistentEntityException("El libro con id " + id + " no existe.");
				}
			}
			throw ex;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	//eliminar un libro//
	public void destroy(long id) throws NonexistentEntityException {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Libro libro;
			try {
				libro = em.getReference(Libro.class, id);
				libro.getIsbn();
				System.out.println("***Se ha eliminado el Libro Correctamente***");
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The libro with id " + id + " no longer exists.", enfe);
			}
			em.remove(libro);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	///traer lista de libros///
	public List<Libro> findLibroEntities() {
		return findLibroEntities(true, -1, -1);
	}

	public List<Libro> findLibroEntities(int maxResults, int firstResult) {
		return findLibroEntities(false, maxResults, firstResult);
	}

	private List<Libro> findLibroEntities(boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Libro.class));
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
	//traer un libro en particular nombre//
	public Libro libroBuscadoPorNombre(String libro) {
		EntityManager em = getEntityManager();
		return (Libro) em.createQuery("SELECT l FROM Libro l WHERE l.titulo = :titulo ")
				.setParameter("titulo", libro).getSingleResult();
	}

	//traer un libro en particular Id//
	public Libro findLibro(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Libro.class, id);
		} finally {
			em.close();
		}
	}

	public int getLibroCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Libro> rt = cq.from(Libro.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
