# ROOM-RESERVATION (spring boot)
it is also a basic but interesting project related to concept of booking with few feature.

Step to Run this Project Application is:

Steps : Go to src → main  → java → com → sancode → miniproject → miniProjectApplication.java 
then run as java ......

In terminal spring project is intiated and  it get stopped and and interuppted 
 ----> data base in not linked.
 ----> server may be listing to another port.
 
 for resolving data base issue: open mysql check about presence od database on name of "miniproject".
 if not exist do create it using query : create database miniproject;
 
 if will get created if you want to check about acknowledgment ... use query : show databases;
 
 it will get reflected in database  list.
 
 now close my sql and again run miniProjectApplication.java and then you may find  in console..
  many line of code is running ....on name of tabels creating ...which genenuinly get created since 
  you have linked data base to it.
  
 
and inlast line of console if it is shown that that default ....
it mean that by default application is running on localhost and port no is 8080;

to confirm about table creation you can go and run query in database about table view you may find many table is created:

query are given below:

use miniproject;
show tables;
describe table "-name of table_";

schema of that particular table will be shown to you:

here in mysql command line you may see this type of tabel in which name of all databases shall be there:

![1_sb](https://user-images.githubusercontent.com/105698566/185990786-a67c66ab-0304-4fde-9102-e0d513e565d5.png)

here you can se name of all tabels get created by application as we are using hibernate here :
![2_sp](https://user-images.githubusercontent.com/105698566/185990941-4c659768-88db-431f-bda4-83f97080eb9b.png)

below two snap showing the description of tables: all field is here and since we didnot do any entry that's why no record is here:

![3_sp](https://user-images.githubusercontent.com/105698566/185990984-83a36e82-77ea-46c5-b8ab-2ac059a754ea.png)

Here method is shown from here we can change method and accordingly we can manage url to request api:

![4_sp](https://user-images.githubusercontent.com/105698566/185991023-9259a6c5-5e2c-4a23-a374-c83b6990d1b3.png)

this is view of postman which is here acting as client:
as we use get method to get the data of  /admin from local host as it is launched on local host.

![5_sp](https://user-images.githubusercontent.com/105698566/185991341-23243df2-ea51-4923-8ae0-4e27811cf422.png)




now you can install postman to use as client to fire api request for application:

through postman:
      we can use many method like get ,post, put ,delete anf many more but in this project here we are using given method only:
     -> get : it will be used to get data from database.
     ->post : it will be used to send data entry to database.
     -> put : it will be used to update existing data.
     -> delete : it is used to delete data from data base.
      
      
