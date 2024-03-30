package restService;

import java.util.ArrayList;

public class Message {

    private ArrayList<String> infoMessages = new ArrayList<String>();
    private ArrayList<String> errorMessages  = new ArrayList<String>();


    public ArrayList<String> getInfoMessage() {
        return infoMessages;
    }

    public ArrayList<String> getErrorMessage() {
        return errorMessages;
    }

    public void addInfoMessage(String message) {
        this.infoMessages.add(message);
    }

    public void addErrorMessage(String error) {
        this.errorMessages.add(error);
    }

}
