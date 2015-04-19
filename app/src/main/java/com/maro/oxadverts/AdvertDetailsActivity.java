package com.maro.oxadverts;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Iterator;

/**
 * Created by maro on 12/04/2015.
 */
public class AdvertDetailsActivity extends Activity {

Advert result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.advert_details);
        Intent intent = getIntent();
        final Advert advert = (Advert) intent.getExtras().getSerializable("Advert");

        new DetailsLoaderTask().execute(advert);

    }

    private class DetailsLoaderTask extends AsyncTask<Advert, Void, Advert> {

        ProgressDialog progressDialog;

        @Override
        protected void onPostExecute(Advert result) {
            progressDialog.cancel();

            TextView dateAdded = (TextView) findViewById(R.id.dateAdded);
            TextView advertHeader = (TextView) findViewById(R.id.advertHeader);
            TextView price = (TextView) findViewById(R.id.price);
            TextView sellerName = (TextView) findViewById(R.id.sellerName);
            TextView sellerPhone = (TextView) findViewById(R.id.sellerPhone);
            TextView sellerCity = (TextView) findViewById(R.id.sellerCity);
            TextView sellerEmail = (TextView) findViewById(R.id.sellerEmail);
            TextView shortContent = (TextView) findViewById(R.id.shortContent);
            TextView fullContent = (TextView) findViewById(R.id.fullContent);
            TextView link = (TextView) findViewById(R.id.link);
            ImageView imageView1 = (ImageView) findViewById(R.id.imageView1);
            ImageView imageView2 = (ImageView) findViewById(R.id.imageView2);
            ImageView imageView3 = (ImageView) findViewById(R.id.imageView3);


            // getting attached intent data
            dateAdded.setText(result.getAdvertDetails().getDateAdded());
            advertHeader.setText(result.getAdvertDetails().getAdvertHeader());
            price.setText(result.getAdvertDetails().getPrice());
            sellerName.setText(result.getAdvertDetails().getSellerName());
            sellerPhone.setText(result.getAdvertDetails().getSellerPhone());
            sellerCity.setText(result.getAdvertDetails().getSellerCity());
            sellerEmail.setText(result.getAdvertDetails().getSellerEmail());
            shortContent.setText(result.getAdvertDetails().getShortContent());
            fullContent.setText(result.getAdvertDetails().getFullContent());
            link.setText(result.getAdvertDetails().getLink());

            if (imageView1 != null) {
                new ImageDownloaderTask(imageView1).execute(result.getAdvertDetails().getImageUrl1());
            }
            if (imageView2 != null) {
                new ImageDownloaderTask(imageView2).execute(result.getAdvertDetails().getImageUrl2());
            }
            if (imageView3 != null) {
                new ImageDownloaderTask(imageView3).execute(result.getAdvertDetails().getImageUrl3());
            }
        }

        @Override
        protected void onPreExecute() {
            //super.onPreExecute();
            progressDialog = new ProgressDialog(
                    AdvertDetailsActivity.this);
            progressDialog.setMessage("Downloading...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected Advert doInBackground(Advert... params) {

            Document docDetails = null;
            Advert advert = params[0];
                // fill details info
                try {
                    docDetails = Jsoup.connect(advert.getAdvertDetails().getLink()).timeout(10*1000).get();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Elements details = docDetails.getElementsByClass("notice");

                String dateAdded = details.select("div.stats").text();
                String advertHeader = details.select("h1").text();
                //String priceDetails = details.select("div.price").text();

                Element table = details.select("table").first();

                Iterator<Element> ite = table.select("td").iterator();
                ite.next();
                String sellerName = ite.next().text();
                ite.next();
                String sellerPhone = ite.next().text();
                ite.next();
                String sellerCity = ite.next().text();
                ite.next();
                String sellerEmail = ite.next().text();
                String shortContent = details.select("div.shortContent").text();
                String fullContent = details.select("div.fullContent").text();

                Element gallery = details.select("div.gallery").first();
                String picUrl1 = null;
                String picUrl2 = null;
                String picUrl3 = null;

                if (gallery != null) {
                    Iterator<Element> galleryIte = gallery.select("a.thumb ").iterator();
                    if (galleryIte.hasNext()) {
                        picUrl1 = galleryIte.next().absUrl("href");
                    }
                    if (galleryIte.hasNext()) {
                        picUrl2 = galleryIte.next().absUrl("href");
                    }
                    if (galleryIte.hasNext()) {
                        picUrl3 = galleryIte.next().absUrl("href");
                    }
                }

                AdvertDetails advertDetails = advert.getAdvertDetails();
                    advertDetails.setDateAdded(dateAdded);
                    advertDetails.setAdvertHeader(advertHeader);
                    advertDetails.setSellerName(sellerName);
                    advertDetails.setSellerPhone(sellerPhone);
                    advertDetails.setSellerCity(sellerCity);
                    advertDetails.setSellerEmail(sellerEmail);
                    advertDetails.setShortContent(shortContent);
                    advertDetails.setFullContent(fullContent);
                    advertDetails.setImageUrl1(picUrl1);
                    advertDetails.setImageUrl2(picUrl2);
                    advertDetails.setImageUrl3(picUrl3);

          return advert;
        }
    }

}
