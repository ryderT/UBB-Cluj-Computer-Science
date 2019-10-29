%P2 11a)
%Replace occurence

%replace(list,number,number,list)
%flow model:(i,i,i,o)

replace([],_,_,[]).
replace([H1|T1],E1,E2,[E2|T2]):-
    H1=:=E1,
    !,
    replace(T1,E1,E2,T2).
replace([H|T1],E1,E2,[H|T2]):-
    replace(T1,E1,E2,T2).

