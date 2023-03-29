import Axios from 'axios';
import { useEffect, useState } from 'react';
import Item from './Item';
import { Receita } from 'types/TReceita';
import styles from './Itens.module.scss';

interface IProps {
  busca: string;
  filtro: number | null;
  ordenador: string;

}


export default function Itens(props: IProps, receitas: Receita) {
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
      case 'nome':
        return novaLista.sort ? ((a: any, b: any) => a.titulo > b.titulo ? 1 : -1) : Function;
      default:
        return novaLista;
    }
  }

  const fetchData = async () => {
    const { data } = await Axios.get('http://localhost:8080/receitas');
    const receitas = data.content;
    receitas.filter((item: Receita) => testaBusca(item.titulo) && testaFiltro(item.categoria.id));
    setLista(receitas);

  };

  useEffect(() => {
    fetchData();
  }, [busca, filtro, ordenador]);

  return (
    <div className={styles.itens}>
      {lista.map((item: Receita) => (
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
          categoria={item.categoria}

        />
      ))}
    </div>
  );
}