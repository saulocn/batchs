package br.com.estudo.saulocn.runner;

import java.util.Properties;
import java.util.logging.Logger;
import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.ejb.EJB;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Schedule;
import javax.ejb.Singleton;

import br.com.estudo.saulocn.dao.BookDao;

@Singleton
public class BookProcessingSchedule {

    private static final String INICIANDO = "Iniciando o job de processamento aqui...";
    private static final String FINALIZANDO = "Finalizando o job de processamento aqui...";

    Logger logger = Logger.getLogger(this.getClass().getCanonicalName());

    @EJB
    private BookDao bookDao;

    @Lock(LockType.READ)
    @Schedule(hour = "*", minute = "*/1", second = "*", info = "Every 5 seconds timer")
    public void jobRunner() {
        logger.info(INICIANDO);

        final JobOperator jobOperator = BatchRuntime.getJobOperator();
        jobOperator.start("processing-job", new Properties());

        logger.info(FINALIZANDO);
    }
}
