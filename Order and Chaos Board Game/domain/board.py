from texttable import *
from validators.boardvalidator import *
from random import choice

class Board:
    def __init__(self,validator):
        self._data=[6*[0],6*[0],6*[0],6*[0],6*[0],6*[0]]
        self._valid=validator
        self._positions=[6*[0],6*[0],6*[0],6*[0],6*[0],6*[0]]
        self._sw=0
        self._l=[]

    def getData(self,x,y):
        return self._data[x][y]

    def isWon(self):

        for i in range(0, 6):
            chaos = 0
            for j in range(0, 5):
                if self._data[i][j] == 2:
                    chaos += 1
            if chaos == 5:
                return True

        for i in range(0, 6):
            chaos = 0
            for j in range(1, 6):
                if self._data[i][j] == 2:
                    chaos += 1
            if chaos == 5:
                return True

        for i in range(0, 6):
            chaos = 0
            for j in range(0, 5):
                if self._data[j][i] == 2:
                    chaos += 1
            if chaos == 5:
                return True

        for i in range(0, 6):
            chaos = 0
            for j in range(1, 6):
                if self._data[j][i] == 2:
                    chaos += 1
            if chaos == 5:
                return True
        chaos = 0
        for i in range(0, 5):
            if self._data[i][i] == 2:
                chaos += 1
        if chaos == 5:
            return True
        chaos = 0

        for i in range(1, 6):
            if self._data[i][i] == 2:
                chaos += 1
        if chaos == 5:
            return True

        chaos = 0
        for i in range(0, 5):
            if self._data[i][i + 1] == 2:
                chaos += 1
        if chaos == 5:
            return True

        chaos = 0
        for i in range(1, 6):
            if self._data[i][i - 1] == 2:
                chaos += 1
        if chaos == 5:
            return True

        chaos = 0
        for i in range(0, 5):
            if self._data[i][5 - i] == 2:
                chaos += 1
        if chaos == 5:
            return True

        chaos = 0
        for i in range(1, 6):
            if self._data[i][5 - i] == 2:
                chaos += 1
        if chaos == 5:
            return True

        chaos = 0
        for i in range(0, 5):
            if self._data[i][5 - i - 1] == 2:
                chaos += 1
        if chaos == 5:
            return True

        chaos = 0
        for i in range(1, 6):
            if self._data[i][5 - i + 1] == 2:
                chaos += 1
        if chaos == 5:
            return True

        return False

    def isLost(self):
        counter=0
        for i in self._data:
            if 0 not in i:
                counter+=1
        if counter==6:
            return True
        for i in range(0,6):
            chaos=0
            for j in range(0,5):
                if self._data[i][j]==1:
                    chaos+=1
            if chaos==5:
                return True

        for i in range(0,6):
            chaos=0
            for j in range(1,6):
                if self._data[i][j]==1:
                    chaos+=1
            if chaos==5:
                return True

        for i in range(0,6):
            chaos=0
            for j in range(0,5):
                if self._data[j][i]==1:
                    chaos+=1
            if chaos==5:
                return True

        for i in range(0,6):
            chaos=0
            for j in range(1,6):
                if self._data[j][i]==1:
                    chaos+=1
            if chaos==5:
                return True
        chaos=0
        for i in range(0,5):
            if self._data[i][i]==1:
                chaos+=1
        if chaos==5:
            return True
        chaos = 0

        for i in range(1, 6):
            if self._data[i][i] == 1:
                chaos += 1
        if chaos == 5:
            return True

        chaos = 0
        for i in range(0, 5):
            if self._data[i][i+1] == 1:
                chaos += 1
        if chaos == 5:
            return True

        chaos = 0
        for i in range(1, 6):
            if self._data[i][i-1] == 1:
                chaos += 1
        if chaos == 5:
            return True

        chaos = 0
        for i in range(0, 5):
            if self._data[i][5-i] == 1:
                chaos += 1
        if chaos == 5:
            return True


        chaos = 0
        for i in range(1, 6):
            if self._data[i][5-i] == 1:
                chaos += 1
        if chaos == 5:
            return True

        chaos = 0
        for i in range(0, 5):
            if self._data[i][5-i-1] == 1:
                chaos += 1
        if chaos == 5:
            return True

        chaos = 0
        for i in range(1, 6):
            if self._data[i][5-i+1] == 1:
                chaos += 1
        if chaos == 5:
            return True

        return False

    def playerMayWin(self):
        """
        The functionality that allows the computer to check if the player might win if he is close to victory
        He checks the patterns and remembers the spots where a player might move
        If the computer is in danger he will counter the player by moving to complete the player's 5 in a row

        """

        self._sw = 0

        for i in range(0, 6):
            chaos = 0
            for j in range(0, 5):
                if self._data[i][j] == 2:
                    chaos += 1
                else:
                    x=i
                    y=j
            if chaos == 4 and self._sw==0 and [x,y] not in self._l:
                self._data[x][y]=1
                self._sw = 1
                self._l.append([x,y])



        for i in range(0, 6):
            chaos = 0
            for j in range(1, 6):
                if self._data[i][j] == 2:
                    chaos += 1
                else:
                    x=i
                    y=j
            if chaos == 4 and self._sw==0 and [x,y] not in self._l:
                self._data[x][y]=1
                self._sw = 1
                self._l.append([x,y])


        for i in range(0, 6):
            chaos = 0
            for j in range(0, 5):
                if self._data[j][i] == 2:
                    chaos += 1
                else:
                    x=j
                    y=i
            if chaos == 4 and self._sw==0 and [x,y] not in self._l:
                self._data[x][y]=1
                self._sw = 1
                self._l.append([x,y])


        for i in range(0, 6):
            chaos = 0
            for j in range(1, 6):
                if self._data[j][i] == 2:
                    chaos += 1
                else:
                    x=j
                    y=i
            if chaos == 4 and self._sw==0 and [x,y] not in self._l:
                self._data[x][y]=1
                self._sw = 1
                self._l.append([x,y])



        chaos = 0
        for i in range(0, 5):
            if self._data[i][i] == 2:
                chaos += 1
            else:
                x = i
                y = i
        if chaos == 4 and self._sw == 0 and [x, y] not in self._l:
            self._data[x][y] = 1
            self._sw = 1
            self._l.append([x, y])


        chaos = 0

        for i in range(1, 6):
            if self._data[i][i] == 2:
                chaos += 1
            else:
                x = i
                y = i
        if chaos == 4 and self._sw == 0 and [x, y] not in self._l:
            self._data[x][y] = 1
            self._sw = 1
            self._l.append([x, y])


        chaos = 0
        for i in range(0, 5):
            if self._data[i][i + 1] == 2:
                chaos += 1
            else:
                x = i
                y = i+1
        if chaos == 4 and self._sw == 0 and [x, y] not in self._l:
            self._data[x][y] = 1
            self._sw = 1
            self._l.append([x, y])


        chaos = 0
        for i in range(1, 6):
            if self._data[i][i - 1] == 2:
                chaos += 1
            else:
                x = i
                y = i-1
        if chaos == 4 and self._sw == 0 and [x, y] not in self._l:
            self._data[x][y] = 1
            self._sw = 1
            self._l.append([x, y])


        chaos = 0
        for i in range(0, 5):
            if self._data[i][5 - i] == 2:
                chaos += 1
            else:
                x = i
                y = 5-i
        if chaos == 4 and self._sw == 0 and [x, y] not in self._l:
            self._data[x][y] = 1
            self._sw = 1
            self._l.append([x, y])

        chaos = 0
        for i in range(1, 6):
            if self._data[i][5 - i] == 2:
                chaos += 1
            else:
                x = i
                y = 5-i
        if chaos == 4 and self._sw == 0 and [x, y] not in self._l:
            self._data[x][y] = 1
            self._sw = 1
            self._l.append([x, y])


        chaos = 0
        for i in range(0, 5):
            if self._data[i][5 - i - 1] == 2:
                chaos += 1
            else:
                x = i
                y = 5-i-1
        if chaos == 4 and self._sw == 0 and [x, y] not in self._l:
            self._data[x][y] = 1
            self._sw = 1
            self._l.append([x, y])


        chaos = 0
        for i in range(1, 6):
            if self._data[i][5 - i + 1] == 2:
                chaos += 1
            else:
                x = i
                y = 5-i+1
        if chaos == 4 and self._sw == 0 and [x, y] not in self._l:
            self._data[x][y] = 1
            self._sw = 1
            self._l.append([x, y])






    def computerPart(self):
        """
        The function that makes the computer check if player may win or not
        If not the computer places a "chaos" piece randomly
        Input:-
        Output: it modifies the data segment list
        """
        self._sw=0
        self.playerMayWin()
        if self._sw==0:
            found=False
            while found==False:
                x=choice([0,1,2,3,4,5])
                y=choice([0,1,2,3,4,5])

                if self._data[x][y]==0:
                    found=True
                    self._data[x][y]=1



    def placePlayerPart(self,x,y):

        self._valid.userInputValidator(x,y,self._data)

        self._data[x][y]=2





    def __str__(self):

        t=Texttable()
        lst=[]
        d = {
            0:" ",
            1:"X", # Chaos(Computer)
            2:"O"  # Order(player)
        }
        for i in range(0,6):
            lst=[]
            for j in range(0,6):
                lst.append(d[self._data[i][j]])
            t.add_row(lst)


        return t.draw()


"""
b=Board()
print(b)
"""