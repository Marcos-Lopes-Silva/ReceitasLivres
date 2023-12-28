import axios from 'axios';
import { getUserLocalStorage } from 'context/AuthProvider/utils';


export const Api = axios.create({
    baseURL: 'http://localhost:8080/',
    // headers: {
    //     'Access-Control-Allow-Origin': 'http://localhost:8080'
    // }
});


Api.interceptors.request.use(
    (config) => {
        const user = getUserLocalStorage();
        if (user?.token !== undefined && !user?.token)
            config.headers.Authorization = `Bearer ${user?.token}`;

        return config;
    },
    (error) => {
        return Promise.reject(error);
    }
);