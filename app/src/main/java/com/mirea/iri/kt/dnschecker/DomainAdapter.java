package com.mirea.iri.kt.dnschecker;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mirea.iri.kt.dnschecker.databinding.ListItemDomainBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DomainAdapter extends ArrayAdapter<DomainsResponse.Domain> {

    public DomainAdapter(Context context, List<DomainsResponse.Domain> domains) {
        super(context, 0, domains);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View ignored, @NonNull ViewGroup parent) {
        ListItemDomainBinding binding;
        binding = ListItemDomainBinding.inflate(LayoutInflater.from(getContext()), parent, false);
        DomainsResponse.Domain domain = getItem(position);

        if (domain != null) {
            binding.tvDomain.setText(domain.getDomain());

            String faviconUrl = "https://www.google.com/s2/favicons?domain=" + domain.getDomain();
            Picasso.get()
                    .load(faviconUrl)
                    .placeholder(R.drawable.ic_loading_favicon)
                    .error(R.drawable.ic_default_favicon)
                    .into(binding.ivFavicon);


            binding.getRoot().setOnClickListener(v -> {
                String url = "http://" + domain.getDomain();
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                getContext().startActivity(browserIntent);
            });

            binding.btnShareDomain.setOnClickListener(v -> {
                Intent sendIntent = new Intent(Intent.ACTION_SEND);
                sendIntent.setType("text/plain");
                sendIntent.putExtra(Intent.EXTRA_TEXT, domain.getDomain());
                getContext().startActivity(Intent.createChooser(sendIntent, getContext().getString(R.string.share_domain)));
            });
        }

        return binding.getRoot();
    }
}


