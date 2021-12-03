public class Doctor {

    private Integer id;
    private String fullName, specialty;

    public Doctor(Integer id, String fullName, String specialty) {
        this.id = id;
        this.fullName = fullName;
        this.specialty = specialty;
    }

    public Integer getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getSpecialty() {
        return specialty;
    }

    public String returnCSV() {
        return id + "," + fullName + "," + specialty + ",";
    }

}
