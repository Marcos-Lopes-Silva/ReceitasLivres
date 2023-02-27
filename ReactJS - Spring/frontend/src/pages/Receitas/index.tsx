import Buscador from 'components/Buscador';
import Filtros from 'components/Filtros';
import { useState } from 'react';
import stylesTema from 'styles/Tema.module.scss';
import Itens from './Itens';
import Ordenador from './Ordenador';
import styles from './Receita.module.scss';

export default function Receitas() {
  const [busca, setBusca] = useState('');
  const [filtro, setFiltro] = useState<number | null>(null);
  const [ordenador, setOrdenador] = useState('');
  return (
    <section className={styles.receita}>
      <h3 className={stylesTema.titulo}>Receitas</h3>
      <Buscador busca={busca} setBusca={setBusca} />
      <div className={styles.receita__filtros}>
        <Filtros filtro={filtro} setFiltro={setFiltro} />
        <Ordenador ordenador={ordenador} setOrdenador={setOrdenador} />
      </div>
      <Itens busca={busca} filtro={filtro} ordenador={ordenador} />
    </section>
  );
}