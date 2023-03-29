export type Receita = {
    sort?(arg0: (a: any, b: any) => 1 | -1): Function;

    id: number;
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