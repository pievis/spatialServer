package it.isac.server.utils.config;

public class DBRedisConfig extends DBConfig {
	String url;
	String port;
	boolean useExpiration = true;
	long expirationSeconds = 60;
	
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public boolean isUseExpiration() {
		return useExpiration;
	}
	public void setUseExpiration(boolean useExpiration) {
		this.useExpiration = useExpiration;
	}
	public long getExpirationSeconds() {
		return expirationSeconds;
	}
	public void setExpirationSeconds(long expirationSeconds) {
		this.expirationSeconds = expirationSeconds;
	}
	
	
}
