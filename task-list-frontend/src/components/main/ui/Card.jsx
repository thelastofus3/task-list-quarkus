import React from 'react';
import styles from "../styles/Main.module.scss";

export const Card = ({ title, type }) => {
    return (
        <div className="col-lg-3 col-md-4 col-sm-12 mb-4">
            <div className="card h-100">
                <div className="card-body">
                    <h4>{type}</h4>
                    <div
                        className={`${styles.main__task_name} p-2 my-3 rounded-4 d-flex jus align-items-center justify-content-between`}>
                        <p className="m-0">{title}</p>
                        <span role="button" className="mx-2">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16"
                                         height="16" fill="currentColor"
                                         className="bi bi-pencil" viewBox="0 0 16 16">
                                    <path
                                        d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325"/>
                                    </svg>
                                </span>
                    </div>
                    <div className="d-grid gap-2">
                        <button className="btn d-flex align-items-center">
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
        </div>
    );
};

