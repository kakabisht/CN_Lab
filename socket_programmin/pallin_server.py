import socket

s = socket.socket()
port = 12345
s.bind(('', port))
s.listen(5)
c, addr = s.accept()
print ("Socket Up and running with a connection from",addr)
while True:
    rcvdData = c.recv(1024).decode()
    print ("Recieved data:",rcvdData)
    sendData = "False"
    if(rcvdData==rcvdData[::-1]): 
        print ("Sending data:",rcvdData)
        sendData="True"
    c.send(sendData.encode())
    if(sendData == "C" or sendData == "c"):
        break
c.close()