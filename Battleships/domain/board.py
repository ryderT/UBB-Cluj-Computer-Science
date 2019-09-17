from texttable import *
from domain.gameerrror import GameError
import random

class Board():
    """
    0 - empty square
    1 - ship part
    2 - hit part

    ships:
    1 battleship - 4 squares
    1 cruiser - 3 squares
    1 destroyer - 2 squares
    """
    def __init__(self):
        self._data1= [[0]*8, [0]*8 ,[0]*8 ,[0]*8 ,[0]*8 ,[0]*8 ,[0]*8 ,[0]*8]
        self._data2= [[0]*8, [0]*8 ,[0]*8 ,[0]*8 ,[0]*8 ,[0]*8 ,[0]*8 ,[0]*8]
        self.hitlist=self._data1
    def getPlayer(self):
        return self._data1

    def getPC(self):
        return self._data22

    def placeBattleship(self,starti,startj,stopi,stopj):
        if starti==stopi:
            for i in range(0,4):
                self.placeShipPartComputer(starti,startj+i)
        elif startj==stopj:
            for i in range(0,4):
                self.placeShipPartComputer(starti+i,startj)

    def placeCruiser(self,starti,startj,stopi,stopj):
        if starti==stopi:
            for i in range(0,3):
                self.placeShipPartComputer(starti,startj+i)
        elif startj==stopj:
            for i in range(0,3):
                self.placeShipPartComputer(starti+i,startj)

    def placeDestroyer(self,starti,startj,stopi,stopj):
        if starti==stopi:
            for i in range(0,2):
                self.placeShipPartComputer(starti,startj+i)
        elif startj==stopj:
            for i in range(0,2):
                self.placeShipPartComputer(starti+i,startj)
    def playerBattleship(self,starti,startj,stopi,stopj):
        if starti==stopi:
            for i in range(0,4):
                self.placeShipPartPlayer(starti,startj+i)
        elif startj==stopj:
            for i in range(0,4):
                self.placeShipPartPlayer(starti+i,startj)


    def playerCruiser(self,starti,startj,stopi,stopj):
        if starti==stopi:
            for i in range(0,3):
                self.placeShipPartPlayer(starti,startj+i)
        elif startj==stopj:
            for i in range(0,3):
                self.placeShipPartPlayer(starti+i,startj)

    def playerDestroyer(self,starti,startj,stopi,stopj):
        if starti==stopi:
            for i in range(0,2):
                self.placeShipPartPlayer(starti,startj+i)
        elif startj==stopj:
            for i in range(0,2):
                self.placeShipPartPlayer(starti+i,startj)
    def computerAttack(self):
        li = [0, 1, 2, 3, 4, 5, 6, 7]
        lj = [0, 1, 2, 3, 4, 5, 6, 7]
        valid=[]
        Valid=False
        while Valid==False:
            x=random.choice(li)
            y=random.choice(lj)
            if [x,y] not in valid:
                Valid=True
                valid.append([x,y])
                self.hitPlayer(x,y)

    def computerAttack1(self):
        """
        2- hit part
        :return:
        """
        hits=0
        li=[0,1,2,3,4,5,6,7]
        lj = [0, 1, 2, 3, 4, 5, 6, 7]
        for i in range(0,8):
            for j in range(0,8):
                if self._data2[i][j]==2:
                    hits+=1
                    if i!= 0 and j!=0 and i!=7 and j!=7:
                        for k in range(i-1,i+1):
                            for m in range(j-1,j+1):
                                if self._data2[k][m] != 2:
                                    self.hitPlayer(k,m)
                                    for i in range(len(li)):
                                        if li[i] == k:
                                            li.pop(i)
                                            break
                                    for i in range(len(lj)):
                                        if lj[i]==m:
                                            lj.pop(i)
                                            break
                                    break
                    else:
                        x=random.choice(li)
                        y=random.choice(lj)
                        for i in range(len(li)):
                            if li[i] == x:
                                li.pop(i)
                                break
                        for i in range(len(lj)):
                            if lj[i] == y:
                                lj.pop(i)
                                break
                        self.hitPlayer(x,y)
        if hits==0:
            x = random.choice(li)
            y = random.choice(lj)
            for i in range(li):
                if li[i] == x:
                    li.pop(i)
                    break
            for i in range(lj):
                if lj[i] == y:
                    lj.pop(i)
                    break
            self.hitPlayer(x, y)






    def computerPlacement(self):

        """
        Battleship
        """

        starti=random.choice([0,1,2,3,4,5,6,7])

        if starti<5:

            if starti+3>4:
                startj = random.choice([0, 1, 2, 3, 4, 5, 6, 7])
                stopi=starti+3
                stopj=startj
                print(1)
            else:
                stopi=random.choice([starti+3,starti])
                if stopi==starti:
                    print(2)
                    startj = random.choice([0, 1, 2, 3])
                    stopj=startj+3
                else:
                    startj = random.choice([0, 1, 2, 3, 4, 5, 6, 7])
                    print(3)
                    stopj=startj



        else:
            startj = random.choice([0, 1, 2, 3])
            stopi=starti
            stopj=stopi+3

        self.placeBattleship(starti,startj,stopi,stopj)
        a,b,c,d=starti, startj, stopi, stopj


        """
        Cruiser
        """
        Valid=False
        while Valid==False:
            starti = random.choice([0, 1, 2, 3, 4, 5, 6, 7])

            if starti < 6:

                if starti + 2 > 5:
                    startj = random.choice([0, 1, 2, 3, 4, 5, 6, 7])
                    stopi = starti + 2
                    stopj = startj
                    #print(1)
                else:
                    stopi = random.choice([starti + 2, starti])
                    if stopi == starti:
                        #print(2)
                        startj = random.choice([0, 1, 2, 3,4])
                        stopj = startj + 2
                    else:
                        startj = random.choice([0, 1, 2, 3, 4, 5, 6, 7])
                        #print(3)
                        stopj = startj

            else:
                startj = random.choice([0, 1, 2, 3,4])
                stopi = starti
                stopj = stopi + 2

            Valid=True
            li=[]
            lj=[]
            for i in range(starti,stopi+1):
                li.append(i)
            for j in range(startj,stopj+1):
                lj.append(j)

            for i in range(a,c+1):
                for j in range(b,d+1):
                    if j in lj and i in li:
                        Valid=False
                        break

        self.placeCruiser(starti,startj,stopi,stopj)
        x1, x2, x3, x4 = starti, startj, stopi, stopj

        """
        Destroyer
        """
        print(li,lj)
        Valid = False
        while Valid == False:
            starti = random.choice([0, 1, 2, 3, 4, 5, 6, 7])

            if starti < 7:

                if starti + 1 > 6:
                    startj = random.choice([0, 1, 2, 3, 4, 5, 6, 7])
                    stopi = starti + 1
                    stopj = startj
                    # print(1)
                else:
                    stopi = random.choice([starti + 1, starti])
                    if stopi == starti:
                        # print(2)
                        startj = random.choice([0, 1, 2, 3, 4,5])
                        stopj = startj + 1
                    else:
                        startj = random.choice([0, 1, 2, 3, 4, 5, 6, 7])
                        # print(3)
                        stopj = startj

            else:
                startj = random.choice([0, 1, 2, 3, 4,5])
                stopi = starti
                stopj = stopi + 1

            Valid = True
            li2=[]
            lj2=[]

            for i in range(starti, stopi + 1):
                li2.append(i)
            for j in range(startj, stopj + 1):
                lj2.append(j)

            for i in range(a, c + 1):
                for j in range(b, d + 1):
                    if j in lj2 and i in li2:
                        Valid = False
                        break
            li = []
            lj = []
            for i in range(starti, stopi + 1):
                li.append(i)
            for j in range(startj, stopj + 1):
                lj.append(j)

            for i in range(x1, x3 + 1):
                for j in range(x2, x4 + 1):
                    if j in lj and i in li:
                        Valid = False
                        break
        self.placeDestroyer(starti, startj, stopi, stopj)


    def playerPlacement(self):
        pass


    def placeShipPartComputer(self,x,y):

        if x<0 or x>7 or y<0 or y>7:
            raise GameError("Coordinates not valid!")

        self._data2[x][y]=1

    def placeShipPartPlayer(self,x,y):

        if x<0 or x>7 or y<0 or y>7:
            raise GameError("Coordinates not valid!")
        if self._data1[x][y]==6:
            raise GameError("There's already something there!")
        else:
            self._data1[x][y]=6
    def hitComputer(self,x,y):

        if x<0 or x>7 or y<0 or y>7:
            raise GameError("Coordinates not valid!")

        if self._data2[x][y]==1:
            self._data2[x][y]=2
        elif self._data2[x][y]==3 or self._data2[x][y]==2:
            raise GameError("Part already hit!")
        else:
            self._data2[x][y]=3

    def hitPlayer(self,x,y):

        if x<0 or x>7 or y<0 or y>7:
            raise GameError("Coordinates not valid!")

        if self._data1[x][y]==6:
            self._data1[x][y]=2
        else:
            self._data1[x][y]=3

    def isWon(self):
        d=self._data2
        for i in range(0,8):
            if 1 in d[i][:]:
                return False
        raise GameError("You won!")

    def isLost(self):
        d=self._data1
        for i in range(0,8):
            if 1 in d[i][:]:
                return False
        raise GameError("You lost!")

    def __str__(self):

        k=0
        t=Texttable()
        d={0:" ",1:" ",2:"X",3:"O",6:"+"}

        for i in range(0,8):
            lst = self._data1[i][:]+self._data2[i][:]

            for j in range(0,8):
                lst[j]=d[lst[j]]

            for j in range(8, 16):
                lst[j] = d[lst[j]]

            lst.insert(8, "||||")
            lst.insert(0,k)
            lst.insert(10,k)
            k+=1
            t.add_row(lst)

        c="A"
        header=[" "]

        for i in range(0,8):
            header.append(c)
            c = chr(ord(c) + 1)

        c="A"
        header.append("||||")
        header.append(" ")

        for i in range(0,8):
            header.append(c)
            c = chr(ord(c) + 1)

        t.header(header)
        return t.draw()
"""

b1=Board()
b1.placeShipPartPlayer(0,5)
"""
"""
d
b1.placeShipPartComputer(0,5)
b1.hitComputer(0,5)
"""
"""
b1.hitPlayer(0,5)
b1.hitPlayer(0,6)
b1.computerPlacement()
print(b1)
"""
