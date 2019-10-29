from domain.graph import Graph
from domain.iterator import *

class UI:
    def __init__(self):
        pass

    def printMenu(self):

        print("1)Number of vertices")
        print("2)Verify if an edge exists")
        print("3)In degree of a vertex")
        print("4)Out degree of a vertex")
        print("5)Modify the cost of an edge")
        print("6)Add edge")
        print("7)Remove edge")
        print("8)Add a vertex")
        print("9)Remove a vertex")
        print("10)Print(in and out)")
        print("11)Parse in a vertex")
        print("12)Parse out a vertex")
        print("13)Parse the vertices")


    def getNrVertices(self, graph):
        return graph.getSize()

    def isEdge(self, graph, start, end):
        return graph.isEdge(start, end)

    def inDegree(self, graph, vertex):
        return graph.inDegree(vertex)

    def outDegree(self, graph, vertex):
        return graph.outDegree(vertex)

    def modifyCost(self, graph, start, end, newCost):
        graph.modifyCost(start, end, newCost)

    def addEdge(self, graph, start, end, cost):
        graph.addEdge(start, end, cost)

    def removeEdge(self, graph, start, end):
        graph.removeEdge(start, end)

    def addVertex(self, graph):
        graph.addVertex()

    def removeVertex(self, graph, vertex):
        graph.removeVertex(vertex)

    def printGraph(self, graph):
        return graph.toString()

    def printIn(self, graph, v):
        it = IteratorIn(graph, v)
        while (it.valid()):
            print(v, "<-", it.getCurrent())
            it.next()
            if len(graph.getListIn()[v]) == 0 and len(graph.getListOut()[v]) == 0:
                print("Isolated")

    def printOut(self, graph, v):
        it = IteratorOut(graph, v)
        while (it.valid()):
            print(v, "->", it.getCurrent())
            it.next()
            if len(graph.getListIn()[v]) == 0 and len(graph.getListOut()[v]) == 0:
                print("Isolated")

    def printVertices(self, graph):
        it = verticesIterator(graph)
        while it.valid():
            print(it.getCurrent())
            it.next()

    def readFile(self,fileName):
        with open(fileName, 'r') as f:
            line = f.readline().split()
            print(line)
            n = int(line[0])
            graph = Graph(n)

            # print(f.readlines())
            for line in f.readlines():
                line = line.split()
                graph.addEdge(int(line[0]), int(line[1]), int(line[2]))

            return graph

        return False



def main():

    ui = UI()
    ui.printMenu()
    command = -1
    graph = ui.readFile("graph1k.txt")

    while command != 0:

        print("Enter a command...")

        command = int(input())

        if command == 1:
            print(ui.getNrVertices(graph))

        elif command == 2:
            print("Start edge: ")
            start = int(input())

            print("End edge: ")
            end = int(input())

            if ui.isEdge(graph, start, end) == 1:
                print("It exists!")
            else:
                print("It doesn't exist!")

        elif command == 3:
            print("Give the vertex: ")
            vertex = int(input())
            print("The in degree is: ", ui.inDegree(graph, vertex))

        elif command == 4:
            print("Give the vertex: ")
            vertex = int(input())
            print("The out degree is: ", ui.outDegree(graph, vertex))

        elif command == 5:
            print("Start edge: ")
            start = int(input())

            print("End edge: ")
            end = int(input())

            print("New cost: ")
            newCost = int(input())
            ui.modifyCost(graph, start, end, newCost)

        elif command == 6:
            print("Start edge: ")
            start = int(input())

            print("End edge: ")
            end = int(input())

            print("Cost: ")
            cost = int(input())
            ui.addEdge(graph, start, end, cost)

        elif command == 7:
            print("Start edge: ")
            start = int(input())

            print("End edge: ")
            end = int(input())
            ui.removeEdge(graph, start, end)

        elif command == 8:
            ui.addVertex(graph)

        elif command == 9:
            print("Give the vertex: ")
            vertex = int(input())
            ui.removeVertex(graph, vertex)

        elif command == 10:
            print(ui.printGraph(graph))

        elif command == 11:
            print("Give the vertex: ")
            vertex = int(input())
            ui.printIn(graph, vertex)

        elif command == 12:
            print("Give the vertex: ")
            vertex = int(input())
            ui.printOut(graph, vertex)

        elif command == 13:
            ui.printVertices(graph)

        else:
            if command != 0:
                print("Invalid command!!")
        print("\n")
        ui.printMenu()
main()

