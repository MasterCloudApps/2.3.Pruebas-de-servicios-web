import axios from 'axios';

export default class Users {
    static async all() {
        let resp = await axios.get('/users.json');
        return resp.data;
    }
}