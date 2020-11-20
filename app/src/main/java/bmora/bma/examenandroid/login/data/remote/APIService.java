package bmora.bma.examenandroid.login.data.remote;

import bmora.bma.examenandroid.login.data.models.LoginPost;
import bmora.bma.examenandroid.login.data.models.SignInPost;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIService {
    @POST("login")
    @FormUrlEncoded
    Call<LoginPost> postLogin(@Field("email") String email,
                              @Field("password") String password);

    @POST("register")
    @FormUrlEncoded
    Call<SignInPost> postRegister(@Field("email") String email,
                                  @Field("password") String password);
}
