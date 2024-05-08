package com.example.pertemuanke_9;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.pertemuanke_9.data.ApiClient;
import com.example.pertemuanke_9.data.ApiInterface;
import com.example.pertemuanke_9.databinding.ActivityMainBinding;
import com.example.pertemuanke_9.model.Login;

import retrofit2.Call;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sessionManager = new SessionManager(MainActivity.this);
        if(!sessionManager.setIsLoggedIn())
        {
            moveToLogin();
        }

        String username = sessionManager.getUserDetail().get(SessionManager.USERNAME);
        String nama = sessionManager.getUserDetail().get(SessionManager.NAME);

        binding.etMainName.setText(nama);
        binding.etMainUsername.setText(username);





    }

    private void moveToLogin() {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.action_logout:
                sessionManager.logoutSession();
                moveToLogin();
        }

        return super.onOptionsItemSelected(item);
    }
}