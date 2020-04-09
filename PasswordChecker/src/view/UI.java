package view;

import java.util.Scanner;

import domain.Password;
import service.PasswordCheckerService;

public class UI {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.println("A password is considered strong if below conditions are all met:");
        System.out.println("1. It has at least 6 characters and at most 20 characters.");
        System.out.println("2. It must contain at least one lowercase letter, at least one uppercase letter, and at least one digit.");
        System.out.println("3. It must NOT contain three repeating characters in a row (...aaa... is weak, but ...aa...a... is strong, assuming other conditions are met).");
        System.out.println("Insertion, deletion or replace of any one character are all considered as one change.");
        System.out.println("\n(input 0 for exit)\n");
        while(true){

            System.out.println("Insert password:");
            String input=scanner.nextLine();
            if(input.equals("0"))
                return;
            Password password=new Password(input);
            PasswordCheckerService passwordChecker=new PasswordCheckerService(password);
            System.out.println("Password needs "+ passwordChecker.check() +" changes!");
            if(passwordChecker.check()==0)
                System.out.println("Your password is Strong!!!");
        }
    }
}
