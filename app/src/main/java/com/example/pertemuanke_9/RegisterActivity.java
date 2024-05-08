package com.example.pertemuanke_9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.pertemuanke_9.data.ApiClient;
import com.example.pertemuanke_9.data.ApiInterface;
import com.example.pertemuanke_9.databinding.ActivityRegisterBinding;
import com.example.pertemuanke_9.model.Register;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.tvLog.setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        });

        binding.btnRegister.setOnClickListener(v -> {
            String Username = binding.etRegisterUsername.getText().toString();
            String Name = binding.etRegisterName.getText().toString();
            String Password = binding.etRegisterPassword.getText().toString();

            register(Username,Name, Password);

        });
    }

    private void register(String username, String name, String password) {

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Register> call = apiInterface.RegisterResponse(username,password,name);
        call.enqueue(new Callback<Register>() {
            @Override
            public void onResponse(Call<Register> call, Response<Register> response) {
                if(response.isSuccessful() && response.body() != null)
                {
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    Toast.makeText(RegisterActivity.this, "Akun berhasil dibuat", Toast.LENGTH_SHORT).show();
                    startActivity(intent);

                } else {
                    Toast.makeText(RegisterActivity.this, response.body().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Register> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Gagal buat akun", Toast.LENGTH_SHORT).show();
            }
        });

    }
}