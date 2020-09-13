package com.soulbuster.zomatodemoapp.Activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.soulbuster.zomatodemoapp.Model.Restaurant;
import com.soulbuster.zomatodemoapp.R;
import com.soulbuster.zomatodemoapp.Util.DataHandler;
import com.soulbuster.zomatodemoapp.Util.ItemClickListener;
import com.soulbuster.zomatodemoapp.Util.fetchData;
import com.soulbuster.zomatodemoapp.Util.myAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements DataHandler, ItemClickListener {

    private static final String TAG = "MainActivity";

    public ArrayList<Restaurant> restaurants=new ArrayList<>();

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    ProgressBar progressBar;
    fetchData fetch;
    Spinner spinner;
    ArrayAdapter arrAdapter;
    Context context;

    String currentCityId="3";


    final int MAX_RESTAURANTS=100;

    boolean isLoading=false;

    int currentItems,totalItems,scrollOutItems=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context=this;
        recyclerView=(RecyclerView)findViewById(R.id.recycler_view);

        final GridLayoutManager gridManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridManager);

        adapter=new myAdapter(restaurants,this,this);
        recyclerView.setAdapter(adapter);


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {


            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                currentItems = gridManager.getChildCount();
                totalItems = gridManager.getItemCount();
                scrollOutItems = gridManager.findFirstVisibleItemPosition();


                if(totalItems==MAX_RESTAURANTS){

                    Toast.makeText(MainActivity.this, "you have reached the end", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "onScrolled: 100 items done");

                }
                else if(dy>0 && (totalItems==currentItems+scrollOutItems)){

                    if(isLoading){

                    }else{
                        isLoading=true;
                        progressBar.setVisibility(View.VISIBLE);

                        Toast.makeText(MainActivity.this, "getting new data", Toast.LENGTH_SHORT).show();

                        fetchData fetch =new fetchData(restaurants);
                        fetch.setDatahandler(MainActivity.this);
                        fetch.execute(currentCityId,Integer.toString(totalItems));
                        Log.e(TAG, "onScrolled: getting new items");
                    }

                }
            }
        });


        progressBar=(ProgressBar)findViewById(R.id.progress);

        spinner=(Spinner)findViewById(R.id.spinner);

        arrAdapter=ArrayAdapter.createFromResource(this,R.array.spinner_arr,android.R.layout.simple_spinner_item);
        arrAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                switch (position){
                    case 0: currentCityId="3";
                        break;
                    case 1: currentCityId="13";
                        break;
                    case 2: currentCityId="1";
                        break;
                    case 3: currentCityId="7";
                        break;
                    case 4: currentCityId="4";
                        break;
                }
                requestCityChange(currentCityId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }


    @Override
    public void giveData( ArrayList<Restaurant> restaurants) {
        this.restaurants=restaurants;

        Log.e(TAG,"giveData: no of objects"+Integer.toString(restaurants.size()));

        isLoading=false;

        progressBar.setVisibility(View.GONE);

        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClicked(int position) {

        Intent i= new Intent(MainActivity.this,Details.class);

        i.putExtra("restaurant",restaurants.get(position));

        startActivityForResult(i,0);

        Log.e(TAG, "onItemClicked: "+Integer.toString(position+1)+"th item clicked");

    }


    public void requestCityChange(String cityId){

        restaurants.removeAll(restaurants);
        adapter.notifyDataSetChanged();

        progressBar.setVisibility(View.VISIBLE);

        fetch= new fetchData(restaurants);
        fetch.setDatahandler(this);
        fetch.execute(cityId,"0");

        Log.e(TAG, "data fetch done");

    }
}
