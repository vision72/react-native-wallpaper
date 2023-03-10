package com.wallpaper;

import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@ReactModule(name = WallpaperModule.NAME)
public class WallpaperModule extends ReactContextBaseJavaModule {
  public static final String NAME = "Wallpaper";

  public WallpaperModule(ReactApplicationContext reactContext) {
    super(reactContext);
  }

  @Override
  @NonNull
  public String getName() {
    return NAME;
  }

  @ReactMethod
  public void setWallpaper(String imageUrl, Promise promise) {
    new SetWallpaperTask(promise).execute(imageUrl);
  }

  // Example method
  // See https://reactnative.dev/docs/native-modules-android
  @ReactMethod
  public void multiply(double a, double b, Promise promise) {
    promise.resolve(a * b);
  }

  private class SetWallpaperTask extends AsyncTask<String, Void, Boolean> {
    private final Promise promise;

    public SetWallpaperTask(Promise promise) {
      this.promise = promise;
    }

    @Override
    protected Boolean doInBackground(String... params) {
      String imageUrl = params[0];

      try {
        URL url = new URL(imageUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoInput(true);
        connection.connect();
        InputStream input = connection.getInputStream();
        Bitmap bitmap = BitmapFactory.decodeStream(input);
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(getReactApplicationContext());
        wallpaperManager.setBitmap(bitmap);
        return true;
      } catch (IOException e) {
        e.printStackTrace();
        return false;
      }
    }

    @Override
    protected void onPostExecute(Boolean success) {
      if (success) {
        promise.resolve(null);
      } else {
        promise.reject("Error setting wallpaper");
      }
    }
  }
}
