package View;
import Repository.RepositoryImplemented;
import Controller.Controller;
import Model.*;

import javax.naming.ldap.Control;

public class View {
    public static void main(String[] args) {
        Professor p=new Professor("Craciun","Florin",false);
        Specialist spec=new Specialist("Andrei","Mihai",false);
        Student st=new Student("Tudor","Ardelean",false);
        RepositoryImplemented repo = new RepositoryImplemented(20);
        Controller cont = new Controller(repo);
        try{
            cont.addParticipant(p);
            cont.addParticipant(spec);
            cont.addParticipant(st);
            //Professor p1=new Professor("Craciasdaun","Florasdin",true);
            //cont.deleteParticipant(p1);

        }catch(Exception e){
            System.out.println(e);
        }
        System.out.println(cont.getList(true));
    }
}


