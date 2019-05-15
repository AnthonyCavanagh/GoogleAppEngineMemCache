package com.lumar.googlecloud.service.cacheservice;

import java.util.List;

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

public interface CacheService {
	
	Type addType(Type type);
	Type findType(Long id);
	Key findTypeKey(Long id);
	TypeState setTypeState(TypeState state);
	Type findCompletedType(Long id);
	
	CreateFields addFields(CreateFields fields);
	List <Field> findFields(Long typeId);
	List<Field> findFields(List<Long> fieldIds);
	void deleteFields(Delete delete);
	
	CreateAffiliations addAffiliations(CreateAffiliations fields);
	List <Affiliation> findAffiliations(Long typeId);
	List<Affiliation> findAffiliations(List<Long> AffiliationIds);
	void deleteAffiliation(Delete delete);
	
	CreateAssets addAssets(CreateAssets Assets);
	List <Asset> findAssets(Long typeId);
	List<Asset> findAssets(List<Long> AssetIds);
	void deleteAsset(Delete delete);
	
	CreateSchemas addSchemas(CreateSchemas schemas);
	List<Schema> findSchemas(Long typeId);
	List<Schema> findSchemas(List<Long> schemaIds);
	void deleteSchemas(Delete delete);
	
	CreateStates addStates(CreateStates states);
	List<State> findStates(Long typeId);
	List<State> findStates(List<Long> stateIds);
	void deleteStates(Delete delete);
}
