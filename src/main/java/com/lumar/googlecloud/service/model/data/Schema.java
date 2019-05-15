package  com.lumar.googlecloud.service.model.data;

public class Schema {

	private Long id;
	private String name;
	private String title;
	private String snippet;
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
	public String getSnippet() {
		return snippet;
	}
	public void setSnippet(String snippet) {
		this.snippet = snippet;
	}
	@Override
	public String toString() {
		return "Schema [id=" + id + ", name=" + name + ", title=" + title + ", snippet=" + snippet + "]";
	}
}
