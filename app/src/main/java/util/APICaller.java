package util;

import android.os.AsyncTask;

import java.util.List;

import adapters.CardSearchAdapter;
import entity.CardItem;
import retrofit2.Call;
import retrofit2.Response;

public class APICaller extends AsyncTask<Call,Void, List<CardItem>> {
    CardSearchAdapter adapter;

    public APICaller(CardSearchAdapter adapter) {
        this.adapter = adapter;
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
//        System.out.println(cardItems.size());
        adapter.notifyDataSetChanged();
    }

}