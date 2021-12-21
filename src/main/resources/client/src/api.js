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
        .then(response => response.data)
        .catch(() => null),

    loadLobbies: async token => client.get("/api/lobbies", authentication(token))
        .then(response => response.data)
        .catch(() => []),

    createLobby: async (token, mode) => client.post("/api/lobbies/create", {mode}, authentication(token))
        .then(response => response.data)
        .catch(() => null),

    loadLobby: async (token, id) => client.get("/api/lobbies/" + id, authentication(token))
        .then(response => response.data)
        .catch(() => null),
};