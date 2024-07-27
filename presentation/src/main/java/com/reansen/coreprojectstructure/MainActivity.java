package com.reansen.coreprojectstructure;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.reansen.coreprojectstructure.databinding.ActivityMainBinding;
import com.reansen.coreprojectstructure.protocol.ProtocolTcpServer;
import com.reansen.coreprojectstructure.ui.purchase_sale.NavigatorSale;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;

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

        NavigatorSale navigatorSale = new NavigatorSale(this);
        binding.btnTesting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigatorSale.navigate();
            }
        });


        //Todo start socket server.

        new Thread(() -> {
            ProtocolTcpServer server = new ProtocolTcpServer();
            server.start(9000); // Start server on port 8080
        }).start();

    }

}