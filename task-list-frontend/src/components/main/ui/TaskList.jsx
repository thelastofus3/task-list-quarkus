import React, {useEffect, useState} from 'react';
import {taskService} from "../service/MainService.jsx";
import {Card} from "./Card.jsx";

export const TaskList = () => {
    const [tasks, setTasks] = useState([]);
    const [taskTypes] = useState(["TODO", "IN PROGRESS", "DONE"]);

    useEffect(() => {
        const fetchTasks = async () => {
            try {
                const response = await taskService.getTasks();
                setTasks(response.data);
            } catch (error) {
                console.log('Error fetching tasks', error);
            }
        };

        fetchTasks();
    }, []);
    return (
        <div className="row justify-content-center mt-4 mx-5">
            {taskTypes.map(type => (
                <Card key={type} title={tasks.find(task => task.status === type)?.title || "No Tasks"} type={type} />
            ))}
        </div>
    );
};

