const supertest = require('supertest');

const { GenericContainer } = require("testcontainers");

let mySQLContainer;
let request;
let conection;

jest.setTimeout(60000)

beforeEach(async () => {
    mySQLContainer = await new GenericContainer("mysql","5.7")
      .withExposedPorts(3306)
      .withEnv("MYSQL_ROOT_PASSWORD", "pass")
      .withEnv("MYSQL_DATABASE","test")
      .start().catch((err)=>{console.log(err)})


    // GET RANDOM PORT FROM CONTAINER
    process.env.MYSQL_PORT = mySQLContainer.getMappedPort(3306)

    // INIT DATABASE CONECTION
    const { db } = require("../../src/db/db");
    conection = db

    // INIT APP (CREATE MIGRATIONS)
    const app = require('../../src/app');
    request = supertest(app);

    // RUN MIGRATIONS
    await db.sync({ force: true }).catch(err=>{
        console.log(err)
    })

});

afterEach(async () => {
    await conection.close()
    await mySQLContainer.stop();
});

let customer = { "email" : "michel.maes@urjc.es", "name" : "Michel", "active" : false }

test('Create new customer, retrive it and remove it', async () => {   

    // CREATE CUSTOMER
    
    const createResponse = await request.post('/api/customers/')
        .send(customer)      
        .expect(201)

    let createdCustomer = createResponse.body;
    
    expect(createdCustomer['name']).toBe("Michel")

    // RETRIVE CREATED CUSTOMER BY ID

    const retrivedResponse = await request.get('/api/customers/'+createdCustomer.id).
        expect(200)


    let retrivedCustomer = retrivedResponse.body;

    expect(retrivedCustomer['active']).toBe(false)

    // DELETE CUSTOMER

    await request.delete('/api/customers/'+createdCustomer.id).
        expect(204)

    // CHECK THAT CUSTOMER DOES NOT EXIST

    await request.get('/api/customers/'+createdCustomer.id).
        expect(404)

})
