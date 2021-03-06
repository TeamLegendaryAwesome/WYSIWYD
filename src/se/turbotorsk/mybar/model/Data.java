/*
New BSD License
Copyright (c) 2012, MyBar Team All rights reserved.
mybar@turbotorsk.se

Redistribution and use in source and binary forms, with or without modification,
 are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice,
  this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice,
  this list of conditions and the following disclaimer in the documentation
  and/or other materials provided with the distribution.
 * Neither the name of the MyBar nor the names of its contributors may be 
  used to endorse or promote products derived from this software without
  specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED 
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. 
IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, 
INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, 
BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, 
DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY 
OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE 
OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED 
OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package se.turbotorsk.mybar.model;

import java.util.LinkedList;
import java.util.List;

import se.turbotorsk.mybar.controller.Controller;
import se.turbotorsk.mybar.controller.MyBarApplication;
import se.turbotorsk.mybar.model.database.DrinkTable;
import se.turbotorsk.mybar.model.database.IngredientTable;
import se.turbotorsk.mybar.model.database.MyBarContentProvider;
import se.turbotorsk.mybar.model.database.MyBarTable;
import se.turbotorsk.mybar.model.externaldata.JsonParse;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

/**
 * Contains all high level methods that communicates directly with
 * MyBarContentProvider. Exposes the ContentProvider table functions to other
 * parts of the application with useful methods.
 * 
 * @author Mathias Karlgren (<a
 *         href="mailto:***REMOVED***">email</a>)
 */
public final class Data {
    // Hide Utility Class Constructor.
    private Data() {
    };

    // Define duplicate literals.
    private static final String DEFAULT_URL = "http://repro.mybar.turbotorsk.se/img/no_img.png";
    private static final String DEFAULT_INGREDIENTS = "test ingredients";
    private static final String DEFAULT_INGREDIENT_DESCRIPTION = "<insert description>";
    private static final int DEFAULT_RATING = 5;
    private static final int DEFAULT_FAVORITE = 0;

    /**
     * Inserts TestData in the database.
     * 
     * @return 0
     */
    public static int insertTestData() {
        Uri myBarUri = null;

        // Drinks uses autoincrement in the _id field.
        Drink[] testDrinks = {
                new Drink(1, "Margarita", DEFAULT_URL, "Martini Glass", DEFAULT_INGREDIENTS,
                        "Margarita instructions", DEFAULT_RATING, DEFAULT_FAVORITE),
                new Drink(2, "Tequila", DEFAULT_URL, "Shot Glass", DEFAULT_INGREDIENTS,
                        "Pour Tequila in shot glass", DEFAULT_RATING, DEFAULT_FAVORITE),
                new Drink(3, "Cosmopolitan", DEFAULT_URL, "Martini Glass", DEFAULT_INGREDIENTS,
                        "Cosmopolitan instructions", DEFAULT_RATING, DEFAULT_FAVORITE),
                new Drink(4, "Cuba Libre", DEFAULT_URL, "Highball Glass", DEFAULT_INGREDIENTS,
                        "Cuba Libre instructions", DEFAULT_RATING, DEFAULT_FAVORITE),
                new Drink(5, "Martini", DEFAULT_URL, "Martini Glass", DEFAULT_INGREDIENTS,
                        "Pour Martini in glass", DEFAULT_RATING, DEFAULT_FAVORITE),
                new Drink(6, "Irish Coffee", DEFAULT_URL, "Coffee Glass", DEFAULT_INGREDIENTS,
                        "Irish Coffee instructions", DEFAULT_RATING, DEFAULT_FAVORITE) };

        // Insert testDrinks.
        for (Drink testDrink : testDrinks) {
            ContentValues values = testDrink.getContentValues();
            myBarUri = MyBarApplication.contentResolver().insert(
                    MyBarContentProvider.CONTENTURI_DRINK, values);
            Log.d(Data.class.getClass().getName(),
                    "Inserted Drink. Created row: " + myBarUri.toString());
        }

        // No autoincrement in Ingredients. Set the _id field manually.
        Ingredient[] testIngredients = {
                new Ingredient(1, "Koskenkorva Vodka", DEFAULT_URL, "Vodka", 40,
                        DEFAULT_INGREDIENT_DESCRIPTION),
                new Ingredient(2, "Baileys", DEFAULT_URL, "Liqueur", 20,
                        DEFAULT_INGREDIENT_DESCRIPTION),
                new Ingredient(3, "Dark Rum", DEFAULT_URL, "Rum", 40,
                        DEFAULT_INGREDIENT_DESCRIPTION),
                new Ingredient(4, "Light Rum", DEFAULT_URL, "Rum", 40,
                        DEFAULT_INGREDIENT_DESCRIPTION),
                new Ingredient(5, "Gordon's Gin", DEFAULT_URL, "Gin", 40,
                        DEFAULT_INGREDIENT_DESCRIPTION) };

        // Insert testIngredients.
        for (Ingredient testIngredient : testIngredients) {
            ContentValues values = testIngredient.getContentValues();
            myBarUri = MyBarApplication.contentResolver().insert(
                    MyBarContentProvider.CONTENTURI_INGREDIENT, values);
            Log.d(Data.class.getClass().getName(),
                    "Inserted Ingredient. Created row: " + myBarUri.toString());
        }
        return 0;
    }

    /**
     * Deletes the testData without removing the database. Does not reset _id
     * counters.
     * 
     * @return rowsDeleted.
     */
    public static int deleteTestData() {
        int rowsDeleted = 0;
        rowsDeleted += MyBarApplication.contentResolver().delete(
                MyBarContentProvider.CONTENTURI_DRINK, null, null);
        rowsDeleted += MyBarApplication.contentResolver().delete(
                MyBarContentProvider.CONTENTURI_INGREDIENT, null, null);
        rowsDeleted += MyBarApplication.contentResolver().delete(
                MyBarContentProvider.CONTENTURI_MYBAR, null, null);
        return rowsDeleted;
    }

    /**
     * Deletes Drink and Ingredients Tables without removing the database. Does
     * not reset _id counters.
     * 
     * @return rowsDeleted.
     */
    public static int deleteData() {
        int rowsDeleted = 0;
        rowsDeleted += MyBarApplication.contentResolver().delete(
                MyBarContentProvider.CONTENTURI_DRINK, null, null);
        rowsDeleted += MyBarApplication.contentResolver().delete(
                MyBarContentProvider.CONTENTURI_INGREDIENT, null, null);
        return rowsDeleted;
    }

    /**
     * Syncs remote datastore (JSON) with local SQLite database.
     * 
     * @return false
     */
    public static boolean syncDatabase() {
        return JsonParse.getDb();
    }

    /**
     * Adds a new ingredient to MyBarTable.
     * 
     * @param ingredientID _id column of the ingredient.
     * @param location "Home", "Work".
     * @return 0 if successful.
     */
    public static int addMyBar(int ingredientID, String location) {
        ContentValues values = new ContentValues();
        values.put("ingredientid", ingredientID);
        values.put("location", location);
        MyBarApplication.contentResolver().insert(MyBarContentProvider.CONTENTURI_MYBAR, values);

        // Print the added drink.
        Log.d(Data.class.getClass().getName(),
                "Added ID to " + "MyBarTable: " + values.get("ingredientid"));

        return 0;
    }

    /**
     * Removes a row in MyBarTable.
     * 
     * @param ingredientID _id column of the ingredient.
     * @param location "Home", "Work".
     * @return 0 if successful. 1 if error. See LogCat.
     */
    public static int dropMyBar(int ingredientID, String location) {
        // Query database.
        Cursor cursor = MyBarApplication.contentResolver()
                .query(MyBarContentProvider.CONTENTURI_MYBAR,
                        null,
                        MyBarTable.COLUMN_INGREDIENTID + " = ? AND " + MyBarTable.COLUMN_LOCATION
                                + " = ? ",
                        new String[] { Integer.toString(ingredientID), location }, null);

        // Successful query?.
        if (cursor != null) {

            // Is there any data from the requested Query.
            if (cursor.moveToFirst()) {

                MyBarApplication.contentResolver().delete(
                        MyBarContentProvider.CONTENTURI_MYBAR,
                        MyBarTable.COLUMN_INGREDIENTID + " = ? AND " + MyBarTable.COLUMN_LOCATION
                                + " = ? ",
                        new String[] { Integer.toString(ingredientID), location });

                // Print the removed ingredient.
                Log.d(Data.class.getClass().getName(),
                        "Removed ID from MyBarTable: "
                                + cursor.getInt(cursor.getColumnIndexOrThrow(MyBarTable.COLUMN_ID))
                                + " "
                                + cursor.getString(cursor
                                        .getColumnIndexOrThrow(MyBarTable.COLUMN_INGREDIENTID))
                                + " "
                                + cursor.getString(cursor
                                        .getColumnIndexOrThrow(MyBarTable.COLUMN_LOCATION)));

                // Close the cursor.
                cursor.close();

                return 0;

            } else {
                // Error message in LogCat.
                Log.e(Data.class.getClass().getName(), "dropMyBar(): " + ingredientID + " "
                        + location + " doesn't exist");

                // Close the cursor.
                cursor.close();

                // ingredient doesn't exist. Return 1.
                return 1;
            }
        }
        return 1;
    }

    /**
     * Inserts a new Drink in the DrinkTable. Trying to insert another Drink
     * with the same name yields an error message.
     * 
     * @param name Drink object that should be inserted into the database.
     * @return 0 if successful, 1 if error. See LogCat.
     */
    public static int addDrink(Drink name) {
        ContentValues values = name.getContentValues();

        // Choose which columns you want to query. null queries all columns.
        String[] projection = { DrinkTable.COLUMN_ID, DrinkTable.COLUMN_NAME };

        // Query database.
        Cursor cursor = MyBarApplication.contentResolver().query(
                MyBarContentProvider.CONTENTURI_DRINK, projection,
                DrinkTable.COLUMN_NAME + " = ? ", new String[] { name.getName() }, null);

        // Successful query?.
        if (cursor != null) {

            // Is there any data from the requested Query.
            if (cursor.moveToFirst()) {

                // Error message in LogCat.
                Log.e(Data.class.getClass().getName(), "addDrink(): " + name.getName()
                        + " already exists");

                // Close the cursor.
                cursor.close();

                // name already exists. Return 1.
                return 1;

            } else {
                MyBarApplication.contentResolver().insert(MyBarContentProvider.CONTENTURI_DRINK,
                        values);

                // Print the added drink.
                Log.d(Data.class.getClass().getName(), "Added Drink: " + name.getName());

                // Close the cursor.
                cursor.close();

                return 0;
            }
        }
        return 1;
    }

    /**
     * Removes a Drink in the DrinkTable.
     * 
     * @param iD an Integer _id that should be removed from the database.
     * @return 0 if successful, 1 if error. See LogCat.
     */
    public static int dropDrink(int iD) {
        // Choose which columns you want to query. null queries all columns.
        String[] projection = { DrinkTable.COLUMN_ID, DrinkTable.COLUMN_NAME };

        // Query database.
        Cursor cursor = MyBarApplication.contentResolver().query(
                MyBarContentProvider.CONTENTURI_DRINK, projection, DrinkTable.COLUMN_ID + "=" + iD,
                null, null);

        // Successful query?.
        if (cursor != null) {

            // Is there any data from the requested Query.
            if (cursor.moveToFirst()) {

                MyBarApplication.contentResolver().delete(MyBarContentProvider.CONTENTURI_DRINK,
                        DrinkTable.COLUMN_ID + "=" + iD, null);

                // Print the removed drink.
                Log.d(Data.class.getClass().getName(),
                        "Removed Drink: "
                                + cursor.getInt(cursor.getColumnIndexOrThrow(DrinkTable.COLUMN_ID))
                                + " "
                                + cursor.getString(cursor
                                        .getColumnIndexOrThrow(DrinkTable.COLUMN_NAME)));

                // Close the cursor.
                cursor.close();

                return 0;

            } else {
                // Error message in LogCat.
                Log.e(Data.class.getClass().getName(), "dropDrink(): " + iD + " doesn't exist");

                // Close the cursor.
                cursor.close();

                // name doesn't exist. Return 1.
                return 1;
            }
        }
        return 1;
    }

    /**
     * Returns all rows in MyBarTable.
     * 
     * @return LinkedList<MyBar>.
     */
    public static List<MyBar> getAllMyBar() {
        LinkedList<MyBar> mybarList = new LinkedList<MyBar>();

        // Query database.
        Cursor cursor = MyBarApplication.contentResolver().query(
                MyBarContentProvider.CONTENTURI_MYBAR, null, null, null, null);

        // Successful query?.
        if (cursor != null) {

            // Is there any data from the requested Query.
            if (cursor.moveToFirst()) {

                do {
                    int id = cursor.getInt(cursor.getColumnIndexOrThrow(MyBarTable.COLUMN_ID));
                    int ingredientid = cursor.getInt(cursor
                            .getColumnIndexOrThrow(MyBarTable.COLUMN_INGREDIENTID));
                    String location = cursor.getString(cursor
                            .getColumnIndexOrThrow(MyBarTable.COLUMN_LOCATION));
                    mybarList.add(new MyBar(id, ingredientid, location));
                } while (cursor.moveToNext());

                // Close the cursor.
                cursor.close();

                return mybarList;
            } else {
                // Close the cursor.
                cursor.close();

                // No mybar in Query. Return Empty LinkedList<MyBar>.
                return new LinkedList<MyBar>();
            }
        }
        return null;
    }

    /**
     * Return all Drinks from the Drinks table.
     * 
     * @return LinkedList<Drink>.
     */
    public static List<Drink> getAllDrinks() {
        LinkedList<Drink> drinkList = new LinkedList<Drink>();

        // Query database.
        Cursor cursor = MyBarApplication.contentResolver().query(
                MyBarContentProvider.CONTENTURI_DRINK, null, null, null,
                DrinkTable.COLUMN_NAME + " COLLATE NOCASE ASC");

        // Successful query?.
        if (cursor != null) {

            // Is there any data from the requested Query.
            if (cursor.moveToFirst()) {

                do {
                    int id = cursor.getInt(cursor.getColumnIndexOrThrow(DrinkTable.COLUMN_ID));
                    String name = cursor.getString(cursor
                            .getColumnIndexOrThrow(DrinkTable.COLUMN_NAME));
                    String url = cursor.getString(cursor
                            .getColumnIndexOrThrow(DrinkTable.COLUMN_URL));
                    String glass = cursor.getString(cursor
                            .getColumnIndexOrThrow(DrinkTable.COLUMN_GLASS));
                    String ingredient = cursor.getString(cursor
                            .getColumnIndexOrThrow(DrinkTable.COLUMN_INGREDIENT));
                    String description = cursor.getString(cursor
                            .getColumnIndexOrThrow(DrinkTable.COLUMN_DESCRIPTION));
                    int rating = cursor.getInt(cursor
                            .getColumnIndexOrThrow(DrinkTable.COLUMN_RATING));
                    int favorite = cursor.getInt(cursor
                            .getColumnIndexOrThrow(DrinkTable.COLUMN_FAVORITE));
                    drinkList.add(new Drink(id, name, url, glass, ingredient, description, rating,
                            favorite));
                } while (cursor.moveToNext());

                // Close the cursor.
                cursor.close();

                return drinkList;
            } else {
                // Close the cursor.
                cursor.close();

                // No drinks in Query. Return Empty LinkedList<Drink>.
                return new LinkedList<Drink>();
            }
        }
        return null;
    }

    /**
     * Returns all Ingredients from the ingredient table.
     * 
     * @return LinkedList<Ingredient>.
     */
    public static List<Ingredient> getAllIngredients() {
        LinkedList<Ingredient> ingredientList = new LinkedList<Ingredient>();

        // Query database. No projection.
        Cursor cursor = MyBarApplication.contentResolver().query(
                MyBarContentProvider.CONTENTURI_INGREDIENT, null, null, null,
                IngredientTable.COLUMN_NAME + " COLLATE NOCASE ASC");

        // Successful query?.
        if (cursor != null) {

            // Is there any data from the requested Query.
            if (cursor.moveToFirst()) {

                do {
                    int id = cursor.getInt(cursor.getColumnIndexOrThrow(IngredientTable.COLUMN_ID));
                    String name = cursor.getString(cursor
                            .getColumnIndexOrThrow(IngredientTable.COLUMN_NAME));
                    String url = cursor.getString(cursor
                            .getColumnIndexOrThrow(IngredientTable.COLUMN_URL));
                    String type = cursor.getString(cursor
                            .getColumnIndexOrThrow(IngredientTable.COLUMN_TYPE));
                    int alcoholcontent = cursor.getInt(cursor
                            .getColumnIndexOrThrow(IngredientTable.COLUMN_ALCOHOLCONTENT));
                    String description = cursor.getString(cursor
                            .getColumnIndexOrThrow(IngredientTable.COLUMN_DESCRIPTION));
                    ingredientList.add(new Ingredient(id, name, url, type, alcoholcontent,
                            description));
                } while (cursor.moveToNext());

                // Close the cursor.
                cursor.close();

                return ingredientList;
            } else {
                // Close the cursor.
                cursor.close();

                // No ingredients in Query. Return Empty
                // LinkedList<Ingredient>.
                return new LinkedList<Ingredient>();
            }
        }
        return null;
    }

    /**
     * Returns a Drink object by ID. Query: SELECT * WHERE _id = id.
     * 
     * @param iD the _id of the Drink to return
     * @return aDrink
     */
    public static Drink getDrinkByID(int iD) {
        // Query database.
        Cursor cursor = MyBarApplication.contentResolver().query(
                MyBarContentProvider.CONTENTURI_DRINK, null, DrinkTable.COLUMN_ID + "=" + iD, null,
                null);

        // Successful query?.
        if (cursor != null) {

            // Is there any data from the requested Query.
            if (cursor.moveToFirst()) {

                int id = cursor.getInt(cursor.getColumnIndexOrThrow(DrinkTable.COLUMN_ID));
                String name = cursor
                        .getString(cursor.getColumnIndexOrThrow(DrinkTable.COLUMN_NAME));
                String url = cursor.getString(cursor.getColumnIndexOrThrow(DrinkTable.COLUMN_URL));
                String glass = cursor.getString(cursor
                        .getColumnIndexOrThrow(DrinkTable.COLUMN_GLASS));
                String ingredient = cursor.getString(cursor
                        .getColumnIndexOrThrow(DrinkTable.COLUMN_INGREDIENT));
                String description = cursor.getString(cursor
                        .getColumnIndexOrThrow(DrinkTable.COLUMN_DESCRIPTION));
                int rating = cursor.getInt(cursor.getColumnIndexOrThrow(DrinkTable.COLUMN_RATING));
                int favorite = cursor.getInt(cursor
                        .getColumnIndexOrThrow(DrinkTable.COLUMN_FAVORITE));
                Drink drink = new Drink(id, name, url, glass, ingredient, description, rating,
                        favorite);

                // Close the cursor.
                cursor.close();

                return drink;
            } else {
                // Error message in LogCat.
                Log.e(Data.class.getClass().getName(), "ID doesn't exist");

                // ID doesn't exist. Return Drink with ID = 0.
                Drink drink = new Drink(0, "", "", "", "", "", 0, 0);

                // Close the cursor.
                cursor.close();

                return drink;
            }
        }
        return null;
    }

    /**
     * Returns an Ingredient object by ID. Query: SELECT * WHERE _id = id.
     * 
     * @param iD the _id of the Ingredient to return
     * @return anIngredient
     */
    public static Ingredient getIngredientByID(int iD) {
        // Query database. No projection.
        Cursor cursor = MyBarApplication.contentResolver().query(
                MyBarContentProvider.CONTENTURI_INGREDIENT, null,
                IngredientTable.COLUMN_ID + "=" + iD, null, null);

        // Successful query?.
        if (cursor != null) {

            // Is there any data from the requested Query.
            if (cursor.moveToFirst()) {

                int id = cursor.getInt(cursor.getColumnIndexOrThrow(IngredientTable.COLUMN_ID));
                String name = cursor.getString(cursor
                        .getColumnIndexOrThrow(IngredientTable.COLUMN_NAME));
                String url = cursor.getString(cursor
                        .getColumnIndexOrThrow(IngredientTable.COLUMN_URL));
                String type = cursor.getString(cursor
                        .getColumnIndexOrThrow(IngredientTable.COLUMN_TYPE));
                int alcoholcontent = cursor.getInt(cursor
                        .getColumnIndexOrThrow(IngredientTable.COLUMN_ALCOHOLCONTENT));
                String description = cursor.getString(cursor
                        .getColumnIndexOrThrow(IngredientTable.COLUMN_DESCRIPTION));
                Ingredient ingredient = new Ingredient(id, name, url, type, alcoholcontent,
                        description);

                // Close the cursor.
                cursor.close();

                return ingredient;
            } else {
                // Error message in LogCat.
                Log.e(Data.class.getClass().getName(), "ID doesn't exist");

                // ID doesn't exist. Return Ingredient with ID = 0.
                Ingredient ingredient = new Ingredient(0, "", "", "", 0, "");

                // Close the cursor.
                cursor.close();

                return ingredient;
            }
        }
        return null;
    }

    /**
     * Returns all Drinks with the Favorite column set to 1.
     * 
     * @return LinkedList<Drink>.
     */
    public static List<Drink> getAllFavorites() {
        LinkedList<Drink> drinkList = new LinkedList<Drink>();

        // Query database. No Projection
        Cursor cursor = MyBarApplication.contentResolver().query(
                MyBarContentProvider.CONTENTURI_DRINK, null, "favorite=1", null,
                DrinkTable.COLUMN_NAME + " COLLATE NOCASE ASC");

        // Successful query?.
        if (cursor != null) {

            // Is there any data from the requested Query.
            if (cursor.moveToFirst()) {

                do {
                    int id = cursor.getInt(cursor.getColumnIndexOrThrow(DrinkTable.COLUMN_ID));
                    String name = cursor.getString(cursor
                            .getColumnIndexOrThrow(DrinkTable.COLUMN_NAME));
                    String url = cursor.getString(cursor
                            .getColumnIndexOrThrow(DrinkTable.COLUMN_URL));
                    String glass = cursor.getString(cursor
                            .getColumnIndexOrThrow(DrinkTable.COLUMN_GLASS));
                    String ingredient = cursor.getString(cursor
                            .getColumnIndexOrThrow(DrinkTable.COLUMN_INGREDIENT));
                    String description = cursor.getString(cursor
                            .getColumnIndexOrThrow(DrinkTable.COLUMN_DESCRIPTION));
                    int rating = cursor.getInt(cursor
                            .getColumnIndexOrThrow(DrinkTable.COLUMN_RATING));
                    int favorite = cursor.getInt(cursor
                            .getColumnIndexOrThrow(DrinkTable.COLUMN_FAVORITE));
                    drinkList.add(new Drink(id, name, url, glass, ingredient, description, rating,
                            favorite));
                } while (cursor.moveToNext());

                // Close the cursor.
                cursor.close();

                return drinkList;
            } else {
                // Close the cursor.
                cursor.close();

                // No Favorites. Return Empty LinkedList<Drink>.
                return new LinkedList<Drink>();
            }
        }
        return null;
    }

    /**
     * Sets the favorite row in a Drink to 1.
     * 
     * @param iD _id of the Drink.
     * @return 0 if OK. 1 if ID doesn't exist.
     */
    public static int setFavoriteByID(int iD) {
        ContentValues values = new ContentValues();

        // Choose which columns you want to query. null queries all columns.
        String[] projection = { DrinkTable.COLUMN_ID, DrinkTable.COLUMN_NAME };

        // Query database.
        Cursor cursor = MyBarApplication.contentResolver().query(
                MyBarContentProvider.CONTENTURI_DRINK, projection, DrinkTable.COLUMN_ID + "=" + iD,
                null, null);

        // Successful query?.
        if (cursor != null) {

            // Is there any data from the requested Query.
            if (cursor.moveToFirst()) {

                values.put("favorite", 1);
                int rowUpdated = MyBarApplication.contentResolver().update(
                        MyBarContentProvider.CONTENTURI_DRINK, values,
                        DrinkTable.COLUMN_ID + "=" + iD, null);

                // Print the favorited drink.
                Log.d(Data.class.getClass().getName(),
                        "Favorited Drink: "
                                + cursor.getString(cursor
                                        .getColumnIndexOrThrow(DrinkTable.COLUMN_NAME))
                                + " How many rows favorited: " + Integer.toString(rowUpdated));

                // Close the cursor.
                cursor.close();

                return 0;
            } else {
                // Close the cursor.
                cursor.close();

                // ID doesn't exist. Return 1.
                return 1;
            }
        }
        return 1;
    }

    /**
     * Sets the favorite row in a Drink to 1.
     * 
     * @param name the name of the Drink.
     * @return 0 if OK. 1 if name doesn't exist.
     */
    public static int setFavoriteByName(String name) {
        ContentValues values = new ContentValues();

        // Choose which columns you want to query. null queries all columns.
        String[] projection = { DrinkTable.COLUMN_ID, DrinkTable.COLUMN_NAME };

        // Query database.
        Cursor cursor = MyBarApplication.contentResolver().query(
                MyBarContentProvider.CONTENTURI_DRINK, projection,
                DrinkTable.COLUMN_NAME + " = ? ", new String[] { name }, null);

        // Successful query?.
        if (cursor != null) {

            // Is there any data from the requested Query.
            if (cursor.moveToFirst()) {

                values.put("favorite", 1);
                int rowUpdated = MyBarApplication.contentResolver().update(
                        MyBarContentProvider.CONTENTURI_DRINK, values,
                        DrinkTable.COLUMN_NAME + " = ? ", new String[] { name });

                // Print the favorited drink.
                Log.d(Data.class.getClass().getName(),
                        "Favorited Drink: "
                                + cursor.getString(cursor
                                        .getColumnIndexOrThrow(DrinkTable.COLUMN_NAME))
                                + " How many rows favorited: " + Integer.toString(rowUpdated));

                // Close the cursor.
                cursor.close();

                return 0;
            } else {
                // Close the cursor.
                cursor.close();

                // name doesn't exist. Return 1.
                return 1;
            }
        }
        return 1;
    }

    /**
     * Sets the columns in the Drink table to different values.
     * 
     * @param name name of the Drink to update.
     * @param column the column to update.
     * @param set the value to update the column with.
     * @return 0 if successful. 1 if error. See LogCat
     */
    public static int setDrink(String name, String column, int set) {
        ContentValues values = new ContentValues();

        // Choose which columns you want to query. null queries all columns.
        String[] projection = { DrinkTable.COLUMN_ID, DrinkTable.COLUMN_NAME };

        // Query database.
        Cursor cursor = MyBarApplication.contentResolver().query(
                MyBarContentProvider.CONTENTURI_DRINK, projection,
                DrinkTable.COLUMN_NAME + " = ? ", new String[] { name }, null);

        // Successful query?.
        if (cursor != null) {

            // Is there any data from the requested Query.
            if (cursor.moveToFirst()) {

                values.put(column, set);
                int rowUpdated = MyBarApplication.contentResolver().update(
                        MyBarContentProvider.CONTENTURI_DRINK, values,
                        DrinkTable.COLUMN_NAME + " = ? ", new String[] { name });

                // Print the updated drink.
                Log.d(Data.class.getClass().getName(),
                        "Drink: "
                                + cursor.getString(cursor
                                        .getColumnIndexOrThrow(DrinkTable.COLUMN_NAME))
                                + ". Rows updated in this query: " + Integer.toString(rowUpdated));

                // Close the cursor.
                cursor.close();

                return 0;
            } else {
                // Error message in LogCat.
                Log.e(Data.class.getClass().getName(), "name doesn't exist");

                // Close the cursor.
                cursor.close();

                // name doesn't exist. Return 1.
                return 1;
            }
        }
        return 1;
    }

    /**
     * Sets the columns in the Drink table to different values.
     * 
     * @param iD _id of the Drink to update.
     * @param column The column to update.
     * @param set The value to update the column with.
     * @return 0 if successful. 1 if error. See LogCat
     */
    public static int setDrink(int iD, String column, int set) {
        ContentValues values = new ContentValues();

        // Choose which columns you want to query. null queries all columns.
        String[] projection = { DrinkTable.COLUMN_ID, DrinkTable.COLUMN_NAME };

        // Query database.
        Cursor cursor = MyBarApplication.contentResolver().query(
                MyBarContentProvider.CONTENTURI_DRINK, projection, DrinkTable.COLUMN_ID + "=" + iD,
                null, null);

        // Successful query?.
        if (cursor != null) {

            // Is there any data from the requested Query.
            if (cursor.moveToFirst()) {

                values.put(column, set);
                int rowUpdated = MyBarApplication.contentResolver().update(
                        MyBarContentProvider.CONTENTURI_DRINK, values,
                        DrinkTable.COLUMN_ID + "=" + iD, null);

                // Print the updated drink.
                Log.d(Data.class.getClass().getName(),
                        "Drink: "
                                + cursor.getString(cursor
                                        .getColumnIndexOrThrow(DrinkTable.COLUMN_NAME))
                                + ". Rows updated in this query: " + Integer.toString(rowUpdated));

                // Close the cursor.
                cursor.close();

                return 0;
            } else {
                // Error message in LogCat.
                Log.e(Data.class.getClass().getName(), "ID doesn't exist");

                // Close the cursor.
                cursor.close();

                // ID doesn't exist. Return 1.
                return 1;
            }
        }
        return 1;
    }

    public static String[] getDrinkNameAsArray() {
        LinkedList<String> nameList = new LinkedList<String>();

        for (int i = 0; i < getAllDrinks().size(); i++) {
            nameList.add(getAllDrinks().get(i).getName());

            Log.d(Controller.class.getClass().getName(), "" + getAllDrinks().get(i).getId() + " "
                    + getAllDrinks().get(i).getName());
        }

        String[] array = new String[nameList.size()];
        nameList.toArray(array);

        return array;
    }

    /**
     * Search for ingredients in the database.
     * 
     * @param search Search for ingredient.
     * @param limit Limit returned ingredients.
     * @return A LinkedList with the ingredients containing the searchName
     *         string.
     */
    public static List<Ingredient> searchIngredients(String search, int limit) {
        LinkedList<Ingredient> ingredientList = new LinkedList<Ingredient>();

        // Query database.
        Cursor cursor = MyBarApplication.contentResolver().query(
                MyBarContentProvider.CONTENTURI_INGREDIENT, null,
                IngredientTable.COLUMN_NAME + " LIKE ? ", new String[] { "%" + search + "%" },
                IngredientTable.COLUMN_NAME + " COLLATE NOCASE ASC LIMIT " + limit);

        // Successful query?.
        if (cursor != null) {

            // Is there any data from the requested Query.
            if (cursor.moveToFirst()) {

                do {
                    int id = cursor.getInt(cursor.getColumnIndexOrThrow(IngredientTable.COLUMN_ID));
                    String name = cursor.getString(cursor
                            .getColumnIndexOrThrow(IngredientTable.COLUMN_NAME));
                    String url = cursor.getString(cursor
                            .getColumnIndexOrThrow(IngredientTable.COLUMN_URL));
                    String type = cursor.getString(cursor
                            .getColumnIndexOrThrow(IngredientTable.COLUMN_TYPE));
                    int alcoholcontent = cursor.getInt(cursor
                            .getColumnIndexOrThrow(IngredientTable.COLUMN_ALCOHOLCONTENT));
                    String description = cursor.getString(cursor
                            .getColumnIndexOrThrow(IngredientTable.COLUMN_DESCRIPTION));
                    ingredientList.add(new Ingredient(id, name, url, type, alcoholcontent,
                            description));
                    Log.d(Data.class.getName(), "Search returned: " + name);
                } while (cursor.moveToNext());

                // Close the cursor.
                cursor.close();

                return ingredientList;
            } else {
                // Close the cursor.
                cursor.close();

                // No ingredients in Query. Return Empty LinkedList<Ingredient>.
                return new LinkedList<Ingredient>();
            }
        }
        return null;
    }

    /**
     * Search for ingredientID's in MyBarTable.
     * 
     * @param iD Search for ingredientID's.
     * @return A LinkedList with MyBar objects.
     */
    public static List<MyBar> searchMyBar(int iD) {
        LinkedList<MyBar> myBarList = new LinkedList<MyBar>();

        // Query database.
        Cursor cursor = MyBarApplication.contentResolver().query(
                MyBarContentProvider.CONTENTURI_MYBAR, null,
                MyBarTable.COLUMN_INGREDIENTID + "=" + iD, null, null);

        // Successful query?.
        if (cursor != null) {

            // Is there any data from the requested Query.
            if (cursor.moveToFirst()) {

                do {
                    int id = cursor.getInt(cursor.getColumnIndexOrThrow(MyBarTable.COLUMN_ID));
                    int ingredientID = cursor.getInt(cursor
                            .getColumnIndexOrThrow(MyBarTable.COLUMN_INGREDIENTID));
                    String location = cursor.getString(cursor
                            .getColumnIndexOrThrow(MyBarTable.COLUMN_LOCATION));
                    myBarList.add(new MyBar(id, ingredientID, location));
                    Log.d(Data.class.getName(), "ingredientID returned: " + ingredientID);
                } while (cursor.moveToNext());

                // Close the cursor.
                cursor.close();

                return myBarList;
            } else {
                // Close the cursor.
                cursor.close();

                // No MyBar's in Query. Return Empty LinkedList<MyBar>.
                return new LinkedList<MyBar>();
            }
        }
        return null;
    }
}
