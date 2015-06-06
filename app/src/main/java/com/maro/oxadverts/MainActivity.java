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
    private String linkParam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        advertAdapter  = new AdvertAdapter(new ArrayList<Advert>(), this);
        advertAdapter.setCustomButtonListener(MainActivity.this);
        ListView lView = (ListView) findViewById(R.id.adverts_list);

        lView.setAdapter(advertAdapter);

        Intent intent = getIntent();
        linkParam = intent.getStringExtra("link");
        if (linkParam == null) {
            linkParam = "http://ogloszenia.ox.pl/34,wynajme.html";
        }

        new AsyncListViewLoader().execute(linkParam);
    }

    @Override
    public void onButtonClickListener(Advert advert) {

        Intent intent = new Intent(MainActivity.this, AdvertDetailsActivity.class);
        intent.putExtra("Advert", advert);
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
            linkParam = params[0];

            try {
                doc = Jsoup.connect(linkParam).timeout(10*1000).get();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Elements articles = doc.getElementsByClass("page")
                    .select("div.content")
                    .select("div.middle_bar")
                    .select("div.fix")
                    .select("div.articles")
                    .select("div.article");

           for (Element article : articles) {
                String shortDescription = article.select("div.inner").select("h2").select("a[href^=http:]").text();
                String date = article.select("div.inner").select("p.date").text();
                String price = article.select("span.price").text();
                String link = article.select("div.inner").select("h2").select("a[href^=http:]").attr("abs:href");

                Element image = article.select("img").first();
                String imageUrl = image.absUrl("src");

                AdvertDetails advertDetails = new AdvertDetails();
                advertDetails.setLink(link);
                advertDetails.setPrice(price);

                myAdverts.add(new Advert(shortDescription, date, price, advertDetails, imageUrl));
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
        
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);

        switch (item.getItemId())
        {
            case R.id.sale_all:
                intent.putExtra("link", "http://ogloszenia.ox.pl/1,sprzedam.html");
                startActivity(intent);
                return true;

            case R.id.sale_properties:
                intent.putExtra("link", "http://ogloszenia.ox.pl/2,nieruchomosci.html");
                startActivity(intent);
                return true;

            case R.id.sale_properties_houses_flats:
                intent.putExtra("link", "http://ogloszenia.ox.pl/3,domy-i-mieszkania.html");
                startActivity(intent);
                return true;

            case R.id.sale_properties_plots:
                intent.putExtra("link", "http://ogloszenia.ox.pl/4,dzialki.html");
                startActivity(intent);
                return true;

            case R.id.sale_properties_other:
                intent.putExtra("link", "http://ogloszenia.ox.pl/5,inne.html");
                startActivity(intent);
                return true;

            case R.id.sale_properties_rent:
                intent.putExtra("link", "http://ogloszenia.ox.pl/34,wynajme.html");
                startActivity(intent);
                return true;

            case R.id.sale_autos_all:
                intent.putExtra("link", "http://ogloszenia.ox.pl/6,motoryzacja.html");
                startActivity(intent);
                return true;

            case R.id.sale_autos:
                intent.putExtra("link", "http://ogloszenia.ox.pl/7,samochody.html");
                startActivity(intent);
                return true;

            case R.id.sale_autos_other:
                intent.putExtra("link", "http://ogloszenia.ox.pl/8,inne.html");
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
