package com.lumar.googlecloud.service;

import org.springframework.stereotype.Component;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;

@Component 
public class DataStoreInit {

	public DatastoreService initDataSource(){
		return DatastoreServiceFactory.getDatastoreService();
	}
}
