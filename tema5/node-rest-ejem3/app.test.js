const app = require('./server')
const supertest = require('supertest')

var Anuncio = require('./anuncio/Anuncio')

jest.mock('./anuncio/Anuncio');

const request = supertest(app)

test('Create new ad', async () => {

    let ad = {
        "title": "Vendo coche", 
        "content": "Vendo Opel Corsa - test", 
        "author":"Michel"
    }

    Anuncio.create.mockImplementation((ad, cb) => cb(null, ad));

    const response = await request.post('/anuncios/')
        .send(ad)      
        .expect('Content-type', /json/)
        .expect(201)

    expect(response.body.author).toBe("Michel")
    expect(Anuncio.create).toHaveBeenCalledTimes(1)

})