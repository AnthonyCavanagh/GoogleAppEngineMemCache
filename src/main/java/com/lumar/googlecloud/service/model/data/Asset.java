package com.lumar.googlecloud.service.model.data;

public class Asset {

	private Long id;
	private String name;
	private String title;
	private String link;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	@Override
	public String toString() {
		return "Asset [id=" + id + ", name=" + name + ", title=" + title + ", link=" + link + "]";
	}
}
