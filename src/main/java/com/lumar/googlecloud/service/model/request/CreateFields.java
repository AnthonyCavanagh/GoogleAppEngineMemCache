package com.lumar.googlecloud.service.model.request;

import java.util.ArrayList;
import java.util.List;

import com.lumar.googlecloud.service.model.data.Field;

public class CreateFields {
	long typeId;
	private List <Field> fields = new ArrayList<Field>();
	public long getTypeId() {
		return typeId;
	}
	public void setTypeId(long typeId) {
		this.typeId = typeId;
	}
	public List<Field> getFields() {
		return fields;
	}
	public void setFields(List<Field> fields) {
		this.fields = fields;
	}
	@Override
	public String toString() {
		return "CreateField [typeId=" + typeId + ", fields=" + fields + "]";
	}
}
