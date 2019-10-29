class Bag:

    # creates a new, empty Bag
    def __init__(self):  # theta(1)
        self.__list = []
        self.__frequency = []

    # adds a new element to the Bag
    def add(self, e):  # O(number of unique elements)
        if e in self.__list:
            position = self.__list.index(e)
            self.__frequency[position] += 1
        else:
            self.__list.append(e)
            self.__frequency.append(1)

    # removes one occurrence of an element from a Bag
    # returns True if an element was actually removed (the Bag contained the element e), or False if nothing was removed
    def remove(self, e):  # O(number of unique elements)
        if e in self.__list:
            position = self.__list.index(e)
            if self.__frequency[position] == 1:
                del self.__list[position]
                del self.__frequency[position]
            else:
                self.__frequency[position] -= 1
            return True
        else:
            return False

    # searches for an element e in the Bag
    # returns True if the Bag contains the element, False otherwise
    def search(self, e):  # O(number of unique elements)
        if e in self.__list:
            return True
        else:
            return False

    # counts and returns the number of times the element e appears in the bag
    def nrOccurrences(self, e):  # O(number of unique elements)
        if e in self.__list:
            position = self.__list.index(e)
            return self.__frequency[position]
        else:
            return 0

    # returns the size of the Bag (the number of elements)
    def size(self):  # theta(number of unique elements)
        nr_of_elements = 0
        for i in range(len(self.__list)):
            nr_of_elements += self.__frequency[i]
        return nr_of_elements

    # returns True if the Bag is empty, False otherwise
    def isEmpty(self):  # theta(1)
        if len(self.__list) == 0:
            return True
        else:
            return False

    # returns a BagIterator for the Bag
    def iterator(self):  # theta(1)
        return BagIterator(self)


class BagIterator:

    # creates an iterator for the Bag b, set to the first element of the bag, or invalid if the Bag is empty
    def __init__(self, b):  # theta(1)
        self.__bag = b
        self.__current_position = 0
        self.__current_frequency = 1

    # returns True if the iterator is valid
    def valid(self):  # theta(1)
        if self.__current_position < len(self.__bag._Bag__list):
            if self.__current_frequency <= self.__bag._Bag__frequency[self.__current_position]:
                return True
        return False

    # returns the current element from the iterator.
    # throws ValueError if the iterator is not valid
    def getCurrent(self):  # theta(1)
        if not self.valid():
            raise ValueError()
        return self.__bag._Bag__list[self.__current_position]

    # moves the iterator to the next element
    # throws ValueError if the iterator is not valid
    def next(self):  # theta(1)
        if not self.valid():
            raise ValueError()
        if self.__current_frequency == self.__bag._Bag__frequency[self.__current_position]:
            self.__current_position += 1
            self.__current_frequency = 1
        else:
            self.__current_frequency += 1

    # sets the iterator to the first element from the Bag
    def first(self):  # theta(1)
        self.__current_position = 0
        self.__current_frequency = 1