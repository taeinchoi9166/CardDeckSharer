package adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import ga.sproutseed.mds.magicdecksharer.R;

public class UserDeckViewHolder extends RecyclerView.ViewHolder {
    TextView userDeckTitle, userDeckDescription, userDeckWriter;
    Button userDeckLinkBtn;

    public UserDeckViewHolder(@NonNull View itemView) {
        super(itemView);
        userDeckTitle = itemView.findViewById(R.id.user_deck_name);
        userDeckDescription = itemView.findViewById(R.id.user_deck_description);
        userDeckWriter = itemView.findViewById(R.id.user_deck_writer);
        userDeckLinkBtn = itemView.findViewById(R.id.user_deck_link_btn);
    }
}
