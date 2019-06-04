package ga.sproutseed.mds.magicdecksharer;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import adapters.CardSearchAdapter;
import entity.CardItem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import util.APICaller;
import util.APIInterface;
import util.APIUtil;

public class CardSearchActivity extends AppCompatActivity{
    EditText inputKeyword;
    EditText inputPage;
    Button searchBtn;
    RecyclerView searchResultList;
    List<CardItem> cardItemList;
    CardSearchAdapter adapter;
    CardSearchActivity activity;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_search);

        inputKeyword = findViewById(R.id.keyword_input);
        inputPage = findViewById(R.id.page_input);
        searchBtn = findViewById(R.id.search_btn);
        searchResultList = findViewById(R.id.search_result);
        toolbar = findViewById(R.id.toolbar);

        cardItemList = new ArrayList<>();
        activity = this;

        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setTitle("검색");

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inputPage.getText().toString().equals("")) Toast.makeText(getApplicationContext(),"페이지를 입력하지 않아 1 페이지를 검색 합니다.",Toast.LENGTH_SHORT).show();
                int page = inputPage.getText().toString().equals("") ? 1 : Integer.parseInt(inputPage.getText().toString());
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://node.sproutseed.ga/api/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                APIInterface service = retrofit.create(APIInterface.class);
                Call<List<CardItem>> req = service.getSearchResult(inputKeyword.getText().toString(),page);
               //System.out.println(page+" "+keyword);
                req.enqueue(new Callback<List<CardItem>>() {
                    @Override
                    public void onResponse(Call<List<CardItem>> call, Response<List<CardItem>> response) {
                        if(response.isSuccessful()){
                            List<CardItem> list = response.body();
                            if(!list.isEmpty()){
                                adapter = new CardSearchAdapter(list,activity);
                                searchResultList.setAdapter(adapter);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<List<CardItem>> call, Throwable t) {
                       Toast.makeText(getApplicationContext(),"검색 실패.",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());
        searchResultList.setLayoutManager(manager);
        adapter = new CardSearchAdapter(cardItemList,activity);
        searchResultList.setAdapter(adapter);

    }



}
