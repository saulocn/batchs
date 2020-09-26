package br.com.estudo.saulocn.batch;

import java.util.List;
import java.util.logging.Logger;
import javax.batch.api.chunk.AbstractItemWriter;
import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

import br.com.estudo.saulocn.dao.BookDao;
import br.com.estudo.saulocn.model.Book;

@Dependent
@Named
public class BookWriter extends AbstractItemWriter {
    private static final String SALVANDO = "Processando: ";

    Logger logger = Logger.getLogger(this.getClass().getCanonicalName());

    @EJB
    BookDao bookDao;

    @Override
    public void writeItems(final List<Object> list) throws Exception {
        logger.info(SALVANDO);
        list.stream().map(Book.class::cast).forEach(book -> bookDao.update(book));
    }
}
