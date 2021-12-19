import axios from "axios";

const api = "http://localhost:8080"
const client = axios.create({
    baseURL: api,
    headers: {
        "Content-Type": "application/json"
    }
})

const authentication = token => ({
    headers: {
        "Authorization": `Bearer ${token}`
    }
})

export default {
    validateToken: async (token) => await client.post("/api/authentication/check", {}, authentication(token))
        .then(() => true)
        .catch(() => false)
};