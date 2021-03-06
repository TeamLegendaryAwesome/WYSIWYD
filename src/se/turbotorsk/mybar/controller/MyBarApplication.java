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

package se.turbotorsk.mybar.controller;

import android.app.Application;
import android.content.ContentResolver;
import android.content.Context;

/**
 * Class used so we can share objects between classes without passing them
 * between methods. The purpose of ContentResolver() is so we can use
 * MyBarContentProvider without passing the ContentResolver to every method
 * where it's needed. We can also use this class to declare Global variables
 * etc.
 * 
 * @author Dag Friden, Mathias Karlgren
 */
public class MyBarApplication extends Application {
	private static MyBarApplication mApplication;

	public MyBarApplication() {
		mApplication = this;
	}

	public static Context context() {
		return mApplication;
	}

	public static ContentResolver contentResolver() {
		return mApplication.getContentResolver();
	}
}