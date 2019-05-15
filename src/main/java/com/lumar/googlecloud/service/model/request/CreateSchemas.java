package com.lumar.googlecloud.service.model.request;

import java.util.ArrayList;
import java.util.List;

import com.lumar.googlecloud.service.model.data.Schema;

public class CreateSchemas {

	private Long typeId;
	private List<Schema> schemas = new ArrayList<Schema> ();
	
	public Long getTypeId() {
		return typeId;
	}
	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}
	public List<Schema> getSchemas() {
		return schemas;
	}
	public void setSchemas(List<Schema> schemas) {
		this.schemas = schemas;
	}
	@Override
	public String toString() {
		return "CreateSchema [typeId=" + typeId + ", schemas=" + schemas + "]";
	}
}
