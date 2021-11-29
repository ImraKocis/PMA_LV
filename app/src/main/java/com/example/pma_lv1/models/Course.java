package com.example.pma_lv1.models;

public class Course {

    public String subject = null;
    public String professor = null;
    public String academicYear = null;
    public String labHours = null;
    public String lectureHours = null;

    public Course(){}

    public Course(String Subject, String Professor, String AcademicYear, String LabHours, String LectureHours) {
        subject = Subject;
        professor = Professor;
        academicYear = AcademicYear;
        labHours = LabHours;
        lectureHours = LectureHours;
    }
}
