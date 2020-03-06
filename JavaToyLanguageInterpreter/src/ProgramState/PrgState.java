package ProgramState;

import Dictionary.MyIDictionary;
import List.MyIList;
import List.MyList;
import Stack.MyIStack;
import Stack.MyStack;
import Statement.IStmt;
import Value.Value;
import Repository.Repository;
import Value.*;
import Heap.*;
import Exception.MyException;
import Dictionary.MyDictionary;
import java.io.BufferedReader;

public class PrgState {
    private MyIStack<IStmt> exeStack;
    private MyIDictionary<String, Value> symTable;
    private MyIList<Value> out;
    private IStmt originalProgram;
    private MyIDictionary<String, BufferedReader> fileTable;
    private MyIHeap<Value> heap;
    private int thread;
    private static Integer threadGlobalCount=0;

    //This is for the normal declaration!!!
    public PrgState(MyIStack<IStmt> exeStack, MyIDictionary<String, Value> symTable, MyIList<Value> out, IStmt originalProgram) {
        this.exeStack = exeStack;
        this.symTable = symTable;
        this.out = out;
        this.originalProgram = originalProgram; //should be  a deep copy..
        this.fileTable = new MyDictionary<>();
        this.heap=new MyHeap<Value>();
        this.thread= threadGlobalCount++;
        exeStack.push(originalProgram);
    }
    /*
    public PrgState(MyIStack<IStmt> exeStack, MyIDictionary<String, Value> symTable, MyIList<Value> out, IStmt originalProgram) {
        this.exeStack = exeStack;
        this.symTable=symTable;
        this.out=out;
        this.originalProgram=originalProgram.deepCopy();
        exeStack.push(originalProgram);
        this.fileTable=new MyDictionary<>();
        this.heap=new MyHeap<Value>();
        this.thread=threadGlobalCount++;
    }*/

    // This is for the fork!!!
    public PrgState(MyIStack<IStmt> st,MyIDictionary<String,Value> sym,MyIList<Value> outt,IStmt oprg,MyIDictionary<String,BufferedReader> fT,MyIHeap<Value> h){
        this.exeStack=st;
        this.symTable=sym;
        this.out=outt;
        this.originalProgram=oprg.deepCopy();
        st.push(oprg);
        this.fileTable=fT;
        this.heap=h;
        this.thread=threadGlobalCount++;
    }

    public boolean isNotCompleted(){return !this.exeStack.isEmpty();}

    public PrgState oneStep(){
        if(exeStack.isEmpty())
            throw new MyException("Execution Stack is Empty!");
        IStmt crtStmt = exeStack.pop();
        return crtStmt.execute(this);
    }

    public MyIHeap<Value> getHeap() {
        return heap;
    }
    public MyIDictionary<String, BufferedReader> getFileTable() {
        return fileTable;
    }

    public IStmt getOriginalProgram() {
        return originalProgram;
    }

    public MyIDictionary<String, Value> getSymTable() {
        return symTable;
    }

    public MyIList<Value> getOut() {
        return out;
    }

    public MyIStack<IStmt> getExeStack() {
        return exeStack;
    }

    public void setExeStack(MyIStack<IStmt> exeStack) {
        this.exeStack = exeStack;
    }

    public void setOriginalProgram(IStmt originalProgram) {
        this.originalProgram = originalProgram;
    }

    public void setOut(MyIList<Value> out) {
        this.out = out;
    }

    public void setSymTable(MyIDictionary<String, Value> symTable) {
        this.symTable = symTable;
    }
    public String toString(){
        return "Thread: "+ thread +" \nExeStack: " + exeStack.toString() + "\nSymTable: "+symTable.toString()+"\nOut: "+out.toString()+"\nHeap: "+heap.toString()+"\nFileTable: "+fileTable.toString()+'\n';
    }

    public int getID() {
        return this.thread;
    }
}
