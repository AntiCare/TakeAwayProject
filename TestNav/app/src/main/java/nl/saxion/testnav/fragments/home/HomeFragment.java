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

import nl.saxion.testnav.R;
import nl.saxion.testnav.RestaurantItemActivity;

public class HomeFragment extends Fragment {
    private HomeViewModel homeViewModel;
    private ListView listView;
    private SearchView mSearchView;
private String[] restaurantName = { "Burger king", "Pizza king", "Sushi king", "BBQ king", "Kapsalon king","Doner king","Fries king","Pasta king","Fish king","Chicken king"
        ,"Seafood king","Beef king"};
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.home_fragment, container, false);
        return root;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mSearchView = (SearchView)getActivity().findViewById(R.id.searchView);
        listView = (ListView) getActivity().findViewById(R.id.restaurantsListVw);
        listView.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, restaurantName));
        listView.setTextFilterEnabled(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView name = (TextView)view;
//                Toast.makeText(getActivity(), name.getText()+"", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity(), RestaurantItemActivity.class);
                intent.putExtra("RN",name.getText());
                startActivity(intent);
            }
        });

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