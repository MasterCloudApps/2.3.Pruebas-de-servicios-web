const app = require('./server');
const supertest = require('supertest');
const db = require('mongoose');
const { GenericContainer } = require("testcontainers");

const request = supertest(app)

let mongoContainer;

beforeAll(async () => {
    mongoContainer = await new GenericContainer("mongo","3.6.21")
      .withExposedPorts(27017)
      .start();

    await db.connect('mongodb://localhost:'+mongoContainer.getMappedPort(27017));
});

afterAll(async () => {
    await db.connection.close()
    await mongoContainer.stop();
});

test('Create new ad', async () => {
    
    let ad = {
        "title": "Vendo coche", 
        "content": "Vendo Opel Corsa - test", 
        "author":"Michel"
    }

    const response = await request.post('/anuncios/')
        .send(ad)      
        .expect('Content-type', /json/)
        .expect(201)

    expect(response.body.author).toBe("Michel")
})