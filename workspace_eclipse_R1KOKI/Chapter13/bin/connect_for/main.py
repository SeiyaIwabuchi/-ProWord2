from board import Board
from versus import random_vs_random, human_vs_random, random_vs_alpha_random, ql_vs_ql, human_vs_dumped, ql_vs_alpha_random, \
    ql_vs_random, mc_vs_mc, mc_vs_alpha_random, human_vs_alpha_random, human_vs_mc, mc_vs_dumped
import sys
# クライアントを作成
import socket
import time

soc = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
while True:
    try:
        # サーバを指定
        soc.connect(('127.0.0.1', 8765))
        print("サーバーへ接続")
        break
    except:
        print("接続失敗。リトライします")
        time.sleep(1)
        continue
# サーバにメッセージを送る
print("応答待ち")
data = ""
soc.send(b'test\n')
data = ""
while data == "":
    # ネットワークのバッファサイズは1024。サーバからの文字列を取得する
    data = soc.recv(1024)
print(data.decode())

try:
    # random_vs_random()
    # human_vs_random()
    # random_vs_alpha_random()
    human_vs_mc(sys.argv[1],soc)
    # ql_vs_ql()
    # human_vs_ql()
    # human_vs_dumped()
    # mc_vs_dumped()
    # random_vs_dumped()
    # ql_vs_alpha_random()
    # ql_vs_random()
    # mc_vs_alpha_random()
    # mc_vs_mc()
    # human_vs_alpha_random()
except:
    pass
finally:
    soc.sendall("-1\n".encode())