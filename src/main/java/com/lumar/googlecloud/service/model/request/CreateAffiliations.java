package com.lumar.googlecloud.service.model.request;

import java.util.List;

import com.lumar.googlecloud.service.model.data.Affiliation;

public class CreateAffiliations {
	
	private long typeId;
	private List <Affiliation>affiliations;
	public long getTypeId() {
		return typeId;
	}
	public void setTypeId(long typeId) {
		this.typeId = typeId;
	}
	public List<Affiliation> getAffiliations() {
		return affiliations;
	}
	public void setAffiliations(List<Affiliation> affiliations) {
		this.affiliations = affiliations;
	}
	
	@Override
	public String toString() {
		return "CreateAffiliations [typeId=" + typeId + ", affiliations=" + affiliations + "]";
	}
}
