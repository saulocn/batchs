package br.com.estudo.saulocn.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "books")
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_book")
    @SequenceGenerator(name = "sq_book", sequenceName = "sq_book")
    private int id;
    private String nome;
    private int numberOfTimesProcessed;
    private boolean processed;

    public Book() {
    }

    public Book(final int id, final String nome) {
        this.id = id;
        this.nome = nome;
    }

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(final boolean processed) {
        this.processed = processed;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(final String nome) {
        this.nome = nome;
    }

    public int getNumberOfTimesProcessed() {
        return numberOfTimesProcessed;
    }

    public void setNumberOfTimesProcessed(final int numberOfTimesProcessed) {
        this.numberOfTimesProcessed = numberOfTimesProcessed;
    }

    public void process() {
        this.numberOfTimesProcessed++;
        this.processed = Boolean.TRUE;
    }

    @Override public String toString() {
        final StringBuilder builder = new StringBuilder()//
                .append("Book [")//
                .append("id=")//
                .append(id)//
                .append(",nome=\"")//
                .append(nome + "\"")//
                .append(",numberOfTimesProcessed=")//
                .append(numberOfTimesProcessed)//
                .append(",processed=")//
                .append(processed)//
                .append("]");
        return builder.toString();
    }

    public static Book of(final int id, final String nome) {
        return new Book(id, nome);
    }

    public static Book of(final String nome) {
        final Book book = new Book();
        book.setNome(nome);
        book.setNumberOfTimesProcessed(1);
        return book;
    }
}
