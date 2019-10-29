


class Graph:
    """A directed graph, represented by adjacency matrix.
    Vertices are numbers from 0 to n-1"""

    def __init__(self, n):
        """Creates a graph with n vertices (numbered from 0 to n-1)
        and no edges"""
        self.nrVertices=n
        self._matr = []#[[0,1,0,0,1,0],[1,0,1,0,1,0],[0,1,0,1,0,0],[0,0,1,0,1,1],[1,1,0,1,0,0],[0,0,0,1,0,0]]
        for i in range(n):
            self._matr.append([])
            for j in range(n):
                self._matr[i].append(0)

    def parseX(self):
        """Returns a list containing all vertices"""
        l=[]
        for i in range(0,self.nrVertices):
            l.append(i)
        return l

    def addEdge(self, x, y):
        """Adds an edge from x to y.
        Precondition: there is no edge from x to y"""
        self._matr[x][y] = 1


    """
    function determines the neighbors of a given vertex
    in: vertex - vertex
    out: l - list of neighbours
    """
    def N(self,vertex):
        c = 0
        l = []
        for i in self._matr[vertex]:
            if i is 1 :
             l.append(c)
            c+=1
        return l
    """Bron-Kerbosch
    Iterates recursively to find cliques
    in: r - list of possible cliques
        p - list of all veritces
        x - list of nodes already in some clique
        l - result listt
    """

    def bronk(self,r,p,x,l):
        if len(p) == 0 and len(x) == 0:
            #print(r)
            l.append(r);
            return
        for vertex in p[:]:
            r_new = r[::]
            r_new.append(vertex)
            p_new = [val for val in p if val in self.N(vertex)] # p intersects N(vertex)
            x_new = [val for val in x if val in self.N(vertex)] # x intersects N(vertex)
            self.bronk(r_new,p_new,x_new,l)
            p.remove(vertex)
            x.append(vertex)
    """
    gets clique of max len
    in:
    out: prints clique of maxium length
    """
    def getMaxClique(self):
        l=[]
        lc=self.parseX()
        self.bronk([], lc, [],l)
        max=len(l[0])
        lmax=l[0]
        for i in range(1,len(l)):
            if max<len(l[i]):
                max=len(l[i])
                lmax=l[i]
        print(lmax)



#bronk([], [0,1,2,3,4,5], [])
g=Graph(6);
g.addEdge(0,1)
g.addEdge(1,2)
g.addEdge(0,2)

g.addEdge(2,3)
g.addEdge(1,3)
g.addEdge(0,3)
#g.bronk([], [0,1,2,3,4,5], [])
g.getMaxClique()