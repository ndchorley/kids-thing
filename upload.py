import ftplib
import os

host = ""
user = ""
password = ""

ftp = ftplib.FTP(host)
ftp.login(user=user, passwd=password)
ftp.quit()

def list_files(directory):
    with os.scandir(directory) as it:
        for entry in it:
            if entry.is_file():
                print(entry.path)
            else:
                print(entry.name + "/")
                list_files(entry.path)

list_files("target")
