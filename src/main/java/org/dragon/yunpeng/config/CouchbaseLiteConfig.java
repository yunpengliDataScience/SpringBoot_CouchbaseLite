package org.dragon.yunpeng.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.couchbase.lite.CouchbaseLite;
import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Database;
import com.couchbase.lite.DatabaseConfiguration;

@Configuration
public class CouchbaseLiteConfig {

    @Bean
    public Database database() throws CouchbaseLiteException {
    	
    	CouchbaseLite.init();
        System.out.println("Counchbase is Initialized");
        
        // Set up database configuration
        DatabaseConfiguration config = new DatabaseConfiguration();
        config.setDirectory("data"); // Set directory for database files

        // Create or open a database
        return new Database("mydatabase", config);
    }
    
}

