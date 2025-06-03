package com.mirea.iri.kt.dnschecker;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mirea.iri.kt.dnschecker.databinding.ActivityDomainSearchBinding;

public class DomainSearchActivity extends AppCompatActivity {

    private ActivityDomainSearchBinding binding;
    private AuthResponse authResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDomainSearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        authResponse = (AuthResponse) getIntent().getSerializableExtra("auth_response");

        binding.btnSearch.setOnClickListener(v -> {
            String query = binding.etSearch.getText().toString();
            if (query.isEmpty()) {
                Toast.makeText(this, R.string.enter_query, Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(DomainSearchActivity.this, DomainResultsActivity.class);
            intent.putExtra("query", query);
            startActivity(intent);
        });

        binding.fabResponse.setOnClickListener(v -> showAuthResponse());
    }

    private void showAuthResponse() {
        new AlertDialog.Builder(this)
                .setTitle("Ответ сервера")
                .setMessage(authResponse.toString())
                .setPositiveButton("OK", null)
                .show();
    }
}
