package jigneshkt.test.com.testproject.domain.repository;

public interface AppContextRepository {
    public Boolean setAccessToken(String access_token);
    public String getAccessToken();
}
