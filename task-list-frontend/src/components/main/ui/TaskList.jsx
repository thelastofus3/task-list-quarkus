import React, {useEffect, useState} from 'react';
import {taskService} from "../service/MainService.jsx";
import {Card} from "./Card.jsx";
import {taskTypes} from "../../../config.jsx";

export const TaskList = () => {
    const [tasks, setTasks] = useState([]);

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
    }, [tasks]);
    return (
        <div className="row justify-content-center mt-4 mx-5">
            {taskTypes.map(type => (
                <Card key={type} tasks={tasks.filter(task => task.status === type)} type={type}/>
            ))}

        </div>
    );
};

