#### Test 

## Basic
A JavaBasicA.java
B 

## Senior
1. Setup mongodb (using localhost:27017)
	- create database 'tiketcom'
	- create collections: customers, employees, orderdetails, orders, products, shippingmethods
2. Run application:
	- Run Zookeeper
	- Run Kafka
	- Run Application
3. Test application
	- Load data from csv to mongodb: hit postman GET http://localhost:8088/load
	- Load data from csv to mongodb: hit postman GET http://localhost:8088/load/kafka
	- Transaksi Data Pemesanan Barang: hit postman GET http://localhost:8088/orders?page=0&&size=1
	(need time to explore collection relation, conversion and mongo aggregation :p)


## EasyShariaSolution

Requirement:
- Credit for online shopping(blibli, bukalapak)
- Customer can pay installment in 6, 12, 24
- Pay option using 'Easy Sharia Credit'
- EasyShariaSolution will calculate and transfer to merchant total amount and profit
- Customer need register via mobile or web
- Customer can tracking payment history, promo, reward, etc

Diagram:
Open "Sharia Credit Solution.drawio" in site https://www.draw.io/ 