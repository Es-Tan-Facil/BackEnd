package esTanFacil.backend.service;

import esTanFacil.backend.model.CNews;
import esTanFacil.backend.repositories.INews;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CServiceTest {

    @Mock
    private INews iNews;

    @InjectMocks
    private CService cService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateNews() {
        CNews news = new CNews();
        cService.createNews(news);
        verify(iNews, times(1)).save(news);
    }

    @Test
    public void testReadNews() {
        List<CNews> newsList = new ArrayList<>();
        when(iNews.findAll()).thenReturn(newsList);

        List<CNews> result = cService.readNews();

        assertEquals(newsList, result);
    }

    @Test
    public void testReadNewsId() {
        Long id = 1L;
        CNews news = new CNews();
        when(iNews.findById(id)).thenReturn(Optional.of(news));

        Optional<CNews> result = cService.readNewsId(id);

        assertEquals(Optional.of(news), result);
    }

    @Test
    public void testUpdateNews() {
        Long id = 1L;
        CNews news = new CNews();
        news.setId(id);
        cService.updateNews(news, id);

        verify(iNews).save(news);
    }

    @Test
    public void testDeleteNews() {
        Long id = 1L;
        cService.deleteNews(id);

        verify(iNews).deleteById(id);
    }
}