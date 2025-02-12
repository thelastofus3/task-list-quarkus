import { httpClient } from '../api/httpClient.jsx';

export const userService = {
    getUser: () => httpClient.get('user'),
    logout: () => {
        localStorage.removeItem('accessToken');
        //TODO: Additional logout logic if needed
    }
};