package com.example.mailview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Faker faker = new Faker();
    List<EmailModel> mailModels;
    List<Drawable> listBackgrounds;
    List<String> hourExtensions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // init list of avatar backgrounds
        listBackgrounds = new ArrayList<>();
        listBackgrounds.add(getResources().getDrawable(R.drawable.rounded_textview1));
        listBackgrounds.add(getResources().getDrawable(R.drawable.rounded_textview2));
        listBackgrounds.add(getResources().getDrawable(R.drawable.rounded_textview3));
        listBackgrounds.add(getResources().getDrawable(R.drawable.rounded_textview4));
        listBackgrounds.add(getResources().getDrawable(R.drawable.rounded_textview5));

        // init hour extension (AM / PM)
        hourExtensions = new ArrayList<>();
        hourExtensions.add("AM");
        hourExtensions.add("PM");

        // init list of mails
        mailModels = new ArrayList<>();
        for (int i = 1; i <= 50; i++){
            boolean isLike = (faker.number().numberBetween(0,1) == 1) ? true : false;
            mailModels.add(new EmailModel(getRandomUserName(i), getRandomHour(),
                    faker.lorem().paragraph(),
                    faker.lorem().paragraph(), isLike));
        }

        RecyclerView recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        RecycleViewAdapter recycleViewAdapter = new RecycleViewAdapter(mailModels, listBackgrounds);
        recyclerView.setAdapter(recycleViewAdapter);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"white\">" + getString(R.string.app_name) + "</font>"));
        return true;
    }

    // Create random send hour
    private String getRandomHour() {
        return faker.number().numberBetween(0, 12) + ":" + faker.number().numberBetween(0, 60) + " " + getRandomHourExtension();
    }

    // Create random AM or PM
    private String getRandomHourExtension(){
        return hourExtensions.get( (int) (faker.number().numberBetween(0, 1)));
    }

    // Create random username that send email
    private String getRandomUserName(int i){
        return faker.name().fullName();
    }
}