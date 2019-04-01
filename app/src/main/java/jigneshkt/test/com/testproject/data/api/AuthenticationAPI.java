package jigneshkt.test.com.testproject.data.api;

import io.reactivex.Observable;
import jigneshkt.test.com.testproject.data.model.body.authentication.AuthenticationBody;
import jigneshkt.test.com.testproject.data.model.response.authentication.AuthenticationResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface AuthenticationAPI {

    @FormUrlEncoded
    @POST("oauth/token")
    Observable<AuthenticationResponse> authenticate(@Field("client_id") String clientId,
                                                    @Field("client_secret")String clientSecret,
                                                    @Field("grant_type") String grantType);


    @FormUrlEncoded
    @POST("oauth/token")
    Call<AuthenticationResponse> refreshToken( @Body  AuthenticationBody authenticationBody);
}
