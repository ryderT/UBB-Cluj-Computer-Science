from domain.board import Board
from validators.boardvalidator import BoardValidator
from game.gameUI import Game

v=BoardValidator()
b=Board(v)
g=Game(b)
g.getUserInput()