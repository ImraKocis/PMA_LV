package com.example.pma_lv1.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pma_lv1.models.Course;
import com.example.pma_lv1.models.Student;

public class SummaryVM extends ViewModel {
    public MutableLiveData<Integer> change = new MutableLiveData<>();
    public MutableLiveData<Student> student = new MutableLiveData<>();
    public MutableLiveData<Course> course = new MutableLiveData<>();

    public SummaryVM(){
        Student s = new Student();
        Course c = new Course();
        student.setValue(s);
        course.setValue(c);
        change.setValue(0);
    }

    public void setStudentProperty(String value, int propIndex){
        Student oStudnet = student.getValue();
        if (propIndex == 1) oStudnet.name = value;
        if (propIndex == 2) oStudnet.surname = value;
        if (propIndex == 3) oStudnet.birthday = value;
        student.setValue(oStudnet);
        change.setValue(1);
    }
    public void setCourseProperty(String value, int propIndex){
        Course oCourse = course.getValue();
        if (propIndex == 4) oCourse.subject = value;
        if (propIndex == 5) oCourse.professor = value;
        if (propIndex == 6) oCourse.academicYear = value;
        if (propIndex == 7) oCourse.lectureHours = value;
        if (propIndex == 8) oCourse.labHours = value;

        course.setValue(oCourse);
        change.setValue(2);
    }
    public Student getStudent(){
        return student.getValue();
    }
    public Course getCourse(){
        return course.getValue();
    }
}
