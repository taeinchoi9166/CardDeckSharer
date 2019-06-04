package adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import entity.CardInfo;
import entity.CardItem;
import ga.sproutseed.mds.magicdecksharer.CardViewerActivity;
import ga.sproutseed.mds.magicdecksharer.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import util.APIInterface;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class CardSearchAdapter extends RecyclerView.Adapter<CardItemViewHolder> {
    Activity activity;
    Context ctx;
    List<CardItem> cardItemList;

    AlertDialog.Builder dialog;
    AlertDialog dialogProcess;

    public CardSearchAdapter(List<CardItem> cardItemList,Activity activity) {
        this.cardItemList = cardItemList;
        this.activity = activity;
        this.ctx = activity.getApplicationContext();
    }

    @NonNull
    @Override
    public CardItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.card_search_item,viewGroup,false);
        return new CardItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardItemViewHolder cardItemViewHolder, int i) {
        System.out.println(i);
        CardItem item = cardItemList.get(i);
        cardItemViewHolder.cardSearchTitle.setText(item.getTitle());
        cardItemViewHolder.cardSearchType.setText(item.getType());
        cardItemViewHolder.cardSearchDetailSpec.setText(item.getDetailSpec());
        cardItemViewHolder.cardSearchGetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("cardId",item.getId());
                intent.putExtra("cardName",item.getTitle());
                activity.setResult(200,intent);
                activity.finish();
            }
        });
        cardItemViewHolder.cardSearchViewInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<CardItem> list = new ArrayList<>();
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://node.sproutseed.ga/api/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                APIInterface service = retrofit.create(APIInterface.class);
                Call<CardInfo> req = service.getItemInfo(Integer.parseInt(item.getId()));
                final Intent intent = new Intent(activity.getApplicationContext(), CardViewerActivity.class);
                req.enqueue(new Callback<CardInfo>() {

                    @Override
                    public void onResponse(Call<CardInfo> call, Response<CardInfo> response) {
                        if(response.isSuccessful()){
                            if(dialogProcess != null && dialogProcess.isShowing()) dialogProcess.dismiss();
                            if(response.body() != null){
                                CardInfo cardInfo = response.body();

                                intent.putExtra("name",cardInfo.getName());
                                intent.putExtra("attr",cardInfo.getAttr());
                                intent.putExtra("type",cardInfo.getType());
                                intent.putExtra("kind",cardInfo.getKind());
                                intent.putExtra("atk",cardInfo.getAttack());
                                intent.putExtra("def",cardInfo.getDefense());
                                intent.putExtra("text",cardInfo.getText());
                                intent.putExtra("level",cardInfo.getLevel());
                                intent.putExtra("imageURL",cardInfo.getImageURL());
                                intent.putExtra("penScale",cardInfo.getPenScale());
                                intent.putExtra("penEffect",cardInfo.getPenEffect());

                                activity.startActivity(intent);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<CardInfo> call, Throwable t) {
                        if(dialogProcess != null && dialogProcess.isShowing()) dialogProcess.dismiss();
                        Toast.makeText(ctx,"카드 정보를 가져오는데 실패했습니다!",Toast.LENGTH_SHORT).show();
                    }
                });

                dialog = new AlertDialog.Builder(activity);
                LayoutInflater inflater = (LayoutInflater) activity.getSystemService(LAYOUT_INFLATER_SERVICE);
                View loadingView = inflater.inflate(R.layout.loading_data_dialog,null);
                dialog.setView(loadingView);
                dialogProcess = dialog.create();
                dialogProcess.setCanceledOnTouchOutside(false);
                dialogProcess.show();

            }
        });
    }

    @Override
    public int getItemCount() {
        System.out.println("size :"+cardItemList.size());
        return cardItemList.size();
    }
}
