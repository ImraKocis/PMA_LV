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
import com.example.pma_lv1.managers.ApiManager;
import com.example.pma_lv1.models.Instructors;
import com.example.pma_lv1.models.ResponseAPI;
import com.example.pma_lv1.viewModels.SummaryVM;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class StudentInfoFragment extends Fragment implements Callback<ResponseAPI> {
    private TextInputEditText etPredmet;
    private TextInputEditText etProfesor;
    private TextInputEditText etAkGodina;
    private TextInputEditText etPredavanja;
    private TextInputEditText etLabosi;
    private SummaryVM viewModelSummary;


    public StudentInfoFragment() { }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ApiManager.getInstance().service().getCourses().enqueue(this); //asinkroni poziv
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewModel = inflater.inflate(R.layout.fragment_student_info, container, false);
        viewModelSummary = new ViewModelProvider(requireActivity()).get(SummaryVM.class);
        etPredmet = (TextInputEditText) viewModel.findViewById(R.id.etUnosPredmet);
        etProfesor=(TextInputEditText) viewModel.findViewById(R.id.etUnosProfessor);
        etAkGodina=(TextInputEditText) viewModel.findViewById(R.id.etUnosAkademskaGodina);
        etPredavanja=(TextInputEditText) viewModel.findViewById(R.id.etUnosSatiPredavanja);
        etLabosi=(TextInputEditText) viewModel.findViewById(R.id.etUnosSatiLv);

        etPredmet.addTextChangedListener(new TextChange(etPredmet));
        etProfesor.addTextChangedListener(new TextChange(etProfesor));
        etAkGodina.addTextChangedListener(new TextChange(etAkGodina));
        etPredavanja.addTextChangedListener(new TextChange(etPredavanja));
        etLabosi.addTextChangedListener(new TextChange(etLabosi));
        return viewModel;
    }

    @Override
    public void onResponse(Call<ResponseAPI> call, Response<ResponseAPI> response) {

    }

    @Override
    public void onFailure(Call<ResponseAPI> call, Throwable t) {

    }

    private void instructorNames(ArrayList<Instructors> instructors){
        etProfesor.setText(instructors.toString());
    }

    private class TextChange implements TextWatcher {
        View view;

        private TextChange (View v) {
            view = v;
        }

        @Override
        public void beforeTextChanged(CharSequence c, int start, int count, int after) { }

        @Override
        public void onTextChanged(CharSequence c, int start, int before, int count) {
            switch (view.getId()) {
                case R.id.etUnosPredmet:
                    viewModelSummary.setCourseProperty(c.toString(), 4);
                    break;
                case R.id.etUnosProfessor:
                    viewModelSummary.setCourseProperty(c.toString(), 5);
                    break;
                case R.id.etUnosAkademskaGodina:
                    viewModelSummary.setCourseProperty(c.toString(), 6);
                    break;
                case R.id.etUnosSatiPredavanja:
                    viewModelSummary.setCourseProperty(c.toString(), 7);
                    break;
                case R.id.etUnosSatiLv:
                    viewModelSummary.setCourseProperty(c.toString(),8);
                    break;
            }
        }

        @Override
        public void afterTextChanged(Editable editable) { }
    }
}