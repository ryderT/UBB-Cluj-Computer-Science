class Game:
    def __init__(self,board):
        self._board=board

    def getUserInput(self):

        print(self._board)
        while self._board.isWon()==False and self._board.isLost()==False:
            try:
                print("Please enter the coordinates!")
                x=int(input("x:"))
                y=int(input("y:"))
                self._board.placePlayerPart(x,y)
                self._board.computerPart()
                print(self._board)
                if self._board.isWon()==True:
                    print("The player won!")
                    return
                if self._board.isLost()==True:
                    print("The Computer won!")
                    return
            except ValueError as er:
                print(er)
            except TypeError as er:
                print(er)

