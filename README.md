# spring-library-docker

* If you want to run this project on your computer. You should install Docker.
  ! By the way The Docker should run background.
  
* Also you should create *.jar file.(But Jar file name is named in pom.xml file)

![jar-file](images/set-jar-file-name.png)
  

* Maven --> clean and install

![mave-clean-install](images/maven-clean-install.png)

* Jar file check

![jar-file-cr](images/jar-file-created.png)
  
* Dockerfile


![dockerfile-img](images/dockerfile-img-1.png)
  


! **docker image pull openjdk:8**

![openjdk](images/openjdk-img.png)
* And then you should create image of your project. 
  - (docker image build -f Dockerfile -t spring-library-docker) .
  
![image-cr](images/image-create-img-1.png)
      
* After create image, you can check. 
    * docker image ls
  
![docker-image](images/docker-image-check.png)

Before the project run in docker stack.I mean, you have to need docker-compose.yml file for project run.
(If you want to use images in the docker-compose.yml file. You should pull or create necessary images for your project before )
! (If you use database, you should pull database image (mysql postresql or anything). Mysql database was used in the this project).
* Mysql version 8.0.23
  * docker image pull mysql:8.0.23
  
![docker-mysql](images/docker-mysql-8.0.23.png)

* docker image check

![docker-image-check-1](images/docker-image-check-1.png)

* docker-compose-yml

![docker-compose](images/docker-compose-image.png)

* Docker stack
  * docker stack deploy -c .\docker-compose.yml spring-library-stack




