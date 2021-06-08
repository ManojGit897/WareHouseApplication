package com.nt.service;

import java.util.List;

import com.nt.model.Document;

public interface IDocumentService {
	
	void saveDocument(Document doc);
	List<Object[]> getDocumentIdAndName();
	
	void deleteDocumentById(Long id);
	Document getDocumentById(Long id);

}
