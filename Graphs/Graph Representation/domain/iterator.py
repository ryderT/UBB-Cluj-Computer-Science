class verticesIterator:
    """
    This is the Iterator that parses all the vertices
    """
    def __init__(self, graph):
        self.__graph = graph
        self.__current = 0

    def valid(self):
        if self.__current < self.__graph.getSize():
            return True
        return False

    def getCurrent(self):
        if self.valid() == True:
            return self.__current
        return False

    def next(self):
        if not self.valid():
            return False
        self.__current += 1

    def first(self):
        self.__current = 0


class IteratorIn:
    """
    Iterator that parses the inbound
    """
    def __init__(self, graph, n):
        self.__graph = graph
        self.__node = n
        self.__index = 0

    def valid(self):
        if self.__index < len(self.__graph.getListIn()[self.__node]):
            return True
        return False

    def first(self):
        self.__index = 0

    def next(self):
        if self.valid() == True:
            self.__index += 1
        else:
            raise ValueError()

    def getCurrent(self):
        if self.valid() == True:
            return self.__graph.getListIn()[self.__node][self.__index]
        else:
            raise ValueError()


class IteratorOut:
    """
    Iterator that parses the outbound
    """
    def __init__(self, graph, n):
        self.__graph = graph
        self.__node = n
        self.__index = 0

    def valid(self):
        if self.__index < len(self.__graph.getListOut()[self.__node]):
            return True
        return False

    def first(self):
        self.__index = 0

    def next(self):
        if self.valid() == True:
            self.__index += 1
        else:
            raise ValueError()

    def getCurrent(self):
        if self.valid() == True:
            return self.__graph.getListOut()[self.__node][self.__index]
        else:
            raise ValueError()