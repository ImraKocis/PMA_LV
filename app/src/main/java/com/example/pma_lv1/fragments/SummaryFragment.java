package com.example.pma_lv1.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.pma_lv1.FirstActivity;
import com.example.pma_lv1.R;
import com.example.pma_lv1.models.Course;
import com.example.pma_lv1.models.Storage;
import com.example.pma_lv1.models.Student;
import com.example.pma_lv1.viewModels.StudentVM;
import com.example.pma_lv1.viewModels.SummaryVM;


public class SummaryFragment extends Fragment {

    private Button btnHome;
    private TextView tvName;
    private TextView tvSubject;
    private TextView tvSurname;
    private TextView tvProfessor;
    private TextView tvAcademicYear;
    private TextView tvBirthday;
    private TextView tvLabHours;
    private TextView tvLectureHours;
    private SummaryVM viewModelSummary;

    public SummaryFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_summary, container, false);
        Storage StorageSingleton = Storage.getInstance();
        viewModelSummary = new ViewModelProvider(requireActivity()).get(SummaryVM.class);
        btnHome = (Button) v.findViewById(R.id.btnHome);
        tvName = (TextView) v.findViewById(R.id.tvIme);
        tvSubject = (TextView) v.findViewById(R.id.tvPredmet);
        tvSurname = (TextView) v.findViewById(R.id.tvPrezime);
        tvProfessor = (TextView) v.findViewById(R.id.tvProfesor);
        tvAcademicYear = (TextView) v.findViewById(R.id.tvAkGodina);
        tvBirthday = (TextView) v.findViewById(R.id.tvDatumRodenja);
        tvLabHours = (TextView) v.findViewById(R.id.tvSatiLabosa);
        tvLectureHours = (TextView) v.findViewById(R.id.tvSatiPredavanja);
        viewModelSummary.change.observe(getViewLifecycleOwner(), list -> {
            Student student = null;
            Course course = null;
            student =  viewModelSummary.getStudent();
            tvName.setText(student.name != null ? student.name : "N/A");
            tvSurname.setText(student.surname != null ? student.surname : "N/A");
            tvBirthday.setText(student.birthday != null ? student.birthday : "N/A");
            course = viewModelSummary.getCourse();
            tvSubject.setText(course.subject != null ? course.subject : "N/A");
            tvProfessor.setText(course.professor != null ? course.professor : "N/A");
            tvAcademicYear.setText(course.academicYear != null ? course.academicYear : "N/A");
            tvLabHours.setText(course.labHours != null ? course.labHours : "N/A");
            tvLectureHours.setText(course.lectureHours != null ? course.lectureHours : "N/A");

        });
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Student student =  viewModelSummary.getStudent();
                Course course = viewModelSummary.getCourse();
                StudentVM studentVM = new StudentVM(
                        "Ime: " + (student.name != null ? student.name : "N/A"),
                        "Prezime: " + (student.surname != null ? student.surname : "N/A"),
                        "Predmet: " + (course.subject != null ? course.subject : "N/A")
                );
                StorageSingleton.addStudent(studentVM);
                Intent firstActivity = new Intent(getActivity(), FirstActivity.class);
                firstActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(firstActivity);
            }
        });
        return v;
    }
}