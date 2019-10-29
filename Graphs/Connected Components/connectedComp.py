class Graph:
    def __init__(self,nrVertices):
        """
        Graph class for undirected graph
        nrVertices - size of graph
        matrix - where the vertices will be stored
        """
        self._nrVertices = nrVertices
        self._matrix = [[] for i in range(nrVertices)]

    def getNrVertices(self):
        return self._nrVertices

    def addEdge(self,v1,v2):
        """
        Adds edge between the 2 vertexes in the matrix
        """
        self._matrix[v1].append(v2)
        self._matrix[v2].append(v1)

    def parseVertex(self, v):
        """Returns an iterable containing the outbound neighbours of vertex v"""
        list = []
        for i in range(len(self._matrix[v])):
            if self._matrix[v][i]:
                list.append(i)
        return list

    def DFSRec(self, v, visitList,componentList):

        """
        Recursive function for depth-first parse
        v-start vertex
        visitList-list to check if we already parsed the vertex
        componentList- list to add the connected components
        """
        visitList[v] = True

        componentList.append(v)

        for i in self._matrix[v]:
            if visitList[i] == False:
                componentList = self.DFSRec(i, visitList,componentList)
        return componentList

    def getConnectedComponents(self):
        """
        Function for getting the connected components
        by using a visitList

        """
        visitList = [False for i in range(self._nrVertices)]
        connectedComponentsList=[]

        for i in range(self._nrVertices):
            if visitList[i]==False:
                componentList=[]
                connectedComponentsList.append(self.DFSRec(i,visitList,componentList))

        return connectedComponentsList


def main():
    g = Graph(6);
    #g.addEdge(1, 0)
    #g.addEdge(2, 3)
    #g.addEdge(3, 4)

    cc = g.getConnectedComponents()
    print("The connected components are:")
    for i in cc:
        print (i)
    #assert(cc==[[0,1],[2,3,4],[5]])
    g.addEdge(1,2)
    cc=g.getConnectedComponents()
    #assert(cc==[[0,1,2,3,4],[5]])


main()