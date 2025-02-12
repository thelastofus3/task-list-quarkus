import { httpClient } from '../api/httpClient.jsx';

export const taskService = {
    getTasks: () => httpClient.get('tasks'),
    updateTask: (task) => httpClient.patch(`tasks/${task.id}`, task),
    createTask: (task) => httpClient.post('tasks', task),
    deleteTask: (id) => httpClient.delete(`tasks/${id}`),
};