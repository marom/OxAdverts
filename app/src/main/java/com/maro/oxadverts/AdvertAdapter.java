package com.maro.oxadverts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.jsoup.helper.StringUtil;

import java.util.List;

/**
 * Created by maro on 21/03/2015.
 */
public class AdvertAdapter extends ArrayAdapter<Advert> {

    private List<Advert> adverts;
    private Context context;

    public AdvertAdapter(List<Advert> adverts, Context ctx) {
        //super(ctx, android.R.layout.simple_list_item_1, adverts);
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
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.advert_item_list, null);
        }

        Advert advert = adverts.get(position);

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


        TextView longDescriptionView = (TextView) v.findViewById(R.id.longDescription);
        longDescriptionView.setText(advert.getLongDescription());

        TextView linkView = (TextView) v.findViewById(R.id.link);
        linkView.setText(advert.getLink());


        return v;
    }

    public List<Advert> getAdverts() {
        return adverts;
    }

    public void setAdverts(List<Advert> adverts) {
        this.adverts = adverts;
    }

}
