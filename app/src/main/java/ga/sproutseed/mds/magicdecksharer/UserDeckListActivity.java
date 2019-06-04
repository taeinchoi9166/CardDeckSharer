package ga.sproutseed.mds.magicdecksharer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class UserDeckListActivity extends AppCompatActivity {
    TextView title;
    TextView userDeckList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_deck_list);

        title = findViewById(R.id.user_deck_title);
        userDeckList = findViewById(R.id.user_deck_list);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String userDeckList = intent.getStringExtra("data");


        try{
            JsonParser parser = new JsonParser();
            JsonArray array = (JsonArray) parser.parse(userDeckList);

            String str = "";
            for(int i = 0; i < array.size(); i++){
                JsonObject obj = (JsonObject) array.get(i);
                str+=obj.get("name").getAsString()+" * "+obj.get("amount").getAsString()+"\n";
            }

            this.title.setText(title);
            this.userDeckList.setText(str);
        }catch (Exception e){
            this.title.setText("에러 발생");
            this.userDeckList.setText("덱리스트를 불러올 수 없습니다.");
        }

    }

}
