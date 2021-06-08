package com.nt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.model.Document;
import com.nt.repo.DocumentRepository;
import com.nt.service.IDocumentService;

@Service
public class DocumentServiceImpl implements IDocumentService {

	@Autowired
   private	DocumentRepository repo;
	
	
	@Override
	public void saveDocument(Document doc) {
		repo.save(doc);
		
	}

	@Override
	public List<Object[]> getDocumentIdAndName() {
		
		return repo.getDocumentIdAndName();
	}

	@Override
	public void deleteDocumentById(Long id) {
		
		if(repo.existsById(id)) 
			repo.deleteById(id);
		else throw new RuntimeException("Not Found ");
		
	}

	@Override
	public Document getDocumentById(Long id) {
		return repo.findById(id).orElseThrow(
				()->new RuntimeException("Document Not exist")
				);
	}

}
