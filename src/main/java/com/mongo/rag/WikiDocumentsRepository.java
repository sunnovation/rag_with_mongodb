package com.mongo.rag;

import org.springframework.ai.document.Document;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class WikiDocumentsRepository {

    private final VectorStore vectorStore;

    public WikiDocumentsRepository(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    public void saveWikiDocument(WikiDocument wikiDocument) {

        Map<String, Object> metadata = new HashMap<>();
        metadata.put("filePath", wikiDocument.getFilePath());
        Document document = new Document(wikiDocument.getContent(), metadata);
        List<Document> documents = new TokenTextSplitter().apply(List.of(document));

        vectorStore.add(documents);
    }

    public List<WikiDocument> findSimilarDocuments(String searchText) {

//        return vectorStore
//                .similaritySearch(SearchRequest
//                        .query(searchText)
//                        .withSimilarityThreshold(0.87)
//                        .withTopK(10))
//                .stream()
//                .map(document -> {
//                    WikiDocument wikiDocument = new WikiDocument();
//                    wikiDocument.setFilePath((String) document.getMetadata().get("filePath"));
//                    wikiDocument.setContent(document.getContent());
//
//                    return wikiDocument;
//                })
//                .toList();

        List<Document> results = vectorStore.similaritySearch(
                SearchRequest
                        .query(searchText)
                        .withTopK(10)
        );
        List<WikiDocument> docs=new ArrayList<>();
        results.forEach(doc->{
            WikiDocument documents=new WikiDocument();
            documents.setFilePath((String)doc.getMetadata().get("filePath"));
            documents.setContent(doc.getContent());
            docs.add(documents);

        });
        return docs;
    }




}
