import Axios from 'axios';
import { useState } from 'react';
import { Categoria } from 'types/TCategoria';
import styles from './Filtros.module.scss';




interface Props {
  filtro: number | null
  setFiltro: React.Dispatch<React.SetStateAction<number | null>>
}

export default function Filtros({ filtro, setFiltro }: Props) {

  const [filtros, setFiltros] = useState([]);

  const fetchData = async () => {

    const { data } = await Axios.get('http://localhost:8080/categorias');
    console.log(data);
    const filtros = data;
    console.log(filtros);
    setFiltros(filtros);
    selecionarFiltro(filtros);
  };

  function selecionarFiltro(opcao: Categoria) {
    if (filtro === opcao.id) return setFiltro(null);
    return setFiltro(opcao.id);
  }

  return (
    <div className={styles.filtros}>
      {filtros.map((opcao: Categoria) => (
        <button className={`${styles.filtros__filtro} ${filtro === opcao.id ? styles['filtros__filtro--ativo'] : ''}`} key={opcao.id} onClick={() => selecionarFiltro(opcao)}>
          {opcao.nome}
        </button>
      ))}
    </div>
  );
}