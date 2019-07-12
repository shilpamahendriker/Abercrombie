package com.example.abercrombie.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abercrombie.R;
import com.example.abercrombie.activity.webview;
import com.example.abercrombie.model.Promotion;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PromoAdapter extends RecyclerView.Adapter<PromoAdapter.PromotionViewHolder> {


    // The activity context
    private Context context;
    // The ArrayList of movies in the RecyclerView
    private ArrayList<Promotion> promoList;

    /**
     * Constructor.
     *
     * @param context   Activity context
     * @param promoList List with promos to show
     */
    public PromoAdapter(Context context, ArrayList<Promotion> promoList) {
        this.context = context;
        this.promoList = promoList;
    }

    /**
     * Initiating ViewHolder with layout.
     *
     * @return RecyclerImageViewHolder
     *
     */
    @Override
    public PromotionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_inner_layout, parent, false);

        return new PromotionViewHolder(view);
    }

    /**
     * Setting content in Views in the ViewHolder.
     *
     * @param holder   ViewHolder
     * @param position position in adapter
     */

    @Override
    public void onBindViewHolder(@NonNull final PromotionViewHolder holder, int position) {




        final Promotion promotion = promoList.get(position);

        //binding the data with the view holder views

        //Using picasso library to display image form url
        Picasso
                .get()
                .load(promotion.getBackGroundImage())
                .into(holder.imageViewBgImage);

        // Assigning the data to the text views
        holder.textBottomDesc.setText(promotion.getTopDescription());
        holder.textBottomDesc.setBackgroundColor(10);
        holder.textTitle.setText(promotion.getTitle());
        Log.v("abcde", promotion.getTitle()) ;
        holder.textPromoMsg.setText(promotion.getPromoMessage());

        // make the link in the Bottom description clickable and redirect it to web link
        //holder.textBottomDesc.setMovementMethod(LinkMovementMethod.getInstance());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.textBottomDesc.setText(Html.fromHtml(promotion.getBottomDescription(), Html.FROM_HTML_MODE_COMPACT));
        } else {
            holder.textBottomDesc.setText(Html.fromHtml(promotion.getBottomDescription()));
        }

        Linkify.addLinks(holder.textBottomDesc, Linkify.ALL);
        //holder.textBottomDesc.setText(promotion.getBottomDescription());

        // Checking if the Json has inner array "content" if yes then adding them as buttons
        if (promotion.getContentList() != null){

            holder.linearLayoutContent.removeAllViews();
                for ( int j = 0; j < promotion.getContentList().size(); j++) {
                    final String buttonClickUrl = promotion.getContentList().get(j).getTarget();

                    Button myButton = new Button(context);
                    ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    holder.linearLayoutContent.addView(myButton);


                        myButton.setId(1000+j);
                        myButton.setText(promotion.getContentList().get(j).getTitle());

                    // Creating button click listner for the dynamically created buttons
                    myButton.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            Intent browserIntent = new Intent(context, webview.class);
                            browserIntent.putExtra("URL",buttonClickUrl);
                            context.startActivity(browserIntent);

                        }
                    });
                    holder.linearLayoutContent.setVisibility(View.VISIBLE);
                }


        } else {
            holder.linearLayoutContent.setVisibility(View.GONE);
        }


    }


    /**
     * @return int number of objects in adapter.
     */
    @Override
    public int getItemCount() {
        return (null != promoList ? promoList.size() : 0);
    }



    /**
     * Inner class, ViewHolder for the elements in the RecyclerView
     */
     class PromotionViewHolder extends RecyclerView.ViewHolder {

        TextView textTopDesc,textBottomDesc, textTitle, textPromoMsg;
        ImageView imageViewBgImage;
        LinearLayout linearLayoutContent;




        public PromotionViewHolder(View itemView) {
            super(itemView);

            imageViewBgImage = itemView.findViewById(R.id.ivbackGroundImage);
            textTopDesc = itemView.findViewById(R.id.txttopDescription);
            textTitle = itemView.findViewById(R.id.txtTitle);
            textPromoMsg = itemView.findViewById(R.id.txtPromoMsg);
            textBottomDesc = itemView.findViewById(R.id.txtBottomDescription);
            linearLayoutContent = itemView.findViewById(R.id.llContent);


        }
    }
}
