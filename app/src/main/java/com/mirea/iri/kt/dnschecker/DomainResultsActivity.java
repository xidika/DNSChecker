package com.mirea.iri.kt.dnschecker;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.mirea.iri.kt.dnschecker.databinding.ActivityDomainResultsBinding;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DomainResultsActivity extends AppCompatActivity {

    private ActivityDomainResultsBinding binding;
    private final ArrayList<DomainsResponse.Domain> domainItems = new ArrayList<>();
    private DomainAdapter adapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDomainResultsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        progressBar = binding.progressBar;

        String query = getIntent().getStringExtra("query");

        adapter = new DomainAdapter(this, domainItems);
        binding.lvDomains.setAdapter(adapter);
        progressBar.setVisibility(View.VISIBLE);
        binding.lvDomains.setVisibility(View.GONE);

        searchDomains(query);

        binding.btnShare.setOnClickListener(v -> shareDomains());
    }

    private void searchDomains(String query) {
        Domains service = DNSApp.getDomainsService();
        Call<DomainsResponse> call = service.searchDomains(query);

        call.enqueue(new Callback<DomainsResponse>() {
            @Override
            public void onResponse(Call<DomainsResponse> call, Response<DomainsResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<DomainsResponse.Domain> domains = response.body().getDomains();
                    progressBar.setVisibility(View.GONE);
                    binding.lvDomains.setVisibility(View.VISIBLE);
                    if (domains != null && !domains.isEmpty()) {
                        domainItems.clear();
                        domainItems.addAll(domains);
                        adapter.notifyDataSetChanged();
                        binding.btnShare.setVisibility(View.VISIBLE);
                    } else {
                        Toast.makeText(DomainResultsActivity.this,
                                R.string.no_domains_found, Toast.LENGTH_LONG).show();
                        finish();
                    }
                } else {
                    Toast.makeText(DomainResultsActivity.this,
                            R.string.search_failed, Toast.LENGTH_LONG).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<DomainsResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                binding.lvDomains.setVisibility(View.VISIBLE);
                Toast.makeText(DomainResultsActivity.this,
                        R.string.network_error, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void shareDomains() {
        String domainsText = "";
        for (DomainsResponse.Domain domain : domainItems) {
            domainsText = domainsText + domain.getDomain() + "\n";
        }
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, domainsText);
        intent.setType("text/plain");
        startActivity(Intent.createChooser(intent, getString(R.string.share_title)));
    }
}