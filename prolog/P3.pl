% for a given n, positive, determine all decomposition of n as a sum of consecutive natural numbers.

getLists(N, E, R) :- E < N,
    getSol(N, E, R).
getLists(N, E, R) :- E < N,
    NE is E + 1,
    getLists(N, NE, R).

getSol(0, _, []).
getSol(N, E, [E|R]) :- N >= E,
    NN is N - E,
    NE is E + 1,
    getSol(NN, NE, R).

main(N, R) :-
    findall(RPartial, getLists(N, 1, RPartial), R).