package my.foodon.app;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import my.foodon.app.customerFoodPanel.CustomerCartFragment;
import my.foodon.app.customerFoodPanel.CustomerHomeFragment;
import my.foodon.app.customerFoodPanel.CustomerOrderFragment;
import my.foodon.app.customerFoodPanel.CustomerProfileFragment;
import my.foodon.app.customerFoodPanel.CustomerTrackFragment;

public class CustomerFoofPanel_BottomNavigation extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_foof_panel_bottom_navigation);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) BottomNavigationView navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setOnNavigationItemSelectedListener(this);
        String name = getIntent().getStringExtra("PAGE");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (name != null) {
            if (name.equalsIgnoreCase("Homepage")) {
                loadcheffragment(new CustomerHomeFragment());
            } else if (name.equalsIgnoreCase("Preparinpage")) {
                loadcheffragment(new CustomerTrackFragment());
            } else if (name.equalsIgnoreCase("DeliveryOrderpage")) {
                loadcheffragment(new CustomerTrackFragment());
            } else if (name.equalsIgnoreCase("Thankyoupage")) {
                loadcheffragment(new CustomerHomeFragment());
            }
        } else {
            loadcheffragment(new CustomerHomeFragment());
        }
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.cust_Home:
                fragment = new CustomerHomeFragment();
                break;
        }
        switch (item.getItemId()) {
            case R.id.cart:
                fragment = new CustomerCartFragment();
                break;
        }
        switch (item.getItemId()) {
            case R.id.cust_profile:
                fragment = new CustomerProfileFragment();
                break;
        }
        switch (item.getItemId()) {
            case R.id.Cust_order:
                fragment = new CustomerOrderFragment();
                break;
        }
        switch (item.getItemId()) {
            case R.id.cart:
                fragment = new CustomerCartFragment();
                break;
        }
        return loadcheffragment(fragment);

    }

    private boolean loadcheffragment(Fragment fragment) {

        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
            return true;
        }
        return false;
    }
}