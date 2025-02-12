import {useEffect, useState} from "react";
import {useNavigate} from "react-router-dom";

export const useAuth = () => {
    const [user, setUser] = useState(null);
    const [isLoading, setIsLoading] = useState(true);
    const navigate = useNavigate();

    const getUser = async () => {
        const token = localStorage.getItem('accessToken');
        if (!token) {
            navigate('/login');
            return null;
        }

        try {
            const tokenData = JSON.parse(atob(token.split('.')[1]));
            return {
                email: tokenData.email,
            };
        } catch (error) {
            console.error('Error parsing user token:', error);
            return null;
        }
    };

    const logout = () => {
        localStorage.removeItem('accessToken');
        setUser(null);
        navigate('/login');
    };

    useEffect(() => {
        const initializeAuth = async () => {
            try {
                const userData = await getUser();
                setUser(userData);
            } catch (error) {
                console.error('Error initializing auth:', error);
                navigate('/login');
            } finally {
                setIsLoading(false);
            }
        };

        initializeAuth();
    }, [navigate]);

    return { user, isLoading, logout };
};