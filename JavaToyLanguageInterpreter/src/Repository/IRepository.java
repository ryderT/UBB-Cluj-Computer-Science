package Repository;

import ProgramState.PrgState;
import Exception.MyException;
import java.util.List;
import java.io.IOException;
import List.MyList;

public interface IRepository {
    void logPrgStateExec(PrgState p) throws MyException;
    List<PrgState> getPrgList();
    void setPrgList(List<PrgState> l);
    PrgState getState(Integer id);
    int size();
}
