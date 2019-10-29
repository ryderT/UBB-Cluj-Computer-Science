class Graph:
    def __init__(self, n):
        """
        It creates the graph
        n- size of graph - size
        listIn- inbound list
        listOut-outbound list
        edges-dict of costs(keys are tuples)
        """
        self.__listIn = [[] for i in range(n)]
        self.__listOut = [[] for i in range(n)]
        self.__size = n
        self.__edges = {}
        for i in range(0, n):
            self.__listIn[i] = []
            self.__listOut[i] = []



    def getSize(self):
        """
        Returns the size of the graph
        """
        return self.__size

    def addEdge(self, start, end, cost):
        """
        Adds a new edge to the graph by adding the start and end to the outbound and inbound lists
        and the cost to the dictionary(edges)
        """
        self.__listIn[end].append(start)
        self.__listOut[start].append(end)
        self.__edges[(start, end)] = cost

    def removeEdge(self, start, end):
        """
        Removes and Edge from the graph
        by deleteing the cost,inbound value and outbound value
        """
        self.__edges.pop((start, end))
        self.__listOut[start].remove(end)
        self.__listIn[end].remove(start)

    def addVertex(self):
        """
        The size of the graph increases by 1
        An empty set of values is added to the inbound/outbound list
        """
        self.__size += 1
        self.__listIn.append([])
        self.__listOut.append([])

    def removeVertex(self, vertex):
        """
        Removes a vertex(vertex) by deleting all it's edges and cost
        """
        self.__listIn[vertex] = []
        self.__listOut[vertex] = []

        new_edges = {}
        for i in self.__edges.keys():
            if i[0] != vertex and i[1] != vertex:
                new_edges[i] = self.__edges[i]

        self.__edges = new_edges

        for nod in range(self.__size - 1):
            if vertex in self.__listIn[nod]:
                self.__listIn[nod].remove(vertex)

            if vertex in self.__listOut[nod]:
                self.__listOut[nod].remove(vertex)

        if vertex == self.__size - 1:
            self.__size = self.__size - 1
        else:
            n = self.__size - 1
            for nod in range(self.__size):
                if n in self.__listIn[nod]:
                    self.__listIn[nod].remove(n)
                    self.__listIn[nod].append(vertex)

                if n in self.__listOut[nod]:
                    self.__listOut[nod].remove(n)
                    self.__listOut[nod].append(vertex)

            for i in range(len(self.__listIn[n])):
                if self.__listIn[n][i] != vertex:
                    self.__listIn[vertex].append(self.__listIn[n][i])

            for i in range(len(self.__listOut[n])):
                if self.__listOut[n][i] != vertex:
                    self.__listOut[vertex].append(self.__listOut[n][i])

            self.__size = self.__size - 1

    def isEdge(self, start, end):
        """
        Check if an edge exists
        Returns 1 if the edge exists
        Returns 0 otherwise
        """
        if (start, end) in self.__edges.keys():
            return 1
        return 0

    def inDegree(self, vertex):
        """
        Returns the in degree of a vertex
        """
        return len(self.__listIn[vertex])

    def outDegree(self, vertex):
        """
        Returns the out degree of a vertex
        """
        return len(self.__listOut[vertex])

    def modifyCost(self, start, end, newCost):
        """
        Modifies the cost of an existing edge
        newCost - the new value that will be added to the list of costs
        """

        if (start, end) not in self.__edges.keys():
            return -1
        self.__edges[(start, end)] = newCost
        return 1

    def toString(self):
        """
        Function used for printing the graph
        by printing it's edges
        """
        s1 = "List out: \n"
        for vertice in range(0, self.__size):
            s1 += str(vertice) + ": "
            for v in self.__listOut[vertice]:
                s1 += str(v) + " "
            s1 += "\n"

        s2 = "\nList in: \n"
        for vertice in range(0, self.__size):
            s2 += str(vertice) + ": "
            for v in self.__listIn[vertice]:
                s2 += str(v) + " "
            s2 += "\n"

        return s1 + s2

    def copyGraph(self):
        """
        Function that returns a copy of the graph
        """
        aux = Graph(self.__size)
        aux.setListIn(self.__listIn)
        aux.setListOut(self.__listOut)
        aux.setEdges(self.__edges)

        return aux

    def setListIn(self, l):
        self.__listIn = l

    def setListOut(self, l):
        self.__listOut = l

    def setEdges(self, l):
        self.__edges = l

    def getListIn(self):
        return self.__listIn

    def getListOut(self):
        return self.__listOut

    def getEdges(self):
        return self.__edges


