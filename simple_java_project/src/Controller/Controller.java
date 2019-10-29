package Controller;
import Model.Participants;
import Repository.*;

public class Controller {
    private Repository repository;
    public Controller(Repository repository){
        this.repository=repository;
    }

    public void addParticipant(Participants p)throws Exception{
        this.repository.addParticipant(p);
    }
    public void deleteParticipant(Participants p) throws Exception{
        this.repository.deleteParticipant(repository.search(p));
    }

    public String getList(boolean wyw){
        String list="";

        Participants[] all=repository.getAll();

        for(Participants i:all){
            if(i.HadPresentedSomething()==wyw)
                list+=i.StringToString()+'\n';
        }
        if (list.equals(""))
            return "The list is empty! :(";
        return list;
    }

}
