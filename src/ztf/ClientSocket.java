package ztf;

public class ClientSocket {
	private String ip;
	private String name;
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ClientSocket(String ip, String name) {
		super();
		this.ip = ip;
		this.name = name;
	}
	
	
	
}
