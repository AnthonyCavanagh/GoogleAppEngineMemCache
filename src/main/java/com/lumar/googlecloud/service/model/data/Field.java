package  com.lumar.googlecloud.service.model.data;

import org.springframework.stereotype.Component;

@Component
public class Field {
	
	private Long id;
	private String name;
	private String primitive;
    private String options;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPrimitive() {
		return primitive;
	}
	public void setPrimitive(String primitive) {
		this.primitive = primitive;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOptions() {
		return options;
	}
	public void setOptions(String options) {
		this.options = options;
	}
	@Override
	public String toString() {
		return "Field [Id=" + id + ", primitive=" + primitive + ", name=" + name + ", options=" + options
				+ "]";
	}

}
