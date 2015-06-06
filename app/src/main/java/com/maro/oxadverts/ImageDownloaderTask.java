package com.maro.oxadverts;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import org.jsoup.helper.StringUtil;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.URL;

/**
 * Created by maro on 15/04/2015.
 */
public class ImageDownloaderTask extends AsyncTask<String, Void, Bitmap> {

        private final WeakReference<ImageView> imageViewReference;

        public ImageDownloaderTask(ImageView imageView) {
            imageViewReference = new WeakReference<>(imageView);
        }

        @Override
        // Actual download method, run in the task thread
        protected Bitmap doInBackground(String... params) {
            // params comes from the execute() call: params[0] is the url.
            if (!StringUtil.isBlank(params[0])) {
                return downloadImage(params[0]);
            } else return null;
        }

        @Override
        // Once the image is downloaded, associates it to the imageView
        protected void onPostExecute(Bitmap bitmap) {
            if (isCancelled()) {
                bitmap = null;
            }

            ImageView imageView = imageViewReference.get();
            if (imageView != null) {
                imageView.setImageBitmap(bitmap);
            }


        }

        static Bitmap downloadImage(String url) {
            URL imageURL;
            try {
                imageURL = new URL(url);
                return BitmapFactory.decodeStream(imageURL.openStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
}
