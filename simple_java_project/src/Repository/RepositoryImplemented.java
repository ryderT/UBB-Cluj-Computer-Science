package Repository;
import Model.Participants;


public class RepositoryImplemented implements Repository{

    private int size;
    private int capacity;
    private Participants[] participants;
    public RepositoryImplemented(int capacity) {
        this.size=0;
        this.capacity=capacity;
        this.participants=new Participants[capacity];
    }


    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public void addParticipant(Participants p) throws Exception{
        if(this.size==this.capacity)
            throw new Exception("The Repository is Full!");
        this.participants[size]=p;
        this.size+=1;
    }

    @Override
    public void deleteParticipant(int index) throws Exception{
        if(index<0 || index>=this.size)
            throw new Exception("This position does not exist!");
        this.participants[index]=this.participants[size-1];
        this.participants[size-1]=null;
        this.size-=1;
    }

    @Override
    public boolean isFull() {
        return this.size==this.capacity;
    }

    @Override
    public Participants[] getAll() {
        Participants[] copy =new Participants[this.size];
        System.arraycopy(this.participants,0,copy,0,this.size);
        return copy;
    }

    @Override
    public int search(Participants p) {
        int k=0;
        for(Participants c:this.participants) {
            if (c == p)
                return k;
            k++;
        }
        return -1;
    }
}
