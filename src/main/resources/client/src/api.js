import axios from "axios";

// TODO: Make this env dependent
const api = "http://localhost:8080";
const client = axios.create({
    baseURL: api,
    headers: {
        "Content-Type": "application/json"
    }
});

const authentication = token => ({
    headers: {
        "Authorization": `Bearer ${token}`
    }
});

export default {
    createAccount: async username => client.post("/api/authentication/create-account", {username})
        .then(response => response.data)
        .catch(() => null),

    validateToken: async token => client.post("/api/authentication/check", {}, authentication(token))
        .then(() => true)
        .catch(() => false),
};