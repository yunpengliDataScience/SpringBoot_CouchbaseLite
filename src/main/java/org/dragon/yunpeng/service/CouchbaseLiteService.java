package org.dragon.yunpeng.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.couchbase.lite.Collection;
import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.DataSource;
import com.couchbase.lite.Database;
import com.couchbase.lite.Document;
import com.couchbase.lite.MutableDocument;
import com.couchbase.lite.Query;
import com.couchbase.lite.QueryBuilder;
import com.couchbase.lite.Result;
import com.couchbase.lite.ResultSet;
import com.couchbase.lite.SelectResult;

@Service
public class CouchbaseLiteService {

	private final Database database;

	public CouchbaseLiteService(Database database) {
		this.database = database;
	}

	private Collection getCollection(String collectionName) throws CouchbaseLiteException {
		// Create a new collection (like a SQL table) in the database.
		Collection collection = database.getCollection(collectionName);
		if (collection == null) {
			collection = database.createCollection(collectionName);

			System.out.println("Collection created: " + collection);
		}

		return collection;
	}

	public void saveDocument(String id, Map<String, Object> data) throws CouchbaseLiteException {

		Collection collection = getCollection("Users");

		MutableDocument document = new MutableDocument(id, data);
		collection.save(document);
	}

	public Document getDocument(String id) throws CouchbaseLiteException {

		Collection collection = getCollection("Users");
		return collection.getDocument(id);
	}

	public List<String> getAllDocuments() {
		List<String> documents = new ArrayList<String>();

		try {
			Collection collection = getCollection("Users");
			Query query = QueryBuilder.select(SelectResult.all()).from(DataSource.collection(collection));

			ResultSet result = query.execute();

			for (Result row : result) {
				String json = row.toJSON();
				documents.add(json);
			}

		} catch (CouchbaseLiteException e) {
			e.printStackTrace();
		}
		return documents;
	}
}
