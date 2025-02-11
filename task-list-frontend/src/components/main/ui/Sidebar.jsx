import React, {useEffect, useState} from 'react';
import styles from "../styles/Main.module.scss";
import {taskTypes} from "../../../config.jsx";
import {taskService} from "../service/MainService.jsx";

export const Sidebar = ({show, task, onEdit}) => {

    const [title, setTitle] = useState('');
    const [description, setDescription] = useState('');
    const [status, setStatus] = useState(taskTypes[0]);

    useEffect(() => {
        setTitle(task?.title || '');
        setDescription(task?.description || '');
        setStatus(task?.status || taskTypes[0]);
    }, [task]);

    const handleSave = async () => {
        try {
            if (task?.id) {
                await taskService.updateTask({id: task.id, title, description, status});
            } else {
                await taskService.createTask({title, description, status});
            }
            onEdit();
        } catch (error) {
            console.error('Error saving task:', error);
        }
    };

    if (!show) return null;

    return (
        <div className={styles.sidebar}>
            <div>
                <h4>{task ? 'Edit Task' : 'Create New Task'}</h4>
                <input
                    type="text"
                    className="form-control my-3"
                    placeholder="Task Title"
                    value={title}
                    onChange={(e) => setTitle(e.target.value)}
                />
                <div className="form-floating my-3">
                    <textarea
                        className="form-control"
                        placeholder="Leave a comment here"
                        id="floatingTextarea"
                        value={description}
                        onChange={(e) => setDescription(e.target.value)}
                    ></textarea>
                    <label htmlFor="floatingTextarea">Description</label>
                </div>
                <div className="form-floating">
                    <select
                        name="status"
                        className="form-select my-3"
                        value={status}
                        onChange={(e) => setStatus(e.target.value)}
                    >
                        {taskTypes.map((taskType) => (
                            <option key={taskType} value={taskType}>
                                {taskType}
                            </option>
                        ))}
                    </select>
                    <label htmlFor="floatingSelect">Status</label>
                </div>
                <div>
                    <button className="btn btn-secondary me-2" onClick={onEdit}>
                        Cancel
                    </button>
                    <button className="btn btn-primary" onClick={handleSave}>
                        Save
                    </button>
                </div>
            </div>
        </div>
    );
};
