package Repository;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import ProgramState.PrgState;
import Exception.MyException;
import Stack.MyIStack;
import java.util.List;
import List.MyList;
import List.MyIList;

import java.io.IOException;
import java.util.Vector;

public class Repository implements IRepository{

    private MyIList<PrgState> v;
    //private int crtPrg;
    private String logFilePath;

    public Repository(PrgState p){
        this.v= new MyList<PrgState>();
        this.v.add(p);
        this.logFilePath="";
    }
    public Repository(PrgState p, String logFile){
        this.v= new MyList<PrgState>();
        this.v.add(p);
        this.logFilePath=logFile;
    }


    @Override
    public void logPrgStateExec(PrgState p) throws MyException {
        try {
            PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(this.logFilePath, true)));
            logFile.write("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||\n");
            logFile.write(p.toString());
            logFile.write("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||\n");
            logFile.close();
        } catch (IOException er) {
            throw new MyException("Log File Error!");
        }

    }


    @Override
    public List<PrgState> getPrgList() {
        return this.v.getAllList();
    }

    @Override
    public void setPrgList(List<PrgState> l) {
        this.v.setAllList(l);
    }
    /*
    @Override
    public PrgState getCrtPrg() {
        return v.get(this.crtPrg);
    }
     */
    @Override
    public PrgState getState(Integer id){
        return this.v.getSpecific(id);
    }

    @Override
    public int size() {
        return this.v.size();
    }

}
