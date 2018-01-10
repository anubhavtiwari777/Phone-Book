# Phone Book Web by Vadimksn

### Used:
* Spring Boot
* Spring JDBC
* Maven
* MySQL
* JavaScript and JQuery
* Junit

### Before run: 
* Install Java
* Install Maven
* Install MySQL Server
* Create database in MySQL with name 'phone_book'
* Change user's password and name for MySQL in application.yml

## SQL Scripts
SQL scripts for creating tables contacts and phones:
```sql
CREATE TABLE `contacts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8
```
```sql
CREATE TABLE `phones` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `phone_number` varchar(255) NOT NULL,
  `contact_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `contact_id_idx` (`contact_id`),
  CONSTRAINT `contact_id` FOREIGN KEY (`contact_id`) REFERENCES `contacts` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8
```

[1]: src/main/resources/assets/startPage.jpg
[2]: src/main/resources/assets/allContacts.jpg
[3]: src/main/resources/assets/filterContact.jpg
[4]: src/main/resources/assets/addContact.jpg

#### Main page:
![Alt text][1]

#### All contacts page:
![Alt text][2]

#### Filter contacts:
![Alt text][3]

#### Add new contact:
![Alt text][4]
#Phone-Book
