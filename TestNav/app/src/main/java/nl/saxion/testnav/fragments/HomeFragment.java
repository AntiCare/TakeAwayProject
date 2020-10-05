package nl.saxion.testnav.fragments;

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

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import nl.saxion.testnav.R;
import nl.saxion.testnav.RestaurantDetailsActivity;
import nl.saxion.testnav.models.Admin;
import nl.saxion.testnav.models.Restaurant;

public class HomeFragment extends Fragment {
    private ListView listView;
    private SearchView mSearchView;
    private ArrayList<String> restaurantNam;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    DatabaseReference myRef = database.child("Restaurant");


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        return root;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //initial
        initial();
        //read the restaurants data from the firebase & add them into the list & click event.
        readRestaurants();
        //search Function
        searchFunction();
    }

    public void initial() {
        mSearchView = (SearchView)getActivity().findViewById(R.id.searchView);
        listView = (ListView) getActivity().findViewById(R.id.restaurantsListVw);
    }

    public void readRestaurants() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot keyNode : dataSnapshot.getChildren()) {
                    Restaurant restaurant = keyNode.getValue(Restaurant.class);
                    Admin.addRestaurant(restaurant);
                    System.out.println(restaurant);
                }
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
                        Intent intent = new Intent(getActivity(), RestaurantDetailsActivity.class);
                        intent.putExtra("RN",name.getText());
                        for (int i = 0; i < Admin.getRestaurants().size(); i++) {
//                            System.out.println(Admin.getRestaurants().get(i).getName());
                            if (name.getText().equals(Admin.getRestaurants().get(i).getName())) {
                                intent.putExtra("URL",Admin.getRestaurants().get(i).getImageURL());
                            }
                        }
                        startActivity(intent);
                    }
                });
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
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
