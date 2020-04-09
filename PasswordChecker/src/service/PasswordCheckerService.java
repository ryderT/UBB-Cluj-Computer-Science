package service;

import domain.Password;

public class PasswordCheckerService {
    //may replace with a repository
    private Password password;

    public PasswordCheckerService(Password pw){
        this.password=pw;
    }

    public Password getPassword() {
        return password;
    }

    public void setPassword(Password password) {
        this.password = password;
    }

    /**
     * Function that uses an algorithm that returns how many changes do we need to make for a password to be strong
     *
     * A strong password must:
     * - Have at least 6 characters and at most 20 characters
     * - Contain at least one lowercase letter, at least one uppercase letter and at least one digit
     * - NOT contain three repeating characters in a row("...aaa..")
     *
     * @return int changesRequired
     */
    public int check(){
        int changesRequired=0;
        int tripleChars=0;
        boolean hasDigit;
        boolean hasLowerCase;
        boolean hasUpperCase;
        //checking for lowercase letters
        if(!password.getContent().matches("(.*)[a-z](.*)")) {
            hasLowerCase=false;
        }
        else{
            hasLowerCase=true;
        }

        //checking for uppercase letters
        if(!password.getContent().matches("(.*)[A-Z](.*)")) {
            hasUpperCase=false;
        }
        else{
            hasUpperCase=true;
        }

        //checking for digits
        if(!password.getContent().matches("(.*)[0-9](.*)")) {
            hasDigit=false;
        }
        else{
            hasDigit=true;
        }

        //checking for tripe chars sequences like aaa

        char[] pass=password.getContent().toCharArray();

        for(int i=0; i<password.getContent().length()-2; i+=1){
            String test=Character.toString(pass[i])+ pass[i] + pass[i];
            String toTest=Character.toString(pass[i])+ pass[i + 1] + pass[i + 2];
            if (toTest.equals(test)){
                i+=2;
                tripleChars+=1;
            }
        }

        // now we handle the length cases:
        int currentLength=this.password.getLength();

        //shorter than 6 characters
        //Strategy: try to add as much characters as we can so we prioritise insertion
        //even if it has triple characters we can just insert between those, like aaa-> a0aBac

        if(currentLength<6){
            if(!hasDigit){
                //insert/replace digit
                if(currentLength<6)
                    currentLength+=1;
                changesRequired+=1;
            }
            if(!hasUpperCase){
                //insert/replace upper case letter
                if(currentLength<6)
                    currentLength+=1;
                changesRequired+=1;
            }
            if(!hasLowerCase){
                //insert/replace lower case letter
                if(currentLength<6)
                    currentLength+=1;
                changesRequired+=1;
            }
            changesRequired+=6-currentLength;
            return changesRequired;
        }

        //password length in parameters
        //Strategy: if there are triple sequence characters we first try to replace one of those with a missing uppercase/lowercase/digit
        //          if we don't have those, we will just replace those with some other character
        else if(currentLength<=20){
            if(!hasDigit){
                //insert/replace digit
                tripleChars-=1;
                changesRequired+=1;
            }
            if(!hasUpperCase){
                //insert/replace upper case letter
                tripleChars-=1;
                changesRequired+=1;
            }
            if(!hasLowerCase){
                //insert/replace lower case letter
                tripleChars-=1;
                changesRequired+=1;
            }
            if(tripleChars>0)
                changesRequired+=tripleChars;
            return changesRequired;
        }

        //length > 20
        //Strategy: delete triplets as much as we afford because we really need to get rid of characters
        //          if we reached a length of 20 we start replacing characters like we do when the length is in parameters

        else {
            if(!hasDigit){
                //replace digit
                tripleChars-=1;
                changesRequired+=1;
            }
            if(!hasUpperCase){
                //replace upper case letter
                tripleChars-=1;
                changesRequired+=1;
            }
            if(!hasLowerCase){
                //replace lower case letter
                tripleChars-=1;
                changesRequired+=1;
            }
            //if we can afford to delete characters we will first delete the triple groups
            //if we reach the desired length we change to replaceing them
            while(tripleChars>0){
                if (currentLength>22){
                    currentLength -= 3;
                    changesRequired += 3;
                }
                else if (currentLength>20){
                    currentLength -= currentLength-20;
                    changesRequired += currentLength-20;
                }
                else{
                    changesRequired+=1;
                }
                tripleChars-=1;
            }
            //if we still have a lot of characters
            if(currentLength>20)
                changesRequired+=currentLength-20;
            return changesRequired;
        }
    }

    @Override
    public String toString() {
        return "PasswordChecker for "+password.toString();
    }
}
