import { Api } from 'services/api';
import { IUser } from 'types/types';

export async function Request(path: string, type: string, id?: string, params?: any,) {
    try {
        if (id != null) {
            if (type === 'get')
                return await (await Api.get(`${path}/${id}`)).data;


            if (type === 'put')
                return await (await Api.put(`${path}/${id}`, params)).data


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
        alert(e.response.data.message);
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


