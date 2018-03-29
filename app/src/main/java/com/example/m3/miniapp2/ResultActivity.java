package com.example.m3.miniapp2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by m3 on 3/19/18.
 */

public class ResultActivity extends AppCompatActivity {

    private Context mContext;
    private ListView mListView;
    private TextView foundRecipesTextView;
    //private ImageView recipeImageView;
    private ArrayList<Recipe> recipeList;
    private ArrayList<Recipe> searchedRecipes;
    private String servingSelection;
    private String dietSelection;
    private String prepSelection;
    RecipeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_layout);

        mContext = this;
        servingSelection = this.getIntent().getExtras().getString("servingSelection");
        dietSelection = this.getIntent().getExtras().getString("dietSelection");
        prepSelection = this.getIntent().getExtras().getString("prepSelection");

        recipeList = Recipe.getRecipesFromFile("movies.json", this);
        searchedRecipes = new ArrayList<>();
        for(int i = 0; i < recipeList.size(); i++){
            Recipe recipe = recipeList.get(i);
            if(checkPrep(recipe, prepSelection) &&
                    checkDiet(recipe, dietSelection) &&
                    checkServings(recipe, servingSelection)){
                searchedRecipes.add(recipe);
            }
        }

        adapter = new RecipeAdapter(this, searchedRecipes);
        mListView = findViewById(R.id.recipe_list_view);
        mListView.setAdapter(adapter);

        foundRecipesTextView = findViewById(R.id.resultText);
        foundRecipesTextView.setText("Found " + searchedRecipes.size() + " recipes");

        //recipeImageView = findViewById(R.id.recipeImage);

    }


    public boolean checkServings(Recipe recipe, String selection){
        int curRecServings = Integer.parseInt(recipe.servings);
        if(selection.equals("less than 4")){
            return (curRecServings < 4);
        } else if (selection.equals("4-6")){
            return (curRecServings > 3) && (curRecServings < 7);
        } else if (selection.equals("7-9")){
            return (curRecServings > 6) && (curRecServings < 10);
        } else if (selection.equals("more than 10")){
            return (curRecServings > 10);
        } else if (selection.equals("")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkPrep(Recipe recipe, String selection) {
        String[] tokens = recipe.prepTime.split(" ");
        Integer timeInMin = 0;
        for (int i = 0; i < tokens.length; i++) {
            String t = tokens[i];
            if (t.equals("hour") || t.equals("hours")) {
                timeInMin += 60 * Integer.parseInt(tokens[i - 1]);
            } else if (t.equals("minutes")) {
                timeInMin += Integer.parseInt(tokens[i - 1]);
            }
        }

        if (selection.equals("30 minutes or less")) {
            return (timeInMin < 31);
        } else if (selection.equals("less than 1 hour")) {
            return (timeInMin < 61);
        } else if (selection.equals("more than 1 hour")) {
            return (timeInMin >= 60);
        } else if (selection.equals("")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkDiet(Recipe recipe, String selection) {
        return ( selection.equals("") ? true : recipe.dietLabel.equals(selection));
    }




}
