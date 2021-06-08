package com.nt.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nt.model.Document;

public interface DocumentRepository extends JpaRepository<Document,Long> {

	@Query("select docId,docName from Document")
	List<Object[]> getDocumentIdAndName();

}
