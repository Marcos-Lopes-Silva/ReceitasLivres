import axios from 'axios';
import { toast } from 'react-toastify';
import { Api } from 'services/api';
import { IUser } from 'types/types';

export async function Request(path: string, type: string, id?: string, params?: any,) {
    try {
        if (id !== null && id !== '' && id !== undefined) {
            if (type === 'get')
                return (await Api.get(`${path}/${id}`)).data;


            if (type === 'put')
                return (await Api.put(`${path}/${id}`, params)).data


            if (type === 'delete')
                return (await Api.delete(`${path}/${id}`)).data;
        } else {

            if (type === 'get')
                return (await Api.get(path)).data;

            if (type === 'post')
                return (await Api.post(path, params)).data;

        }
        // eslint-disable-next-line
    } catch (e: any) {
        if (e.response.status === 403) {
            setUserLocalStorage(null);
        }
        toast.error('Um erro inesperado aconteceu. Aguarde alguns instantes e tente novamente, se o erro persistir, entre em contato com o suporte.');
    }
}

export function setUserLocalStorage(user: IUser | null) {
    if (user)
        localStorage.setItem('u', JSON.stringify(user));
    else
        localStorage.removeItem('u');
}

export function getUserLocalStorage() {
    const user = localStorage.getItem('u');

    return user ? JSON.parse(user) : null;
}

export async function LoginRequest(login: string, senha: string) {
    try {
        const request = await Api.post('login', {
            login,
            senha
        });

        return request.data;

    } catch (error) {
        return null;
    }
}

export async function AWSBucket(path: string, params: any, type: string) {
    try {
        const request = await axios.put(path, params, { headers: { 'Content-Type': type } });

        return request.data;

    } catch (error: any) {
        console.log(error)
    }
}


