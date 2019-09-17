import unittest
from domain.board import Board

class Test(unittest.TestCase):
    def setUp(self):
        unittest.TestCase.setUp()
        self.b=Board()
    def tearDown(self):
        unittest.TestCase.tearDown()
    def testdat(self):
        self.asssertEqual(self.b.getPC(),[[0]*8, [0]*8 ,[0]*8 ,[0]*8 ,[0]*8 ,[0]*8 ,[0]*8 ,[0]*8])
        self.assertEqual(self.b.getPlayer(),[[0]*8, [0]*8 ,[0]*8 ,[0]*8 ,[0]*8 ,[0]*8 ,[0]*8 ,[0]*8])
