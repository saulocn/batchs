package br.com.estudo.saulocn.batch;

import java.util.logging.Logger;
import javax.batch.api.chunk.ItemProcessor;
import javax.inject.Named;

import br.com.estudo.saulocn.model.Book;

@Named
public class BookProcessor implements ItemProcessor {
    private static final String PROCESSANDO = "Processando: ";

    Logger logger = Logger.getLogger(this.getClass().getCanonicalName());

    @Override
    public Object processItem(final Object object) throws Exception {
        final Book book = (Book) object;
        logger.info(PROCESSANDO + book.getNome());
        book.process();
        return book;
    }
}
