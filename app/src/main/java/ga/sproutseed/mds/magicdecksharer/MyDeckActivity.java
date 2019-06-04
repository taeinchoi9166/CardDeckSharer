package ga.sproutseed.mds.magicdecksharer;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Adapter;
import android.widget.Toast;

import java.util.List;

import adapters.UserDeckAdapter;
import entity.UserDeck;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import util.APIInterface;

public class MyDeckActivity extends AppCompatActivity {
    RecyclerView myDeckList;
    Activity activity;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_deck);

        activity = this;

        myDeckList = findViewById(R.id.my_deck_list);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setTitle("나의 덱");

        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        myDeckList.setLayoutManager(manager);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String password = intent.getStringExtra("password");

        Retrofit dbUtil = new Retrofit.Builder()
                .baseUrl("http://node.sproutseed.ga/db/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIInterface apiInterface = dbUtil.create(APIInterface.class);
        Call<List<UserDeck>> req = apiInterface.getUserDecks(id,password);

        req.enqueue(new Callback<List<UserDeck>>() {
            @Override
            public void onResponse(Call<List<UserDeck>> call, Response<List<UserDeck>> response) {
                if(response.isSuccessful()){
                    List<UserDeck> list = response.body();

                    if(list.size() > 0){
                        UserDeckAdapter adapter = new UserDeckAdapter(activity,list);
                        myDeckList.setAdapter(adapter);
                    }

                }
            }

            @Override
            public void onFailure(Call<List<UserDeck>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"로딩중 문제가 발생했습니다.",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
