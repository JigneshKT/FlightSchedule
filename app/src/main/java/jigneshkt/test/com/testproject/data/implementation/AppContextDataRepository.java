package jigneshkt.test.com.testproject.data.implementation;

import jigneshkt.test.com.testproject.domain.repository.AppContextRepository;

public class AppContextDataRepository implements AppContextRepository {

    private String access_token=null;

    @Override
    public Boolean setAccessToken(String access_token){
        this.access_token=access_token;
        return this.access_token==null ? false: true;
    }

    @Override
    public String getAccessToken(){
        return access_token;
    }


}
