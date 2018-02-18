package com.pluralsight.bookstore.repository;

import com.pluralsight.bookstore.model.Book;
import com.pluralsight.bookstore.model.Language;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.core.api.annotation.Inject;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.runner.RunWith;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class BookRepositoryTest {

    @javax.inject.Inject
    private  BookRepository bookRepository;

    @org.junit.Test
    public void create() throws Exception {
        //Test counting books
        assertEquals(Long.valueOf(0), bookRepository.countAll());
        assertEquals(0, bookRepository.findAll().size());

        Book book = new Book("title", "String description", 12F, "isbn", new Date(), 50, "http://imageUrl", Language.ENGLISH);
        book = bookRepository.create(book);
        Long bookId = book.getId();

        //Check created book
        assertNotNull(bookId);

        //Find created book
        Book bookFound = bookRepository.find(bookId);

        //Check the found book
        assertEquals("title", bookFound.getTitle());

        //Test counting books
        assertEquals(Long.valueOf(1), bookRepository.countAll());
        assertEquals(1, bookRepository.findAll().size());

        //Delete the book
        bookRepository.delete(bookId);

        //Test counting books
        assertEquals(Long.valueOf(0), bookRepository.countAll());
        assertEquals(0, bookRepository.findAll().size());


    }

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(BookRepository.class)
                .addClass(Book.class)
                .addClass(Language.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsManifestResource("META-INF/test-persistence.xml", "persistence.xml");
    }

}
