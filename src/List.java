import java.io.*;
import java.util.HashMap;

public class List {

    private HashMap<Integer, Doctor> doctorList;
    private HashMap<Integer, Patient> patientList;
    private HashMap<Integer, Appointment> appointmentList;

    public List() {
        doctorList = new HashMap<>();
        patientList = new HashMap<>();
        appointmentList = new HashMap<>();
    }

    public void load() throws IOException {
        boolean empty = false;
        int counter;
        String cursor = "";
        File doctorFile = new File("doctorList.txt");
        File patientFile = new File("patientList.txt");
        File appointmentFile = new File("appointmentList.txt");

        doctorFile.createNewFile();
        patientFile.createNewFile();
        appointmentFile.createNewFile();

        BufferedReader doctorFileReader = new BufferedReader(new FileReader("doctorList.txt"));
        Integer doctorId = null;
        String doctorFullName = null, doctorSpecialty = null, doctor;

        do {
            counter = 0;
            doctor = doctorFileReader.readLine();

            if (doctor == null) {
                empty = true;
            }
            else {
                for (char letter : doctor.toCharArray()) {
                    if (letter != ',') {
                        cursor = cursor.concat(String.valueOf(letter));
                    } else {
                        switch (counter) {
                            case 0 -> {
                                doctorId = Integer.parseInt(cursor);
                                cursor = "";
                                counter++;
                            }
                            case 1 -> {
                                doctorFullName = cursor;
                                cursor = "";
                                counter++;
                            }
                            case 2 -> {
                                doctorSpecialty = cursor;
                                cursor = "";
                                counter++;
                            }
                        }
                    }
                }

                Doctor loadedDoctor = new Doctor(doctorId, doctorFullName, doctorSpecialty);

                doctorList.put(doctorId, loadedDoctor);
            }
        }
        while (!empty);
        empty = false;

        BufferedReader patientFileReader = new BufferedReader(new FileReader("patientList.txt"));
        Integer patientId = null;
        String patientFullName = null, patient;

        do {
            counter = 0;
            patient = patientFileReader.readLine();

            if (patient == null) {
                empty = true;
            }
            else {
                for (char letter : patient.toCharArray()) {
                    if (letter != ',') {
                        cursor = cursor.concat(String.valueOf(letter));
                    } else {
                        switch (counter) {
                            case 0 -> {
                                patientId = Integer.parseInt(cursor);
                                cursor = "";
                                counter++;
                            }
                            case 1 -> {
                                patientFullName = cursor;
                                cursor = "";
                                counter++;
                            }
                        }
                    }
                }

                Patient loadedPatient = new Patient(patientId, patientFullName);

                patientList.put(patientId, loadedPatient);
            }
        }
        while (!empty);
        empty = false;

        BufferedReader appointmentFileReader = new BufferedReader(new FileReader("appointmentList.txt"));
        Integer appointmentId = null;
        String appointmentTimeAndDate = null, appointmentMotive = null, appointment;
        Doctor appointedDoctor = null;
        Patient appointedPatient = null;

        do {
            counter = 0;
            appointment = appointmentFileReader.readLine();

            if (appointment == null) {
                empty = true;
            } else {
                for (char letter : appointment.toCharArray()) {
                    if (letter != ',') {
                        cursor = cursor.concat(String.valueOf(letter));
                    } else {
                        switch (counter) {
                            case 0 -> {
                                appointmentId = Integer.parseInt(cursor);
                                cursor = "";
                                counter++;
                            }
                            case 1 -> {
                                appointmentTimeAndDate = cursor;
                                cursor = "";
                                counter++;
                            }
                            case 2 -> {
                                appointmentMotive = cursor;
                                cursor = "";
                                counter++;
                            }
                            case 3 -> {
                                appointedDoctor = doctorList.get(Integer.parseInt(cursor));
                                cursor = "";
                                counter++;
                            }
                            case 4 -> {
                                appointedPatient = patientList.get(Integer.parseInt(cursor));
                                cursor = "";
                                counter++;
                            }
                        }
                    }
                }

                Appointment loadedAppointment = new Appointment(appointmentId, appointmentTimeAndDate, appointmentMotive, appointedDoctor, appointedPatient);

                appointmentList.put(appointmentId, loadedAppointment);

            }
        }
        while (!empty);
    }


    public void save() throws IOException {
        File doctorFile = new File("doctorList.txt");
        File patientFile = new File("patientList.txt");
        File appointmentFile = new File("appointmentList.txt");

        doctorFile.delete();
        patientFile.delete();
        appointmentFile.delete();

        doctorFile.createNewFile();
        patientFile.createNewFile();
        appointmentFile.createNewFile();

        BufferedWriter doctorFileWriter = new BufferedWriter(new FileWriter("doctorList.txt"));
        BufferedWriter patientFileWriter = new BufferedWriter(new FileWriter("patientList.txt"));
        BufferedWriter appointmentFileWriter = new BufferedWriter(new FileWriter("appointmentList.txt"));
        Integer[] doctorKeySet = doctorList.keySet().toArray(new Integer[0]);
        Integer[] patientKeySet = patientList.keySet().toArray(new Integer[0]);
        Integer[] appointmentKeySet = appointmentList.keySet().toArray(new Integer[0]);

        if (doctorKeySet.length != 0) {
            for (Integer key : doctorKeySet) {
                doctorFileWriter.write(doctorList.get(key).returnCSV() + "\n");
            }
        }
        doctorFileWriter.close();

        if (patientKeySet.length != 0) {
            for (Integer key : patientKeySet) {
                patientFileWriter.write(patientList.get(key).returnCSV() + "\n");
            }
        }
        patientFileWriter.close();

        if (appointmentKeySet.length != 0) {
            for (Integer key : appointmentKeySet) {
                appointmentFileWriter.write(appointmentList.get(key).returnCSV() + "\n");
            }
        }
        appointmentFileWriter.close();

    }

    public void addDoctor() {

        Integer doctorID;
        String doctorFullName, doctorSpecialty;

        doctorID = getPositiveWholeNumberInput("\n[?] Insert the id of the doctor.\n>>> ");
        doctorFullName = getAlphabeticalInput("\n[?] Insert the full name of the doctor.\n>>> ");
        doctorSpecialty = getAlphabeticalInput("\n[?] Insert the specialty of the doctor.\n>>> ");

        Doctor newDoctor = new Doctor(doctorID, doctorFullName, doctorSpecialty);

        doctorList.put(doctorID, newDoctor);

    }

    public void addPatient() {
        Integer patientID;
        String patientFullName;

        patientID = getPositiveWholeNumberInput("\n[?] Insert the id of the patient.\n>>> ");
        patientFullName = getAlphabeticalInput("\n[?] Insert the full name of the patient.\n>>> ");

        Patient newPatient = new Patient(patientID, patientFullName);

        patientList.put(patientID, newPatient);

    }

    public void addAppointment() {

        Integer appointmentID;
        String timeAndDate,motive;
        Doctor appointedDoctor;
        Patient appointedPatient;

        appointmentID = getPositiveWholeNumberInput("\n[?] Insert the id of the appointment.\n>>> ");
        timeAndDate = getStringInput("\n[?] Insert the time and date of the appointment.\n>>> ");
        motive = getAlphabeticalInput("\n[?] Insert the motive of the appointment.\n>>> ");
        appointedDoctor = (Doctor) getObjectInput("\n[?] Insert the id of the doctor.\n>>> ", 0);
        appointedPatient = (Patient) getObjectInput("\n[?] Insert the id of the patient.\n>>> ", 1);

        Appointment newAppointment = new Appointment(appointmentID, timeAndDate, motive, appointedDoctor, appointedPatient);

        appointmentList.put(appointmentID, newAppointment);

    }

    private Integer getPositiveWholeNumberInput(String promptMessage) {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        Integer userInput = null;
        boolean error;

        do {
            try {
                System.out.print(promptMessage);
                userInput = Integer.parseInt(bufferedReader.readLine());

                if (userInput < 0) {
                    throw new NumberFormatException();
                }

                error = false;

            } catch (NumberFormatException e) {
                System.out.print("\n[!] Invalid value! Please insert a positive whole number.");
                error = true;

            } catch (IOException e) {
                e.printStackTrace();
                error = true;

            }
        }
        while (error);

        return userInput;
    }

    private String getAlphabeticalInput(String promptMessage) {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String userInput = null;
        boolean error;

        do {
            try {
                System.out.print(promptMessage);
                userInput = bufferedReader.readLine();

                if (userInput.isEmpty()) {
                    throw new NullPointerException();
                }
                else {

                    for (char character: userInput.toCharArray()) {

                        if (!Character.isAlphabetic(character) && !Character.isWhitespace(character)){
                            throw new Exception();
                        }

                    }

                }

                error = false;
            } catch (NullPointerException e) {
                System.out.print("\n[!] Invalid value! Please fill the field.");
                error = true;

            } catch (IOException e) {
                e.printStackTrace();
                error = true;

            } catch (Exception e) {
                System.out.print("\n[!] Invalid value! Please insert only alphabetical values.");
                error = true;

            }
        }
        while (error);

        return userInput;
    }

    private String getStringInput(String promptMessage) {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String userInput = null;
        boolean error;

        do {
            try {
                System.out.print(promptMessage);
                userInput = bufferedReader.readLine();

                if (userInput.isEmpty()) {
                    throw new NullPointerException();
                }

                error = false;
            } catch (NullPointerException e) {
                System.out.print("\n[!] Invalid value! Please fill the field.");
                error = true;

            } catch (IOException e) {
                e.printStackTrace();
                error = true;

            }
        }
        while (error);

        return userInput;
    }

    private Object getObjectInput(String promptMessage, int objectType) {
        Integer userInput;
        Object object = null;
        boolean error;

        do {
            userInput = getPositiveWholeNumberInput(promptMessage);

            try {
                switch (objectType) {
                    case 0 -> {
                        if (!doctorList.containsKey(userInput)) {
                            throw new Exception();
                        }
                        object = doctorList.get(userInput);

                    }
                    case 1 -> {
                        if (!patientList.containsKey(userInput)) {
                            throw new Exception();
                        }
                        object = patientList.get(userInput);

                    }
                }

                error = false;
            } catch (Exception e) {
                switch (objectType) {
                    case 0 -> System.out.print("\n[!] The requested doctor doesn't exist. Please try again.");
                    case 1 -> System.out.print("\n[!] The requested patient doesn't exist. Please try again.");

                }
                error = true;
            }
        }
        while (error);

        return object;
    }

}
