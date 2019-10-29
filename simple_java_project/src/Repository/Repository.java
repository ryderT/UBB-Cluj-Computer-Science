package Repository;
import Model.Participants;
public interface Repository {
    public int getSize();
    public void addParticipant(Participants p)throws Exception;
    public void deleteParticipant(int index)throws Exception;
    public boolean isFull();
    public Participants[] getAll();
    //Returns participant position in repo or -1 if it does not exist
    int search(Participants p);
}
