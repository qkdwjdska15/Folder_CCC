package model;

public class JoinVO {

	private String id;
	private String password;
	private String name;
	
	public JoinVO() {
		super();
	}

	public JoinVO(String id) {
		super();
		this.id = id;
	}

	public JoinVO(String id, String password) {
		super();
		this.id = id;
		this.password = password;
	}

	public JoinVO(String id, String password, String name) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
	}
	
	
	
}
