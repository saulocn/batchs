package br.com.estudo.saulocn.batch;

import java.util.logging.Logger;
import javax.batch.api.chunk.AbstractItemReader;
import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

import br.com.estudo.saulocn.dao.BookDao;
import br.com.estudo.saulocn.model.Book;

@Dependent
@Named
public class BookReader extends AbstractItemReader {
    private static final String LENDO_MESSAGE = "Lendo livros não processados";

    Logger logger = Logger.getLogger(this.getClass().getCanonicalName());

    @EJB
    BookDao bookDao;

    @Override
    public Object readItem() throws Exception {
        logger.info(LENDO_MESSAGE);
        final Book book = bookDao.findUnprocessed();
        return book;
    }
}
