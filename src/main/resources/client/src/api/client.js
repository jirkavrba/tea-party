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
        console.log(token, auth(token));

        return await client.get("/api/login/check", {headers: auth(token)})
            .then(response => true)
            .catch(error => false)
    }
}