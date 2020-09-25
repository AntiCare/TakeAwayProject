package nl.saxion.testnav.fragments.home;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import java.lang.reflect.Array;
import java.util.ArrayList;

import nl.saxion.testnav.R;
import nl.saxion.testnav.RestaurantItemActivity;
import nl.saxion.testnav.models.Admin;
import nl.saxion.testnav.models.Restaurant;

public class HomeFragment extends Fragment {
    private HomeViewModel homeViewModel;
    private ListView listView;
    private SearchView mSearchView;
    private ArrayList<String> restaurantNam;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        return root;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //initial
        initial();
        //create restaurant
        createRestaurant();
        //Click events for each restaurant
        clickRestaurantEvent();
        //search Function
        searchFunction();
    }

    public void initial() {
        mSearchView = (SearchView)getActivity().findViewById(R.id.searchView);
        listView = (ListView) getActivity().findViewById(R.id.restaurantsListVw);
    }

    public void createRestaurant() {
        Restaurant r1 = new Restaurant("Burger king","street1","1111","shit","11");
        new Admin().addRestaurant(r1);
        Restaurant r2 = new Restaurant("Pizza king","street2","1111","shit","11");
        new Admin().addRestaurant(r2);
        Restaurant r3 = new Restaurant("BBQ king","street3","1111","shit","11");
        new Admin().addRestaurant(r3);
        Restaurant r4 = new Restaurant("Kapsalon king","street4","1111","shit","11");
        new Admin().addRestaurant(r4);
        Restaurant r5 = new Restaurant("Doner king","street5","1111","shit","11");
        new Admin().addRestaurant(r5);
        Restaurant r6 = new Restaurant("Fries king","street6","1111","shit","11");
        new Admin().addRestaurant(r6);
        Restaurant r7 = new Restaurant("Pasta king","street7","1111","shit","11");
        new Admin().addRestaurant(r7);
        Restaurant r8 = new Restaurant("Fish king","street8","1111","shit","11");
        new Admin().addRestaurant(r8);
        Restaurant r9 = new Restaurant("Chicken king","street9","1111","shit","11");
        new Admin().addRestaurant(r9);
        Restaurant r10 = new Restaurant("Seafood king","street10","1111","shit","11");
        new Admin().addRestaurant(r10);
        Restaurant r11 = new Restaurant("Beef king","street11","1111","shit","11");
        new Admin().addRestaurant(r11);
        Restaurant r12 = new Restaurant("Sushi king","street12","1111","shit","11");
        new Admin().addRestaurant(r12);
    }

    public void clickRestaurantEvent() {
        restaurantNam = new ArrayList<>();
        for (int i = 0; i <Admin.getRestaurants().size(); i++) {
            restaurantNam.add(Admin.getRestaurants().get(i).getName()) ;
        }

        listView.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, restaurantNam));
        listView.setTextFilterEnabled(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView name = (TextView)view;
                Intent intent = new Intent(getActivity(), RestaurantItemActivity.class);
                intent.putExtra("RN",name.getText());
                startActivity(intent);
            }
        });
    }

    public void searchFunction() {
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // This method is triggered when the search button is clicked
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            // This method is triggered when the search content changes
            @Override
            public boolean onQueryTextChange(String newText) {
                if (!TextUtils.isEmpty(newText)){
                    listView.setFilterText(newText);
                }else{
                    listView.clearTextFilter();
                }
                return false;
            }
        });
    }



}
