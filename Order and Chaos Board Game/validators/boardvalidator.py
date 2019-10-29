class BoardValidator:
    def __init__(self):
        pass

    def userInputValidator(self,x,y,data):
        if x<0 or y<0 or x>5 or y>5:
            raise ValueError("Coordinates are invalid!")

        if data[x][y]!=0:
            raise ValueError("There is already something there!")






bv=BoardValidator()

bv.userInputValidator(2,2,[[],[],[0,0,0]])