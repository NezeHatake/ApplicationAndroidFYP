package com.example.newapps;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import androidx.fragment.app.Fragment; // Make sure to import this
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.example.newapps.databinding.ActivityMainBinding;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;
import android.content.pm.ActivityInfo;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private DrawerLayout drawerLayout;
    private BottomAppBar bottomAppBar;
    private BottomNavigationView bottomNavigationView;

    // ActivityResultLauncher for requesting camera permission
    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    showCamera();
                } else {
                    Toast.makeText(this, "Camera permission denied", Toast.LENGTH_SHORT).show();
                }
            });

    // ActivityResultLauncher for QR code scanning
    private final ActivityResultLauncher<ScanOptions> qrCodeLauncher = registerForActivityResult(new ScanContract(), result -> {
        if (result.getContents() == null) {
            Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show();
        } else {
            handleQRCodeResult(result.getContents());
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        loadFragment(new HomeFragment());

        // Lock the orientation to portrait
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // Initialize views using binding
        drawerLayout = binding.drawerLayout;
        bottomAppBar = binding.bottomAppBar;
        bottomNavigationView = binding.bottomNavigationView;

        // Set up listeners
        setupBottomAppBarListener();
        setupBottomNavigationViewListener();

        // Initialize FloatingActionButton click listener
        binding.fab.setOnClickListener(v -> checkPermissionAndShowActivity());

        // Execute the AsyncTask to fetch data
        new FetchDataTask().execute();
    }

    private void setupBottomAppBarListener() {
        bottomAppBar.setOnMenuItemClickListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.home) {
                loadFragment(new HomeFragment());
            } else if (itemId == R.id.notifications) {
                loadFragment(new NotificationFragment());
            } else if (itemId == R.id.settings) {
                loadFragment(new SettingFragment());
            }
            return false;
        });
    }
    private void loadFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager(); // Use this for androidx
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }

    private void setupBottomNavigationViewListener() {
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.home) {
                loadFragment(new HomeFragment());
            } else if (itemId == R.id.notifications) {
                loadFragment(new NotificationFragment());
            } else if (itemId == R.id.settings) {
                loadFragment(new SettingFragment());
            }
            return false;
        });
    }

    private void checkPermissionAndShowActivity() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            showCamera();
        } else if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
            Toast.makeText(this, "Camera permission required", Toast.LENGTH_SHORT).show();
            requestPermissionLauncher.launch(Manifest.permission.CAMERA);
        } else {
            requestPermissionLauncher.launch(Manifest.permission.CAMERA);
        }
    }

    private void showCamera() {
        ScanOptions options = new ScanOptions();
        options.setDesiredBarcodeFormats(ScanOptions.QR_CODE);
        options.setPrompt("Scan a QR code");
        options.setCameraId(0);
        options.setBeepEnabled(false);
        options.setBarcodeImageEnabled(true);
        options.setOrientationLocked(false);

        qrCodeLauncher.launch(options);
    }

    private void handleQRCodeResult(String contents) {
        Toast.makeText(this, "QR Code: " + contents, Toast.LENGTH_SHORT).show();
    }

    private class FetchDataTask extends AsyncTask<Void, Void, JSONArray> {

        @Override
        protected JSONArray doInBackground(Void... voids) {
            HttpURLConnection urlConnection = null;
            JSONArray jsonArray = null;
            try {
                // URL for your API endpoint
                URL url = new URL("https://forms.fillout.com/t/w8mgfMnWf2us?ProjectAttendanceJKM=xxx"); // Replace with your API URL
                urlConnection = (HttpURLConnection) url.openConnection();

                // Set request method and headers
                urlConnection.setRequestMethod("GET");
                urlConnection.setRequestProperty("Authorization", "Bearer sk_prod_D994r6yKUo4GShEqrlvFYh7b9FRBpxadCwzNY2v6GgsXAP7ARH2mUGLYVWM6Fh3nqssqvsThh8n1MJ8Wx2A2s9xStkwxwoNTw2K_10258");

                // Read the response
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                StringBuilder result = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }

                jsonArray = new JSONArray(result.toString());
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }
            return jsonArray;
        }

        @Override
        protected void onPostExecute(JSONArray jsonArray) {
            super.onPostExecute(jsonArray);
            // Handle the result here, e.g., update UI or process data
            if (jsonArray != null) {
                // Process JSON data
            } else {
                Toast.makeText(MainActivity.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
