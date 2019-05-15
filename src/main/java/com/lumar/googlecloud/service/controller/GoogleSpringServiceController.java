package com.lumar.googlecloud.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.appengine.api.datastore.Key;
import com.lumar.googlecloud.service.cacheservice.CacheService;
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
import com.lumar.googlecloud.service.model.request.Request;
import com.lumar.googlecloud.service.model.request.TypeState;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class GoogleSpringServiceController {

	@Autowired
	CacheService service;
	
	private static final Logger logger = LoggerFactory.getLogger(GoogleSpringServiceController.class);
	
	@RequestMapping("/home")
    public String home() {
		logger.info("Running Home");
            return "Google Cloud Spring running ";
    }
	
	
	
	@RequestMapping(value = "/createType", method = RequestMethod.POST)
	public ResponseEntity<Type>  saveType(@RequestBody Type type) {
		logger.info("Save type "+type.toString());
		type = service.addType(type);
		return new ResponseEntity<Type>(type, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/findType", method = RequestMethod.POST)
	public ResponseEntity<Type>  findType(@RequestBody Request request) {
		logger.info("Find type "+request.getId());
		Type type = service.findType(request.getId());
		return new ResponseEntity<Type>(type, HttpStatus.FOUND);
	}
	
	@RequestMapping(value = "/findCompleteType", method = RequestMethod.POST)
	public ResponseEntity<Type>  findComleteType(@RequestBody Request request) {
		logger.info("Find CompleteType "+request.getId());
		Type type = service.findCompletedType(request.getId());
		return new ResponseEntity<Type>(type, HttpStatus.FOUND);
	}
	
	@RequestMapping(value = "/completeType", method = RequestMethod.POST)
	public ResponseEntity<TypeState>  completeType(@RequestBody TypeState state) {
		logger.info("Save type "+state.toString());
		state = service.setTypeState(state);
		return new ResponseEntity<TypeState>(state, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/findTypeKey", method = RequestMethod.POST)
	public ResponseEntity<Key>  findTypeKey(@RequestBody Request request) {
		logger.info("Find type "+request.getId());
		Key key = service.findTypeKey(request.getId());
		return new ResponseEntity<Key>(key, HttpStatus.FOUND);
	}
	
	@RequestMapping(value = "/addFields", method = RequestMethod.POST)
	public ResponseEntity<CreateFields>  saveFields(@RequestBody CreateFields fields) {
		logger.info("Save fields for type "+fields.toString());
		fields = service.addFields(fields);
		return new ResponseEntity<CreateFields>(fields, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/findFields", method = RequestMethod.POST)
	public ResponseEntity<List<Field>>  findFields(@RequestBody Request request) {
		logger.info("Find Fields for type "+request.getId());
		List<Field> fields = service.findFields(request.getId());
		return new ResponseEntity<List<Field>>(fields, HttpStatus.FOUND);
	}
	

	@RequestMapping(value = "/deleteField", method = RequestMethod.POST)
	public ResponseEntity<Void>  deleteField(@RequestBody Delete delete) {
		logger.info("Delete Fields for type "+delete.toString());
		service.deleteFields(delete);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/addAffiliations", method = RequestMethod.POST)
	public ResponseEntity<CreateAffiliations>  saveAffiliations(@RequestBody CreateAffiliations  affiliations) {
		logger.info("Save affiliationss for type "+affiliations.toString());
		affiliations = service.addAffiliations(affiliations);
		return new ResponseEntity<CreateAffiliations>(affiliations, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/findAffiliations", method = RequestMethod.POST)
	public ResponseEntity<List<Affiliation>>  findAffiliations(@RequestBody Request request) {
		logger.info("Find Affiliations for type "+request.getId());
		List<Affiliation> affiliations = service.findAffiliations(request.getId());
		return new ResponseEntity<List<Affiliation>>(affiliations, HttpStatus.FOUND);
	}
	
	@RequestMapping(value = "/deleteAffiliation", method = RequestMethod.POST)
	public ResponseEntity<Void>  deleteAffiliation(@RequestBody Delete delete) {
		logger.info("Delete Affiliation for type "+delete.toString());
		service.deleteAffiliation(delete);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/addAssets", method = RequestMethod.POST)
	public ResponseEntity<CreateAssets>  saveAssets(@RequestBody CreateAssets  assets) {
		logger.info("Save assetss for type "+assets.toString());
		assets = service.addAssets(assets);
		return new ResponseEntity<CreateAssets>(assets, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/findAssets", method = RequestMethod.POST)
	public ResponseEntity<List<Asset>>  findAssets(@RequestBody Request request) {
		logger.info("Find Assets for type "+request.getId());
		List<Asset> assets = service.findAssets(request.getId());
		return new ResponseEntity<List<Asset>>(assets, HttpStatus.FOUND);
	}
	
	@RequestMapping(value = "/deleteAsset", method = RequestMethod.POST)
	public ResponseEntity<Void>  deleteAsset(@RequestBody Delete delete) {
		logger.info("Delete Asset for type "+delete.toString());
		service.deleteAsset(delete);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/addSchemas", method = RequestMethod.POST)
	public ResponseEntity<CreateSchemas>  saveSchemas(@RequestBody CreateSchemas  schemas) {
		logger.info("Save Schemass for type "+schemas.toString());
		schemas = service.addSchemas(schemas);
		return new ResponseEntity<CreateSchemas>(schemas, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/findSchemas", method = RequestMethod.POST)
	public ResponseEntity<List<Schema>>  findSchemas(@RequestBody Request request) {
		logger.info("Find Schemas for type "+request.getId());
		List<Schema> schemas = service.findSchemas(request.getId());
		return new ResponseEntity<List<Schema>>(schemas, HttpStatus.FOUND);
	}
	
	@RequestMapping(value = "/deleteSchema", method = RequestMethod.POST)
	public ResponseEntity<Void>  deleteSchema(@RequestBody Delete delete) {
		logger.info("Delete Schema for type "+delete.toString());
		service.deleteSchemas(delete);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/addStates", method = RequestMethod.POST)
	public ResponseEntity<CreateStates>  saveStates(@RequestBody CreateStates  states) {
		logger.info("Save Statess for type "+states.toString());
		states = service.addStates(states);
		return new ResponseEntity<CreateStates>(states, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/findStates", method = RequestMethod.POST)
	public ResponseEntity<List<State>>  findStates(@RequestBody Request request) {
		logger.info("Find States for type "+request.getId());
		List<State> states = service.findStates(request.getId());
		return new ResponseEntity<List<State>>(states, HttpStatus.FOUND);
	}
	
	@RequestMapping(value = "/deleteState", method = RequestMethod.POST)
	public ResponseEntity<Void>  deleteState(@RequestBody Delete delete) {
		logger.info("Delete State for type "+delete.toString());
		service.deleteStates(delete);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
