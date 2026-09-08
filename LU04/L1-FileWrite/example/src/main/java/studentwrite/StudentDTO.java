package studentwrite;

public record StudentDTO(String firstName, String lastName, double gpa, Major major)
{
    // Convert DTO to UI Model
    public Student toModel()
    {
        return new Student(firstName, lastName, gpa, major);
    }

    // Static helper to create DTO from UI Model
    public static StudentDTO fromModel(Student s)
    {
        return new StudentDTO(s.getFirstName(), s.getLastName(), s.getGpa(), s.getMajor());
    }

    public String toCsv() {
        return String.format("%s,%s,%.2f,%s", firstName, lastName, gpa, major);
    }
}