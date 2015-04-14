package com.maro.oxadverts;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class MainActivity extends ActionBarActivity implements AdvertAdapter.customButtonListener {

    private AdvertAdapter advertAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        advertAdapter  = new AdvertAdapter(new ArrayList<Advert>(), this);
        advertAdapter.setCustomButtonListener(MainActivity.this);
        ListView lView = (ListView) findViewById(R.id.adverts_list);

        lView.setAdapter(advertAdapter);
        new AsyncListViewLoader().execute();
    }

    @Override
    public void onButtonClickListener(int position, Advert advert) {

        Intent intent = new Intent(MainActivity.this, AdvertDetailsActivity.class);
        intent.putExtra("dateAdded", advert.getAdvertDetails().getDateAdded());
        intent.putExtra("advertHeader", advert.getAdvertDetails().getAdvertHeader());
        intent.putExtra("price", advert.getAdvertDetails().getPrice());

        intent.putExtra("sellerName", advert.getAdvertDetails().getSellerName());
        intent.putExtra("sellerPhone", advert.getAdvertDetails().getSellerPhone());
        intent.putExtra("sellerCity", advert.getAdvertDetails().getSellerCity());
        intent.putExtra("sellerEmail", advert.getAdvertDetails().getSellerEmail());

        intent.putExtra("shortContent", advert.getAdvertDetails().getShortContent());
        intent.putExtra("fullContent", advert.getAdvertDetails().getFullContent());
        intent.putExtra("link", advert.getAdvertDetails().getLink());
        // Launch the Activity using the intent
        startActivity(intent);
     }

    private class AsyncListViewLoader extends AsyncTask<String, Void, List<Advert>> {
        private final ProgressDialog dialog = new ProgressDialog(MainActivity.this);

        @Override
        protected void onPostExecute(List<Advert> result) {
            super.onPostExecute(result);
            dialog.dismiss();
            advertAdapter.setAdverts(result);
            advertAdapter.notifyDataSetChanged();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setMessage("Szukam ogłoszeń...");
            dialog.show();
        }

        @Override
        protected List<Advert> doInBackground(String... params) {
            List myAdverts = new ArrayList<Advert>();

            Document doc = null;
            Document docDetails = null;

            try {
                doc = Jsoup.connect("http://ogloszenia.ox.pl/34,wynajme.html").get();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Elements articles = doc.getElementsByClass("articles").select("div.article");

            int count = 0;

            for (Element article : articles) {

                count++;

                String shortDescription = article.select("div.inner").select("h2").select("a[href^=http:]").text();
                String date = article.select("div.inner").select("p.date").text();
                String price = article.select("span.price").text();
                String link = article.select("div.inner").select("h2").select("a[href^=http:]").attr("abs:href");

                // fill details info
                try {
                    docDetails = Jsoup.connect(link).timeout(10*1000).get();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Elements details = docDetails.getElementsByClass("notice");

                String dateAdded = details.select("div.stats").text();
                String advertHeader = details.select("h1").text();
                String priceDetails = details.select("div.price").text();

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

                AdvertDetails advertDetails = new AdvertDetails(dateAdded, advertHeader, price, sellerName, sellerPhone, sellerCity, sellerEmail, shortContent, fullContent, link);

                if(count == 20) break;

                myAdverts.add(new Advert(shortDescription, date, price, advertDetails));

            }
            return myAdverts;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
