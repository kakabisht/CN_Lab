import socket

s = socket.socket()
s.connect(('127.0.0.1',12345))
while True:
    str = input("Enter a String to check a pallindrome and to break press C: ")
    print ("Sending data:",str)
    s.send(str.encode())
    if(str == "C" or str == "c"):
        break
    print ("Recieved data:",s.recv(1024).decode())

s.close()