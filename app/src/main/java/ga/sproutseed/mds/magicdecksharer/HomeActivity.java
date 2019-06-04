package ga.sproutseed.mds.magicdecksharer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import entity.Result;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import util.APIInterface;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout navDrawer;
    private NavigationView navView;
    ActionBarDrawerToggle drawerToggle;
    Toolbar toolbar;
    AlertDialog alertDialog = null;
    EditText id = null, password = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.register_dialog,null);

        builder.setView(v);
        builder.setPositiveButton("사용자 추가",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String inputId = "";
                String inputPassword = "";
                if(id != null&&id.getText() != null) {
                    inputId = id.getText().toString();
                }
                 if(password!=null&&password.getText() != null){
                     inputPassword = password.getText().toString();
                }

                System.out.println("id "+id.getText().toString()+" password "+password.getText().toString());

                Retrofit dbUtil = new Retrofit.Builder()
                        .baseUrl("http://node.sproutseed.ga/db/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                APIInterface apiInterface = dbUtil.create(APIInterface.class);
                Call<Result> req = apiInterface.registerUser(inputId,inputPassword);
                req.enqueue(new Callback<Result>() {
                    @Override
                    public void onResponse(Call<Result> call, Response<Result> response) {
                        if(response.isSuccessful()){
                            Result res = response.body();
                            if(res.isSuccessed()){
                                Toast.makeText(getApplicationContext(),"사용자가 등록 됬습니다.",Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(getApplicationContext(),"사용자가 등록 중 문제가 생겼습니다.",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Result> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"사용자가 등록 중 문제가 생겼습니다.",Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
        builder.setNegativeButton("취소",null);
        alertDialog = builder.create();
        id = v.findViewById(R.id.new_user_id);
        password = v.findViewById(R.id.new_user_password);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.show();
                //builder.create().show();
            }
        });
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setTitle("CardDeckSharer");
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setHomeAsUpIndicator(R.drawable.ic_menu_white);

        navDrawer = findViewById(R.id.nav_drawer);
        navView = findViewById(R.id.nav_view);
        drawerToggle = new ActionBarDrawerToggle(this,navDrawer,toolbar,R.string.open_drawer,R.string.close_drawer);
        navDrawer.addDrawerListener(drawerToggle);
        navView.setNavigationItemSelectedListener(this);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        final Intent myDeckIntent = new Intent(getApplicationContext(),MyDeckActivity.class);
        final Intent makeDeckIntent = new Intent(getApplicationContext(), EditDeckActivity.class);
        final Intent uploadIntent = new Intent(getApplicationContext(),UploadActivity.class);
        switch (menuItem.getItemId()){
            case R.id.myDeck:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);

                LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                View v = inflater.inflate(R.layout.login_dialog,null);

                EditText id = v.findViewById(R.id.user_id);
                EditText password = v.findViewById(R.id.user_password);

                builder.setView(v);
                builder.setPositiveButton("로그인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        myDeckIntent.putExtra("id",id.getText().toString());
                        myDeckIntent.putExtra("password",password.getText().toString());
                        startActivity(myDeckIntent);
                    }
                });
                builder.setNegativeButton("취소",null);
                builder.create().show();


                break;
            case R.id.makeDeck:
                makeDeckIntent.putExtra("deckIdx",-1);
                startActivityForResult(makeDeckIntent,3000);
                break;
        }
        return true;
    }


    @Override
    public void onBackPressed(){
        if(navDrawer.isDrawerOpen(GravityCompat.START)){
            navDrawer.closeDrawers();
        }else{
            super.onBackPressed();
        }
    }
}
