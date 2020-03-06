package View;
import Repository.*;
import Controller.Controller;
import Statement.*;
import Type.*;
import Expression.*;
import Value.*;
import Stack.*;
import Dictionary.*;
import List.*;
import ProgramState.PrgState;


import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;


public class View {

    private TextMenu menu;


    public Map<String,Command> populateList(){
        return menu.getCommands();
    }


    public void populate() {
        IStmt ex1= new CompStmt(new VarDeclStmt("a",new IntType()),
                new CompStmt(new VarDeclStmt("b",new IntType()),
                        new CompStmt(new AssignStmt("a",new ValueExp( new IntValue(1))),
                                new CompStmt(new AssignStmt("b",new ValueExp(new IntValue(2))),
                                        new CompStmt(new VarDeclStmt("v",new BoolType()),
                                                new CompStmt(new AssignStmt("v",new RelationalExp("<",new VarExp("a"),new VarExp("b"))),
                                                        new PrintStmt(new VarExp("v"))))))));
        IStmt ex2 = new CompStmt(new VarDeclStmt("varf",new StringType()),
                new CompStmt(new AssignStmt("varf",new ValueExp(new StringValue("testt.in"))),
                        new CompStmt(new VarDeclStmt("varc",new IntType()),
                                new CompStmt(new openRFileStmt(new VarExp("varf")),
                                        new CompStmt(new readFileStmt("varc", new VarExp("varf")),
                                                new CompStmt( new PrintStmt(new VarExp("varc")),
                                                        new CompStmt(new readFileStmt("varc", new VarExp("varf")),
                                                                new CompStmt(new PrintStmt(new VarExp("varc")),new closeRFileStmt(new VarExp("varf"))))))))));
        IStmt ex3 = new CompStmt(new VarDeclStmt("varf",new StringType()),
                new CompStmt(new AssignStmt("varf",new ValueExp(new StringValue("testt.in"))),
                        new CompStmt(new VarDeclStmt("varc",new IntType()),
                                new CompStmt(new openRFileStmt(new VarExp("varf")),new CompStmt(new openRFileStmt(new VarExp("varf")),
                                        new CompStmt(new readFileStmt("varc", new VarExp("varf")),
                                                new CompStmt( new PrintStmt(new VarExp("varc")),
                                                        new CompStmt(new readFileStmt("varc", new VarExp("varf")),
                                                                new CompStmt(new PrintStmt(new VarExp("varc")),new closeRFileStmt(new VarExp("varf")))))))))));



        IStmt ex4 = new CompStmt(new VarDeclStmt("v",new RefType(new IntType())),
                new CompStmt(new NewStmt("v",new ValueExp(new IntValue(20))),
                        new CompStmt(new VarDeclStmt("a",new RefType(new RefType(new IntType()))),
                                new CompStmt(new NewStmt("a",new VarExp("v")),
                                        new CompStmt(new PrintStmt(new VarExp("v")),new PrintStmt(new VarExp("a")))))));

        IStmt ex5 = new CompStmt(new VarDeclStmt("v",new IntType()),
                new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(4))),
                        new CompStmt(new WhileStmt(new RelationalExp(">",new VarExp("v"),new ValueExp(new IntValue(0))),
                                new CompStmt(new PrintStmt(new VarExp("v")),new AssignStmt("v",new ArithExp(2,new VarExp("v"),new ValueExp(new IntValue(1)))))),
                                new PrintStmt(new VarExp("v")))));
        //Ref int v;new(v,20);print(rH(v)); wH(v,30);print(rH(v)+5);
        IStmt ex6 = new CompStmt(new VarDeclStmt("v",new RefType(new IntType())),
                new CompStmt(new NewStmt("v",new ValueExp(new IntValue(20))),
                        new CompStmt(new PrintStmt(new readHeapExp(new VarExp("v"))),
                                new CompStmt(new writeHeapStmt("v",new ValueExp(new IntValue(30))),
                                        new PrintStmt(new ArithExp(1,new readHeapExp(new VarExp("v")),new ValueExp(new IntValue(5))))))));

        IStmt ex7 = new CompStmt(new VarDeclStmt("v",new IntType()),
                new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(4))),
                        new CompStmt(new WhileStmt(new ArithExp(1,new ValueExp(new IntValue(5)),new ValueExp(new IntValue(1))),
                                new CompStmt(new PrintStmt(new VarExp("v")),new AssignStmt("v",new ArithExp(2,new VarExp("v"),new ValueExp(new IntValue(1)))))),
                                new PrintStmt(new VarExp("v")))));
        //Ref int v;new(v,20);Ref Ref int a; new(a,v); new(v,30);print(rH(rH(a)))
        IStmt ex8 = new CompStmt(new VarDeclStmt("v",new RefType(new IntType())),
                new CompStmt(new NewStmt("v",new ValueExp(new IntValue(20))),
                        new CompStmt(new VarDeclStmt("a",new RefType(new RefType(new IntType()))),
                        new CompStmt(new NewStmt("a",new VarExp("v")),
                                new CompStmt(new NewStmt("v",new ValueExp(new IntValue(30))),
                                        new PrintStmt(new readHeapExp(new readHeapExp(new VarExp("a")))))))));
        //Ref int v;new(v,20); new(v,30);
        IStmt ex9 = new CompStmt(new VarDeclStmt("v",new RefType(new IntType())),
                new CompStmt(new NewStmt("v",new ValueExp(new IntValue(20))),
                        new NewStmt("v",new ValueExp(new IntValue(30)))));

        IStmt ex10 = new CompStmt(new VarDeclStmt("a",new IntType()),new forkStmt(new PrintStmt(new VarExp("a"))));

        IStmt ex11 = new CompStmt(new VarDeclStmt("a",new IntType()), new CompStmt(new VarDeclStmt("b",new IntType()),
                new CompStmt(new forkStmt(new forkStmt(new PrintStmt(new VarExp("a")))), new forkStmt(new PrintStmt(new VarExp("b"))))));

        PrgState prog1 = new PrgState(new MyStack<IStmt>(),new MyDictionary<>(),new MyList<Value>(),ex1);
        IRepository repo1 = new Repository(prog1,"log1.txt");
        Controller ctrl1 = new Controller(repo1);

        PrgState prog6 = new PrgState(new MyStack<IStmt>(),new MyDictionary<>(),new MyList<Value>(),ex6);
        IRepository repo6 = new Repository(prog6, "log6.txt");
        Controller ctrl6 = new Controller(repo6);



        PrgState prg3 = new PrgState(new MyStack<IStmt>() ,new MyDictionary<>(),new MyList<Value>(),ex3);
        Repository repo3 = new Repository(prg3,"log3.txt");
        Controller ctrl3=new Controller(repo3);

        PrgState prg10 = new PrgState(new MyStack<IStmt>() ,new MyDictionary<>(),new MyList<Value>(),ex10);
        Repository repo10 = new Repository(prg10,"log10.txt");
        Controller ctrl10=new Controller(repo10);

        PrgState prg11 = new PrgState(new MyStack<IStmt>() ,new MyDictionary<>(),new MyList<Value>(),ex11);
        Repository repo11 = new Repository(prg11,"log11.txt");
        Controller ctrl11=new Controller(repo11);





        menu = new TextMenu();
        //menu.addCommand(new ExitCommand("0","exit"));
        menu.addCommand(new RunExampleCommand("1",ex1.toString(),ctrl1));
        menu.addCommand(new RunExampleCommand("6",ex6.toString(),ctrl6));
        menu.addCommand(new RunExampleCommand("3",ex3.toString(),ctrl3));
        menu.addCommand(new RunExampleCommand("10",ex10.toString(),ctrl10));
        menu.addCommand(new RunExampleCommand("11",ex11.toString(),ctrl11));
        //menu.show();



        /*

MyIStack<IStmt> stk1 = new MyStack<IStmt>();
        MyIDictionary<String, Value> symTbl1 = new MyDictionary<String, Value>();
        MyIList<Value> out1 = new MyList<Value>();
        MyIDictionary<String, BufferedReader> fT1 = new MyDictionary<String, BufferedReader>();
        PrgState prg1 = new PrgState((MyStack<IStmt>) stk1, symTbl1, out1, ex1,fT1);
        Repository repo1 = new Repository(prg1,"log.txt");
        Controller ctrl1 = new Controller(repo1);
        Repository repo2 = new Repository("log1.txt");
        MyIStack<IStmt> stk2 = new MyStack<IStmt>();
        MyIDictionary<String, Value> symTbl2 = new MyDictionary<String, Value>();
        MyIList<Value> out2 = new MyList<Value>();
        MyIDictionary<String, BufferedReader> fT2 = new MyDictionary<String, BufferedReader>();
        PrgState prg2 = new PrgState((MyStack<IStmt>) stk2, symTbl2, out2, ex2,fT2);
        repo2.add(prg2);

        Repository repo3 = new Repository("log3.txt");
        MyIStack<IStmt> stk3 = new MyStack<IStmt>();
        MyIDictionary<String, Value> symTbl3 = new MyDictionary<String, Value>();
        MyIList<Value> out3 = new MyList<Value>();
        MyIDictionary<String, BufferedReader> fT3 = new MyDictionary<String, BufferedReader>();
        PrgState prg3 = new PrgState((MyStack<IStmt>) stk3, symTbl3, out3, ex3,fT3);
        repo3.add(prg3);

        Repository repo4 = new Repository("log4.txt");
        MyIStack<IStmt> stk4 = new MyStack<IStmt>();
        MyIDictionary<String, Value> symTbl4 = new MyDictionary<String, Value>();
        MyIList<Value> out4 = new MyList<Value>();
        MyIDictionary<String, BufferedReader> fT4 = new MyDictionary<String, BufferedReader>();
        PrgState prg4 = new PrgState((MyStack<IStmt>) stk4, symTbl4, out4, ex4,fT4);
        repo4.add(prg4);

        Repository repo5 = new Repository("log5.txt");
        MyIStack<IStmt> stk5 = new MyStack<IStmt>();
        MyIDictionary<String, Value> symTbl5 = new MyDictionary<String, Value>();
        MyIList<Value> out5 = new MyList<Value>();
        MyIDictionary<String, BufferedReader> fT5 = new MyDictionary<String, BufferedReader>();
        PrgState prg5 = new PrgState((MyStack<IStmt>) stk5, symTbl5, out5, ex5,fT5);
        repo5.add(prg5);

        Repository repo6 = new Repository("log6.txt");
        MyIStack<IStmt> stk6 = new MyStack<IStmt>();
        MyIDictionary<String, Value> symTbl6 = new MyDictionary<String, Value>();
        MyIList<Value> out6 = new MyList<Value>();
        MyIDictionary<String, BufferedReader> fT6 = new MyDictionary<String, BufferedReader>();
        PrgState prg6 = new PrgState((MyStack<IStmt>) stk6, symTbl6, out6, ex6,fT6);
        repo6.add(prg6);

        Repository repo7 = new Repository("log7.txt");
        MyIStack<IStmt> stk7 = new MyStack<IStmt>();
        MyIDictionary<String, Value> symTbl7 = new MyDictionary<String, Value>();
        MyIList<Value> out7 = new MyList<Value>();
        MyIDictionary<String, BufferedReader> fT7 = new MyDictionary<String, BufferedReader>();
        PrgState prg7 = new PrgState((MyStack<IStmt>) stk7, symTbl7, out7, ex7,fT7);
        repo7.add(prg7);

        Repository repo8 = new Repository("log8.txt");
        MyIStack<IStmt> stk8 = new MyStack<IStmt>();
        MyIDictionary<String, Value> symTbl8 = new MyDictionary<String, Value>();
        MyIList<Value> out8 = new MyList<Value>();
        MyIDictionary<String, BufferedReader> fT8 = new MyDictionary<String, BufferedReader>();
        PrgState prg8 = new PrgState((MyStack<IStmt>) stk8, symTbl8, out8, ex8,fT8);
        repo8.add(prg8);

        Repository repo9 = new Repository("log9.txt");
        MyIStack<IStmt> stk9 = new MyStack<IStmt>();
        MyIDictionary<String, Value> symTbl9 = new MyDictionary<String, Value>();
        MyIList<Value> out9 = new MyList<Value>();
        MyIDictionary<String, BufferedReader> fT9 = new MyDictionary<String, BufferedReader>();
        PrgState prg9 = new PrgState((MyStack<IStmt>) stk9, symTbl9, out9, ex9,fT9);
        repo9.add(prg9);

        Controller ctrl1 = new Controller(repo1, true);
        Controller ctrl2 = new Controller(repo2, true);
        Controller ctrl3 = new Controller(repo3, true);
        Controller ctrl4 = new Controller(repo4, true);
        Controller ctrl5 = new Controller(repo5, true);
        Controller ctrl6 = new Controller(repo6, true);
        Controller ctrl7 = new Controller(repo7, true);
        Controller ctrl8 = new Controller(repo8, true);
        Controller ctrl9 = new Controller(repo9, true);


        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0","exit"));
        menu.addCommand(new RunExampleCommand("1",ex1.toString(),ctrl1));
        menu.addCommand(new RunExampleCommand("2",ex2.toString(),ctrl2));
        menu.addCommand(new RunExampleCommand("3",ex3.toString(),ctrl3));
        menu.addCommand(new RunExampleCommand("4",ex4.toString(),ctrl4));
        menu.addCommand(new RunExampleCommand("5",ex5.toString(),ctrl5));
        menu.addCommand(new RunExampleCommand("6",ex6.toString(),ctrl6));
        menu.addCommand(new RunExampleCommand("7",ex7.toString(),ctrl7));
        menu.addCommand(new RunExampleCommand("8",ex8.toString(),ctrl8));
        menu.addCommand(new RunExampleCommand("9",ex9.toString(),ctrl9));
        menu.show();

         */

    }
}