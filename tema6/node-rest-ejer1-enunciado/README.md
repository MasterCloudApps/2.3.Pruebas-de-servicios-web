# Node.js Rest APIs with Express & MySQL example

## Project setup
```
    npm install
```

### Run
```
    npm start
```

### Setup MySQL

```
    docker run --name master-mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=pass -e MYSQL_DATABASE=test -d  mysql:5.7
```

# Create Customer

```
curl -d '{ "email" : "michel.maes@urjc.es", "name" : "Michel", "active" : false }'\
    -H "Content-Type: application/json" -X POST http://localhost:3000/api/customers/
```
