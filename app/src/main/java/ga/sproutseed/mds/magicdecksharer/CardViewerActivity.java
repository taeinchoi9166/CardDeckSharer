package ga.sproutseed.mds.magicdecksharer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class CardViewerActivity extends AppCompatActivity {
    ImageView cardViewImage;
    TextView cardViewName, cardViewAttr, cardViewType, cardViewKind, cardViewAtk, cardViewDef, cardViewLevel, cardViewText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_viewer);

        cardViewImage = findViewById(R.id.card_view_image);
        cardViewName = findViewById(R.id.card_view_name);
        cardViewAttr = findViewById(R.id.card_view_attr);
        cardViewType = findViewById(R.id.card_view_type);
        cardViewKind = findViewById(R.id.card_view_kind);
        cardViewLevel = findViewById(R.id.card_view_level);
        cardViewAtk = findViewById(R.id.card_view_atk);
        cardViewDef = findViewById(R.id.card_view_def);
        cardViewText = findViewById(R.id.card_view_text);

        Intent intent = getIntent();
        Glide.with(this).load(intent.getStringExtra("imageURL")).into(cardViewImage);

        cardViewAttr.setText(intent.getStringExtra("attr"));
        cardViewType.setText(intent.getStringExtra("type"));
        cardViewName.setText(intent.getStringExtra("name"));
        cardViewKind.setText(intent.getStringExtra("kind"));
        String kind = intent.getStringExtra("kind");
        if(kind!=null){
            if(kind.lastIndexOf("엑시즈") != -1){
                cardViewLevel.setText("랭크 "+intent.getStringExtra("level"));
            }else if(kind.lastIndexOf("링크") != -1){
                cardViewLevel.setText("링크 "+intent.getStringExtra("level"));
            }else{
                cardViewLevel.setText("레벨 "+intent.getStringExtra("level"));
            }
        }

        cardViewAtk.setText(intent.getStringExtra("atk") != null ? "공격력 "+intent.getStringExtra("atk") : "");
        cardViewDef.setText(intent.getStringExtra("def") != null ? "수비력 "+intent.getStringExtra("def") : "");
        String penScale = intent.getStringExtra("penScale");
        String penEffect = intent.getStringExtra("penEffect");
        String text = intent.getStringExtra("text");

        if(intent.getStringExtra("kind") != null && intent.getStringExtra("kind").lastIndexOf("펜듈럼") != -1){
            cardViewText.setText("펜듈럼 스케일 : "+penScale+"\n펜듈럼 효과 : \n"+penEffect+"\n\n"+text);
        }else{
            cardViewText.setText(text);
        }

    }
}
