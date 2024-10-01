package za.nico.customer.dtos;

public class CustomerDto {
	private String name;
	private Long id;
	private String email;
	
	
	public CustomerDto() {
		super();
	}


	public CustomerDto(String name, Long id, String email) {
		super();
		this.name = name;
		this.id = id;
		this.email = email;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	@Override
	public String toString() {
		return "CustomerDto [name=" + name + ", id=" + id + ", email=" + email + "]";
	}
	
	

}
