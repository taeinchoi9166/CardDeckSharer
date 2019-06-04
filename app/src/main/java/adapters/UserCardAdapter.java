package adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import entity.UserCard;
import ga.sproutseed.mds.magicdecksharer.R;

public class UserCardAdapter extends RecyclerView.Adapter<UserCardViewHolder> {
    Context ctx;
    List<UserCard> userCardList;

    public UserCardAdapter(Context ctx, List<UserCard> userCardList) {
        this.ctx = ctx;
        this.userCardList = userCardList;
        System.out.println(" list size "+userCardList.size());
    }

    @NonNull
    @Override
    public UserCardViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(ctx).inflate(R.layout.card_edit_item,viewGroup,false);
        return new UserCardViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UserCardViewHolder userCardViewHolder, int i) {
        UserCard userCard = userCardList.get(i);

        userCardViewHolder.cardEditName.setText(userCard.getName());
        userCardViewHolder.cardEditCount.setText(userCard.getAmount()+"");
        userCardViewHolder.cardEditIncBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(userCard.getAmount());
                if(userCard.getAmount() < 3){
                    userCard.setAmount(userCard.getAmount()+1);
                    notifyDataSetChanged();
                }
            }
        });
        userCardViewHolder.cardEditDecBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(userCard.getAmount());
                if(userCard.getAmount() > 1){
                    userCard.setAmount(userCard.getAmount()-1);
                    notifyDataSetChanged();
                }else{
                    userCardList.remove(i);
                    notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return userCardList.size();
    }
}
