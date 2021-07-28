package com.example.bhawook54545434.noteit;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

import java.util.concurrent.Executor;

public class NotesSplash extends AppCompatActivity {

    LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_splash);

        getSupportActionBar().hide();

        lottieAnimationView = findViewById(R.id.lot_notes);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                        .setTitle("Hey User").setDescription("Please verify yourself!")
                        .setNegativeButtonText("Cancel").build();
                getPrompt().authenticate(promptInfo);
            }
        }, 4000);

    }

    private androidx.biometric.BiometricPrompt getPrompt() {
        Executor executor = ContextCompat.getMainExecutor(this);
        androidx.biometric.BiometricPrompt.AuthenticationCallback callback = new androidx.biometric.BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                NotifyUser(errString.toString());
                finish();
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull androidx.biometric.BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                NotifyUser("Authentication failed!!");
            }
        };

        androidx.biometric.BiometricPrompt biometricPrompt = new BiometricPrompt(this, executor, callback);
        return biometricPrompt;

    }

    private void NotifyUser(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}