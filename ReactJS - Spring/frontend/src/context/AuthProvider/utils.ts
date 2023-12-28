import { Api } from 'services/api';
import { IReceita, IUser } from 'types/types';

export async function getReceitas(id?: string) {
    try {
        if (id != null) {
            const request = await Api.get(`receitas/${id}`);

            return request.data;
        } else {
            const request = await Api.get('receitas');

            return request.data;
        }
        // eslint-disable-next-line
    } catch (e: any) {
        if (e.response.status === 403) {
            setUserLocalStorage(null);
            if (id != null) {
                const request = await Api.get(`receitas/${id}`);

                return request.data;
            } else {
                const request = await Api.get('receitas');

                return request.data.content;
            }
        }
    }
}

export async function getCategorias() {
    try {
        const request = await Api.get('categorias');

        return request.data;
        // eslint-disable-next-line
    } catch (e: any) {
        if (e.response.status === 403) {
            setUserLocalStorage(null);

            const request = await Api.get('categorias');

            return request.data;
        }
    }
}

export function setUserLocalStorage(user: IUser | null) {
    if(user)
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

export async function setReceita(params: IReceita) {
    try {
        const request = await Api.post('receitas/criar', params);

        return request.data;
    } catch (error) {
        return null;
    }
}
