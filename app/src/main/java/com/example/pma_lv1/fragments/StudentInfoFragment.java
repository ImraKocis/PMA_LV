package com.example.pma_lv1.fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.pma_lv1.R;
import com.example.pma_lv1.managers.ApiManager;
import com.example.pma_lv1.models.CourseAPI;
import com.example.pma_lv1.models.Instructors;
import com.example.pma_lv1.models.ResponseAPI;
import com.example.pma_lv1.viewModels.SummaryVM;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class StudentInfoFragment extends Fragment implements Callback<ResponseAPI>, AdapterView.OnItemSelectedListener {
    private Spinner spinnerPredemt;
    private Spinner spinnerProfesor;
    private TextInputEditText etAkGodina;
    private TextInputEditText etPredavanja;
    private TextInputEditText etLabosi;
    private SummaryVM viewModelSummary;
    private List<String> naziviPredmeta = new ArrayList<String>();
    private List<String> predavaci = new ArrayList<String>();

    ResponseAPI responseAPI;

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

        spinnerPredemt = viewModel.findViewById(R.id.spinnerPredmet);
        spinnerProfesor= viewModel.findViewById(R.id.spinnerProfesor);
        etAkGodina=(TextInputEditText) viewModel.findViewById(R.id.etUnosAkademskaGodina);
        etPredavanja=(TextInputEditText) viewModel.findViewById(R.id.etUnosSatiPredavanja);
        etLabosi=(TextInputEditText) viewModel.findViewById(R.id.etUnosSatiLv);

        spinnerPredemt.setOnItemSelectedListener(this);
        spinnerProfesor.setOnItemSelectedListener(this);
        naziviPredmeta.add(0, "Predmet");
        predavaci.add(0,"Profesor");
        ArrayAdapter<String> dataAdapterPredavaci = new ArrayAdapter<String>(getActivity(), R.layout.support_simple_spinner_dropdown_item, predavaci){
            @Override
            public boolean isEnabled(int position){
                if (position == 0) return false;

                return true;
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent){
                View mainView = super.getDropDownView(position, convertView, parent);
                TextView mainTextView = (TextView) mainView;
                if (position == 0) mainTextView.setTextColor(Color.GRAY);
                else mainTextView.setTextColor(Color.BLACK);

                return mainView;
            }
        };

        ArrayAdapter<String> dataAdapterPredmeti = new ArrayAdapter<String>(getActivity(), R.layout.support_simple_spinner_dropdown_item, naziviPredmeta){
            @Override
            public boolean isEnabled(int position){
                if (position == 0) return false;
                return true;
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent){
                View mainView = super.getDropDownView(position, convertView, parent);
                TextView mainTextView = (TextView) mainView;
                if (position == 0) mainTextView.setTextColor(Color.GRAY);
                else mainTextView.setTextColor(Color.BLACK);

                return mainView;
            }
        };

        dataAdapterPredmeti.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapterPredavaci.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPredemt.setAdapter(dataAdapterPredmeti);
        spinnerProfesor.setAdapter(dataAdapterPredavaci);
        spinnerPredemt.setSelection(0);
        spinnerProfesor.setSelection(0);
        etAkGodina.addTextChangedListener(new TextChange(etAkGodina));
        etPredavanja.addTextChangedListener(new TextChange(etPredavanja));
        etLabosi.addTextChangedListener(new TextChange(etLabosi));
        return viewModel;
    }

    @Override
    public void onResponse(Call<ResponseAPI> call, Response<ResponseAPI> response) {
        if (response.isSuccessful() && response.body() != null){
            responseAPI = (ResponseAPI) response.body();
            for (CourseAPI courseAPI : responseAPI.courses){
                if (courseAPI.title != null && !courseAPI.title.trim().isEmpty()){
                    naziviPredmeta.add(courseAPI.title);
                }
                if (courseAPI.instructors != null){
                    for (Instructors instructors : courseAPI.instructors){
                        if (courseAPI.title != null && !courseAPI.title.trim().isEmpty()){
                            predavaci.add(instructors.name);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onFailure(Call<ResponseAPI> call, Throwable t) {
        t.printStackTrace();
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.spinnerProfesor:
                if (position != 0){
                    spinnerProfesor.setSelection(position,true);
                    viewModelSummary.setCourseProperty(predavaci.get(position),5);
                }
                break;

            case R.id.spinnerPredmet:
                if (position != 0){
                    spinnerPredemt.setSelection(position,true);
                    viewModelSummary.setCourseProperty(naziviPredmeta.get(position),4);
                }
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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