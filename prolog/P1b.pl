%Alternate sum for list elements
%10b


% altSum(L:list, S:int)
% flow model: (i o), (i i)
% Rez = l1 - l2 + l3 - ... + (-1)^(n+1)*ln
altSum(List,Rez):-
    altSum(List,1,Rez).

altSum([],_,0).
altSum([H|T],Sign,Rez):-
    AuxSign is Sign * (-1),
    altSum(T,AuxSign,AuxRez),
    Rez is Sign * H + AuxRez.