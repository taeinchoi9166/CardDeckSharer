package adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import entity.UserDeck;
import ga.sproutseed.mds.magicdecksharer.R;
import ga.sproutseed.mds.magicdecksharer.UserDeckListActivity;


public class UserDeckAdapter extends RecyclerView.Adapter<UserDeckViewHolder> {
    Activity activity;
    Context ctx;
    List<UserDeck> userDeckList;

    public UserDeckAdapter(Activity activity, List<UserDeck> userDeckList) {
        this.activity = activity;
        this.ctx = activity.getApplicationContext();
        this.userDeckList = userDeckList;
    }

    @NonNull
    @Override
    public UserDeckViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(ctx).inflate(R.layout.user_deck_item,viewGroup,false);
        return new UserDeckViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UserDeckViewHolder userDeckViewHolder, int i) {
        UserDeck item = userDeckList.get(i);
        userDeckViewHolder.userDeckTitle.setText(item.getTitle());
        userDeckViewHolder.userDeckDescription.setText(item.getDescription());
        userDeckViewHolder.userDeckWriter.setText("by "+item.getWriter());

        userDeckViewHolder.userDeckLinkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity.getApplicationContext(), UserDeckListActivity.class);
                intent.putExtra("data",item.getData());
                intent.putExtra("title",item.getTitle());
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        System.out.println(userDeckList.size());
        return userDeckList.size();
    }
}
