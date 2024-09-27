package com.mongo.rag;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class WikiDocumentsController {

    final WikiDocumentsServiceImpl wikiDocumentsService;

   public  WikiDocumentsController(WikiDocumentsServiceImpl wikiDocumentsService){
       this.wikiDocumentsService=wikiDocumentsService;
   }
    @PostMapping
    public ResponseEntity<Void> saveDocument(@RequestParam String filePath) {
        wikiDocumentsService.saveWikiDocument(filePath);

        return ResponseEntity.status(201).build();
    }

    @GetMapping
    public List<WikiDocument> get(@RequestParam("searchText") String searchText) {
        return wikiDocumentsService.findSimilarDocuments(searchText);
    }
}
