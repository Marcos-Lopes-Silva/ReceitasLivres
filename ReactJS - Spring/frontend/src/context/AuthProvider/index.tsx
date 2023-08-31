import { createContext, useEffect, useState } from 'react';
import { IContext, IAuthProvider, IUser } from 'types/types';
import { LoginRequest, setUserLocalStorage, getUserLocalStorage } from './utils';


export const AuthContext = createContext<IContext>({} as IContext);


export const AuthProvider = ({ children }: IAuthProvider) => {
    const [user, setUser] = useState<IUser | null>();

    useEffect(() => {
        const user = getUserLocalStorage();

        if (user) {
            setUser(user);
        }
    }, []);

    async function authenticate(login: string, senha: string) {
        const response = await LoginRequest(login, senha);
        const payload = { token: response.token, login };

        setUser(payload);
        setUserLocalStorage(payload);
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