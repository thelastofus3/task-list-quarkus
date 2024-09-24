import React, {useEffect, useState} from 'react';
import styles from './styles/Main.module.scss'
import {Card} from "./ui/Card.jsx";
import {userService} from "./service/MainService.jsx";
import {useNavigate} from "react-router-dom";
import {TaskList} from "./ui/TaskList.jsx";

export const Main = () => {
    const [userEmail, setUserEmail] = useState('');
    const navigate = useNavigate();

    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await userService.getUser();
                setUserEmail(response.data.email);
            } catch (error) {
                console.log("Error while getting user email" ,error);
                navigate('/login')
            }
        };

        fetchData();
    }, []);

    return (
        <div className={styles.main}>
            <header
                className={` ${styles.main__header} d-flex justify-content-between align-items-center p-3 shadow-sm`}>
                <div className="d-flex align-items-center">
                    <h1 className="mb-0 me-2">Task</h1>
                    <span className="mx-3">
                        <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor"
                             className="bi bi-collection" viewBox="0 0 16 16">
                            <path
                                d="M2.5 3.5a.5.5 0 0 1 0-1h11a.5.5 0 0 1 0 1zm2-2a.5.5 0 0 1 0-1h7a.5.5 0 0 1 0 1zM0 13a1.5 1.5 0 0 0 1.5 1.5h13A1.5 1.5 0 0 0 16 13V6a1.5 1.5 0 0 0-1.5-1.5h-13A1.5 1.5 0 0 0 0 6zm1.5.5A.5.5 0 0 1 1 13V6a.5.5 0 0 1 .5-.5h13a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-.5.5z"/>
                        </svg>
                    </span>
                </div>
                <div className="d-flex align-items-center">
                    <span className="fw-bold mx-4">{userEmail}</span>
                    <button className="btn btn-primary me-2">Log out</button>
                </div>
            </header>
            <TaskList/>
        </div>
    );
};

