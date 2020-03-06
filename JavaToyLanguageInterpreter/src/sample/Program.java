package sample;

import Dictionary.*;
import Stack.MyStack;
import Statement.IStmt;
import Value.StringValue;
import List.MyIList;
import Exception.MyException;
import DataConversion.HeapToTable;
import DataConversion.SymTblToTable;
import ProgramState.PrgState;
import Value.Value;
import View.RunExampleCommand;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class Program implements Initializable {
    // Command, list of states, current state we re at
    private RunExampleCommand cmd;
    private List<PrgState> prgStates;
    private PrgState currentState;

    //fxml stuff

    @FXML
    private ListView<String> outListView;

    @FXML
    private ListView<String> ftListView;

    @FXML
    private ListView<String> prgListView;

    @FXML
    private ListView<String> exeStackListView;

    @FXML
    private Label nrPLabel;

    @FXML
    private Label prgText;

    @FXML
    private TableView<HeapToTable> heapTableView;

    @FXML
    private TableView<SymTblToTable> symTblTableView;

    @FXML
    private Button backButton;

    @FXML
    private Button runButton;

    @FXML
    private Button exitButton;

    @FXML
    private TableColumn<Map.Entry<Integer,String>,Integer> addressHeapTable;

    @FXML
    private TableColumn<Map.Entry<Integer,String>,String> valueHeapTable;
    @FXML
    private TableView<Map.Entry<String,String>> symbolTableView;

    @FXML
    private TableColumn<Map.Entry<String,String>,String> variableColumn;

    @FXML
    private TableColumn<Map.Entry<String,String>,String> valueSymbolCollumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cmd=(RunExampleCommand)Controller.cd;
        prgStates=cmd.getController().getRepo().getPrgList();
        currentState=prgStates.get(0);
        this.cmd.getController().setUpGUI();

        TableColumn<HeapToTable, Integer> addressColumn = new TableColumn<>("Address");
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        TableColumn<HeapToTable, String> valueColumn = new TableColumn<>("Value");
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
        valueColumn.setMinWidth(100);
        heapTableView.getColumns().addAll(addressColumn, valueColumn);

        TableColumn<SymTblToTable, String> variableNameColumn = new TableColumn<>("varName");
        variableNameColumn.setCellValueFactory(new PropertyValueFactory<>("varName"));
        variableNameColumn.setMinWidth(100);
        TableColumn<SymTblToTable, String> valColumn = new TableColumn<>("Value");
        valColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
        symTblTableView.getColumns().addAll(variableNameColumn, valColumn);


        prgListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        prgText.setText(cmd.getDescription());
    }

    private void setOutTable() throws MyException{
        List<String> outStr = this.currentState.getOut().getAllList().stream().map(Object::toString).collect(Collectors.toList());
        ObservableList<String> outList = FXCollections.observableArrayList(outStr);
        this.outListView.setItems(outList);
    }
    /*
    private void setOutTable(PrgState state) throws MyException{
        this.outListView.getItems().clear();
        MyIList<Value>out=state.getOut();
        int i=0;
        while(i<out.size()){
            this.outListView.getItems().add(out.getElem(i).toString());
            i+=1;
        }
    }
    */


    private void setFileTable(PrgState state) throws MyException{
        this.ftListView.getItems().clear();
        Map<String, BufferedReader> fT=state.getFileTable().getDict();
        Iterator it = fT.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry pair = (Map.Entry)it.next();
            this.ftListView.getItems().add(pair.getKey()+"-->"+pair.getValue().toString()+"; ");
        }
    }
    private void setProgramStates() throws MyException {
        this.prgListView.getItems().clear();
        for (PrgState program : cmd.getController().getRepo().getPrgList()) {
            this.prgListView.getItems().add(Integer.toString(program.getID()));
        }
    }
    private void setSymbolTable(PrgState state) throws MyException{


        this.symTblTableView.getItems().clear();
        ObservableList<SymTblToTable> symData = FXCollections.observableArrayList();
        Map<String, Value> sym=state.getSymTable().getDict();
        System.out.println(sym.toString());
        Iterator it= sym.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry pair = (Map.Entry)it.next();
            symData.add(new SymTblToTable((String)pair.getKey(),pair.getValue().toString()));
        }
        this.symTblTableView.setItems(symData);


    }
    private void setHeap(PrgState state) throws MyException{
        this.heapTableView.getItems().clear();
        ObservableList<HeapToTable> heapData = FXCollections.observableArrayList();
        Map<Integer, Value> hp=state.getHeap().getHeap();
        Iterator it= hp.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry pair = (Map.Entry)it.next();
            heapData.add(new HeapToTable((Integer)pair.getKey(),pair.getValue().toString()));
        }
        this.heapTableView.setItems(heapData);
    }

    private void setExeStack(PrgState state) throws MyException{
        this.exeStackListView.getItems().clear();
        MyStack<IStmt> stack=(MyStack<IStmt>)state.getExeStack();
        Stack<?> clone=stack.clone();
        while(!clone.isEmpty()){
            this.exeStackListView.getItems().add(clone.pop().toString());
        }
    }

    public void selectState()throws MyException{
        String id = this.prgListView.getSelectionModel().getSelectedItem();
        PrgState prg=cmd.getController().getRepo().getState(Integer.parseInt(id)+2);
        this.currentState=prg;
        this.setSymbolTable(prg);
        this.setExeStack(prg);
    }

    public void goBack(){
        Stage st=(Stage) exitButton.getScene().getWindow();
        Parent root=null;
        try{
            root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Crashed!", ButtonType.OK);
            alert.showAndWait();
        }
        st.setTitle("Main menu");
        Scene s = new Scene(root,700,500);
        st.setScene(s);
        st.show();
    }

    public void exit(){
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    public void run(){
        this.nrPLabel.setText(Integer.toString(cmd.getController().size()));
        try{
            setProgramStates();
            setSymbolTable(this.currentState);
            setExeStack(this.currentState);
            setOutTable();
            try {

                setFileTable(prgStates.get(0));
                setHeap(prgStates.get(0));
            }
            catch(Exception e){}
        }
        catch(MyException e){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Program finished!", ButtonType.OK);
            alert.showAndWait();
        }

        try{
            this.prgStates=cmd.getController().allStep();
        } catch (MyException | InterruptedException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Program finished!", ButtonType.OK);
            alert.showAndWait();
        }

    }

}
