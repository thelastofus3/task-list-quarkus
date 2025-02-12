import {useCallback, useEffect, useState} from "react";
import {taskService} from "../service/taskService.jsx";

export const useTasks = () => {
    const [tasks, setTasks] = useState([]);
    const [isLoading, setIsLoading] = useState(true);
    const [error, setError] = useState(null);

    const fetchTasks = useCallback(async () => {
        try {
            setIsLoading(true);
            const response = await taskService.getTasks();
            setTasks(response.data);
            setError(null);
        } catch (err) {
            setError('Failed to fetch tasks');
            console.error('Error fetching tasks:', err);
        } finally {
            setIsLoading(false);
        }
    }, []);

    const updateTask = useCallback(async (updatedTask) => {
        try {
            await taskService.updateTask(updatedTask);
            setTasks(prev => prev.map(task =>
                task.id === updatedTask.id ? updatedTask : task
            ));
        } catch (err) {
            console.error('Error updating task:', err);
            throw err;
        }
    }, []);

    const createTask = useCallback(async (newTask) => {
        try {
            const response = await taskService.createTask(newTask);
            setTasks(prev => [...prev, response.data]);
            return response.data;
        } catch (err) {
            console.error('Error creating task:', err);
            throw err;
        }
    }, []);

    const deleteTask = useCallback(async (id) => {
        try {
            await taskService.deleteTask(id);
            setTasks(prev => prev.filter(task => task.id !== id));
        } catch (err) {
            console.error('Error deleting task:', err);
            throw err;
        }
    }, []);

    useEffect(() => {
        fetchTasks();
    }, [fetchTasks]);

    return {
        tasks,
        isLoading,
        error,
        updateTask,
        createTask,
        deleteTask
    };
};