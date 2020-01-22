from board import DRAW
from board import ALL_POS_COUNT

class PlayerHuman:
    def __init__(self,turn):
        self.name="Human"
        self.myturn=turn

    def act(self,board):
        valid = False
        while not valid:
            try:
                act = input()
                act = int(act)
                #if act >= 1 and act <= 9 and board.board[act-1]==EMPTY:
                if act >= 1 and act <= ALL_POS_COUNT:
                    valid=True
                    return act-1
                else:
                    #print ("That is not a valid move! Please try again.")
                    print(-1)
            except Exception as e:
                    #print (act +  "is not a valid move! Please try again.")
                    print(-1)
        return act

    def getGameResult(self,board):
        if board.winner is not None and board.winner!=self.myturn and board.winner!=DRAW:
            #print("I lost...")
            pass