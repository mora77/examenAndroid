package bmora.bma.examenandroid.login.data.remote;

public class ApiUtils {

    private ApiUtils(){}

    public static final String BASE_URL= "https://reqres.in/api/";

    public static APIService getApiService(){
        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}
