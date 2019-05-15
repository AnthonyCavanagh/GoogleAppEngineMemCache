package com.lumar.googlecloud.service.model.request;

public class TypeState {

	private long typeId;
	private boolean complete;
	public long getTypeId() {
		return typeId;
	}
	public void setTypeId(long typeId) {
		this.typeId = typeId;
	}
	public boolean isComplete() {
		return complete;
	}
	public void setComplete(boolean complete) {
		this.complete = complete;
	}
	@Override
	public String toString() {
		return "TypeState [typeId=" + typeId + ", complete=" + complete + "]";
	}
}
