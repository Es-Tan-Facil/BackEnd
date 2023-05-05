package esTanFacil.backend.controller;


import esTanFacil.backend.model.CNews;
import esTanFacil.backend.service.CService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("api/v1/news")
@CrossOrigin("*")
public class CNewsController {
    @Autowired
    CService cService;

    @GetMapping("")
    public List<CNews> readNews(){
        return cService.readNews();

    }
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Optional<CNews> readNewsId(@PathVariable("id") Long id){
        return cService.readNewsId(id);

    }

    @PostMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public void createNews(@RequestBody CNews news){
        cService.createNews(news);

    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void updateNews(@RequestBody CNews news,@PathVariable("id")Long id){
        cService.updateNews(news,id);

    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteNews(@PathVariable("id")Long id){
        cService.deleteNews(id);


    }



}



