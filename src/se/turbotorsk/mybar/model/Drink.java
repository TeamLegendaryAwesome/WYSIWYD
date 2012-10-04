/*
Copyright (c) 2012, MyBar Team All rights reserved.
mybar@turbotorsk.se
Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
�	Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
�	Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
�	Neither the name of the <ORGANIZATION> nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/


/*
Copyright (c) 2012, MyBar Team All rights reserved.
mybar@turbotorsk.se
Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
�	Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
�	Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
�	Neither the name of the <ORGANIZATION> nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/

package se.turbotorsk.mybar.model;

import android.content.ContentValues;

//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Date;
//import java.text.ParseException;
//import android.util.Log;

/**
 * A drink object contains information about a drink.
 * 
 * @author Mathias Karlgren (matkarlg)
 * 
 */
public class Drink {

	private int _id = 0;
	private String name = "";
	private String url = "";
	private String glass = "";
	private String ingredient = "";
	private String description = "";
	private int rating = 0;
	private ContentValues values = null;
	
	public Drink(int _id, String name, String url, String glass,
			String ingredient, String description, int rating) {
		this._id = _id;
		this.name = name;
		this.url = url;
		this.glass = glass;
		this.ingredient = ingredient;
		this.description = description;
		this.rating = rating;
		
		values = new ContentValues();
		values.put("name", name);
	    values.put("url", url);
	    values.put("glass", glass);
	    values.put("ingredient", ingredient);
	    values.put("description", description);
	    values.put("rating", rating);
	}
	
	/*private Calendar calendar = Calendar.getInstance();
	private Date date = new Date(calendar.getTime().getTime());
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	public String getDate() {
		return dateFormat.format(date);
	}

	public void setDate(int year, int month, int day) {
		try {
			this.date = dateFormat.parse("" + year + "-" + month + "-" + day);
		} catch (ParseException e) {
			Log.e(e.getClass().getName(), "ParseException: " + e.getMessage(),
					e);
		}
	}

	public void setDate(Date date) {
		try {
			this.date = dateFormat.parse(date.toString());
		} catch (ParseException e) {
			Log.e(e.getClass().getName(), "ParseException: " + e.getMessage(),
					e);
		}
	}*/
	
	public ContentValues getContentValues() {
		return values;
	}
	
	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIngredient() {
		return ingredient;
	}

	public void setIngredient(String ingredient) {
		this.ingredient = ingredient;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getGlass() {
		return glass;
	}

	public void setGlass(String glass) {
		this.glass = glass;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
