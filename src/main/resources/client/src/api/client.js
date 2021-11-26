import axios from "axios";

const client = axios.create({
    // TODO: Make this node.env dependent
    baseURL: "http://localhost:8080",
    headers: {
        "Content-Type": "application/json"
    }
});

const authenticate = (token) => ({
    headers: {
        "Authorization": `Bearer ${token}`
    }
})

export default {
    async login(username) {
        return await client
            .post("/api/login", {username})
            .then(response => response.data.token);
    },
    async validateToken(token) {
        return await client.get("/api/login/check", authenticate(token))
            .then(response => true)
            .catch(error => false)
    },
    async createLobby(token, mode) {
        return await client.post("/api/lobby/create", {mode: mode}, authenticate(token))
            .then(response => response.data);
    },
    async loadLobbies(token) {
        return await client.get("/api/lobbies", authenticate(token))
            .then(response => response.data);
    },
    async loadLobby(token, id) {
        return await client.get(`/api/lobby/${id}`, authenticate(token))
            .then(response => response.data);
    },
    async joinLobby(token, id) {
        return await client.post(`/api/lobby/${id}/join`, {}, authenticate(token))
            .then(response => response.data)
            .catch(response => response.data)
    }
}