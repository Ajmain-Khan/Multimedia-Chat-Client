'''
    Adapted From: https://github.com/realpython/materials/blob/master/python-sockets-tutorial/app-server.py
'''
# Imports
import click
import selectors
import socket
import sys
import traceback

import libmessage
@click.command()
@click.option("-P", "--port", default=2021, help="Port number")

# Use an empty string to listen on all interfaces
@click.option("-h", "--host", default="127.0.0.1", help="Host address")

@click.option("-c", "--max-clients", default=25, help="Maximum number of clients")
# @click.option("-p", "--password", prompt="Set the password:\n\t", help="Server password", required = True)
# def main(port, host, max_clients, password):
def main(port, host, max_clients):
    with selectors.DefaultSelector() as sel:
        lsock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

        lsock.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)

        lsock.bind((host, port))
        lsock.listen()
        print("listening on", (host, port))

        lsock.setblocking(False)

        sel.register(lsock, selectors.EVENT_READ, data=None)

        try:
            while True:

                events = sel.select(timeout=None)

                # For the key and mask of every socket in events:
                for key, mask in events:

                    if key.data is None:
                        
                        # Note that "key.fileobj" here is currently "lsock"
                        conn, addr = key.fileobj.accept()
                    
                        print("accepted connection from", addr)
                        conn.setblocking(False)
                        message = libmessage.Message(sel, conn, addr)
                        sel.register(conn, selectors.EVENT_READ, data=message)

                    else:
                        message = key.data
                    
                        try:
                            message.process_events(mask)
                        except Exception:
                            print(
                                "main: error: exception for",
                                f"{message.addr}:\n\t{traceback.format_exc()}\n\n"
                            )
                            message.close()
        except KeyboardInterrupt:
            print("caught keyboard interrupt, exiting")

if __name__ == "__main__":
    main()
