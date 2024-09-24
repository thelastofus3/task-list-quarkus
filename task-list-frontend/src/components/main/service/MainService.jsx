import axios from "axios";
import httpService from "./HttpService.jsx";

export const taskService = {
    getTasks: () => httpService.get('tasks'),
    updateTask: (data) => axios.patch('http://localhost:8080/api/v1/tasks', data),
};

export const userService = {
    getUser: () => httpService.get('user')
}