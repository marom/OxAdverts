package com.maro.oxadverts;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import org.jsoup.helper.StringUtil;

import java.util.List;

/**
 * Created by maro on 21/03/2015.
 */
public class AdvertAdapter extends ArrayAdapter<Advert> {

    customButtonListener customListener;

    public interface  customButtonListener {
        public void onButtonClickListener(int position, Advert advert);
    }

    public void setCustomButtonListener(customButtonListener listener) {
        this.customListener = listener;
    }

    private List<Advert> adverts;
    private Context context;


    public AdvertAdapter(List<Advert> adverts, Context ctx) {
        super(ctx, R.layout.advert_item_list, adverts);
        this.adverts = adverts;
        this.context = ctx;
    }

    public int getCount() {
        if (adverts != null)
            return adverts.size();
        return 0;
    }

    public Advert getItem(int position) {
        if (adverts != null)
            return adverts.get(position);
        return null;
    }

    public long getItemId(int position) {
        if (adverts != null)
            return adverts.get(position).hashCode();
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View v = convertView;
        final String link;

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.advert_item_list, null);
        }

        final Advert advert = adverts.get(position);

        TextView shortDescriptionView = (TextView) v.findViewById(R.id.shortDescription);
        shortDescriptionView.setText(advert.getShortDescription());

        TextView dateView = (TextView) v.findViewById(R.id.date);
        dateView.setText(advert.getDate());

        TextView priceView = (TextView) v.findViewById(R.id.price);

        if (StringUtil.isBlank(advert.getPrice())) {
            priceView.setVisibility(View.GONE);
        } else {
            priceView.setText(advert.getPrice());
        }

        Button launchAdvertDetailsActivityButton = (Button) v.findViewById(R.id.bLaunchAdvertDetils);
        launchAdvertDetailsActivityButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (customListener != null) {
                    customListener.onButtonClickListener(position, getItem(position) );
                }
            }
        });

        return v;
    }

    public List<Advert> getAdverts() {
        return adverts;
    }

    public void setAdverts(List<Advert> adverts) {
        this.adverts = adverts;
    }

}
