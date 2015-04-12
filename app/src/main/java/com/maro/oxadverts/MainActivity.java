package com.maro.oxadverts;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private AdvertAdapter advertAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        advertAdapter  = new AdvertAdapter(new ArrayList<Advert>(), this);
        ListView lView = (ListView) findViewById(R.id.adverts_list);

        lView.setAdapter(advertAdapter);
        new AsyncListViewLoader().execute();
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
            try {
                doc = Jsoup.connect("http://ogloszenia.ox.pl/34,wynajme.html").get();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Elements articles = doc.getElementsByClass("articles").select("div.article");


            for (Element article : articles) {

                String link = article.select("div.inner").select("h2").select("a[href^=http:]").attr("abs:href");
                String shortDescription = article.select("div.inner").select("h2").select("a[href^=http:]").text();
                String longDescription = article.select("div.inner").select("p.desc").text();
                String date = article.select("div.inner").select("p.date").text();
                String price = article.select("span.price").text();

                myAdverts.add(new Advert(link, shortDescription, longDescription, date, price));

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
