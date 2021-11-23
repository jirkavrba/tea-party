import axios from "axios";

const client = axios.create({
    // TODO: Make this node.env dependent
    baseURL: "http://localhost:8080",
    headers: {
        "Content-Type": "application/json"
    }
});

const auth = (token) => ({
    "Authorization": `Bearer ${token}`
})

export default {
    async login(username) {
        return await client
            .post("/api/login", {username})
            .then(response => response.data.token);
    },
    async validateToken(token) {
        return await client.get("/api/login/check", {headers: auth(token)})
            .then(response => true)
            .catch(error => false)
    },
    async loadLobbies(token) {
        return await client.get("/api/lobbies", {headers: auth(token)})
            .then(response => response.data);
    },
    async createLobby(token, mode) {
        return await client.post("/api/lobby/create", {mode: mode}, {headers: auth(token)})
            .then(response => response.data);
    }
}