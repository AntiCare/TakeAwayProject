package nl.saxion.testnav.fragments.language;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import nl.saxion.testnav.R;

public class LanguageFragment extends Fragment {

    private LanguageViewModel languageViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        languageViewModel =
                ViewModelProviders.of(this).get(LanguageViewModel.class);
        View root = inflater.inflate(R.layout.language_fragment, container, false);


        return root;
    }
}