const app = require('./app')
const supertest = require('supertest')

const request = supertest(app)

test('gets the ads endpoint', async () => {

    const response = await request.get('/ads')
        .expect('Content-type', /json/)
        .expect(200)

    expect(response.body.length).toBe(3)

})