package List;
import java.util.ArrayList;
import java.util.List;
import Exception.MyException;
import ProgramState.PrgState;

public class MyList<TElem> implements MyIList<TElem> {

    private ArrayList<TElem> list;

    public MyList(){
        this.list=new ArrayList<TElem>();
    }


    @Override
    public void add(TElem elem) {
        this.list.add(elem);
    }

    @Override
    public String toString() {
        return this.list.toString();
    }

    @Override
    public List<TElem> getAllList() {
        return (List<TElem>)this.list;
    }

    @Override
    public TElem getElem(int pos) throws MyException {
        try{return this.list.get(pos);}
        catch(Exception e){throw new MyException("Position invalid!");}
    }

    @Override
    public void setAllList(List<TElem> l) {
        this.list=new ArrayList<TElem>();
        this.list.addAll(l);
    }

    @Override
    public int size() {
        return this.list.size();
    }

    @Override
    public PrgState getSpecific(Integer id) {
        for(TElem program : this.list){
            PrgState prgs=(PrgState)program;
            if(prgs.getID() == id)
                return prgs;
        }
        return null;
    }

}
