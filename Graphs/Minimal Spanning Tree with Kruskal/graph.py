
class Graph:
    def __init__(self, nrVertices):
        self.nrVertices = nrVertices
        self.graph = []

    def addEdge(self, u, v, w):
        self.graph.append([u, v, w])

    """
    in: parent - list of nodes
        i - current node
    out: if the current node is the parent return it
         else return find(parent,current node)
    """
    def find(self, parent, i):
        if parent[i] == i:
            return i
        return self.find(parent, parent[i])


    """
    unites the the edges
    in:parent - list of vertices
       rank - list of ranks
       x - vertice 1
       y - vertice 2
    out:
    """
    def union(self, parent, rank, x, y):
        xroot = self.find(parent, x)
        yroot = self.find(parent, y)
        if rank[xroot] < rank[yroot]:
            parent[xroot] = yroot
        elif rank[xroot] > rank[yroot]:
            parent[yroot] = xroot
        else:
            parent[yroot] = xroot
            rank[xroot] += 1


    """
    in:
    sorts the edges by weight
    
    out: prints the edges of the constructed spanning tree
    """
    def KruskalMST(self):
        result = []
        i = 0
        e = 0
        self.graph = sorted(self.graph, key=lambda item: item[2])
        parent = []
        rank = []

        for node in range(self.nrVertices):
            parent.append(node)
            rank.append(0)

        while e < self.nrVertices - 1:
            u, v, w = self.graph[i]
            i = i + 1
            x = self.find(parent, u)
            y = self.find(parent, v)
            if x != y:
                e = e + 1
                result.append([u, v, w])
                self.union(parent, rank, x, y)
        print("Edges in the constructed Minimal Spanning Tree")
        for u, v, weight in result:
            print("start:"+str(u)+" stop:" +str(v)+" weight:"+str( weight))

"""
g = Graph(9)
g.addEdge(0,1,4)
g.addEdge(1,2,8)
g.addEdge(2,3,7)
g.addEdge(3,4,9)
g.addEdge(4,5,10)
g.addEdge(5,6,2)
g.addEdge(6,7,1)
g.addEdge(7,0,8)
g.addEdge(1,7,11)
g.addEdge(7,8,7)
g.addEdge(6,8,6)
g.addEdge(2,8,2)
g.addEdge(2,5,4)
g.addEdge(3,5,14)
"""
g = Graph(4)
g.addEdge(0, 1, 10)
g.addEdge(0, 2, 6)
g.addEdge(0, 3, 5)
g.addEdge(1, 3, 15)
g.addEdge(2, 3, 4)
g.KruskalMST()
