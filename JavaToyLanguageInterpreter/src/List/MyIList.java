package List;
import java.util.List;
import Exception.MyException;
import ProgramState.PrgState;

public interface MyIList<TElem> {
    void add(TElem elem);
    String toString();
    List<TElem> getAllList();
    TElem getElem(int pos) throws MyException;
    void setAllList(List<TElem> l);
    int size();
    PrgState getSpecific(Integer id);

}
