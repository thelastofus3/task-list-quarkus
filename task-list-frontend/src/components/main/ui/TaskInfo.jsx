import React from 'react';
import styles from "../styles/Main.module.scss";

export const TaskInfo = ({ task, name, onEdit, onDelete, onDescription, showDescription }) => {
    return (<>
        <div
            className={`${styles.main__task_name} p-2 my-3 rounded-4 d-flex align-items-center justify-content-between`}>
                <span role="button" onClick={onDescription}>
                    <p className="m-0">{name || 'No Tasks'}</p>
                </span>
            {name && <span>
                        <span role="button" className={`${styles.anim_icon} mx-2`} onClick={onEdit}
                              style={{'--hover-color': '#2471a3'}}>
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16"
                                         height="16" fill="currentColor"
                                         className="bi bi-pencil" viewBox="0 0 16 16">
                                    <path
                                        d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325"/>
                                    </svg>
                        </span>
                        <span role="button" className={`${styles.anim_icon} mx-2`} onClick={onDelete}
                              style={{'--hover-color': '#a93226'}}>
                                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor"
                                         className="bi bi-x" viewBox="0 0 16 16">
                                    <path
                                        d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708"/>
                                    </svg>
                        </span>
                    </span>
            }
        </div>
        {showDescription && task.description && (
            <div className="d-flex align-items-center mt-2 my-2 p-2 border rounded-3 bg-light">
                <p className="m-0">{task.description}</p>
            </div>)}
    </>);
};

