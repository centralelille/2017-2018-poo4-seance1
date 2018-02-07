# Séance 1

## Oracle Java

### Resources
- https://stackoverflow.com/questions/25289482/installing-jdk8-on-ubuntu-unable-to-locate-package-update-doesnt-fix/31869659#31869659
- http://www.webupd8.org/2014/03/how-to-install-oracle-java-8-in-debian.html
- https://netbeans.org/kb/docs/ide/java-db.html#registering
- https://docs.oracle.com/netbeans/nb82/netbeans/NBDAG/work_app_servers.htm#NBDAG1687

### Install
```bash
sudo aptitude update
sudo aptitude install openjdk-8-jdk
```
```bash
#Probably useless stuff
#sudo apt-get purge openjdk-\*
#sudo apt-get purge oracle-java8-installer
#sudo apt-get purge oracle-java9-installer
#sudo aptitude install libderby-java libderbyclient-java
#sudo apt-get install python-software-properties software-properties-common
#sudo add-apt-repository ppa:webupd8team/java
#sudo apt-get install oracle-java8-installer
#sudo apt-get install oracle-java8-set-default
```

## Netbeans

### Resources
- https://netbeans.org/downloads/
- https://netbeans.org/community/releases/80/install.html#installation

### Install
- Go to https://netbeans.org/downloads/
- Download `Java EE`
```bash
cd ~
mv Downloads/netbeans-8.2-javaee-linux.sh ./
chmod +x ./netbeans-8.2-javaee-linux.sh
# Select Glassfish AND Tomcat
# For JDK installation path, select /usr/lib/jvm/java-8-openjdk-amd64
./netbeans-8.2-javaee-linux.sh
```

### Setup Database
- Open Netbeans
- Services > Databases > Java DB > Start Server
- Services > Databases > Java DB > Create Database
  - Database Name : database
  - User Name : username
  - Password : password
  - Password : password
- [OK]
- Services > Databases > jdbc:derby... > connect

### Create Project
- File > New Project > Java > Java Application
  - Project Name : 2017-2018-poo4-seance1
  - Project Location : ~
  - Project Folder : ~/2017-2018-poo4-seance1
  - x Use dedicated ...
  - Main class : centralellile._2017_2018.poo4.seance1.Main

### Setup JDBC
- Projects > 2017-2018-poo4-seance1 > Librairies > Add Librairies
  - [Import] > Java DB Driver
  - [Add Library]

## Percistance Unit
- centralellile._2017_2018.poo4.seance1 > new > other > Persistence > Persitence Unit > ...
  - Pu name : 2017-2018-poo4-seance1-pu
  - Database Connection : jdbc:derby...


