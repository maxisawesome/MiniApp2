package com.example.m3.miniapp2;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by m3 on 3/18/18.
 */

public class RecipeAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Recipe> mRecipeList;
    private LayoutInflater mInflator;

    //constructor
    public RecipeAdapter(Context mContext, ArrayList<Recipe> mRecipeList){
        //initialize instances variables
        this.mContext = mContext;
        this.mRecipeList = mRecipeList;
        mInflator = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount(){
        return mRecipeList.size();
    }

    @Override
    public Object getItem(int position){
        return mRecipeList.get(position);
    }

    // returns the row id associated with the specific position in the list
    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder;
        // check if the view already exists
        // if yes, you don't need to inflate and findViewbyId again

        if (convertView == null) {
            // inflate
            convertView = mInflator.inflate(R.layout.list_item_detail, parent, false);
            // add the views to the golder
            holder = new ViewHolder();
            // views
            holder.titleTextView = convertView.findViewById(R.id.recipeNameText);
            holder.servingTextView = convertView.findViewById(R.id.servingTextListDetail);
            holder.thumbnailImageView = convertView.findViewById(R.id.recipeImage);
            holder.timeTextView = convertView.findViewById(R.id.prepTextListDetail);
            holder.notifButton = convertView.findViewById(R.id.notifButton);
            // add the holder to the view
            // for the future use
            convertView.setTag(holder);

        } else {
            // get the view holder from convertView
            holder = (ViewHolder)convertView.getTag();
        }
        // get relative subview of the row view
        // get corresponding recipe for each row
        // update the row view's textViews and imageview to display the information

        TextView titleTextView = holder.titleTextView;
        TextView servingTextView = holder.servingTextView;
        TextView timeTextView = holder.timeTextView;
        ImageView thumbnailImageView = holder.thumbnailImageView;
        Button notifButton = holder.notifButton;

        Recipe recipe = (Recipe)getItem(position);

        final Intent openRecipe = new Intent(Intent.ACTION_VIEW);
        openRecipe.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        openRecipe.setData(Uri.parse(recipe.instructionUrl));
        final PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0, openRecipe, 0);

        final String notifText = "Click here for detailed instructions on how to cook " + recipe.title + "!";
        notifButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(mContext, "channel_ID")
                        .setSmallIcon(R.drawable.chef_hat3)
                        .setContentTitle("Recipe Instructions")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true);
                NotificationCompat.BigTextStyle bigText = new NotificationCompat.BigTextStyle();
                bigText.bigText(notifText);
                mBuilder.setStyle(bigText);

                NotificationManagerCompat manager = NotificationManagerCompat.from(mContext);
                manager.notify((int) System.currentTimeMillis(), mBuilder.build());
            }
        });

        //update the row view's textviews and imageview to display the information

        titleTextView.setText(recipe.title);

        servingTextView.setText(recipe.servings + " servings");

        timeTextView.setText(recipe.prepTime);


        Picasso.with(mContext).load(recipe.imageUrl).into(thumbnailImageView);

        return convertView;

    }

    // viewHolder
    // is used to customize what you want to put into the view
    // it depends on the layout of your row
    // this will be a private statc class you have to define
    private static class ViewHolder {
        public TextView titleTextView;
        public TextView servingTextView;
        public TextView timeTextView;
        public ImageView thumbnailImageView;
        public Button notifButton;

    }

    //intent is used to pass information between activities
    // intent -> package
    // sender, reciever

}
