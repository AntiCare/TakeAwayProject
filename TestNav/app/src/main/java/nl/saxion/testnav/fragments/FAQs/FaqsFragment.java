package nl.saxion.testnav.fragments.FAQs;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import nl.saxion.testnav.R;

public class FaqsFragment extends Fragment {

    private FaqsViewModel faqsViewModel;

    public static FaqsFragment newInstance() {
        return new FaqsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_faqs, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        faqsViewModel = ViewModelProviders.of(this).get(FaqsViewModel.class);
        // TODO: Use the ViewModel
    }

}