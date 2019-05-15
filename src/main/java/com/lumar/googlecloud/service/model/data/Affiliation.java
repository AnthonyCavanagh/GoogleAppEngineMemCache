package com.lumar.googlecloud.service.model.data;

public class Affiliation {

	private Long id;
	private String uuid;
	private String name;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Affiliation [id=" + id + ", uuid=" + uuid + ", name=" + name + "]";
	}
}
