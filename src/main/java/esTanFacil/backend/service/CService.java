package esTanFacil.backend.service;


import esTanFacil.backend.model.CNews;
import esTanFacil.backend.repositories.INews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CService {
    @Autowired
    private INews iNews;

    public void createNews(CNews news) {
        iNews.save(news);

    }

    public List<CNews> readNews() {
        List<CNews> news = new ArrayList<CNews>(iNews.findAll());
        return news;

    }

    public Optional<CNews> readNewsId(Long id) {
        Optional<CNews> news = iNews.findById(id);
        return news;
    }

    public void updateNews(CNews news, Long id) {
        news.setId(id);
        iNews.save(news);

    }

    public void deleteNews(Long id) {
        iNews.deleteById(id);


    }


}






