package com.example.pma_lv1.fragments;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;


import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.pma_lv1.R;
import com.example.pma_lv1.viewModels.SummaryVM;
import com.google.android.material.textfield.TextInputEditText;


public class PersonalInfoFragment extends Fragment {
    private TextInputEditText etUnosIme;
    private TextInputEditText etUnosPrezime;
    private TextInputEditText etUnosDatumRodenja;
    private Button btnKamera;
    private ImageView ivProfilePicture;
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
        return viewModel;
    }

   public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
        viewModelSummary = new ViewModelProvider(requireActivity()).get(SummaryVM.class);
        etUnosIme = (TextInputEditText) view.findViewById(R.id.unosIme);
        etUnosPrezime=(TextInputEditText) view.findViewById(R.id.unosPrezime);
        etUnosDatumRodenja=(TextInputEditText) view.findViewById(R.id.unosDatumRodenja);
        ivProfilePicture = view.findViewById(R.id.ivImage);

        etUnosIme.addTextChangedListener(new TextChange(etUnosIme));
        etUnosPrezime.addTextChangedListener(new TextChange(etUnosPrezime));
        etUnosDatumRodenja.addTextChangedListener(new TextChange(etUnosDatumRodenja));
        btnKamera = (Button) view.findViewById(R.id.btnKamera);
        btnKamera.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v){

           }
       });
        btnKamera.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                dispatchTakePictureIntent();
            }
        });
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

    static final int REQUEST_IMAGE_CAPTURE = 1;
    Bitmap imageBitmap;
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == REQUEST_IMAGE_CAPTURE && requestCode != Activity.RESULT_CANCELED && resultCode == Activity.RESULT_OK){
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            ivProfilePicture.setImageBitmap(imageBitmap);

        }
    }
    private void dispatchTakePictureIntent(){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }catch (Error e){
            e.getCause();
        }
    }
}