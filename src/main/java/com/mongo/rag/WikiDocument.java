package com.mongo.rag;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class WikiDocument {
    private String filePath;
    private String content;


}
