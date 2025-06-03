package com.mirea.iri.kt.dnschecker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.mirea.iri.kt.dnschecker.databinding.ActivityMainBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private static final String GROUP = "RIBO-02-23";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnLogin.setOnClickListener(v -> authenticateUser());
    }

    private void authenticateUser() {
        String login = binding.etLogin.getText().toString();
        String password = binding.etPassword.getText().toString();

        if (login.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, R.string.fill_all_fields, Toast.LENGTH_SHORT).show();
            return;
        }
        Auth service = DNSApp.getAuthService();
        Call<AuthResponse> call = service.authenticate(login, password, GROUP);
        call.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    AuthResponse authResponse = response.body();
                    if (authResponse.getResultCode() == 1) {
                        Intent intent = new Intent(MainActivity.this, DomainSearchActivity.class);
                        intent.putExtra("auth_response", authResponse);
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this,
                                R.string.auth_failed, Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this,
                            R.string.auth_error, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this,
                        R.string.network_error, Toast.LENGTH_LONG).show();
            }
        });
    }
}