public class Appointment {

    private Integer id;
    private String timeAndDate, motive;
    private Doctor appointedDoctor;
    private Patient appointedPatient;

    public Appointment(Integer id, String timeAndDate, String motive, Doctor appointedDoctor, Patient appointedPatient) {
        this.id = id;
        this.timeAndDate = timeAndDate;
        this.motive = motive;
        this.appointedDoctor = appointedDoctor;
        this.appointedPatient = appointedPatient;
    }

    public String returnCSV() {
        return id + "," + timeAndDate + "," + motive + "," + appointedDoctor.getId() + "," + appointedPatient.getId() + ",";
    }

}
