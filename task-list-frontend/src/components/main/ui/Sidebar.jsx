import React from 'react';
import styles from "../styles/Main.module.scss";
import {taskTypes} from "../../../config.jsx";

export const Sidebar = ({show, task, onEdit}) => {

    if (!show) return null;

    return (
        <div className={styles.sidebar}>
            <div>
                <h4>Edit:</h4>
                <input
                    type="text"
                    className="form-control my-3"
                    placeholder={task.title}
                />
                <div className="form-floating my-3">
                    <textarea className="form-control" placeholder="Leave a comment here"
                              id="floatingTextarea"></textarea>
                    <label htmlFor="floatingTextarea">Description</label>
                </div>
                <div className="form-floating">
                    <select name="status" className="form-select my-3">
                        {taskTypes.map(taskType => (
                            <option key={taskType} value={taskType}>{taskType}</option>
                        ))}
                    </select>
                    <label htmlFor="floatingSelect">Status</label>
                </div>

                <div>
                    <button className="btn btn-secondary me-2" onClick={onEdit}>
                        Cancel
                    </button>
                    <button className="btn btn-primary">
                    Save
                    </button>
                </div>
            </div>
        </div>
    );
};
