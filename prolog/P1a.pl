%10a)
%valley

%valley(List)
%flow model: (i)
valley([H1,H2|T]):-
    H1>H2,decrease([H1,H2|T]).
%decrease(list)
%flow model:(i)
decrease([H1,H2|T]):-
         H1>H2,decrease([H2|T]).
decrease([H1,H2|T]):-
         H1<H2,increase([H1,H2|T]).
%increase(List)
%flow model: (i)
increase([_]).
increase([H1,H2|T]):-
         H1<H2,increase([H2|T]).
 

         
     
        
