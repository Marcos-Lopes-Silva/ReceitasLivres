import { useEffect, useState } from 'react';
import Item from './Item';
import itens from './itens.json';
import styles from './Itens.module.scss';

interface IProps {
    busca: string;
    filtro: number | null;
    ordenador: string;

}
export default function Itens(props: IProps) {
    const [lista, setLista] = useState(itens);
    const { busca, filtro, ordenador } = props;

    function testaBusca(title: string) {
        const regex = new RegExp(busca, 'i');
        return regex.test(title);
    }

    function testaFiltro(id: number) {
        if (filtro !== null) return filtro === id;
        return true;
    }

    function ordenar(novaLista: typeof itens) {
        switch (ordenador) {
            case 'porcao':
                return novaLista.sort((a, b) => a.size > b.size ? 1 : -1);
            case 'qtd_pessoas':
                return novaLista.sort((a, b) => a.serving > b.serving ? 1 : -1);
            case 'preco':
                return novaLista.sort((a, b) => a.price > b.price ? 1 : -1);
            default:
                return novaLista;
        }
    }

    useEffect(() => {
        const novaLista = itens.filter(item => testaBusca(item.title) && testaFiltro(item.category.id));
        setLista(ordenar(novaLista));
    }, [busca, filtro, ordenador])

    return (
        <div className={styles.itens}>
            {lista.map((item) => (
                <Item
                    id={item.id}
                    title={item.title}
                    description={item.description}
                    photo={item.photo}
                    size={item.size}
                    serving={item.serving}
                    category={
                        item.category
                    }
                    price={item.price}
                />
            ))}
        </div>
    )
}