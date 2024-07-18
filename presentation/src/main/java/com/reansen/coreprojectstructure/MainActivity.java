package com.reansen.coreprojectstructure;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.reansen.coreprojectstructure.databinding.ActivityMainBinding;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);


        try {
            byte[] mdk = UDKGenerator.hexStringToByteArray("0123456789ABCDEF0123456789ABCDEF");
            byte[] pan = UDKGenerator.hexStringToByteArray("43219876543210987");
            byte panSequenceNumber = 0x00;
            String derivationOption = "Option A";
            String keyParity = "right odd";

            byte[] udk = UDKGenerator.deriveUDK(mdk, pan, panSequenceNumber, derivationOption, keyParity);
            String hexUdk = UDKGenerator.bytesToHexString(udk);
            String hexKcv = UDKGenerator.bytesToHexString(Arrays.copyOfRange(udk, 0, 3)); // KCV is typically the first 3 bytes

            System.out.println("UDK: " + hexUdk);
            System.out.println("KCV: " + hexKcv);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}