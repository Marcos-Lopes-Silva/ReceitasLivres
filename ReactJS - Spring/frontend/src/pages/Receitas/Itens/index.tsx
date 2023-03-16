import Axios from 'axios';
import { useEffect, useState } from 'react';
import Item, { Receitas } from './Item';
import styles from './Itens.module.scss';

interface IProps {
  busca: string;
  filtro: number | null;
  ordenador: string;

}


export default function Itens(props: IProps, receitas: Receitas) {
  const [lista, setLista] = useState([]);
  const { busca, filtro, ordenador } = props;

  function testaBusca(title: string) {
    const regex = new RegExp(busca, 'i');
    return regex.test(title);
  }

  function testaFiltro(id: number) {
    if (filtro !== null) return filtro === id;
    return true;
  }

  function ordenar(novaLista: typeof receitas) {
    switch (ordenador) {
      case 'porcao':
        return novaLista.sort ? ((a: any, b: any) => a.size > b.size ? 1 : -1) : Function;
      case 'qtd_pessoas':
        return novaLista.sort ? ((a: any, b: any) => a.serving > b.serving ? 1 : -1) : Function;
      case 'preco':
        return novaLista.sort ? ((a: any, b: any) => a.price > b.price ? 1 : -1) : Function;
      default:
        return novaLista;
    }
  }

  const fetchData = async () => {
    const { data } = await Axios.get('http://localhost:8080/receitas');

    const receitas = data;
    receitas.filter((item: Receitas) => testaBusca(item.titulo) && testaFiltro(item.category.id))
    setLista(receitas);
    console.log(receitas);
  };

  useEffect(() => {
    fetchData();
  }, [busca, filtro, ordenador]);

  return (
    <div className={styles.itens}>
      {lista.map((item: Receitas) => (
        <Item
          key={item.id}
          id={item.id}
          titulo={item.titulo}
          ingredientes={item.ingredientes}
          modoPreparo={item.modoPreparo}
          descricao={item.descricao}
          urlImage={item.urlImage}
          size={item.size}
          usuario={item.usuario}
          serve={item.serve}
          category={
            item.category
          }

        />
      ))}
    </div>
  );
}