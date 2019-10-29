import math
class Graph:
    """A directed graph, represented by adjacency matrix.
    Vertices are numbers from 0 to n-1"""

    def __init__(self, n):
        """Creates a graph with n vertices (numbered from 0 to n-1)
        and no edges"""
        self._matr = []
        for i in range(n):
            self._matr.append([])
            for j in range(n):
                self._matr[i].append(False)

    def parseX(self):
        """Returns an iterable containing all the vertices"""
        nrOfVertices = len(self._matr)
        return range(nrOfVertices)

    def parseNout(self, x):
        """Returns an iterable containing the outbound neighbours of x"""
        list = []
        for i in range(len(self._matr[x])):
            if self._matr[x][i]:
                list.append(i)

        #print(list)
        return list

    def parseNin(self, x):
        """Returns an iterable containing the inbound neighbours of x"""
        list = []
        for i in range(len(self._matr)):
            if self._matr[i][x]:
                list.append(i)
        return list

    def isEdge(self, x, y):
        """Returns True if there is an edge from x to y, False otherwise"""
        return self._matr[x][y]

    def addEdge(self, x, y,val):
        """Adds an edge from x to y.
        Precondition: there is no edge from x to y"""
        self._matr[x][y] = val
    def cost(self,x,y):
        """
        returns the cost between x and y
        """
        return self._matr[x][y]

    def bellman(self, start, maxLen):
        """
        bellman-ford algorithm for getting the walk costs
        :param start: start vertex
        :param maxLen: the maximum length a walk can have
        :return: list of maps
        """
        initMap = {start:0}
        dist = [initMap]
        for k in range(1, maxLen + 1):
            prevMap = dist[k - 1]
            currMap = {}
            for y in prevMap:
                #minList={}
                #for x in self.parseNout(y):
                    #minList[x] = ""
                for x in self.parseNout(y):
                    #print(y,"->",x)
                    if x not in currMap or currMap[x] > prevMap[y] + self.cost(y, x):
                        #print(x)
                        currMap[x] = prevMap[y] + self.cost(y, x)
                        #currMap[y] = prevMap[x] + self.cost(y, x)

                      #  minList[x]+=str(y)+"->"+str(x)
                #print(minList)
            dist.append(currMap)
       # print (minList)
        return dist

    def minCost(self,start,end,maxLen):
        """
        minimum cost walk algorithm
        :param start: start vertex
        :param end:  end vertex
        :param maxLen: max length of walk
        :return: minimum walk from start to end
        """
        dist=self.bellman(start,maxLen)
        sw=0
        minim=math.inf
        for i in range(1,maxLen):
            if end in dist[i].keys():
                if sw==0:
                    sw=1
                    minim=dist[i][end]
                    #print(minim)
                elif sw == 1 and minim > dist[i][end]:
                    minim = dist[i][end]
                    #print(minim)

        return minim

    def getWalk(self,start,end,maxlen):
        minimCost=self.minCost(start,end,maxlen)




g=Graph(5)
g.addEdge(0,1,1)
#g.addEdge(0,2,-5)
g.addEdge(0,2,50)
g.addEdge(1,2,2)
g.addEdge(0,4,-5)
#g.addEdge(3,2,-6)
x=g.minCost(0,2,3)
print(g.bellman(0,3))
if(x<0):
    print("Negative cost walk!")
else:
    print("The minimum cost: ",x)
