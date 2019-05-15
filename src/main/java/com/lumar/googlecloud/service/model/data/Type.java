package  com.lumar.googlecloud.service.model.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Type implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private Long parentId;
	private boolean comlete = false;
	private List <Field> fields = new ArrayList<Field>();
	private List <Asset> assets = new ArrayList<Asset>(); 
	private List<Schema> schemas = new ArrayList<Schema>();
	private List<State> states = new ArrayList<State>();
	private List<Affiliation> affiliates = new ArrayList<Affiliation>();
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public List<Field> getFields() {
		return fields;
	}
	public void setFields(List<Field> fields) {
		this.fields = fields;
	}
	public List<Asset> getAssets() {
		return assets;
	}
	public void setAssets(List<Asset> assets) {
		this.assets = assets;
	}

	public List<Schema> getSchemas() {
		return schemas;
	}
	public void setSchemas(List<Schema> schemas) {
		this.schemas = schemas;
	}
	public List<State> getStates() {
		return states;
	}
	public void setStates(List<State> states) {
		this.states = states;
	}

	public List<Affiliation> getAffiliates() {
		return affiliates;
	}
	public void setAffiliates(List<Affiliation> affiliates) {
		this.affiliates = affiliates;
	}
	@Override
	public String toString() {
		return "Type [id=" + id + ", name=" + name + ", parentId=" + parentId + ", fields=" + fields + ", assets="
				+ assets + ", schemas=" + schemas + ", states=" + states + ", affiliates=" + affiliates + "]";
	}
	public boolean isComlete() {
		return comlete;
	}
	public void setComlete(boolean comlete) {
		this.comlete = comlete;
	}  
}
