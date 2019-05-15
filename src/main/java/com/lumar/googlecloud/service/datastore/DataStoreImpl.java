package com.lumar.googlecloud.service.datastore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.lumar.googlecloud.service.DataStoreInit;
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

@Service
public class DataStoreImpl implements DataStore {

	DatastoreService dstore;

	@Autowired
	DataStoreInit storeInit;

	private static final Logger logger = LoggerFactory.getLogger(DataStoreImpl.class);

	@PostConstruct
	public void initIt() {
		dstore = storeInit.initDataSource();
	}

	@Override
	public Type addType(Type type) {
		Entity entity = new Entity("Type", type.getId());
		try {
			entity = dstore.get(entity.getKey());
			entity.setProperty("Name", type.getName());
			entity.setProperty("Fields", new ArrayList<Long>());
		} catch (EntityNotFoundException e) {
			// store a new Entity for type
			entity.setProperty("Id", type.getId());
			entity.setProperty("Name", type.getName());
			entity.setProperty("Complete", false);
		}
		dstore.put(entity);
		return type;
	}

	@Override
	public Type findType(Long id) {
		Entity entity = new Entity("Type", id);
		Type type = new Type();
		try {
			entity = dstore.get(entity.getKey());
			Map<String, Object> props = entity.getProperties();
			type = findType(type, props);
			type.setId(id);
		} catch (EntityNotFoundException e) {
			logger.info("Type doesnt exist for id " + id);
		}
		return type;
	}

	@Override
	public TypeState setTypeState(TypeState state) {
		Entity entity = new Entity("Type", state.getTypeId());
		try {
			entity = dstore.get(entity.getKey());
			entity.setProperty("Complete", state.isComplete());
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
		}
		return state;
	}

	@Override
	public Type findCompletedType(Long id) {
		logger.info("Find Type for  id " + id);
		Entity entity = new Entity("Type", id);
		Type type = new Type();
		try {
			entity = dstore.get(entity.getKey());
			Map<String, Object> props = entity.getProperties();
			Boolean completed = (Boolean) props.get("Complete");
			if (completed) {
				type = findType(type, props);
			}
			type.setId(id);
		} catch (EntityNotFoundException e) {
			logger.info("Type doesnt exist for id " + id);
		}
		return type;
	}

	@Override
	public CreateFields addFields(CreateFields fields) {
		Entity entity = new Entity("Type", fields.getTypeId());
		try {
			entity = dstore.get(entity.getKey());
			List<Long> fieldIds = (List<Long>) entity.getProperties().get("Fields");
			if (fieldIds == null) {
				fieldIds = new ArrayList<Long>();
			}
			List<Field> fList = fields.getFields();
			for (Field field : fList) {
				fieldIds.add(field.getId());
				saveField(field);
			}
			entity.setProperty("Fields", fieldIds);
			dstore.put(entity);
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
		}
		return fields;
	}

	@Override
	public List<Field> findFields(Long typeId) {
		Entity entity = new Entity("Type", typeId);
		List<Field> fields = new ArrayList<Field>();
		try {
			entity = dstore.get(entity.getKey());
			Map<String, Object> props = entity.getProperties();
			List<Long> fieldIds = (List<Long>) props.get("Fields");
			if (fieldIds != null) {
				fields = findFieldList(fieldIds);
			}
		} catch (EntityNotFoundException e) {

		}
		return fields;
	}

	@Override
	public List<Field> findFields(List<Long> fieldIds) {
		return findFieldList(fieldIds);
	}

	@Override
	public void deleteFields(Delete delete) {
		Entity entity = new Entity("Type", delete.getTypeId());
		try {
			entity = dstore.get(entity.getKey());
			List<Long> fieldsIds = (List<Long>) entity.getProperties().get("Fields");
			if (fieldsIds != null) {
				fieldsIds.removeIf(a -> a == delete.getDeleteId());
				deleteField(delete.getDeleteId());
				entity.setProperty("Fields", fieldsIds);
				dstore.put(entity);
			}
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public CreateAffiliations addAffiliations(CreateAffiliations affiliations) {
		Entity entity = new Entity("Type", affiliations.getTypeId());
		try {
			entity = dstore.get(entity.getKey());
			List<Long> affiliationIds = (List<Long>) entity.getProperties().get("Affiliations");
			if (affiliationIds == null) {
				affiliationIds = new ArrayList<Long>();
			}
			List<Affiliation> aList = affiliations.getAffiliations();
			for (Affiliation affiliation : aList) {
				affiliationIds.add(affiliation.getId());
				saveAffiliation(affiliation);
			}
			entity.setProperty("Affiliations", affiliationIds);
			dstore.put(entity);
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
		}
		return affiliations;
	}

	@Override
	public List<Affiliation> findAffiliations(Long typeId) {
		Entity entity = new Entity("Type", typeId);
		List<Affiliation> affiliations = new ArrayList<Affiliation>();
		try {
			entity = dstore.get(entity.getKey());
			Map<String, Object> props = entity.getProperties();
			List<Long> affiliationIds = (List<Long>) props.get("Affiliations");
			if (affiliationIds != null) {
				affiliations = findAffiliationList(affiliationIds);
			}
		} catch (EntityNotFoundException e) {

		}
		return affiliations;
	}

	@Override
	public List<Affiliation> findAffiliations(List<Long> AffiliationIds) {
		return findAffiliations(AffiliationIds);
	}

	@Override
	public void deleteAffiliation(Delete delete) {
		Entity entity = new Entity("Type", delete.getTypeId());
		try {
			entity = dstore.get(entity.getKey());
			List<Long> affiliationIds = (List<Long>) entity.getProperties().get("Affiliations");
			if (affiliationIds != null) {
				affiliationIds.removeIf(a -> a == delete.getDeleteId());
				deleteAffiliation(delete.getDeleteId());
				entity.setProperty("Affiliations", affiliationIds);
				dstore.put(entity);
			}
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public CreateAssets addAssets(CreateAssets assets) {
		Entity entity = new Entity("Type", assets.getTypeId());
		try {
			entity = dstore.get(entity.getKey());
			List<Long> assetIds = (List<Long>) entity.getProperties().get("Asset");
			if (assetIds == null) {
				assetIds = new ArrayList<Long>();
			}
			List<Asset> aList = assets.getAsset();
			for (Asset asset : aList) {
				assetIds.add(asset.getId());
				saveAsset(asset);
			}
			entity.setProperty("Asset", assetIds);
			dstore.put(entity);
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
		}
		return assets;
	}

	@Override
	public List<Asset> findAssets(Long typeId) {
		Entity entity = new Entity("Type", typeId);
		List<Asset> Asset = new ArrayList<Asset>();
		try {
			entity = dstore.get(entity.getKey());
			Map<String, Object> props = entity.getProperties();
			List<Long> AssetIds = (List<Long>) props.get("Asset");
			if (AssetIds != null) {
				Asset = findAssetList(AssetIds);
			}
		} catch (EntityNotFoundException e) {

		}
		return Asset;
	}

	@Override
	public List<Asset> findAssets(List<Long> assetIds) {
		return findAssets(assetIds);
	}

	@Override
	public void deleteAsset(Delete delete) {
		Entity entity = new Entity("Type", delete.getTypeId());
		try {
			entity = dstore.get(entity.getKey());
			List<Long> AssetIds = (List<Long>) entity.getProperties().get("Asset");
			if (AssetIds != null) {
				AssetIds.removeIf(a -> a == delete.getDeleteId());
				deleteAsset(delete.getDeleteId());
				entity.setProperty("Asset", AssetIds);
				dstore.put(entity);
			}
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Key findTypeKey(Long id) {
		Entity entity = new Entity("Type", id);
		return entity.getKey();
	}

	@Override
	public CreateSchemas addSchemas(CreateSchemas schemas) {
		Entity entity = new Entity("Type", schemas.getTypeId());
		try {
			entity = dstore.get(entity.getKey());
			List<Long> SchemaIds = (List<Long>) entity.getProperties().get("Schemas");
			if (SchemaIds == null) {
				SchemaIds = new ArrayList<Long>();
			}
			List<Schema> fList = schemas.getSchemas();
			for (Schema Schema : fList) {
				SchemaIds.add(Schema.getId());
				saveSchema(Schema);
			}
			entity.setProperty("Schemas", SchemaIds);
			dstore.put(entity);
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
		}
		return schemas;
	}

	@Override
	public List<Schema> findSchemas(Long typeId) {
		Entity entity = new Entity("Type", typeId);
		List<Schema> Schemas = new ArrayList<Schema>();
		try {
			entity = dstore.get(entity.getKey());
			Map<String, Object> props = entity.getProperties();
			List<Long> SchemaIds = (List<Long>) props.get("Schemas");
			if (SchemaIds != null) {
				Schemas = findSchemaList(SchemaIds);
			}
		} catch (EntityNotFoundException e) {

		}
		return Schemas;
	}

	@Override
	public List<Schema> findSchemas(List<Long> schemaIds) {
		return findSchemaList(schemaIds);
	}

	@Override
	public void deleteSchemas(Delete delete) {
		Entity entity = new Entity("Type", delete.getTypeId());
		try {
			entity = dstore.get(entity.getKey());
			List<Long> schemasIds = (List<Long>) entity.getProperties().get("Schemas");
			if (schemasIds != null) {
				schemasIds.removeIf(a -> a == delete.getDeleteId());
				deleteSchema(delete.getDeleteId());
				entity.setProperty("Schemas", schemasIds);
				dstore.put(entity);
			}
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public CreateStates addStates(CreateStates states) {
		Entity entity = new Entity("Type", states.getTypeId());
		try {
			entity = dstore.get(entity.getKey());
			List<Long> StateIds = (List<Long>) entity.getProperties().get("States");
			if (StateIds == null) {
				StateIds = new ArrayList<Long>();
			}
			List<State> fList = states.getStates();
			for (State State : fList) {
				StateIds.add(State.getId());
				saveState(State);
			}
			entity.setProperty("States", StateIds);
			dstore.put(entity);
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
		}
		return states;
	}

	@Override
	public List<State> findStates(Long typeId) {
		Entity entity = new Entity("Type", typeId);
		List<State> States = new ArrayList<State>();
		try {
			entity = dstore.get(entity.getKey());
			Map<String, Object> props = entity.getProperties();
			List<Long> StateIds = (List<Long>) props.get("States");
			if (StateIds != null) {
				States = findStateList(StateIds);
			}
		} catch (EntityNotFoundException e) {

		}
		return States;
	}

	@Override
	public List<State> findStates(List<Long> stateIds) {
		return findStateList(stateIds);
	}

	@Override
	public void deleteStates(Delete delete) {
		Entity entity = new Entity("Type", delete.getTypeId());
		try {
			entity = dstore.get(entity.getKey());
			List<Long> statesIds = (List<Long>) entity.getProperties().get("States");
			if (statesIds != null) {
				statesIds.removeIf(a -> a == delete.getDeleteId());
				deleteState(delete.getDeleteId());
				entity.setProperty("States", statesIds);
				dstore.put(entity);
			}
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Find Type
	private Type findType(Type type, Map<String, Object> props) {
		type.setName((String) props.get("Name"));
		List<Long> fieldIds = (List<Long>) props.get("Fields");
		if (fieldIds != null) {
			type.getFields().addAll(findFieldList(fieldIds));
		}
		List<Long> affiliationIds = (List<Long>) props.get("Affiliations");
		if (affiliationIds != null) {
			type.getAffiliates().addAll(findAffiliationList(affiliationIds));
		}
		List<Long> assetIds = (List<Long>) props.get("Asset");
		if (assetIds != null) {
			type.getAssets().addAll(findAssetList(assetIds));
		}
		List<Long> stateIds = (List<Long>) props.get("States");
		if (stateIds != null) {
			type.getStates().addAll(findStateList(stateIds));
		}
		List<Long> schemaIds = (List<Long>) props.get("Schemas");
		if (schemaIds != null) {
			type.getSchemas().addAll(findSchemaList(schemaIds));
		}
		return type;
	}

	// Fields
	private List<Field> findFieldList(List<Long> fieldIds) {
		List<Field> fields = new ArrayList<Field>();
		for (Long id : fieldIds) {
			Entity entity = new Entity("Fields", id);
			try {
				entity = dstore.get(entity.getKey());
				fields.add(findField(entity));
			} catch (EntityNotFoundException e) {
				e.printStackTrace();
			}
		}
		return fields;
	}

	private void saveField(Field field) {
		Entity entity = new Entity("Fields", field.getId());
		entity.setProperty("id", field.getId());
		entity.setProperty("Name", field.getName());
		entity.setProperty("Option", field.getOptions());
		entity.setProperty("Primitive", field.getPrimitive());
		dstore.put(entity);
	}

	private Field findField(Entity entity) {
		Map<String, Object> props = entity.getProperties();
		Field field = new Field();
		field.setId((Long) props.get("id"));
		field.setName((String) props.get("Name"));
		field.setOptions((String) props.get("Option"));
		field.setPrimitive((String) props.get("Primitive"));
		return field;
	}

	private void deleteField(Long id) {
		Entity entity = new Entity("Fields", id);
		dstore.delete(entity.getKey());
	}

	// Affiliation
	private List<Affiliation> findAffiliationList(List<Long> affiliationIds) {
		List<Affiliation> affiliations = new ArrayList<Affiliation>();
		for (Long id : affiliationIds) {
			Entity entity = new Entity("Fields", id);
			try {
				entity = dstore.get(entity.getKey());
				affiliations.add(findAffiliation(entity));
			} catch (EntityNotFoundException e) {
				e.printStackTrace();
			}
		}
		return affiliations;
	}

	private void saveAffiliation(Affiliation affiliation) {
		Entity entity = new Entity("Affiliation", affiliation.getId());
		entity.setProperty("id", affiliation.getId());
		entity.setProperty("Name", affiliation.getName());
		entity.setProperty("UUID", affiliation.getUuid());
		dstore.put(entity);
	}

	private Affiliation findAffiliation(Entity entity) {
		Map<String, Object> props = entity.getProperties();
		Affiliation affiliation = new Affiliation();
		affiliation.setId((Long) props.get("id"));
		affiliation.setName((String) props.get("Name"));
		affiliation.setUuid((String) props.get("UUID"));
		logger.info(affiliation.toString());
		return affiliation;
	}

	private void deleteAffiliation(Long id) {
		Entity entity = new Entity("Fields", id);
		dstore.delete(entity.getKey());
	}

	// Assets
	private List<Asset> findAssetList(List<Long> assetIds) {
		List<Asset> assets = new ArrayList<Asset>();
		for (Long id : assetIds) {
			Entity entity = new Entity("Assets", id);
			try {
				entity = dstore.get(entity.getKey());
				assets.add(findAsset(entity));
			} catch (EntityNotFoundException e) {
				e.printStackTrace();
			}
		}
		return assets;
	}

	private void saveAsset(Asset asset) {
		Entity entity = new Entity("Asset", asset.getId());
		entity.setProperty("id", asset.getId());
		entity.setProperty("Name", asset.getName());
		entity.setProperty("Title", asset.getTitle());
		entity.setProperty("Link", asset.getLink());
		dstore.put(entity);
	}

	private Asset findAsset(Entity entity) {
		Map<String, Object> props = entity.getProperties();
		Asset asset = new Asset();
		asset.setId((Long) props.get("id"));
		asset.setName((String) props.get("Name"));
		asset.setTitle((String) props.get("Title"));
		asset.setLink((String) props.get("Link"));
		logger.info(asset.toString());
		return asset;
	}

	private void deleteAsset(Long id) {
		Entity entity = new Entity("Asset", id);
		dstore.delete(entity.getKey());
	}

	// Schemas
	private List<Schema> findSchemaList(List<Long> schemaIds) {
		List<Schema> schemas = new ArrayList<Schema>();
		for (Long id : schemaIds) {
			Entity entity = new Entity("Schemas", id);
			try {
				entity = dstore.get(entity.getKey());
				schemas.add(findSchema(entity));
			} catch (EntityNotFoundException e) {
				e.printStackTrace();
			}
		}
		return schemas;
	}

	private void saveSchema(Schema schema) {
		Entity entity = new Entity("Schemas", schema.getId());
		entity.setProperty("id", schema.getId());
		entity.setProperty("Name", schema.getName());
		entity.setProperty("Title", schema.getTitle());
		entity.setProperty("Snippet", schema.getSnippet());
		dstore.put(entity);
	}

	private Schema findSchema(Entity entity) {
		Map<String, Object> props = entity.getProperties();
		Schema Schema = new Schema();
		Schema.setId((Long) props.get("id"));
		Schema.setName((String) props.get("Name"));
		Schema.setTitle((String) props.get("Title"));
		Schema.setSnippet((String) props.get("Snippet"));
		return Schema;
	}

	private void deleteSchema(Long id) {
		Entity entity = new Entity("Schemas", id);
		dstore.delete(entity.getKey());
	}

	// States
	private List<State> findStateList(List<Long> StateIds) {
		List<State> States = new ArrayList<State>();
		for (Long id : StateIds) {
			Entity entity = new Entity("States", id);
			try {
				entity = dstore.get(entity.getKey());
				States.add(findState(entity));
			} catch (EntityNotFoundException e) {
				e.printStackTrace();
			}
		}
		return States;
	}

	private void saveState(State state) {
		Entity entity = new Entity("States", state.getId());
		entity.setProperty("id", state.getId());
		entity.setProperty("Name", state.getName());
		entity.setProperty("State", state.getState());
		dstore.put(entity);
	}

	private State findState(Entity entity) {
		Map<String, Object> props = entity.getProperties();
		State state = new State();
		state.setId((Long) props.get("id"));
		state.setName((String) props.get("Name"));
		state.setState((String) props.get("State"));
		return state;
	}

	private void deleteState(Long id) {
		Entity entity = new Entity("States", id);
		dstore.delete(entity.getKey());
	}

}
