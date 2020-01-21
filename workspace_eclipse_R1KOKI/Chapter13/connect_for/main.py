from board import Board
from versus import random_vs_random, human_vs_random, random_vs_alpha_random, ql_vs_ql, human_vs_dumped, ql_vs_alpha_random, \
    ql_vs_random, mc_vs_mc, mc_vs_alpha_random, human_vs_alpha_random, human_vs_mc, mc_vs_dumped
import sys
# クライアントを作成
import socket
with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
    while True:
        try:
            # サーバを指定
            s.connect(('127.0.0.1', 8765))
            print("接続")
            break
        except:
            continue
    # サーバにメッセージを送る
    s.sendall(b'test')
    print("応答待ち")
    while True:
        # ネットワークのバッファサイズは1024。サーバからの文字列を取得する
        #data = s.recv(1024)
        print(data)
#
    print("サーバーからの応答:" + repr(data))

# random_vs_random()
# human_vs_random()
# random_vs_alpha_random()
human_vs_mc(sys.argv[1])
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