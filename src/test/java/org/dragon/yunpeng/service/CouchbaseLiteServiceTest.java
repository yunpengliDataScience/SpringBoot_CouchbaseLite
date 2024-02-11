package org.dragon.yunpeng.service;

import java.util.HashMap;
import java.util.Map;

import org.dragon.yunpeng.service.CouchbaseLiteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Document;

@SpringBootTest
public class CouchbaseLiteServiceTest {

	@Autowired
	private CouchbaseLiteService couchbaseLiteService;

	@Test
	public void testSaveDocument() throws CouchbaseLiteException {

		for (int i = 1; i <= 5; i++) {
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("firstName", "Bigdaddy" + i);
			data.put("lastName", "Lee" + i);

			String id = String.valueOf(i);
			couchbaseLiteService.saveDocument(id, data);
		}

		Document doc = couchbaseLiteService.getDocument("1");

		System.out.println(doc);

		System.out.println(couchbaseLiteService.getAllDocuments());
	}
}
