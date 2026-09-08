package studentwrite;

import javafx.beans.property.*;
import javafx.collections.FXCollections;

public class Student
{
    private final StringProperty firstName = new SimpleStringProperty();
    private final StringProperty lastName = new SimpleStringProperty();
    private final DoubleProperty gpa = new SimpleDoubleProperty();
    private final ObjectProperty<Major> major = new SimpleObjectProperty<>();

    public Student(String first, String last, double gpa, Major major)
    {
        setFirstName(first);
        setLastName(last);
        setGpa(gpa);
        setMajor(major);
    }

    //We need two sets of getters: one for the UI binding, and one for the DTO conversion
    //TODO is there a way to avoid this redundancy?
    public String getFirstName() { return firstName.get(); }
    public void setFirstName(String val) { firstName.set(val); }
    public StringProperty firstNameProperty() { return firstName; }

    public String getLastName() { return lastName.get(); }
    public void setLastName(String val) { lastName.set(val); }
    public StringProperty lastNameProperty() { return lastName; }

    public double getGpa() { return gpa.get(); }
    public void setGpa(double val) { gpa.set(val); }
    public DoubleProperty gpaProperty() { return gpa; }

    public Major getMajor() { return major.get(); }
    public void setMajor(Major val) { major.set(val); }
    public ObjectProperty<Major> majorProperty() { return major; }
}