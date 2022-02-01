import supertest from 'supertest';
import app from '../../src/app';
import Customer from '../../src/models/customer.model';

const request = supertest(app);

jest.mock('../../src/models/customer.model');

let customer = { "email": "michel.maes@urjc.es", "name": "Michel", "active": false }

beforeAll(async() => {

    Customer.create.mockImplementation(() => Promise.resolve(customer));
    Customer.findAll.mockImplementation(() => Promise.resolve([customer]));

});

test('Create new customer', async() => {

    const response = await request.post('/api/customers/')
        .send(customer)
        .expect(201)
    expect(response.body['name']).toBe("Michel")

})

test('Retrive all customers', async() => {

    const response = await request.get('/api/customers/')
        .expect(200)
    expect(response.body[0]['name']).toBe("Michel")

})