import { useEffect, useState } from 'react';
import Item from './Item';
import { IReceita } from 'types/types';
import styles from './Itens.module.scss';
import { getReceitas } from 'context/AuthProvider/utils';

interface IProps {
  busca: string;
  filtro: number | null;
  ordenador: string;

}


export default function Itens(props: IProps) {
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

  function ordenar(novaLista: never[] = []) {
    switch (ordenador) {
      case 'porcao':
        return novaLista.sort((a: IReceita, b: IReceita) => a.size > b.size ? 1 : -1);
      case 'qtd_pessoas':
        return novaLista.sort((a: IReceita, b: IReceita) => a.serve > b.serve ? 1 : -1);
      case 'nome':
        return novaLista.sort((a: IReceita, b: IReceita) => a.titulo > b.titulo ? 1 : -1);
      default:
        return novaLista;
    }
  }

  const fetchData = async () => {
    const data = await getReceitas();
    const receitas = data.content;
    const novaLista = receitas.filter((item: IReceita) => testaBusca(item.titulo) && testaFiltro(item.categoria.id));
    setLista(ordenar(novaLista));
  };

  useEffect(() => {
    fetchData();
  }, [busca, filtro, ordenador]);

  return (
    <div className={styles.itens}>
      {lista.map((item: IReceita) => (
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