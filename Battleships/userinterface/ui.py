from domain.board import Board
from domain.gameerrror import GameError

class Game:
    def __init__(self):
        self._board=Board()

    def placeBattleship(self):
        """
        This is where the player will introduce the value of the coordinates for ships

        """
        d={"A":0,
           "B": 1,
           "C": 2,
           "D": 3,
           "E": 4,
           "F": 5,
           "G": 6,
           "H": 7,
           }

        print("Place your battleship start!")
        x1=int(input("Enter the X coordinate(0-7):"))
        y1=input("Enter the Y coordinate(A-H):")

        print("Place your battleship stop!")
        x2 = int(input("Enter the X coordinate(0-7):"))
        y2 = input("Enter the Y coordinate(A-H):")

        if x1<0 or x1>7:
            raise GameError("Invalid coordinates!")
        if d[y1]<0 or d[y1]>7:
            raise GameError("Invalid coordinates!")
        if x2<0 or x2>7:
            raise GameError("Invalid coordinates!")
        if d[y2]<0 or d[y2]>7:
            raise GameError("Invalid coordinates!")
        if y1 not in d.keys():
            raise GameError("Invalid coordiantes!")
        if y2 not in d.keys():
            raise GameError("Invalid coordinates!")
        print(x1, d[y1], x2, d[y2])
        if (x2-x1!=3 or d[y2]-d[y1]!=0) and (x2-x1!=0 or d[y2]-d[y1]!=3):
            raise GameError("Invalid size!")


        print(x1,d[y1],x2,d[y2])
        self._board.playerBattleship(x1,d[y1],x2,d[y2])
        return False




    def placeCruiser(self):
        """
        This is where the player will introduce the value of the coordinates for ships

        """
        d = {"A": 0,
             "B": 1,
             "C": 2,
             "D": 3,
             "E": 4,
             "F": 5,
             "G": 6,
             "H": 7,
             }

        print("Place your Cruiser start!")
        x1 = int(input("Enter the X coordinate(0-7):"))
        y1 = input("Enter the Y coordinate(A-H):")

        print("Place your Cruiser stop!")
        x2 = int(input("Enter the X coordinate(0-7):"))
        y2 = input("Enter the Y coordinate(A-H):")
        if y1 not in d.keys():
            raise GameError("Invalid coordiantes!")
        if y2 not in d.keys():
            raise GameError("Invalid coordinates!")
        if x1 < 0 or x1 > 7:
            raise GameError("Invalid coordinates!")
        if d[y1] < 0 or d[y1] > 7:
            raise GameError("Invalid coordinates!")
        if x2 < 0 or x2 > 7:
            raise GameError("Invalid coordinates!")
        if d[y2] < 0 or d[y2] > 7:
            raise GameError("Invalid coordinates!")
        if (x2 - x1 != 2 or d[y2] - d[y1] != 0) and (x2 - x1 != 0 or d[y2] - d[y1] != 2) :
            raise GameError("Invalid size!")

        self._board.playerCruiser(x1, d[y1], x2, d[y2])
        return False

    def placeDesstroyer(self):
        """
        This is where the player will introduce the value of the coordinates for ships

        """
        d = {"A": 0,
             "B": 1,
             "C": 2,
             "D": 3,
             "E": 4,
             "F": 5,
             "G": 6,
             "H": 7,
             }

        print("Place your Destroyer start!")
        x1 = int(input("Enter the X coordinate(0-7):"))
        y1 = input("Enter the Y coordinate(A-H):")

        print("Place your Destroyer stop!")
        x2 = int(input("Enter the X coordinate(0-7):"))
        y2 = input("Enter the Y coordinate(A-H):")
        if y1 not in d.keys():
            raise GameError("Invalid coordiantes!")
        if y2 not in d.keys():
            raise GameError("Invalid coordinates!")
        if x1 < 0 or x1 > 7:
            raise GameError("Invalid coordinates!")
        if d[y1] < 0 or d[y1] > 7:
            raise GameError("Invalid coordinates!")
        if x2 < 0 or x2 > 7:
            raise GameError("Invalid coordinates!")
        if d[y2] < 0 or d[y2] > 7:
            raise GameError("Invalid coordinates!")
        if (x2 - x1 != 0 or d[y2] - d[y1] != 1 )and( x2 - x1 != 1 or d[y2] - d[y1] != 0):
            raise GameError("Invalid size!")

        self._board.playerDestroyer(x1, d[y1], x2, d[y2])
        return False

    def placement(self):
        x=True
        while x==True:
            try:
                self.placeBattleship()
                x=False
            except GameError as er:
                print(er)
            except ValueError as er:
                print(er)
            except KeyError as er:
                print(er)
        x = True
        while x == True:
            try:
                self.placeCruiser()
                x = False
            except GameError as er:
                print(er)
            except ValueError as er:
                print(er)
            except KeyError as er:
                print(er)
        x = True
        while x == True:
            try:
                self.placeDesstroyer()
                x = False
            except GameError as er:
                print(er)
            except ValueError as er:
                print(er)
            except KeyError as er:
                print(er)

    def start(self):

        self.placement()
        self._board.computerPlacement()
        d = {"A": 0,
             "B": 1,
             "C": 2,
             "D": 3,
             "E": 4,
             "F": 5,
             "G": 6,
             "H": 7,
             }
        #print(self._board)
        while True:
            try:
                print(self._board)
                #the player hits
                print("Choose the coordinates to hit!")
                x=int(input("Hit X:"))
                y=input("Hit Y:")
                if y not in d.keys():
                    raise GameError("Invalid coordinates!")
                if x<0 or x>7:
                    raise GameError("Invalid coordinates!")
                if d[y]<0 or d[y]>7:
                    raise GameError("Invalid coordinates!")

                self._board.hitComputer(x,d[y])

                #the computer hits
                self._board.computerAttack()

                if self._board.isWon() !=False:
                    self.board.isWon()
                    return
                if self._board.isLost() !=False:
                    self.board.isLost()
                    return


            except GameError as er:
                print(er)
            except ValueError as er:
                print(er)
            except KeyError as er:
                print(er)






print("This is the board:")
board=Board()
print(board)
game=Game()
game.start()


