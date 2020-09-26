package br.com.estudo.saulocn.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.estudo.saulocn.model.Book;

@Stateless
public class BookDao {

    @PersistenceContext(unitName = "books")
    private EntityManager entityManager;

    public List<Book> list() {
        return entityManager.createQuery("select b from Book b", Book.class).getResultList();
    }

    public void save(final Book book) {
        entityManager.persist(book);
    }

    public Book getById(final int id) {
        return entityManager.find(Book.class, id);
    }

    public void update(final int id, final Book newBook) {
        final Book oldBook = this.getById(id);
        oldBook.setNome(newBook.getNome());
        entityManager.merge(oldBook);
    }

    public void update(final Book book) {
        entityManager.merge(book);
    }

    public void delete(final int id) {
        final Book book = this.getById(id);
        entityManager.remove(book);
    }

    public Book findUnprocessed() {
        try {
            final TypedQuery<Book> query = entityManager
                    .createQuery("select b from Book b where b.processed = false", Book.class);
            query.setMaxResults(1);
            return query.getSingleResult();
        } catch (final NoResultException e) {
        }
        return null;
    }
}
