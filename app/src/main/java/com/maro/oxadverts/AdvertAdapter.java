package com.maro.oxadverts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.jsoup.helper.StringUtil;

import java.util.List;

/**
 * Created by maro on 21/03/2015.
 */
public class AdvertAdapter extends ArrayAdapter<Advert> {

    customButtonListener customListener;

    public interface  customButtonListener {
        public void onButtonClickListener(Advert advert);
    }

    public void setCustomButtonListener(customButtonListener listener) {
        this.customListener = listener;
    }

    private List<Advert> adverts;
    private Context context;

    static class ViewHolder {
        public TextView shortDescription;
        public TextView date;
        public TextView price;
        public Button bLaunchAdvertDetails;
        public ImageView imageView;
    }

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

        View rowView = convertView;

        if (rowView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.advert_item_list, null);

            // configure view holder
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.shortDescription = (TextView) rowView.findViewById(R.id.shortDescription);
            viewHolder.date = (TextView) rowView.findViewById(R.id.date);
            viewHolder.price = (TextView) rowView.findViewById(R.id.price);
            viewHolder.bLaunchAdvertDetails = (Button) rowView.findViewById(R.id.bLaunchAdvertDetails);
            viewHolder.imageView = (ImageView) rowView.findViewById(R.id.imageView);
            rowView.setTag(viewHolder);
        }

        // fill data
        ViewHolder holder = (ViewHolder) rowView.getTag();
        final Advert advert = adverts.get(position);

        holder.shortDescription.setText(advert.getShortDescription());
        holder.date.setText(advert.getDate());

        if (StringUtil.isBlank(advert.getPrice())) {
            holder.price.setVisibility(View.GONE);
        } else {
            holder.price.setText(advert.getPrice());
        }

        holder.bLaunchAdvertDetails.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (customListener != null) {
                    customListener.onButtonClickListener(getItem(position));
                    customListener.onButtonClickListener(getItem(position));
                }
            }
        });

        if (holder.imageView != null) {
            new ImageDownloaderTask(holder.imageView).execute(advert.getImageUrl());
        }
        return rowView;
    }

    public List<Advert> getAdverts() {
        return adverts;
    }

    public void setAdverts(List<Advert> adverts) {
        this.adverts = adverts;
    }


}
