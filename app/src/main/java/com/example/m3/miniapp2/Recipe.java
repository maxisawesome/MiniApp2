package com.example.m3.miniapp2;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by m3 on 3/18/18.
 */

public class Recipe {
    //instance variables or fields
    public String title;
    public String imageUrl;
    public String instructionUrl;
    public String description;
    public String servings;
    public String prepTime;
    public String dietLabel;

    //constructor
    //default will be fine, so write nothing here

    //method
    //static methods that read the json in and load into Recipe

    //static method that loads our recipes.json using the helper method
    //this method will return an array list of recipes constructed from the JSON file
    public static ArrayList<Recipe> getRecipesFromFile(String filename, Context context){
        ArrayList<Recipe> recipeList = new ArrayList<Recipe>();

        //try to read from json, get info by using tags
        //construct a recipe object for each recipe in json
        //add the object to arrayList, return ArrayList
        try{
            String jsonString = loadJsonFromAsset("recipes.json", context);
            JSONObject json = new JSONObject(jsonString);
            JSONArray recipes = json.getJSONArray("recipes");

            //for loop to go through each recipe in your recipes array

            for (int i = 0; i < recipes.length(); i++){
                Recipe recipe = new Recipe();
                recipe.title = recipes.getJSONObject(i).getString("title");
                recipe.imageUrl = recipes.getJSONObject(i).getString("image");
                recipe.instructionUrl = recipes.getJSONObject(i).getString("url");
                recipe.description = recipes.getJSONObject(i).getString("description");
                recipe.servings = recipes.getJSONObject(i).getString("servings");
                recipe.prepTime = recipes.getJSONObject(i).getString("prepTime");
                recipe.dietLabel = recipes.getJSONObject(i).getString("dietLabel");
                recipeList.add(recipe);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return recipeList;
    }

    // helper method that loads from any Json file
    private static String loadJsonFromAsset(String filename, Context context) {
        String json = null;

        try {
            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        }
        catch (java.io.IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return json;
    }

}

