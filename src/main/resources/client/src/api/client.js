// TODO: Make this node.env dependent
const api = "http://localhost:8080";

export default {
    async login(username) {
        const response = await fetch(api + "/api/login", {
            method: "post",
            body: JSON.stringify({ username })
        }).then(response => response.json())

        return response.token;
    }
}