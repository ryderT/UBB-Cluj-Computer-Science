%P2 11a)
%Replace occurence

%replace(list,number,number,list)
%flow model:(i,i,i,o)

replace([],_,_,[]).
replace([H1|T1],E1,E2,[E2|T2]):-
    H1 is E1,
    !,
    replace(T1,E1,E2,T2).
replace([H|T1],E1,E2,[H|T2]):-
    replace(T1,E1,E2,T2).

%b)
maxNumber(A, B, R) :- A>B,!,R is A.
maxNumber(A, B, R) :- A<B,R is B.


maxList([H], H).
maxList([H|T], R) :- number(H), !,
    maxList(T, RM),
    maxNumber(H, RM,R).
maxList([_|T], R) :- 
    maxList(T, R).

heterList([], _, []).
heterList([H|T], M, [HR|R]) :- is_list(H), !,
    maxList(H, RM),
    replace(H, M, RM, HR),
    heterList(T, M, R).
heterList([H|T], M, [H|R]) :-
    heterList(T, M, R).
main(L, R) :- 
    maxList(L, RM),
    heterList(L, RM, R).
    