package ga.sproutseed.mds.magicdecksharer;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import adapters.UserCardAdapter;
import entity.CardItem;
import entity.Deck;
import entity.Result;
import entity.UserCard;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import util.APIInterface;

public class EditDeckActivity extends AppCompatActivity {
    Deck deck;
    List<UserCard> userDeck;
    Button saveBtn,addCardBtn;
    EditText title,description, userId, userPassword;
    RecyclerView cardEditList;
    UserCardAdapter userCardAdapter;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_deck);

        deck = new Deck();
        userDeck = new ArrayList<>();

        saveBtn = findViewById(R.id.save_deck_btn);
        addCardBtn = findViewById(R.id.add_card_btn);
        title = findViewById(R.id.edit_title);
        description = findViewById(R.id.edit_description);
        cardEditList = findViewById(R.id.card_edit_list);

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setTitle("덱 편집");

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        addCardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CardSearchActivity.class);
                startActivityForResult(intent, 3000);
            }
        });
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog = null;
                LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                View view = inflater.inflate(R.layout.login_dialog,null);
                userId = view.findViewById(R.id.user_id);
                userPassword = view.findViewById(R.id.user_password);
                builder.setView(view);

                builder.setPositiveButton("덱 저장", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String data = "[";
                        for(UserCard card : userDeck){
                            data+="{'name':'"+card.getName()+"','amount':"+card.getAmount()+"},";
                        }
                        data = data.substring(0,data.length()-1)+"]";
                        if(userDeck.size() == 0) data = "";

                        System.out.println(data);
                        if(!data.equals("")&&!userId.getText().toString().equals("")&&!userPassword.getText().toString().equals("")&&!title.getText().toString().equals("")){
                            Retrofit dbUtil = new Retrofit.Builder()
                                    .baseUrl("http://node.sproutseed.ga/db/")
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build();
                            APIInterface apiInterface = dbUtil.create(APIInterface.class);
                            Call<Result> req = apiInterface.saveDeck(userId.getText().toString(),userPassword.getText().toString(),title.getText().toString(),description.getText().toString(),data);
                            req.enqueue(new Callback<Result>() {
                                @Override
                                public void onResponse(Call<Result> call, Response<Result> response) {
                                    if(response.isSuccessful()){
                                        Result res = response.body();
                                        if(res.isSuccessed()){
                                            Toast.makeText(getApplicationContext(),"덱이 등록 됬습니다.",Toast.LENGTH_SHORT).show();
                                        }else{
                                            Toast.makeText(getApplicationContext(),"덱을 저장하던중 문제가 생겼습니다.",Toast.LENGTH_SHORT).show();
                                        }


                                    }
                                }

                                @Override
                                public void onFailure(Call<Result> call, Throwable t) {
                                    Toast.makeText(getApplicationContext(),"덱을 저장하던중 문제가 생겼습니다.",Toast.LENGTH_SHORT).show();
                                }
                            });
                        }else{
                            Toast.makeText(getApplicationContext(),"제목, 아이디, 비밀번호는 필수 이고, 카드는 한장이라도 있어야합니다.",Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                builder.setNegativeButton("취소",null);

                dialog = builder.create();
                dialog.show();

            }
        });

        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        cardEditList.setLayoutManager(manager);

        userCardAdapter = new UserCardAdapter(getApplicationContext(),userDeck);
        cardEditList.setAdapter(userCardAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==3000&&resultCode==200){
            int amount = 1;
            String name = data.getStringExtra("cardName");

            UserCard uc = new UserCard(name,amount);
            boolean isAddable = true;

            for( int i = 0; i < userDeck.size(); i++){
                if(userDeck.get(i).getName().equals(name)) isAddable = false;
            }

            if(isAddable) userDeck.add(uc);

            userCardAdapter = new UserCardAdapter(getApplicationContext(),userDeck);
            cardEditList.setAdapter(userCardAdapter);

            System.out.println(userDeck.size());
        }
    }
}
