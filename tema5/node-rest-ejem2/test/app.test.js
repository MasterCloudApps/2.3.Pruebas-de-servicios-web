const app = require('../src/app')
const supertest = require('supertest')
const mongoose = require('mongoose');
const request = supertest(app)

beforeAll( async ()=>{
    await mongoose.connect('mongodb://localhost:27017');
})

afterAll(async () => {
    await mongoose.connection.close()
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