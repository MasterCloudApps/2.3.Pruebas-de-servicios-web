import app from '../src/app';
import supertest from 'supertest';
import mongoose from 'mongoose';
import { GenericContainer } from "testcontainers";

jest.setTimeout(30000)

const request = supertest(app)

let mongoContainer;

beforeAll(async() => {
    mongoContainer = await new GenericContainer("mongo", "3.6.21")
        .withExposedPorts(27017)
        .start();

    await mongoose.connect('mongodb://localhost:' + mongoContainer.getMappedPort(27017));
});

afterAll(async() => {
    await mongoose.connection.close()
    await mongoContainer.stop();
});

test('Create new ad', async() => {

    let ad = {
        "title": "Vendo coche",
        "content": "Vendo Opel Corsa - test",
        "author": "Michel"
    }

    const response = await request.post('/anuncios/')
        .send(ad)
        .expect('Content-type', /json/)
        .expect(201)

    expect(response.body.author).toBe("Michel")
})