import random
"""
目的変数：出す手
説明変数：過去に出したそれぞれの手の累計とプレイ回数
"""
judList = [
    [2,0,1],
    [1,2,0],
    [0,1,2]
]
winWay = [2,0,1]
msgList = ["負け","勝ち","あいこ"]
class Player:
    def __init__(self):
        self.suchTime = [0,0,0]
        self.totalTime = 0
        self.winTime = 0

    def getMin(self):
        return self.suchTime.index(min(self.suchTime[2],min(self.suchTime[0],self.suchTime[1])))
    
    def getMax(self):
        return self.suchTime.index(max(self.suchTime[2],max(self.suchTime[0],self.suchTime[1])))

plr = Player()
while True:
    print("じゃんけん 1:ぐー,2:ちょき,3:パー")
    try:
        p = int(input())-1
        plr.suchTime[p] += 1
        res = judList[winWay[plr.getMax()]][p]
        print(msgList[res])
        plr.totalTime += 1
        if res == 1:
            plr.winTime += 1
        print(f"勝率{plr.winTime/plr.totalTime}")
    except (IndexError):
        print("ちゃんとやって！！！")
    except ValueError:
        print("入力して！！")