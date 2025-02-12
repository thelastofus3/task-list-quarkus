import React, {useState} from 'react';
import {TaskInfo} from "./TaskInfo.jsx";
import {Sidebar} from "./Sidebar.jsx";
import styles from "./../styles/Main.module.scss";
import {taskService} from "../service/taskService.jsx";

export const Card = ({tasks, type, setTasks, allTasks}) => {
    const [isModalOpen, setModalOpen] = useState(false);
    const [currentTask, setCurrentTask] = useState(null);
    const [viewDescriptionId, setViewDescriptionId] = useState(null);

    const handleEdit = (task) => {
        setCurrentTask(task);
        setModalOpen(!isModalOpen);
    }

    const handleDelete = async (id) => {
        try {
            await taskService.deleteTask(id);
            setTasks(allTasks.filter(task => task.id !== id));
        } catch (error) {
            console.error("Error while try to delete task:", error);
        }
    }

    const handleCreateNewTask = () => {
        setCurrentTask(null);
        setModalOpen(!isModalOpen);
    }

    const handleDescription = (task) => {
        setViewDescriptionId(viewDescriptionId === task.id ? null : task.id);
    }

    return (
        <div className="col-lg-3 col-md-4 col-sm-12 mb-4">
            <div className="card h-100">
                <div className="card-body">
                    <h4>{type.replace('_', ' ')}</h4>
                    {tasks.length > 0 ? (
                        tasks.map((task) => (
                            <TaskInfo
                                key={task.id}
                                name={task.title}
                                description={task.description}
                                onDescription={() => handleDescription(task)}
                                onEdit={() => handleEdit(task)}
                                onDelete={() => handleDelete(task.id)}
                                viewDescription={viewDescriptionId === task.id}
                            />
                        ))
                    ) : (
                        <TaskInfo/>
                    )}
                    <div className="d-grid gap-2">
                        <button className={`${styles.anim_icon} btn d-flex align-items-center`}
                                onClick={handleCreateNewTask}
                                style={{ '--hover-color': '#28a745' }}
                        >
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                 fill="currentColor"
                                 className="bi bi-plus-lg" viewBox="0 0 16 16">
                                <path fillRule="evenodd"
                                      d="M8 2a.5.5 0 0 1 .5.5v5h5a.5.5 0 0 1 0 1h-5v5a.5.5 0 0 1-1 0v-5h-5a.5.5 0 0 1 0-1h5v-5A.5.5 0 0 1 8 2"/>
                            </svg>
                            <p className="mb-0 ms-2">Create new task</p>
                        </button>
                    </div>
                </div>
            </div>
            <Sidebar show={isModalOpen} task={currentTask} onEdit={handleEdit} setTasks={setTasks} />
        </div>
    );
};

