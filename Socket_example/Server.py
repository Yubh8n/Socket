#!/usr/bin/env python3

import socket
from time import sleep

HOST = '192.168.8.108'
PORT = 65432
prev_data = 0
while True:
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
        s.bind((HOST, PORT))
        s.listen()
        conn, addr = s.accept()
        with conn:
            print("connected to: ", addr)
            while True:
                data = conn.recv(1024)
                #if prev_data != data:
                sleep(0.1)
                print ("I heard: " + str(data))
                #prev_data = data