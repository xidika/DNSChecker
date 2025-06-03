package com.mirea.iri.kt.dnschecker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.List;

public class DomainAdapter extends ArrayAdapter<DomainsResponse.Domain> {

    public DomainAdapter(Context context, List<DomainsResponse.Domain> domains) {
        super(context, 0, domains);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View ignored, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(getContext())
                .inflate(R.layout.list_item_domain, parent, false);

        ImageView ivFavicon = view.findViewById(R.id.ivFavicon);
        TextView tvDomain = view.findViewById(R.id.tvDomain);

        DomainsResponse.Domain domain = getItem(position);

        if (domain != null) {
            tvDomain.setText(domain.getDomain());

            String faviconUrl = "https://www.google.com/s2/favicons?domain=" + domain.getDomain();
            Picasso.get()
                    .load(faviconUrl)
                    .placeholder(R.drawable.ic_loading_favicon)
                    .error(R.drawable.ic_default_favicon)
                    .into(ivFavicon);
        }

        return view;
    }
}


