import app from '../src/app';
import supertest from 'supertest';
import Anuncio from '../src/model/Anuncio';

jest.mock('../src/model/Anuncio');

const request = supertest(app)

test('Create new ad', async() => {

    let ad = {
        "title": "Vendo coche",
        "content": "Vendo Opel Corsa - test",
        "author": "Michel"
    }

    Anuncio.create.mockImplementation((ad, cb) => cb(null, ad));

    const response = await request.post('/anuncios/')
        .send(ad)
        .expect('Content-type', /json/)
        .expect(201)

    expect(response.body.author).toBe("Michel")
    expect(Anuncio.create).toHaveBeenCalledTimes(1)

})