export type IReceita = {
    sort?(arg0: (a: any, b: any) => 1 | -1): Function;

    id?: number;
    titulo: string;
    modoPreparo: string;
    ingredientes: string;
    size: number;
    usuario: {
        id: number;
        nome: string;
    }
    urlImage: string;
    descricao: string;
    categoria: {
        id: number;
        nome: string;
    }
    serve: number;
}

export type Categoria = {
    id: number;
    nome: string;
}

export interface IUser {
    login?: string;
    token?: string;
}

export interface IContext extends IUser {
    authenticate: (email: string, password: string) => Promise<void>;
    logout: () => void;
}

export interface IAuthProvider {
    children: JSX.Element;
}

export interface ISetReceita {
    titulo: string;
    modoPreparo: string;
    ingredientes: string;
    size: number;   
    urlImage: string;
    descricao: string;
    categoria: {
        id: number;
        nome: string;
    }
    serve: number;
}