package util;

import java.util.List;
import entity.CardItem;
import entity.CardInfo;
import entity.Result;
import entity.UserCard;
import entity.UserDeck;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIInterface {
    @GET("search/{keyword}/{id}")
    Call<List<CardItem>> getSearchResult(@Path("keyword") String keyword,@Path("id") int page);

    @GET("get/{id}")
    Call<CardInfo> getItemInfo(@Path("id") int id);

    @FormUrlEncoded
    @POST("register")
    Call<Result> registerUser(@Field("id") String id,@Field("password") String password);

    @FormUrlEncoded
    @POST("save")
    Call<Result> saveDeck(@Field("writer") String id, @Field("password") String password, @Field("title") String title, @Field("description") String description, @Field("data") String data);

    @GET("get")
    Call<List<UserDeck>> getUserDecks(@Query("id") String id, @Query("password") String password);
}
