from domain.board import Board
from validators.boardvalidator import BoardValidator



def computerMoveTest():
    v = BoardValidator()
    b = Board(v)




    b.placePlayerPart(0, 0)
    b.placePlayerPart(0, 1)
    b.placePlayerPart(0, 2)
    b.placePlayerPart(0, 3)
    b.computerPart()

    assert b.getData(0, 4) == 1

    b.placePlayerPart(1, 0)
    b.placePlayerPart(1, 1)
    b.placePlayerPart(1, 2)
    b.placePlayerPart(1, 3)
    b.computerPart()
    assert b.getData(1, 4) == 1

    b.computerPart()

    s=0
    for i in range(0, 6):
        for j in range(0, 6):
            s=s+b.getData(i, j)
    assert s == 8*2+3


computerMoveTest()