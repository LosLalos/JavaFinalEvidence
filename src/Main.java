import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        application();
    }

    public static void application() throws IOException {
        int userInput;
        boolean exit = false;

        List list = new List();
        list.load();

        do {
            userInput = getUserInput();

            switch (userInput) {
                case 0 -> list.addDoctor();
                case 1 -> list.addPatient();
                case 2 -> list.addAppointment();
                case 3 -> exit = true;
            }
        }
        while(!exit);


        list.save();
    }

    public static int getUserInput(){

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        Integer userInput = null;
        boolean error;

        do {
            try {
                System.out.print("""

                        [?] Choose one of the following menu options:
                             [0] Add a doctor.
                             [1] Add a patient.
                             [2] Add an appointment.
                             [3] Exit.
                        
                        >>>\s""");
                userInput = Integer.parseInt(bufferedReader.readLine());
                if (userInput > 3 || userInput < 0) {
                    throw new Exception();
                }
                error = false;

            } catch (NumberFormatException e) {
                System.out.print("\n[!] Invalid value! Please insert a whole number.");
                error = true;

            } catch (IOException e) {
                e.printStackTrace();
                error = true;

            } catch (Exception e) {
                System.out.print("\n[!] Invalid value! Please insert a valid menu option.");
                error = true;
            }
        }
        while (error);

        return userInput;
    }

}
