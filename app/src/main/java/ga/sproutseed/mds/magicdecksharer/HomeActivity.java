package ga.sproutseed.mds.magicdecksharer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout navDrawer;
    private NavigationView navView;
    ActionBarDrawerToggle drawerToggle;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setTitle("HEEEEEEllow!!!");
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
        Intent intent = null;
        AlertDialog.Builder builder = null;
        LayoutInflater inflater = null;
        View v = null;
        switch (menuItem.getItemId()){
            case R.id.myDeck:
               builder = new AlertDialog.Builder(this);

                inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                v = inflater.inflate(R.layout.login_dialog,null);
                builder.setView(v);
                builder.setPositiveButton("로그인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final Intent redirectIntent = new Intent(getApplicationContext(),MyDeckActivity.class);
                        redirectIntent.putExtra("username","user");
                        startActivity(redirectIntent);
                    }
                });

                builder.setNegativeButton("취소",null);
                builder.create().show();

                break;
            case R.id.makeDeck:
                intent = new Intent(getApplicationContext(),EditDeckActivity.class);
                intent.putExtra("deckIdx",-1);
                startActivityForResult(intent,3000);
                break;
            case R.id.getUsersDeck:
                intent = new Intent(getApplicationContext(),UploadActivity.class);
                startActivity(intent);
                break;
            case R.id.registerUser:
               builder = new AlertDialog.Builder(this);
                AlertDialog alertDialog = null;
                EditText id = null, password = null,passwordConfirm = null;

                inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                v = inflater.inflate(R.layout.login_dialog,null);
                builder.setView(v);

                id = alertDialog.findViewById(R.id.user_id);
                password = alertDialog.findViewById(R.id.user_password);
                passwordConfirm = alertDialog.findViewById(R.id.user_passwordConfirm);

                builder.setPositiveButton("사용자 추가", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(password.getText().toString().equals(passwordConfirm.getText().toString())){

                        }
                    }
                });

                builder.setNegativeButton("취소",null);

                alertDialog = builder.create();


                alertDialog.show();
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
