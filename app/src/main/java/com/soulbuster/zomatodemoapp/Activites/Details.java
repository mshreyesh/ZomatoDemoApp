package com.soulbuster.zomatodemoapp.Activites;

import androidx.appcompat.app.AppCompatActivity;
import com.soulbuster.zomatodemoapp.R;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.soulbuster.zomatodemoapp.Model.Restaurant;

public class Details extends AppCompatActivity {

    private static final String TAG = "Details";

    TextView bigTitle,rating,address,costForTwo;
    ImageView bigPoster;

    Restaurant currentRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        bigTitle=(TextView)findViewById(R.id.big_title);
        rating=(TextView)findViewById(R.id.rating);
        address=(TextView)findViewById(R.id.address);
        costForTwo=(TextView)findViewById(R.id.cost_for_two);
        bigPoster=(ImageView) findViewById(R.id.big_poster);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        currentRes=(Restaurant)getIntent().getSerializableExtra("restaurant");

        Glide.with(this).load(currentRes.getImage()).centerCrop().into(bigPoster);

        bigTitle.setText(currentRes.getName());
        rating.setText(currentRes.getRating()+"/5");
        address.setText("address : "+currentRes.getAddress());
        costForTwo.setText("Cost For Two : "+currentRes.getAvg_cost_for_two()+" "+currentRes.getCurrency());

    }
}

