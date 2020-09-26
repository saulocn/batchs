package br.com.estudo.saulocn.runner;

import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Schedule;
import javax.ejb.Singleton;

import br.com.estudo.saulocn.dao.BookDao;
import br.com.estudo.saulocn.model.Book;

@Singleton
public class BookCreationSchedule {

    private static final String INICIANDO = "Iniciando o job aqui...";
    private static final String FINALIZANDO = "Finalizando o job aqui...";

    private static final String BOOK_NAME = "Teste 1";

    Logger logger = Logger.getLogger(this.getClass().getCanonicalName());

    @EJB
    private BookDao bookDao;

    @Lock(LockType.READ)
    @Schedule(hour = "*", minute = "*", second = "*/15", info = "Every 5 seconds timer")
    public void jobRunner() {
        logger.info(INICIANDO);
        final Book book = Book.of(BOOK_NAME);
        bookDao.save(book);
        logger.info(FINALIZANDO);
    }
}
