const app = require('./server')
const supertest = require('supertest')

const request = supertest(app)
var db = require('./db');

afterAll(async () => {
    await db.connection.close()
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