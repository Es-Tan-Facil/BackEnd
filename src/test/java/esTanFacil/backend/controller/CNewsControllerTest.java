package esTanFacil.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import esTanFacil.backend.model.CNews;
import esTanFacil.backend.service.CService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public
class CNewsControllerTest {


    @Mock
    private CService cService;

    @InjectMocks
    private CNewsController cNewsController;

    private MockMvc mockMvc;

    @Test
    public void testReadNews() throws Exception {

        List<CNews> newsList = Arrays.asList(new CNews(), new CNews());
        when(cService.readNews()).thenReturn(newsList);

        mockMvc.perform(get("/api/v1/news"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0]").exists())
                .andExpect(jsonPath("$[1]").exists());

        verify(cService, times(1)).readNews();
        verifyNoMoreInteractions(cService);
    }

    @Test
    public void testReadNewsId() throws Exception {

        Long newsId = 1L;
        CNews news = new CNews();
        when(cService.readNewsId(newsId)).thenReturn(Optional.of(news));

        mockMvc.perform(get("/api/v1/news/{id}", newsId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").exists());

        verify(cService, times(1)).readNewsId(newsId);
        verifyNoMoreInteractions(cService);
    }

    @Test
    public void testCreateNews() throws Exception {

        CNews news = new CNews();
        news.setTitle("Title");
        news.setDescription("Description");
        news.setUrlImg("https://example.com/image.jpg");

        mockMvc.perform(post("/api/v1/news")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(news)))
                .andExpect(status().isOk());

        ArgumentCaptor<CNews> captor = ArgumentCaptor.forClass(CNews.class);
        verify(cService, times(1)).createNews(captor.capture());
        verifyNoMoreInteractions(cService);

        CNews capturedNews = captor.getValue();
        assertNotNull(capturedNews);
        assertEquals(news.getTitle(), capturedNews.getTitle());
        assertEquals(news.getDescription(), capturedNews.getDescription());
        assertEquals(news.getUrlImg(), capturedNews.getUrlImg());
    }

    @Test
    public void testUpdateNews() throws Exception {

        Long newsId = 1L;
        CNews news = new CNews();
        news.setTitle("Updated Title");
        news.setDescription("Updated Description");
        news.setUrlImg("https://example.com/updated-image.jpg");

        mockMvc.perform(put("/api/v1/news/{id}", newsId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(news)))
                .andExpect(status().isOk());

        ArgumentCaptor<CNews> captor = ArgumentCaptor.forClass(CNews.class);
        verify(cService, times(1)).updateNews(captor.capture(), eq(newsId));
        verifyNoMoreInteractions(cService);

        CNews capturedNews = captor.getValue();
        assertNotNull(capturedNews);
        assertEquals(news.getTitle(), capturedNews.getTitle());
        assertEquals(news.getDescription(), capturedNews.getDescription());
        assertEquals(news.getUrlImg(), capturedNews.getUrlImg());
    }

    @Test
    public void testDeleteNews() throws Exception {

        Long newsId = 1L;

        mockMvc.perform(delete("/api/v1/news/{id}", newsId))
                .andExpect(status().isOk());

        verify(cService, times(1)).deleteNews(newsId);
        verifyNoMoreInteractions(cService);
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(cNewsController).build();
    }
}