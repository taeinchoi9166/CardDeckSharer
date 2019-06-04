package adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import ga.sproutseed.mds.magicdecksharer.R;

public class CardItemViewHolder extends RecyclerView.ViewHolder {
    TextView cardSearchTitle;
    TextView cardSearchType;
    TextView cardSearchDetailSpec;
    Button cardSearchGetButton;
    Button cardSearchViewInfo;

    public CardItemViewHolder(@NonNull View itemView) {
        super(itemView);
        cardSearchTitle = itemView.findViewById(R.id.card_search_name);
        cardSearchType = itemView.findViewById(R.id.card_search_type);
        cardSearchDetailSpec = itemView.findViewById(R.id.card_search_detail_spec);
        cardSearchGetButton = itemView.findViewById(R.id.card_search_get_button);
        cardSearchViewInfo = itemView.findViewById(R.id.card_search_view_info);
    }
}
