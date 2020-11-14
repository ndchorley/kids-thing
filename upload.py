import ftplib
import os
import sys

def upload_files(directory, ftp):
    with os.scandir(directory) as it:
        for entry in it:
            if entry.is_file():
                print("Uploading file " + entry.path)
                with open(entry.path, "rb") as f:
                    ftp.storbinary(
                        "STOR " + entry.path.replace("target/", ""),
                        f,
                        blocksize=32768
                    )
            else:
                name = entry.path.replace("target/", "")
                print("Creating directory " + name + "/")
                try:
                    ftp.mkd(name)
                except:
                    pass

                upload_files(entry.path, ftp)


host = sys.argv[1]
user = sys.argv[2]
password = sys.argv[3]
server_dir = sys.argv[4]

ftp = ftplib.FTP(host)
ftp.login(user=user, passwd=password)
ftp.cwd(server_dir)

upload_files("target", ftp)

ftp.quit()
