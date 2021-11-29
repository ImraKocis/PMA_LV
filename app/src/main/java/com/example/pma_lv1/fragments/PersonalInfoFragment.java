package com.example.pma_lv1.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;


import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pma_lv1.R;
import com.example.pma_lv1.models.Student;
import com.example.pma_lv1.viewModels.SummaryVM;
import com.google.android.material.textfield.TextInputEditText;


public class PersonalInfoFragment extends Fragment {
    private TextInputEditText etUnosIme;
    private TextInputEditText etUnosPrezime;
    private TextInputEditText etUnosDatumRodenja;
    private SummaryVM viewModelSummary;

    public PersonalInfoFragment() { }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewModel = inflater.inflate(R.layout.fragment_personal_info, container, false);
        viewModelSummary = new ViewModelProvider(requireActivity()).get(SummaryVM.class);
        etUnosIme = (TextInputEditText) viewModel.findViewById(R.id.unosIme);
        etUnosPrezime=(TextInputEditText) viewModel.findViewById(R.id.unosPrezime);
        etUnosDatumRodenja=(TextInputEditText) viewModel.findViewById(R.id.unosDatumRodenja);

        etUnosIme.addTextChangedListener(new TextChange(etUnosIme));
        etUnosPrezime.addTextChangedListener(new TextChange(etUnosPrezime));
        etUnosDatumRodenja.addTextChangedListener(new TextChange(etUnosDatumRodenja));
        return viewModel;
    }

    private class TextChange implements TextWatcher {
        View view;

        private TextChange (View v) {
            view = v;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            switch (view.getId()) {
                case R.id.unosIme:
                    viewModelSummary.setStudentProperty(s.toString(), 1);
                    break;
                case R.id.unosPrezime:
                    viewModelSummary.setStudentProperty(s.toString(), 2);
                    break;
                case R.id.unosDatumRodenja:
                    viewModelSummary.setStudentProperty(s.toString(), 3);
                    break;
            }
        }

        @Override
        public void afterTextChanged(Editable editable) { }
    }
}