package Controller;

import Repository.IRepository;
import ProgramState.PrgState;
import Exception.MyException;
import Statement.IStmt;
import Stack.MyIStack;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import Value.Value;
import Value.RefValue;

public class Controller {
    private IRepository repo;
    private ExecutorService executor=null;


    public Controller(IRepository rep) {
        this.repo=rep;
    }
    /*
    ***updated***
    public PrgState oneStep(PrgState state) throws MyException{
        MyIStack<IStmt> stk=state.getExeStack();
        if(stk.isEmpty())
            throw new MyException("prgstate stack is empty");
        IStmt crtStmt = stk.pop();
        return crtStmt.execute(state);
    }
    public void allStep() throws MyException {
        PrgState program = repo.getCrtPrg();
        System.out.println(program+"\n");
        repo.logPrgStateExec();
        while(!program.getExeStack().isEmpty()){
            try {
                oneStep(program);
                repo.logPrgStateExec();
                executeGarbageCollector();
                System.out.println(program);
            }
            catch (MyException error){
                throw new MyException(error.getMessage());
            }

        }

    }
     */
    public void setUpGUI(){this.executor= Executors.newFixedThreadPool(2);}
    public List<PrgState> allStep() throws MyException, InterruptedException {
        List<PrgState> programs=removeCompletedPrg(repo.getPrgList());
        if (programs.size() > 0) {
            executeGarbageCollector();
            oneStepForAllPrograms(programs);
            programs = removeCompletedPrg(repo.getPrgList());
        }
        else {
            executor.shutdownNow();
            throw new MyException("Finished!");
        }
        repo.setPrgList(programs);
        return this.repo.getPrgList();
    }

    private List<PrgState> removeCompletedPrg(List<PrgState> inProgramList){
        return inProgramList.stream().filter(PrgState::isNotCompleted).collect(Collectors.toList());
    }

    public void oneStepForAllPrograms(List<PrgState> programs) throws InterruptedException {

        programs.forEach(prg->{repo.logPrgStateExec(prg);
            System.out.println(prg);});

        List<Callable<PrgState>> callList = programs.stream().filter(p->!p.getExeStack().isEmpty()).
                map((PrgState p)->(Callable<PrgState>)(p::oneStep)).collect(Collectors.toList());

        List<PrgState> newProgramList = executor.invokeAll(callList).stream().map(future->{
            try{
                return future.get();
            }
            catch (MyException | InterruptedException | ExecutionException exc){
                throw new MyException(exc.getMessage());
            }
        }).filter(Objects::nonNull).collect(Collectors.toList());
        programs.addAll(newProgramList);
        programs.forEach(prg->{
            System.out.println(prg);repo.logPrgStateExec(prg);});
        repo.setPrgList(programs);
    }


    private Map<Integer, Value> safeGarbageCollector(List<Integer> addresses, Map<Integer,Value> heap){
        return heap.entrySet().stream().filter(e->addresses.contains(e.getKey())).
                collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));
    }
    private void executeGarbageCollector(){
        List<PrgState> programs = repo.getPrgList();
        List<List<Integer>> addrSymTableList = programs.stream()
                .map(PrgState::getSymTable)
                .map(p->getAddressFromSymTable(p.getValues()))
                .collect(Collectors.toList());
        List<Integer> addresses= new ArrayList<Integer>();
        addrSymTableList.forEach(addresses::addAll);
        List<Integer> add2 = getAddrFromHeap(programs.get(0).getHeap().getValues());
        addresses.addAll(add2);
        Map<Integer,Value> garbCollector = safeGarbageCollector(addresses,programs.get(0).getHeap().getHeap());
        programs.forEach(p->p.getHeap().setHeap(garbCollector));
    }
    private List<Integer> getAddressFromSymTable(Collection<Value> symTableValues){
        return symTableValues.stream()
                .filter(v->v instanceof RefValue)
                .map(v->((RefValue)v).getAddress())
                .collect(Collectors.toList());

    }
    private List<Integer> getAddrFromHeap(Collection<Value> heapValues){
        //reference to reference
        return heapValues.stream().
                filter(v->v instanceof RefValue)
                .map(v->((RefValue)v).getAddress())
                .collect(Collectors.toList());
    }

    public IRepository getRepo(){return this.repo;}

    public int size() {
        return this.repo.size();
    }
}
