package nl.saxion.testnav.fragments.language;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import nl.saxion.testnav.R;

public class LanguageFragment extends Fragment {

    private LanguageViewModel languageViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        languageViewModel =
                ViewModelProviders.of(this).get(LanguageViewModel.class);
        View root = inflater.inflate(R.layout.fragment_language, container, false);


        return root;
    }
}