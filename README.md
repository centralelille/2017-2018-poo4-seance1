# Session 1 [![Build Status](https://travis-ci.org/eleves-ig2i/ig2i-le4-poo-2018-tp1.svg?branch=master)](https://travis-ci.org/eleves-ig2i/ig2i-le4-poo-2018-tp1)

## Description

> This TP was realized with the concept of object-relational mapping. It allows to illustrate the following notions :
> * object-relational mapping
> * JPA API (Java Persistence API)
> * mapping association relationships

## Table of contents
- [Installation](#installation)
	- [Prerequisites](#prerequisites)
		- [Java](#java)
		- [NetBeans](#netbeans)
	- [Installing](#installing)
		- [Create the project](#create-the-project)		
		- [Setup Database](#setup-database)
    - [Setup Project](#setup-project)
		- [Setup JDBC](#setup-jdbc)
		- [Persistance Unit](#persistance-unit)
- [Tools conf](#tools-conf)
- [Authors](#authors)
- [License](#license)

## Installation
### Prerequisites
You need to :
* Java
* NetBeans

#### Java
##### Resources
- https://stackoverflow.com/questions/25289482/installing-jdk8-on-ubuntu-unable-to-locate-package-update-doesnt-fix/31869659#31869659
- http://www.webupd8.org/2014/03/how-to-install-oracle-java-8-in-debian.html
- https://netbeans.org/kb/docs/ide/java-db.html#registering
- https://docs.oracle.com/netbeans/nb82/netbeans/NBDAG/work_app_servers.htm#NBDAG1687

##### Install
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

#### NetBeans
##### Resources
- https://netbeans.org/downloads/
- https://netbeans.org/community/releases/80/install.html#installation

##### Install
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
### Installing
#### Create Project
- File > New Project > Java > Java Application
  - Project Name : 2017-2018-poo4-seance1
  - Project Location : ~
  - Project Folder : ~/2017-2018-poo4-seance1
  - x Use dedicated ...
  - Main class : centralellile._2017_2018.poo4.seance1.Main

#### Setup Database
- Open Netbeans
- Services > Databases > Java DB > Start Server
- Services > Databases > Java DB > Create Database
  - Database Name : database
  - User Name : username
  - Password : password
  - Password : password
- [OK]
- Services > Databases > jdbc:derby... > connect

#### Setup Project
* Open Netbeans
* File > Project Properties ${project name} > Build > Compilation
	* [Check] Compile on Save
	* [Check] Enable Annotation Processing
* [OK]

#### Setup JDBC
- Projects > 2017-2018-poo4-seance1 > Librairies > Add Librairies
  - [Import] > Java DB Driver
  - [Add Library]

#### Percistance Unit
- centralellile._2017_2018.poo4.seance1 > new > other > Persistence > Persitence Unit > ...
  - Pu name : 2017-2018-poo4-seance1-pu
  - Database Connection : jdbc:derby...

## Tools conf
- [Use tabs](https://stackoverflow.com/questions/1949014/how-can-i-configure-netbeans-to-insert-tabs-instead-of-a-bunch-of-spaces)

## Authors
* [Loïc BOURGOIS](https://github.com/loicbourgois)
* [Sébastien CORNUEL](https://github.com/Hercules0402)

## License [![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)
This TP is licensed under the MIT License - see the [LICENSE](LICENSE) file for details