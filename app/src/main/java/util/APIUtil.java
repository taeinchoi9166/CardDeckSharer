package util;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import adapters.CardSearchAdapter;
import entity.CardInfo;
import entity.CardItem;
import ga.sproutseed.mds.magicdecksharer.CardSearchActivity;
import ga.sproutseed.mds.magicdecksharer.CardViewerActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIUtil {
    public APIUtil(){

    }

    public Call<List<CardItem>> getSearchResult(int page, String keyword, CardSearchAdapter adapter, CardSearchActivity activity){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://node.sproutseed.ga/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIInterface service = retrofit.create(APIInterface.class);
        Call<List<CardItem>> req = service.getSearchResult(keyword,page);
        System.out.println(page+" "+keyword);
        req.enqueue(new Callback<List<CardItem>>() {
            @Override
            public void onResponse(Call<List<CardItem>> call, Response<List<CardItem>> response) {
                if(response.isSuccessful()){
                 List<CardItem> list = response.body();
                 if(!list.isEmpty()){
                     //adapter = new CardSearchAdapter(list,activity);
                     //rec
                 }
                }
            }

            @Override
            public void onFailure(Call<List<CardItem>> call, Throwable t) {
                System.out.println("실패. ");
            }
        });
        return req;
    }
    private void resetAdapter(List<CardItem> list, CardSearchAdapter adapter, CardSearchActivity activity){
        adapter = new CardSearchAdapter(list,activity);
        adapter.notifyDataSetChanged();
    }

    public Call<CardInfo> getCardInfo(int page){
        List<CardItem> list = new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://node.sproutseed.ga/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIInterface service = retrofit.create(APIInterface.class);
        Call<CardInfo> req = service.getItemInfo(page);
        req.enqueue(new Callback<CardInfo>() {

            @Override
            public void onResponse(Call<CardInfo> call, Response<CardInfo> response) {

            }

            @Override
            public void onFailure(Call<CardInfo> call, Throwable t) {

            }
        });
        return req;
    }

}

class WaitNotify{
    synchronized public void requestWait(){
        try{
            wait();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    synchronized public void requestNotify(){
        try{
            notify();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}

class APICallerForItem extends AsyncTask<Call,Void, List<CardItem>> {
    CardSearchAdapter adapter;
    WaitNotify wn;


    public APICallerForItem(CardSearchAdapter adapter,WaitNotify wn) {
        this.adapter = adapter;
        this.wn = wn;
    }

    @Override
    protected List<CardItem> doInBackground(Call... calls) {
        try{
            Call<List<CardItem>> call = calls[0];
            Response<List<CardItem>> response = call.execute();
            System.out.println(response.body().get(1).getTitle());
            return response.body();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    @Override
    protected void onPostExecute(List<CardItem> cardItems) {
        System.out.println(cardItems.size());
        adapter.notifyDataSetChanged();
    }



}

class APICallerForInfo extends AsyncTask<Call,Void, CardInfo> {
    Activity activity;
    Intent intent;

    public APICallerForInfo(Activity activity, Intent intent) {
        this.activity = activity;
        this.intent = intent;
    }

    @Override
    protected CardInfo doInBackground(Call... calls) {
        return null;
    }

    @Override
    protected void onPostExecute(CardInfo cardInfo) {
        // System.out.println(cardItems.get(1).getTitle());
        intent = new Intent(activity.getApplicationContext(), CardViewerActivity.class);

        intent.putExtra("name",cardInfo.getName());
        intent.putExtra("attr",cardInfo.getAttr());
        intent.putExtra("type",cardInfo.getType());
        intent.putExtra("kind",cardInfo.getKind());
        intent.putExtra("atk",cardInfo.getAttack());
        intent.putExtra("def",cardInfo.getDefense());
        intent.putExtra("text",cardInfo.getText());
        intent.putExtra("level",cardInfo.getLevel());

        activity.startActivity(intent);
    }
}