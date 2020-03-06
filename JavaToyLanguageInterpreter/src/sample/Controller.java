package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import View.View;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import View.Command;
import View.RunExampleCommand;
import javafx.scene.control.SelectionMode;

public class Controller implements Initializable {
    public static Command cd;
    private View view;

    @FXML
    private ListView<String> programsList;
    @FXML
    private Button startButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        view= new View();
        view.populate();
        Map<String, Command> cmds = view.populateList();
        for(Map.Entry entry: cmds.entrySet()){
            Command c=(RunExampleCommand) entry.getValue();
            programsList.getItems().add(c.getKey()+" "+c.getDescription());
        }
        programsList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

    }

    @FXML
    void startProgram() throws IOException {
        Integer id;
        String selected=programsList.getSelectionModel().getSelectedItem();
        try{
            id=Integer.parseInt(selected.substring(0,2));
        }catch(Exception e) {
            id=Integer.parseInt(selected.substring(0,1));
        }
        String sid=id.toString();
        view= new View();
        view.populate();
        //Map<String, Command> cmds = view.populateList();
        cd = view.populateList().get(sid);
        Parent root = FXMLLoader.load(getClass().getResource("program.fxml"));
        startButton.getScene().setRoot(root);

    }
}
