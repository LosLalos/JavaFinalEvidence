public class Patient {

    private Integer id;
    private String fullName;

    public Patient(Integer id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    public Integer getId() {
        return id;
    }

    public String returnCSV() {
        return id + "," + fullName + ",";
    }

}
