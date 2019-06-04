package adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import ga.sproutseed.mds.magicdecksharer.R;

public class UserCardViewHolder extends RecyclerView.ViewHolder {
    TextView cardEditName, cardEditCount;
    Button cardEditIncBtn, cardEditDecBtn;

    public UserCardViewHolder(@NonNull View itemView) {
        super(itemView);
        cardEditName = itemView.findViewById(R.id.card_edit_name);
        cardEditCount = itemView.findViewById(R.id.card_edit_count);
        cardEditIncBtn = itemView.findViewById(R.id.card_edit_inc_btn);
        cardEditDecBtn = itemView.findViewById(R.id.card_edit_dec_btn);
    }
}
