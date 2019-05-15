package com.lumar.googlecloud.service.model.request;

public class Delete {

	private long typeId;
	private long deleteId;
	public long getTypeId() {
		return typeId;
	}
	public void setTypeId(long typeId) {
		this.typeId = typeId;
	}
	public long getDeleteId() {
		return deleteId;
	}
	public void setDeleteId(long deleteId) {
		this.deleteId = deleteId;
	}
	@Override
	public String toString() {
		return "Delete [typeId=" + typeId + ", deleteId=" + deleteId + "]";
	}
}
