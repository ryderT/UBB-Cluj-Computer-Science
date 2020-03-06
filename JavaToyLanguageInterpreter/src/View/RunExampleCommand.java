package View;
import Controller.Controller;
import Exception.MyException;

import java.io.IOException;

public class RunExampleCommand extends Command {
    private String key;
    private String description;
    private Controller controller;

    public RunExampleCommand(String key,String description,Controller contr){
        super(key,description);
        this.controller=contr;
    }
    @Override
    public void execute() {
        try{
            controller.allStep();
        }
        catch (MyException exc){
            System.out.println(exc.toString());
            //System.out.println("Error");
        }
        catch( InterruptedException exc){
            System.out.println(exc.getMessage());
        }
    }
    public Controller getController(){return controller;}
}
