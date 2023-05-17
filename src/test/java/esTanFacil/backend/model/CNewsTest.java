package esTanFacil.backend.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CNewsTest {

    @Test
    void getId() {
        CNews news = new CNews();
        news.setId(1L);
        assertEquals(1L, news.getId());
    }

    @Test
    void getTitle() {
        CNews news = new CNews();
        news.setTitle("Example Title");
        assertEquals("Example Title", news.getTitle());
    }

    @Test
    void getDescription() {
        CNews news = new CNews();
        news.setDescription("Example Description");
        assertEquals("Example Description", news.getDescription());
    }

    @Test
    void getDate() {
        CNews news = new CNews();
        news.setDate(LocalDate.now());
        assertEquals(LocalDate.now(), news.getDate());
    }

    @Test
    void getUrlImg() {
        CNews news = new CNews();
        news.setUrlImg("example.jpg");
        assertEquals("example.jpg", news.getUrlImg());
    }
}