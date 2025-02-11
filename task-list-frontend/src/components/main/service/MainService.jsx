import httpService from "./HttpService.jsx";

export const taskService = {
    getTasks: () => httpService.get('tasks'),
    updateTask: (data) => httpService.patch('tasks', data),
    createTask: (data) => httpService.post('tasks', data),
};

export const userService = {
    getUser: () => httpService.get('user')
}