package com.lumar.googlecloud.service.model.request;

import java.util.ArrayList;
import java.util.List;

import com.lumar.googlecloud.service.model.data.State;

public class CreateStates {

	private Long typeId;
	private List <State> states = new ArrayList<State> ();
	public Long getTypeId() {
		return typeId;
	}
	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}
	public List<State> getStates() {
		return states;
	}
	public void setStates(List<State> states) {
		this.states = states;
	}
	@Override
	public String toString() {
		return "CreateState [typeId=" + typeId + ", states=" + states + "]";
	}

}
