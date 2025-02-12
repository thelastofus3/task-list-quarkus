import axios from 'axios';

const createHttpClient = (baseURL) => {
    const client = axios.create({ baseURL });

    client.interceptors.request.use(
        (config) => {
            const token = localStorage.getItem('accessToken');
            if (token) {
                config.headers.Authorization = `Bearer ${token}`;
            }
            return config;
        },
        (error) => Promise.reject(error)
    );

    return client;
};

export const httpClient = createHttpClient('http://localhost:8080/api/v1/');