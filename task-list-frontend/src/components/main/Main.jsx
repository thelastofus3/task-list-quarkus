import React from 'react';
import styles from './styles/Main.module.scss'
import {Navigate} from "react-router-dom";
import {TaskList} from "./ui/TaskList.jsx";
import {useAuth} from "./hooks/useAuth.jsx";
import {Header} from "./ui/Header.jsx";

export const Main = () => {
    const { user, isLoading, logout } = useAuth();

    if (isLoading) {
        return (
            <div className="d-flex justify-content-center align-items-center vh-100">
                <div className="spinner-border text-primary" role="status">
                    <span className="visually-hidden">Loading...</span>
                </div>
            </div>
        );
    }
    if (!user) {
        return <Navigate to="/login" replace />;
    }

    return (
        <div className={styles.main}>
            <Header
                userEmail={user.email}
                onLogout={logout}
            />
            <TaskList />
        </div>
    );
};