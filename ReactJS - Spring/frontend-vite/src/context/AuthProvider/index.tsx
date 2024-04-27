import { createContext, useEffect, useState } from 'react';
import { IContext, IAuthProvider, IUser } from '@/types/types';
import { LoginRequest, setUserLocalStorage, getUserLocalStorage } from './utils';


export const AuthContext = createContext<IContext>({} as IContext);


export const AuthProvider = ({ children }: IAuthProvider) => {
    const [user, setUser] = useState<IUser | null>();

    useEffect(() => {
        if (user) {
            setUser(user);
        } else {
            const user = getUserLocalStorage();
            if(user) setUser(user);
        }
    }, []);

    async function authenticate(login: string, senha: string) {
        const response = await LoginRequest(login, senha);
        console.log(response);
        const payload = { token: response.token, login, nome: response.nome, photo: response.photo, id: response.id};

        setUser(payload);
        setUserLocalStorage({token: payload.token, login: payload.login});
    }

    function logout() {
        setUser(null);
        setUserLocalStorage(null);
    }



    return (
        <AuthContext.Provider value={{ ...user, authenticate, logout }}>
            {children};
        </AuthContext.Provider>
    )
}