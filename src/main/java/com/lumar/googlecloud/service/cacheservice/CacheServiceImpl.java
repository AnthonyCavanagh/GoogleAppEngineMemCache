package com.lumar.googlecloud.service.cacheservice;

import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lumar.googlecloud.service.controller.GoogleSpringServiceController;
import com.lumar.googlecloud.service.datastore.DataStore;

import com.google.appengine.api.datastore.Key;
import com.lumar.googlecloud.service.model.data.Affiliation;
import com.lumar.googlecloud.service.model.data.Asset;
import com.lumar.googlecloud.service.model.data.Field;
import com.lumar.googlecloud.service.model.data.Schema;
import com.lumar.googlecloud.service.model.data.State;
import com.lumar.googlecloud.service.model.data.Type;
import com.lumar.googlecloud.service.model.request.CreateAffiliations;
import com.lumar.googlecloud.service.model.request.CreateAssets;
import com.lumar.googlecloud.service.model.request.CreateFields;
import com.lumar.googlecloud.service.model.request.CreateSchemas;
import com.lumar.googlecloud.service.model.request.CreateStates;
import com.lumar.googlecloud.service.model.request.Delete;
import com.lumar.googlecloud.service.model.request.TypeState;

import com.google.appengine.api.memcache.MemcacheService;


@Service
public class CacheServiceImpl implements CacheService {
	
	@Autowired
	DataStore store;
	
	 @Autowired
	 MemcacheService memcacheService;
	 
	 private static final Logger logger = LoggerFactory.getLogger(CacheServiceImpl.class);

	@Override
	public Type addType(Type type) {
		type = store.addType(type);
		return type;
	}

	@Override
	public Type findType(Long id) {
		return store.findType(id);
	}
	
	@Override
	public TypeState setTypeState(TypeState state) {
		return store.setTypeState(state);
	}

	@Override
	public CreateFields addFields(CreateFields fields) {
		return store.addFields(fields);
	}

	@Override
	public List<Field> findFields(Long typeId) {
		return store.findFields(typeId);
	}

	@Override
	public List<Field> findFields(List<Long> fieldIds) {
		return store.findFields(fieldIds);
	}

	@Override
	public CreateAffiliations addAffiliations(CreateAffiliations affiliations) {
		return store.addAffiliations(affiliations);
	}

	@Override
	public List<Affiliation> findAffiliations(Long typeId) {
		return store.findAffiliations(typeId);
	}

	@Override
	public List<Affiliation> findAffiliations(List<Long> AffiliationIds) {
		return store.findAffiliations(AffiliationIds);
	}

	@Override
	public void deleteFields(Delete delete) {
		store.deleteFields(delete);
		
	}

	@Override
	public void deleteAffiliation(Delete delete) {
		store.deleteAffiliation(delete);
	}

	@Override
	public Key findTypeKey(Long id) {
		return store.findTypeKey(id);
	}

	@Override
	public CreateAssets addAssets(CreateAssets Assets) {
		return store.addAssets(Assets);
	}

	@Override
	public List<Asset> findAssets(Long typeId) {
		return store.findAssets(typeId);
	}

	@Override
	public List<Asset> findAssets(List<Long> AssetIds) {
		return store.findAssets(AssetIds);
	}

	@Override
	public void deleteAsset(Delete delete) {
		store.deleteAsset(delete);
	}

	@Override
	public CreateSchemas addSchemas(CreateSchemas schemas) {
		return store.addSchemas(schemas);
	}

	@Override
	public List<Schema> findSchemas(Long typeId) {
		return store.findSchemas(typeId);
	}

	@Override
	public List<Schema> findSchemas(List<Long> schemaIds) {
		return store.findSchemas(schemaIds);
	}

	@Override
	public void deleteSchemas(Delete delete) {
		store.deleteSchemas(delete);
	}

	@Override
	public CreateStates addStates(CreateStates states) {
		return store.addStates(states);
	}

	@Override
	public List<State> findStates(Long typeId) {
		return store.findStates(typeId);
	}

	@Override
	public List<State> findStates(List<Long> stateIds) {
		return store.findStates(stateIds);
	}

	@Override
	public void deleteStates(Delete delete) {
		store.deleteStates(delete);
	}

	@Override
	public Type findCompletedType(Long id) {
		logger.info("findCompletedType for id "+id);
		Type type = (Type)memcacheService.get(id);
		if(type == null){
			type = store.findCompletedType(id);
			logger.info(" Add Completed Type to cache "+type.toString());
			memcacheService.put(id, type);
		}
		return type;
	}
}
