import { useEffect, useState } from 'react';
import { Categoria } from '@/types/types';
import styles from './Filtros.module.scss';
import { Request } from '@/context/AuthProvider/utils';




interface Props {
  filtro: number | null
  setFiltro: React.Dispatch<React.SetStateAction<number | null>>
}

export default function Filtros({ filtro, setFiltro }: Props) {

  const [filtros, setFiltros] = useState([]);

  const fetchData = async () => {
    const data = Request('categorias', 'get');
    const filtros = data;
    setFiltros(await filtros);
  };

  function selecionarFiltro(opcao: Categoria) {
    if (filtro === opcao.id) return setFiltro(null);
    return setFiltro(opcao.id);
  }

  useEffect(() => {
    fetchData();
  }, []);

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